<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<a href="users" >Users</a>
<form action="meals" method="get">
    <select name="userSelect">
        <option value="1">user1</option>
        <option value="2">user2</option>
    </select>
    <input type="submit" value="Получить список еды"/>
</form>
</body>
</html>
