<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!doctype html>
<html lang="ru">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Новая конструкция</title>

    <script type="text/javascript">

        function addRow() {
            let row = document.createElement("tr");
            row.innerHTML = "<td><input type='text' name='material' placeholder='материал'></td>" +
                "<td><input type='number' name='prise' min='0' step='0.01'></td>" +
                "<td><input type='button' value=' - ' onclick='deleteRow()'><td>";
            document.getElementById("node").appendChild(row);
        }

        function deleteRow() {
            // let a = document.getElementById("addRow")

            let td = event.target.parentNode;

            let tr = td.parentNode;

            let table = tr.parentNode;

            table.removeChild(tr);
        }

    </script>

</head>
<body>

<form:form modelAttribute="construction" action="/saveConstruction">
     <label>
         <form:input path="construction" type="text" placeholder="название конструкции"/>
     </label>
     <br>
     <table id = "node">
         <tr>
             <th>материал</th>
             <th>цена</th>
         </tr>
        <tr>
            <td>
                <label>
                    <input type="text" placeholder="название материала" name="material"/>
                </label>
            </td>
            <td>
                <label>
                    <input type="number" placeholder="0" name="price" min="0" step="0.01"/>
                </label>
            </td>
            <td>
                <input type="button" value=" - " onclick="deleteRow()"/>
            </td>
        </tr>
     </table>
     <br>
     <input type="button" value="добавить строку" onclick="addRow()"/>
     <br><br>
     <table>
         <tr>
             <td>
                 <input type="submit" formaction="/allConstruction">
             </td>
             <td>
                 <input type="submit" value="сохранить">
             </td>
         </tr>
     </table>
 </form:form>

</body>
</html>