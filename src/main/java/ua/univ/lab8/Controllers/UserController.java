package ua.univ.lab8.Controllers;

import ua.univ.lab8.Commands.CommandFactory;
import ua.univ.lab8.Commands.AgencyCommand;
import ua.univ.lab8.Data.DAO.ServiceDAO;
import ua.univ.lab8.Data.DAO.TourDAO;
import ua.univ.lab8.Data.DAO.UserDAO;
import ua.univ.lab8.Data.DAO.InvoiceDAO;
import ua.univ.lab8.Data.Enums.UserRoles;
import ua.univ.lab8.Data.Models.Service;
import ua.univ.lab8.Data.Models.Tour;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Data.Models.Invoice;
import ua.univ.lab8.Settings.Settings;
import ua.univ.lab8.Utils.ConnectionPool;
import ua.univ.lab8.Utils.OptionPair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/userController")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().removeAttribute("command");

        User who = (User)req.getSession().getAttribute("user");

        if(who == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
            rd.forward(req, resp);
            return;
        }

        //Navigation menu
        ArrayList<OptionPair> options = new ArrayList<>();
        options.add(new OptionPair().setOptionText("My invoices").setOptionValue("?act=inv"));
        options.add(new OptionPair().setOptionText("Tours").setOptionValue("?act=trs"));

        if(who.getRole() == UserRoles.ADMINISTRATOR) {
            options.add(new OptionPair().setOptionText("Manage users").setOptionValue("?act=mng"));
            options.add(new OptionPair().setOptionText("Add Tour").setOptionValue("?act=adt"));
        }
        options.add(new OptionPair().setOptionText("Logout").setOptionValue("?act=end"));
        req.setAttribute("menuOptions", options);



        String act = req.getParameter("act");
        act = (act == null) ? "inv" : act;
        req.setAttribute("requestType", null);

        Settings.infoLogger.info("sid: " + req.getSession().getId() + " HttpGet request, act = " + act + ", user = " + who);

        try {
            switch (act) {
                case "": throw new RuntimeException("Are you serious?"); // should not be reachable
                case "inv": // invoices
                {
                    Connection conn = ConnectionPool.getConnection();
                    InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

                    List<Invoice> invoices = invoiceDAO.getInvoicesForUser(who.getId());

                    req.setAttribute("invoices", invoices);

                    invoiceDAO.closeConnection();
                }
                req.setAttribute("requestType", act);
                break;
                case "trs" : //tours
                {
                    Connection conn = ConnectionPool.getConnection();
                    TourDAO tourDAO = new TourDAO(conn);

                    List<Tour> tours = tourDAO.getTourByCountry(who.getGlobalCountry());
                    who.setGlobalCountry("");
                    req.setAttribute("tours",tours);

                }
                req.setAttribute("requestType",act);
                break;
                case "adt" : //tours
                {
                    Connection conn = ConnectionPool.getConnection();
                    TourDAO tourDAO = new TourDAO(conn);
                    tourDAO.closeConnection();
                }

                case "mng": // manage banned users, for admin only
                {
                    if(who.getRole() != UserRoles.ADMINISTRATOR) {
                        req.setAttribute("errorStr", "You don't have permission to do that.");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                        rd.forward(req, resp);
                        return;
                    }

                    Connection conn = ConnectionPool.getConnection();
                    UserDAO userDAO = new UserDAO(conn);

                    List<User> users = userDAO.getAllUsers();

                    //users.removeIf(e -> (e.getRole() == UserRoles.ADMINISTRATOR));

                    Map<Integer , Double> usersDebts = new TreeMap<>();
                    req.setAttribute("activeUsers", users);

                    userDAO.closeConnection();
                }
                req.setAttribute("requestType", act);
                break;
                case "end": // logout
                {
                    req.getSession().removeAttribute("user");
                    resp.sendRedirect("/");
                }
                break;
                default:
                    req.setAttribute("errorStr", String.format("Unknown request: %s.", act));
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                    return;

            }
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);

            req.setAttribute("errorStr", e.getMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
            return;
        }

        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/user.jsp");
            rd.forward(req, resp);
        }catch (IllegalStateException e) { Settings.errorLogger.error("Error", e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, Object> params = new TreeMap<>();
            Enumeration<String> paramNames = req.getParameterNames();

            String commandName = null;

            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                if (name.equals("command"))
                    commandName = req.getParameter(name);
                else if(name.endsWith("[]"))
                    params.put(name, req.getParameterValues(name));
                else
                    params.put(name, req.getParameter(name));
            }

            User who = (User) req.getSession().getAttribute("user");

            Settings.infoLogger.info("sid: " + req.getSession().getId() + " HttpPost, user = " + who + ", params = " + params );

            AgencyCommand command = CommandFactory.createAndInitCommand(commandName , params);
            req.getSession().removeAttribute("command");

            boolean result = command.doAction(who);

            Settings.infoLogger.info("sid: " + req.getSession().getId() + " result of command execution is " + result);

            RequestDispatcher rd = null;

            if (result) {
                String requestType = (String) req.getSession().getAttribute("requestType");
                req.getSession().removeAttribute("requestType");
                resp.sendRedirect("/userController?act=" + requestType);
                return;
            } else {
                req.setAttribute("errorStr", "An error occurred during command execution.");
                rd = getServletContext().getRequestDispatcher("/error.jsp");
            }

            rd.forward(req, resp);
        } catch (Exception e) {
            Settings.errorLogger.error("Error", e);

            req.setAttribute("errorStr", e.getMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }
}