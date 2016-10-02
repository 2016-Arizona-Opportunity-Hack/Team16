<%--
  Created by IntelliJ IDEA.
  User: Vikranth
  Date: 10/2/2016
  Time: 12:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add event</title>
    <!-- include css -->
    <jsp:include page="../css.jsp"></jsp:include>
    <!-- include js -->
    <jsp:include page="../js.jsp"></jsp:include>
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container col-sm-6 col-sm-offset-3 ">
    <div class="well">
        <c:url var="formAction" value="/event"/>
        <form:form method="post"
                   class="form-horizontal" commandName="event" action="${formAction}">
            <legend>Add Event</legend>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="title">Title</label>
                <div class="col-sm-7">
                    <form:input path="title" class="form-control" type="text"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="description">Description</label>
                <div class="col-sm-7">
                    <form:input path="description" class="form-control" type="text"/>
                </div>
            </div>
            <div class="form-group" id="startTime">
                <label class="col-sm-4 control-label" for="startTimeString">Start time</label>
                <div class="col-sm-7">
                    <form:input path="startTimeString" class="form-control" type="text"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="endTimeString">End time</label>
                <div class="col-sm-7">
                    <form:input path="endTimeString" class="form-control" type="text"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-7">
                    <input type="submit" id="submit" class="btn btn-primary btn-lg btn-sm-10"
                           value="Add Event">
                </div>
            </div>
        </form:form>
    </div>

</body>
</html>
