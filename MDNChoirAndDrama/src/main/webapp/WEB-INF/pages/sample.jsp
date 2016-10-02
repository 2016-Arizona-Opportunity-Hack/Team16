<%--
  Created by IntelliJ IDEA.
  User: jaydatta
  Date: 10/2/16
  Time: 1:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@page session="true"%>
<html>
<head>
    <title>Title</title>

    <c:if test="${not empty name}">
        <div class="error">${name}</div>
    </c:if>

</head>
<body>

</body>
</html>
