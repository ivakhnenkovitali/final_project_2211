<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>Pizza_2211</title>
      <link rel="stylesheet" href="/css/styles.css">
   </head>
   <body>
      <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
      <img class="default-image" src="<%=JspConstants.BACKGROUND_IMAGE%>" alt="pizza">
      <h1 style="position: absolute; top: 50%; width: 100%; text-align: center;">
               The most tasty pizza!!!
      </h1>
   </body>
</html>

