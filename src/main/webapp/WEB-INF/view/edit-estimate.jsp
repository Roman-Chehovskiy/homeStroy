<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script type=text/javascript>
        function showResult(idInput, prise, idOutput, idAllResult) {
            // alert("start");
            // alert(idInput);
            // alert(idOutput);
            let countConstruction = parseInt(document.getElementById(idInput).value);
            let startResult = parseInt(document.getElementById(idOutput).value);
            let startAllResult = parseInt(document.getElementById(idAllResult).value);
            let priceConstruction = parseInt(prise);

            if (isNaN(countConstruction)) countConstruction = 0;
            if (isNaN(startResult)) startResult = 0;
            if (isNaN(startAllResult)) startAllResult = 0;
            if (isNaN(priceConstruction)) priceConstruction = 0;

            alert(startAllResult);

            let resultOperation = countConstruction * priceConstruction;
            let outResult = document.getElementById(idOutput);
            outResult.innerHTML = resultOperation.toString();
            showAllResults(resultOperation, idAllResult, startAllResult, startResult);
        }

        function showAllResults(resultOperation, idAllResult, startAllResult, startResult) {
            // alert(startAllResult);
            let allOperationResult = startAllResult - startResult + resultOperation;
            // alert(allOperationResult);
            let outAllOperationResult = document.getElementById(idAllResult);
            // alert(outAllOperationResult);
            outAllOperationResult.innerHTML = allOperationResult.toString();
            document.getElementById("result").value = allOperationResult;
        }

    </script>
</head>
<body>

<h1>Рассчет сметы</h1>
<br><br>


<form:form action="saveEstimate" modelAttribute="estimate">
    <table id="myTable">

        <th>Кострукция</th>
        <th>Материал</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Стоимость</th>

        <c:forEach var="c" items="${constructions}">
            <tr>
                <td>${c.construction}</td>
            </tr>
            <c:forEach var="materialist" items="${c.materialsList}">
                <tr>
                    <td></td>
                    <td>${materialist.material}</td>
                    <td>${materialist.price}</td>
                    <td><label for=type="number"></label>
                        <input id=${materialist.id} type="number" min="0" value="0"
                               oninput="showResult(${materialist.id}, ${materialist.price}, '${materialist.id}out', 'resultPrint');">
                    </td>

                    <td>
                        <output id='${materialist.id}out'>0</output>
                    </td>

                </tr>
            </c:forEach>

        </c:forEach>
    </table>
    <br><br>
    <h3>Итоговая стоимость
        <output id="resultPrint"></output>
    </h3>
    <form:hidden path="money" id="result" value="0"/>
    <form:input path="name" value=""/>


    <input type="submit" value="Сохранить">
</form:form>
</body>
</html>