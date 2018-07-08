<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 06.07.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal edit</title>
</head>
<body>
    <h1>
        <c:if test="${param.action} == 'update'">
            Meal Update
        </c:if>
        <c:if test="${param.action} == 'add'">
            Meal create
        </c:if>
    </h1>
    <c:set var="meal" value="${requestScope.meal}"/>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal"/>
    <form action="meals" method="post" name="update_form">
        <table border="1" cellpadding="5" >
            <tr>
                <th>Дата</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
            <tr>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="datetime"/>
                <fmt:formatDate value="${datetime}" pattern="dd-MM-yyyy HH:mm" var="formDate"/>

                <td><input name="datetime" type="datetime-local" value="${formDate}" required/></td>
                <td><input name="description" type="text" value="${meal.description}" required/></td>
                <td><input name="calories" type="text" value="${meal.calories}" required/></td>
                <input type="hidden" name="id" value="${meal.id}"/>
            </tr>
        </table>
        <input type="submit" value="Применить"/>
        <button onclick="window.history.back()">Назад</button>
    </form>
</body>
</html>
