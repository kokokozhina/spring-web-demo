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

    <title>User</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>User Page ${pageContext.request.userPrincipal.name} | <a href="${contextPath}/logoutform">Logout</a>
        </h2>


        <form:form method="POST" class="form-signin" action="/user">
            <h3>Current settings</h3>
            <table cellpadding="10" border="1" width="80%">
                <tr>
                    <th>ID</th>
                    <th>Gitlab group</th>
                    <th>Gitlab project</th>
                    <th>Slack channel</th>
                    <th>Delete setting<th>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.gitlabGroup}</td>
                        <td>${item.gitlabProject}</td>
                        <td>${item.slackChannel}</td>
                        <td>
                            <input type="hidden" name="action" value="deleteSetting">
                            <input type="hidden" name="settingId" value="${item.id}">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>

        <form:form method="POST" class="form-signin">

            <h3>Add new setting</h3>
            <spring:bind path="gitlabGroup">
                <table cellpadding="10" border="1" width="50%">
                    <tr>
                        <th>Gitlab groups</th>
                    </tr>
                    <c:forEach items="${groups}" var="item">
                        <tr>
                            <td><input type="radio" name="gitlabGroup" value="${item}" />${item}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <spring:hasBindErrors name="gitlabGroup"></spring:hasBindErrors>
                        </td>
                    </tr>
                </table>
            </spring:bind>



            <table cellpadding="10" border="1" width="50%">
                <tr>
                    <th>Slack channels</th>
                </tr>
                <c:forEach items="${channels}" var="item">
                    <tr>
                        <td><input type="radio" name="slackChannel" value="${item}" />${item}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <errors path="slackChannel"></errors>
                    </td>
                </tr>
            </table>


            <h4>Do you want to choose the project?</h4>
            <div>
                <input type="hidden" name="action" value="chooseProject" >
                <button class="btn btn-lg btn-primary btn-block" type="submit">Yes</button>
            </div>
        <%--</form:form>--%>

        <%--<form:form method="POST" class="form-signin">--%>
            <c:if test="${not empty projects}">
                <table cellpadding="10" border="1" width="50%">
                    <tr>
                        <th>Gitlab projects</th>
                    </tr>
                    <c:forEach items="${projects}" var="item">
                        <tr>
                            <td>
                                <input type="radio" name="gitlabProject" value="${item}" />${item}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <errors path="gitlabProject"></errors>
                        </td>
                    </tr>
                </table>
            </c:if>

        <%--</form:form>--%>
            <%--<c:if test="${not empty error}">--%>
                <%--Error: ${error}--%>
            <%--</c:if>--%>

        <%--<form:form method="POST" class="form-signin">--%>
            <div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" formaction="/user/addSetting">Submit</button>
            </div>

        </form:form>




        <%--<form:form method="POST" modelAttribute="action" class="form-signin">--%>
            <%--<input type="hidden" name="action" value="delete">--%>
            <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Delete</button>--%>
        <%--</form:form>--%>
            <%--<spring:bind path="role">--%>
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<td>New role </td>--%>
                        <%--<td><form:radiobutton path="role" value="USER"/>USER--%>
                            <%--<form:radiobutton path="role" value="ANONYMOUS"/>ANONYMOUS</td>--%>
                        <%--<td><form:errors path="role" /></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</spring:bind>--%>



    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>