<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Receipt page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
      <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
      <div class="receipt-container-container">
          ${orderReceipt}
      </div>

</body>
</html>
