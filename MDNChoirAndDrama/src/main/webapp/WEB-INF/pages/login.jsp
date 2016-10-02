<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add event</title>
    <!-- include css -->
    <jsp:include page="css.jsp"></jsp:include>
    <!-- include js -->
    <jsp:include page="js.jsp"></jsp:include>
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container col-sm-6 col-sm-offset-3 ">
    <div class="well">
        <c:url var="formAction" value="/login"/>
        <form method="post"
                   class="form-horizontal" commandName="user" action="${formAction}">
            <legend>Login</legend>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="username">Username</label>
                <div class="col-sm-7">
                    <input id="username" class="form-control" name="username" type="text"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="password">Password</label>
                <div class="col-sm-7">
                    <input id="password" class="form-control" type="password" name="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-7">
                    <input type="submit" id="submit" class="btn btn-primary btn-lg btn-sm-10"
                           value="Login">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
