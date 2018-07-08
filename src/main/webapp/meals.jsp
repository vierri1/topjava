<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 05.07.2018
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal List</title>
</head>
<body>
        <table border="1" cellpadding="9">
            <tr>
                <th>Дата</th>
                <th>Описание</th>
                <th>Калории</th>
                <th>Превышение</th>
                <th colspan="2">Действие</th>
            </tr>
        <c:forEach var="meal" items="${requestScope.mealList}" >
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <c:if test="${!meal.exceed}">
                <tr style="color: green">
            </c:if>
            <c:if test="${meal.exceed}">
                <tr style="color: red">
            </c:if>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="datetime"/>
                <fmt:formatDate value="${datetime}" pattern="d-MM-yyyy HH:mm" var="formDate"/>
                <td>${formDate}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.id}</td>
                <td><a href="meals?action=update&id=${meal.id}">UPDATE</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">DELETE</a></td>
            </tr>
        </c:forEach>
        </table>
        <form name="add_meal_form" action="meals" method="get">
            <input type="hidden" name="action" value="add"/>
            <input type="submit" value="Добавить еду"/>
        </form>
</body>
</html>
