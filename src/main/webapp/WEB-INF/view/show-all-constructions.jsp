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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

    <script type=text/javascript>
        function showResult(idInput, prise, idOutput, idAllResult, outputName) {
            // alert("start");
            // alert(idInput);
            // alert(idOutput);
            let countConstruction = parseFloat(document.getElementById(idInput).value);
            let startResult = parseFloat(document.getElementById(idOutput).value);
            let startAllResult = parseFloat(document.getElementById(idAllResult).value);
            let priceConstruction = parseFloat(prise);

            // alert(countConstruction);

            if (isNaN(countConstruction)) countConstruction = 0;
            if (isNaN(startResult)) startResult = 0;
            if (isNaN(startAllResult)) startAllResult = 0;
            if (isNaN(priceConstruction)) priceConstruction = 0;

            // alert(startAllResult);

            let resultOperation = countConstruction * priceConstruction;
            let outResult = document.getElementById(idOutput);
            outResult.innerHTML = resultOperation.toString();
            document.getElementsByName(outputName).value = resultOperation;
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


            // $('#postButton').click(function() {
            //     var myObjects = [];
            //     $('#b tr').each(function(index, item) {
            //         let $item = $(item);
            //         myObjects.push({
            //             name: $item.find("td input[name='name']").val(),
            //             series: $item.find("td input[name='series']").val(),
            //             value: $item.find("td input[name='value']").val(),
            //         });
            //     });
            //     $.ajax({
            //         url: '/series',
            //         method: 'POST',
            //         contentType : 'application/json; charset=utf-8',
            //         data: JSON.stringify(myObjects)
            //     })
            //         .done(function(myObjects) {
            //             // handle success
            //         })
            //         .fail(function() {
            //             // handle fail
            //         });
            // });


    </script>

    <%--    <script type="text/javascript">--%>
    <%--        function sendData() {--%>
    <%--            alert("start")--%>
    <%--            $.ajax(--%>
    <%--                alert("start"),--%>
    <%--                {--%>
    <%--                type: "POST",--%>
    <%--                url: "/saveEstimate",--%>
    <%--                data: $('#myTable').serialize()--%>
    <%--            })--%>
    <%--            // { alert("send by ajax"); });--%>
    <%--            alert("end")--%>
    <%--        }--%>
    <%--    </script>--%>
</head>
<body>
<form:form modelAttribute="allConstructions" action="allConstruction">
    <table>
        <th>Название</th>
        <th>Материал</th>
        <th>Цена</th>
        <c:forEach var="construction" items="${allConstructions}">

            <tr>
                <td>${construction.construction}</td>
                <c:url var="deleteConstruction" value="/deleteConstruction">
                    <c:param name="idConstruction" value="${construction.id}"/>
                </c:url>
                <c:url var="editConstruction" value="/editConstruction">
                    <c:param name="idConstruction" value="${construction.id}"/>
                </c:url>
            </tr>
            <c:forEach var="material" items="${construction.materialsList}">

                <tr>
                    <td></td>
                    <td>${material.material}</td>
                    <td>${material.price}</td>
                </tr>

            </c:forEach>
            <tr>
                <td>
                    <input type="button" value="Редактировать" onclick="window.location.href = '${editConstruction}'"/>
                </td>
                <td>
                    <input type="button" value="Удалить" onclick="window.location.href = '${deleteConstruction}'"/>
                </td>
            </tr>

        </c:forEach>
    </table>
</form:form>
<br>
<c:url var="newConstruction" value="/newConstruction"/>
<input type="button" value="Добавить" onclick="window.location.href = '${newConstruction}'"/>


</body>
</html>