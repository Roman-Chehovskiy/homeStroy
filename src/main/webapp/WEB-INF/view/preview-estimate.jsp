<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<h1> Предварительный просмотр</h1>

<form:form method="post" modelAttribute="estimateResult" action="saveEstimate" id="saveEstimate" name="saveEstimate">

    <form:hidden path="id" value="${estimateResult.id}"/>

    <table>
        <th>Кострукция</th>
        <th>Материал</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Стоимость</th>

        <c:forEach var="constructionsEstimate" items="${estimateResult.constructionList}">
            <%--            <c:forEach var="construction" items="${constructions.}"--%>
            <tr>
                <td>
                    <input type="hidden" name="${constructionsEstimate.id}" value="${constructionsEstimate.name}"/>
                        ${constructionsEstimate.name}
                </td>
            </tr>

            <c:forEach var="materialEstimate" items="${constructionsEstimate.materialsList}">
                <tr>
                    <td><input type="hidden" name="${constructionsEstimate.id}id" value="${materialEstimate.id}"/></td>
                    <td>
                        <input type="hidden" name="${constructionsEstimate.id}name"
                               value="${materialEstimate.name}"/>
                            ${materialEstimate.name}
                    </td>
                    <td>
                        <input type="hidden" name="${constructionsEstimate.id}price"
                               value="${materialEstimate.price}"/>
                            ${materialEstimate.price}
                    </td>
                    <td>
                        <input type="hidden" name="${constructionsEstimate.id}count"
                               value="${materialEstimate.countMaterial}"/>
                            ${materialEstimate.countMaterial}
                    </td>
                    <td>
                        <input type="hidden" name="${constructionsEstimate.id}money"
                               value="${materialEstimate.money}"/>
                            ${materialEstimate.money}
                    </td>
                </tr>
            </c:forEach>

        </c:forEach>
    </table>
    <br><br>

    <output>${estimateResult.money}</output>
    <form:hidden path="money" value="${estimateResult.money}"/>
    <br>
    Название
    <form:input path="name" value="${estimateResult.name}"/>
    <form:errors path="name"/>
    <br>

    <table>
        <tr>
            <td>
                <input type="submit" formaction="changeNewEstimate" value="Редактировать">
            </td>
            <td>
                <input type="submit" value="OK">
            </td>
        </tr>
    </table>

</form:form>

</body>
</html>