<%@ page import="by.itclass.constants.ApplicationConstants"%>
<%@ page import="by.itclass.constants.JspConstants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Home Page</title>
        <link rel="stylesheet" href="/css/styles.css">
    </head>
    <body>
        <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
        <img class="default-image" src="<%=JspConstants.BACKGROUND_IMAGE%>" alt="pizza">
        <h2>User Info</h2>
        <p>login: ${user.login}</p>
        <p>name : ${user.name}</p>
        <p>email: ${user.email}</p>
    </body>
</html>
