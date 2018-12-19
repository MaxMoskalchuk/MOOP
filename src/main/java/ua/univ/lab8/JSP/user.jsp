<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
    <script type="text/javascript">
        <%@include file="static/js/jquery-3.3.1.min.js" %>
    </script>
    <style>
        <%@include file="static/css/style.css" %>
    </style>
</head>
<body>
<script type="text/javascript">
    function makeCall(btn) {
        var form = document.createElement('form');
        document.body.appendChild(form);
        form.method = 'post';
        form.action = "";
        {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.value = $(btn).closest('div').children("input[name='receiverId']").val();
            input.name = "receiverId";
            form.appendChild(input);
        }
        {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.value = $(btn).closest('div').children("input[name='command']").val();
            input.name = "command";
            form.appendChild(input);
        }
        $("input:checked").each(function(){
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = "services[]";
            input.value = $(this).attr("name");
            form.appendChild(input);
        });
        form.submit();
    }
</script>

<div class="nav">
    <c:forEach items="${requestScope.menuOptions}" var="opt">
        <c:if test="${opt.shortValue == requestScope.requestType}">
            <a href="${opt.optionValue}" class="btn active">${opt.optionText}</a>
        </c:if>
        <c:if test="${opt.shortValue != requestScope.requestType}">
            <a href="${opt.optionValue}" class="btn">${opt.optionText}</a>
        </c:if>
    </c:forEach>
</div>
<br>
<hr>
<%-- Content section --%>
<c:choose>

    <c:when test="${requestScope.requestType == 'inv'}">
        <table class="table-fill">
            <thead>
            <tr>
                <th class="text-center">Agency name</th><th class="text-center">Country</th><th class="text-center">Cost</th><th class="text-center">&nbsp;</th><th class="text-center">&nbsp;</th>
            </tr>
            </thead>
            <c:forEach items="${invoices}" var="inv">
                <tr>
                    <td class="text-center">${inv.user.userName}</td><td class="text-center">${inv.tour.country} </td><td class="text-center">${inv.amount}</td><td class="text-center"><form method="POST" action=""><input name="command" value="PAY_INVOICE_COMMAND" type="hidden"><input name="invoiceId" value="${inv.id}" type="hidden"><input type="submit" value="Pay"></form></td>
                    <td class="text-center"><form method="POST" action=""><input name="command" value="ABORT_INVOICE_COMMAND" type="hidden"><input name="invoiceId" value="${inv.id}" type="hidden"><input type="submit" value="Abort"></form></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>



    <c:when test="${requestScope.requestType == 'trs'}">
        <table class="table-fill">
            <td class="text-center"><form method="POST" action=""><input name="command" value="FIND_TOUR_COMMAND" type="hidden"><input name="country" value=""><input type="submit" value="Find Tours"></form></td>
        </table>
        <table class="table-fill">
            <thead>
            <tr>
                <th class="text-center">Agency name</th><th class="text-center">Country</th><th class="text-center">Cost</th><th class="text-center">Start</th><th class="text-center">End</th><th class="text-center">Tour Info</th><th class="text-center">&nbsp;</th>
            </tr>
            </thead>
            <c:forEach items="${tours}" var="trs">
                <tr>
                    <td class="text-center">${trs.agency.userName}</td><td class="text-center">${trs.country} </td><td class="text-center">${trs.cost}</td><td class="text-center"><fmt:formatDate value="${trs.startTour}" pattern="dd-MM-yyyy"/></td><td class="text-center"><fmt:formatDate value="${trs.endTour}" pattern="dd-MM-yyyy"/><td class="text-center">${trs.services}</td></td><td class="text-center"><form method="POST" action=""><input name="command" value="BUY_TOUR_COMMAND" type="hidden"><input name="tourId" value="${trs.id}" type="hidden"><input type="submit" value="Buy"></form></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:when test="${requestScope.requestType == 'adt'}">
        <table class="table-fill">
                <tr>
                    <td class="text-center"><form method="POST" action=""><input name="command" value="ADD_TOUR_COMMAND" type="hidden">Country: <input name="country" value=""><br>Cost: <input name="cost" value=""><br>
                        Start Tour: <input type="date" name="startTour" value=""><br>End Tour: <input type="date" name="endTour"  value="">
                        <br>Relaxation: <input type="checkbox" name="relx" value=true>
                        <br>Excursion: <input type="checkbox" name="excur" value=true>
                        <br>Shopping: <input type="checkbox" name="shop" value=true>
                        <br>Hot: <input type="checkbox" name="hot" value=true><br><input type="submit" value="Add"></form></td>
                </tr>
        </table>
    </c:when>
    <c:when test="${requestScope.requestType == 'mng'}">
        <table class="table-fill">
            <thead>
            <tr>
                <th colspan=4 class="text-center">Active users</th>
            </tr>
            <tr>
                <th class="text-center">Username</th><th class="text-center">Bought tours</th><th class="text-center">Discount</th><th class="text-center">&nbsp;</th>
            </tr>
            </thead>
            <c:forEach items="${activeUsers}" var="us">
                <tr>
                    <td class="text-center">${us.userName}</td><td class="text-center">${us.tours}</td><td class="text-center">${us.discount}</td><td class="text-center"><form method="POST" action=""><input name="command" value="CHANGE_DISCOUNT_COMMAND" type="hidden"><input name="new_dis" value=""><input type="hidden" value="${us.id}" name="userId"><input type="submit" value="Change Discount"></form></td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </c:when>
</c:choose>
<%-- Commands section --%>
<%--<% ua.univ.lab8.Commands.TelephoneStationCommand cmd = null; %>
<c:choose>
    <c:when test="${requestScope.requestType == 'call'}">
        <% cmd = new ua.univ.lab8.Commands.MakeCallCommand(); %>
    </c:when>
    <c:when test="${requestScope.requestType == 'ans'}">
        <% cmd = new ua.univ.lab8.Commands.AcceptCallCommand(); %>
    </c:when>
    <c:when test="${requestScope.requestType == 'my'}">
        <% cmd = new ua.univ.lab8.Commands.CancelCallCommand(); %>
    </c:when>
    <c:when test="${requestScope.requestType == 'srv'}">
        <% cmd = new ua.univ.lab8.Commands.ChangeServiceStatusCommand(); %>
    </c:when>
    <c:when test="${requestScope.requestType == 'con'}">
        <% cmd = new ua.univ.lab8.Commands.ConnectCallCommand(); %>
    </c:when>
    <c:when test="${requestScope.requestType == 'mng'}">
        <% cmd = new ua.univ.lab8.Commands.ChangeBanStatusCommand(); %>
    </c:when>
</c:choose>
<% request.getSession().setAttribute("command", cmd); %>--%>
<% request.getSession().setAttribute("requestType", request.getAttribute("requestType")); %>
</center>
</body>
</html>
