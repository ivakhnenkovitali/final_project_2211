<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<jsp:include page="<%=JspConstants.MENU_JSP%>"/>
<h2>Hello ${user.name}</h2>
<c:choose>

    <c:when test="${not empty orders}">
        <h2>Your order:</h2>
        <c:forEach var="order" items="${orders}">
            <div class="order-list-container">
                <h3>${order.date} you ordered delivery to address ${order.address}, id of order  ${order.id}</h3>
                <form method="post" action="<%=ApplicationConstants.PRINT_ORDER_CONTROLLER%>">
                    <input type="hidden" name="<%=JspConstants.ORDER_ID_PARAM%>" value="${order.id}">
                    <input type="submit" value="Print receipt">
                </form>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h3>You have no orders for now...</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
