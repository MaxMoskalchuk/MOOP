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
<div class="forms">
    <form action="/tourController" id="add" method="POST">
        <h1>Add Tour</h1>
        <div class="input-field">
            <input type="hidden" value="ADD_TOUR_COMMAND" name="command">
            <label for="Country">Country</label>
            <input type="text" name="country" value="">
            <label for="Cost">Cost</label>

            <input type="text" name="cost" required/>
            <input type="submit" value="Add" class="button"/>

            <label for="startTour">Start Tour</label>
            <input type="text" name="startTour" required/>
            <label for="endTour">End Tour</label>
            <input type="text" name="endTour" required/>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('.tab a').on('click', function (e) {
            e.preventDefault();
            $(this).parent().addClass('active');
            $(this).parent().siblings().removeClass('active');
            var href = $(this).attr('href');
            $('.forms > form').hide();
            $(href).fadeIn(500);
        });
    });
</script>
<%
    ua.univ.lab8.Commands.AgencyCommand cmd = new ua.univ.lab8.Commands.AddTourCommand();
    request.getSession().setAttribute("command", cmd);
%>
</body>
</html>
