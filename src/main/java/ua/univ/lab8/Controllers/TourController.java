package ua.univ.lab8.Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univ.lab8.Commands.CommandFactory;
import ua.univ.lab8.Commands.SignUpLoginCommand;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;


import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/tourController")
public class TourController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Settings.infoLogger.info("sid: " + req.getSession().getId() + " tries to access " +
                "login controller through GET request");

        req.setAttribute("errorStr", "loginController couldn't be accessed through HttpGet request.");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Map<String, Object> params = new TreeMap<>();
            Enumeration<String> paramNames = req.getParameterNames();

            String commandName = null;
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                if (name.equals("command"))
                    commandName = req.getParameter(name);
                else
                    params.put(name, req.getParameter(name));
            }

            User who = (User) req.getSession().getAttribute("user");

            SignUpLoginCommand command = (SignUpLoginCommand) CommandFactory.createAndInitCommand(commandName , params);
            req.getSession().removeAttribute("command");

            boolean result = command.doAction(who);

            Settings.infoLogger.info("sid: " + req.getSession().getId() + " an attempt of login or sign up procedure, result: " + result);

            RequestDispatcher rd = null;

            who = command.getUser();

            if (result) {

            } else {
                req.setAttribute("errorStr", "Authentication failed.");
                rd = getServletContext().getRequestDispatcher("/error.jsp");
            }

            rd.forward(req, resp);
        }catch (Exception e) {
            Settings.errorLogger.error("Error", e);

            //e.printStackTrace();
            req.setAttribute("errorStr", e.getMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }
}
