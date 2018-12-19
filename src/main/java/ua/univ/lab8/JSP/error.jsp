<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Oops!</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<center>
    <h1 style="color:red;">
        An error occurred!<br>
        ${requestScope.errorStr}<br>
    </h1>
    <h3>
        <a href="http://localhost:8080/userController">Return to main page</a>
    </h3>
</center>
</body>
</html>