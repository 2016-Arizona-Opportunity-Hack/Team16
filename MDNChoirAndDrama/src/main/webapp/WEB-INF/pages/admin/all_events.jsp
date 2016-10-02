<%--
  Created by IntelliJ IDEA.
  User: Vikranth
  Date: 10/2/2016
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>All events</title>
    <!-- include css -->
    <jsp:include page="../css.jsp"></jsp:include>
    <!-- include js -->
    <jsp:include page="../js.jsp"></jsp:include>
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container col-sm-8 col-sm-offset-2 ">
    <h3>All upcoming events</h3>
    <c:if test="${fn:length(events) gt 0}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Start time</th>
                <th>End time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event" varStatus="loop">
                <tr>
                    <td><c:out value="${event.title}"/></td>
                    <td><c:out value="${event.description}"/></td>
                    <td><fmt:formatDate value="${event.startTime}" /></td>
                    <td><fmt:formatDate value="${event.endTime}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${fn:length(events) eq 0}">
        <div class="well">No pending events</div>
    </c:if>
</div>

</body>
</html>