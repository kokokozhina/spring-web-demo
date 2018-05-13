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


        <form:form method="POST" class="form-signin">
            <h3>Current settings</h3>
            <table cellpadding="10" border="1" width="100%">
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
                            <input type="hidden" name="settingId" value="${item.id}">
                            <button class="btn btn-lg btn-primary btn-block" type="submit" formaction="/user">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>


        <form:form method="POST" modelAttribute="userChangesForm" class="form-signin">

            <h3>Add new setting</h3>
            <table cellpadding="10" border="1" width="50%">
                <tr>
                    <th>Gitlab groups</th>
                </tr>
                <c:forEach items="${groups}" var="item">
                    <tr>
                        <td><form:radiobutton path="gitlabGroup" value="${item}" />${item}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="gitlabGroup" />
            </div>

            <table cellpadding="10" border="1" width="50%">
                <tr>
                    <th>Slack channels</th>
                </tr>
                <c:forEach items="${channels}" var="item">
                    <tr>
                        <td><form:radiobutton path="slackChannel" value="${item}" />${item}</td>
                        <%--<td><input type="radio" name="slackChannel" value="${item}" />${item}</td>--%>
                    </tr>
                </c:forEach>
            </table>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="slackChannel" />
            </div>

            <div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" formaction="/user/chooseProject">
                    Choose project from group
                </button>
            </div>
            <%--</form:form>--%>

            <%--<form:form method="POST" class="form-signin">--%>
            <c:if test="${not empty projects}">
                <h4> </h4>
                <table cellpadding="10" border="1" width="50%">
                    <tr>
                        <th>Gitlab projects</th>
                    </tr>
                    <c:forEach items="${projects}" var="item">
                        <tr>
                            <td><form:radiobutton path="gitlabProject" value="${item}" />${item}</td>
                                <%--<input type="radio" name="gitlabProject" value="${item}" />${item}</td>--%>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="gitlabProject" />
            </div>


            <div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" formaction="/user/addSetting">Submit</button>
            </div>


        </form:form>

    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>