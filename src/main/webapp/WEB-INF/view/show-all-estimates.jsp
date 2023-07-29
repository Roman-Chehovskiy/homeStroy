<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!doctype html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<h1>Просмотр всех смет</h1>
<br><br>

<table>
    <th>Название</th>
    <th>Стоимость</th>
    <c:forEach var="estimate" items="${estimates}">
        <c:url var="deleteButton" value="/deleteEstimate">
            <c:param name="idEstimate" value="${estimate.id}"/>
        </c:url>
        <c:url var="showButton" value="/showEstimate">
            <c:param name="idEstimate" value="${estimate.id}"/>
        </c:url>
        <tr>
            <td>${estimate.name}</td>
            <td>${estimate.money}</td>
            <td>
                <input type="button" value="подробности" onclick="window.location.href='${showButton}'">
            </td>
            <td>
                <input type="button" value="Удалить" onclick="window.location.href='${deleteButton}'">
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="newEstimate">
    <input type="submit" value="Новая смета">
</form>

</body>
</html>