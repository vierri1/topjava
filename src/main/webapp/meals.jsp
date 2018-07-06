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
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meal List</title>
</head>
<body>
        <table border="1" cellpadding="5" >
            <tr>
                <th>Дата</th>
                <th>Описание</th>
                <th>Калории</th>
                <th>Превышение</th>
                <th colspan="2">Действие</th>
            </tr>
        <c:forEach var="meal" items="${requestScope.mealList}" >
            <c:if test="${!meal.isExceed()}">
                <tr style="color: green">
            </c:if>
            <c:if test="${meal.isExceed()}">
                <tr style="color: red">
            </c:if>
                <td>${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>${meal.getId()}</td>
                <td><a href="meals?action=update&id=${meal.getId()}">UPDATE</a></td>
                <td><a href="meals?action=delete&id=${meal.getId()}">DELETE</a></td>
            </tr>
        </c:forEach>
        </table>
        <form name="add_meal_form" action="meals" method="get">
            <input type="hidden" name="action" value="add"/>
            <input type="submit" value="Добавить еду"/>
        </form>
</body>
</html>
