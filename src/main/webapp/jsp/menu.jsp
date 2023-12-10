<%@ page import="by.itclass.constants.ApplicationConstants"%>
<%@ page import="by.itclass.constants.JspConstants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav-ul">
  <c:choose>
    <c:when test="${empty user}">
      <li class="nav-li float-left">
        <a class="active" href="<%=JspConstants.INDEX_JSP%>">Index</a></li>
      <li class="nav-li">
        <a href="<%=JspConstants.LOGIN_JSP%>">Login</a></li>
      <li class="nav-li">
        <a href="<%=JspConstants.REGISTRATION_JSP%>">Registration</a></li>
    </c:when>
    <c:otherwise>
      <li class="nav-li float-left">
        <a class="active" href="<%=JspConstants.HOME_JSP%>">Home</a></li>
      <li class="nav-li">
        <a href="<%=ApplicationConstants.LOGOUT_CONTROLLER%>">Logout</a></li>
    </c:otherwise>
  </c:choose>
</ul>
