<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Редактирование конструкции</title>
    <script type=text/javascript>

        // function createRow() {
        //         let row = document.createElement("tr");
        //         row.innerHTML = "<td>test</td>";
        //         document.getElementById("node").appendChild(row);
        //
        // }

//        document.getElementById("addRow").onclick = function() {
//
//            let row = document.createElement("tr");
//            row.innerHTML = "<td>test</td>";
//            document.getElementById("node").appendChild(row);
//        }

         function createRow() {
            let row = document.createElement("tr");
            row.innerHTML = "<td><input type='text' name='material' placeholder='материал'></td>" +
                "<td><input type='number' name='price' min='0' step='0.01'></td>" +
                "<td><input type='button' value=' - ' onclick='deleteRow()'><td>";
            document.getElementById("node").appendChild(row);

        }

         function deleteRow() {
            let a = document.getElementById("addRow")

            let td = event.target.parentNode;

            let tr = td.parentNode;

            let table = tr.parentNode;

            table.removeChild(tr);
        }

    </script>
</head>
<body>

<form:form modelAttribute="construction" action="saveConstruction">
    <form:hidden path="id"/>
    <form:input path="construction" placeholder="название конструкции"/>
    <br><br>
    <table class="authors-list" id="node">
        <tr>
            <th>Материал</th>
            <th>Цена</th>
        </tr>
        <c:forEach var="material" items="${construction.materialsList}">

            <tr>
                <td><label>
                    <input type="text" name="material" value="${material.material}"/>
                </label></td>
                <td><label>
                    <input type="number" name="price" value="${material.price}"/>
                </label></td>
                <td><input type="button" value=" - " onclick="deleteRow()"></td>
            </tr>

        </c:forEach>
    </table>
    <br>
    <button id="addRow" type="button" onclick="createRow()">Добавить</button>
    <br>
    <table>
        <tr>
            <td>
                <input type="submit" value="Отмена" formaction="allConstruction">
            </td>
            <td>
                <input type="submit" value="Сохранить">
            </td>
        </tr>
    </table>

</form:form>

</body>
</html>