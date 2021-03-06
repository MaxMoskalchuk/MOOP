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
    <ul class="tab-group">
        <li class="tab active"><a href="#login">Log In</a></li>
        <li class="tab"><a href="#signup">Sign Up</a></li>
    </ul>
    <form action="/loginController" id="login" method="POST">
        <h1>Login form</h1>
        <div class="input-field">
            <input type="hidden" value="LOGIN_COMMAND" name="command">
            <label for="userName">Username</label>
            <input type="text" name="userName" value="">
            <label for="password">Password</label>
            <input type="password" name="password" required/>
            <input type="submit" value="Login" class="button"/>
        </div>
    </form>
    <form action="/loginController" id="signup" method="POST">
        <h1>Sign Up form</h1>
        <div class="input-field">
            <input type="hidden" value="SIGN_UP_COMMAND" name="command">
            <label for="userName">Username</label>
            <input type="userName" name="userName" required/>
            <label for="password">Password</label>
            <input type="password" name="password" required/>
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" name="confirmPassword" required/>
            <input type="submit" value="Sign up" class="button" />
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
    ua.univ.lab8.Commands.AgencyCommand cmd = new ua.univ.lab8.Commands.LoginCommand();
    request.getSession().setAttribute("command", cmd);
%>
</body>
</html>
