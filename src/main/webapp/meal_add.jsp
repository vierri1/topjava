<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 06.07.2018
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<form action="meals?action=add" method="post" name="add_form">
    <table border="1" cellpadding="5" >
        <tr>
            <th>Дата</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
        <tr>
            <td><input name="datetime" type="datetime-local" value="Введите дату и время"/></td>
            <td><input name="description" type="text" value="Описание"/></td>
            <td><input name="calories" type="text" value="Калории"/></td>
        </tr>
    </table>
    <input type="submit" value="Добавить"/>
</form>
</body>
</html>
