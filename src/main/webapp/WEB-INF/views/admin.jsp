<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a href="${contextPath}/logoutform">Logout</a>
        </h2>



        <form:form method="POST" modelAttribute="adminForm" class="form-signin">

            <h3>Change role for user</h3>
            <table cellpadding="10" border="1" width="80%">
                <tr>
                    <th>Username</th>
                    <th>Current role</th>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.username}</td>
                        <td>${item.role}</td>

                    </tr>
                </c:forEach>
            </table>



            <spring:bind path="username">
                <h3>Type username</h3>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="role">
                <table>
                    <tr>
                        <td>New role </td>
                        <td><form:radiobutton path="role" value="USER"/>USER
                            <form:radiobutton path="role" value="UNCHECKED"/>UNCHECKED</td>
                            <form:radiobutton path="role" value="ADMIN"/>ADMIN</td>
                        <td><form:errors path="role" /></td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></td>
                    </tr>
                </table>
            </spring:bind>

        </form:form>

    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>