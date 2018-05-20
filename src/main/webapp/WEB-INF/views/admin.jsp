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



        <form:form method="POST" class="form-signin">

            <h3>Change role for user</h3>

            <table cellpadding="10" border="1" width="100%" class="form-table">
                <tr>
                    <th>Username</th>
                    <th>Current role</th>
                    <th>Edit</th>
                </tr>
                <c:forEach items="${list}" var="item" varStatus="loop">
                    <tr>
                        <td>${item.username}</td>
                        <td>${item.role}</td>
                        <c:if test="${edit == null}">
                            <td>
                                <button class="btn btn-lg btn-primary btn-block" type="submit"
                                        name="editId" value="${item.username}" formaction="/admin/edit">Edit
                                </button>
                            </td>
                        </c:if>
                        <c:if test="${edit != null && edit == item.username}">
                            <td>
                                <input type="hidden" name="name" value="${edit}">
                                <input type="radio" name="role" value="UNCHECKED">UNCHECKED
                                <input type="radio" name="role" value="USER">USER
                                <input type="radio" name="role" value="ADMIN">ADMIN
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>

            <form:errors path="role" />

            <c:if test="${edit != null}">
                <h4> </h4>
                <button class="btn btn-lg btn-primary btn-block" type="submit" formaction="/admin">Submit</button></td>
            </c:if>


            <%--<h3>Type username</h3>--%>
            <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<form:input type="text" path="username" class="form-control" placeholder="Username"--%>
                            <%--autofocus="true"></form:input>--%>
                <%--<form:errors path="username"></form:errors>--%>
            <%--</div>--%>

            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td>New role </td>--%>
                    <%--<td><form:radiobutton path="role" value="USER"/>USER</td>--%>
                    <%--<td><form:radiobutton path="role" value="UNCHECKED"/>UNCHECKED</td>--%>
                    <%--<td><form:radiobutton path="role" value="ADMIN"/>ADMIN</td>--%>
                    <%--<td><form:errors path="role" /></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></td>--%>
                <%--</tr>--%>
            <%--</table>--%>

        </form:form>

    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>