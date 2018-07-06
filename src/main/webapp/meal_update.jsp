<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 06.07.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Meal</title>
</head>
<body>
    <c:set var="meal" value="${requestScope.meal}"/>
    <form action="meals?action=update&id=${requestScope.meal.getId()}" method="post" name="update_form">
        <table border="1" cellpadding="5" >
            <tr>
                <th>Дата</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
            <tr>
                <td><input name="datetime" type="datetime-local" value="${meal.getDate()} ${meal.getTime()}"/></td>
                <td><input name="description" type="text" value="${meal.getDescription()}"/></td>
                <td><input name="calories" type="text" value="${meal.getCalories()}"/></td>
            </tr>
        </table>
        <input type="submit" value="Обновить"/>
    </form>
</body>
</html>
