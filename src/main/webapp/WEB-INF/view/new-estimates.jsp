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
    <%--    <script>--%>
    <%--    $('document').ready(function () {--%>
    <%--    $("#imgload").change(function () {--%>
    <%--    if (this.files && this.files[0]) {--%>
    <%--    var reader = new FileReader();--%>
    <%--    reader.onload = function (e) {--%>
    <%--    $('#imgshow').attr('src', e.target.result);--%>
    <%--    }--%>
    <%--    reader.readAsDataURL(this.files[0]);--%>
    <%--    }--%>
    <%--    });--%>
    <%--    });--%>
    <%--    </script>--%>


    <script>
        jQuery(document).ready(function ($) {

            var maxFileSize = 2 * 1024 * 1024; // (байт) Максимальный размер файла (2мб)
            var queue = {};
            var form = $('form#newEstimate');
            var imagesList = $('#uploadImagesList');

            var itemPreviewTemplate = imagesList.find('.item.template').clone();
            itemPreviewTemplate.removeClass('template');
            imagesList.find('.item.template').remove();


            $('#addImages').on('change', function () {
                var files = this.files;

                for (var i = 0; i < files.length; i++) {
                    var file = files[i];

                    if (!file.type.match(/image\/(jpeg|jpg|png|gif)/)) {
                        alert('Фотография должна быть в формате jpg, png или gif');
                        continue;
                    }

                    if (file.size > maxFileSize) {
                        alert('Размер фотографии не должен превышать 2 Мб');
                        continue;
                    }

                    preview(files[i]);
                }

                this.value = '';
            });

            // Создание превью
            function preview(file) {
                var reader = new FileReader();
                reader.addEventListener('load', function (event) {
                    // var img = document.createElement('img');

                    var itemPreview = itemPreviewTemplate.clone();

                    itemPreview.find('.img-wrap img').attr('src', event.target.result);
                    itemPreview.data('id', file.name);

                    imagesList.append(itemPreview);

                    queue[file.name] = file;

                });
                reader.readAsDataURL(file);
            }

            // Удаление фотографий
            imagesList.on('click', '.delete-link', function () {
                var item = $(this).closest('.item'),
                    id = item.data('id');

                delete queue[id];

                item.remove();
            });


            // // Отправка формы
            // form.on('submit', function(event) {
            //
            //     var formData = new FormData(this);
            //
            //     for (var id in queue) {
            //         formData.append('images[]', queue[id]);
            //     }
            //
            //     $.ajax({
            //         url: $(this).attr('action'),
            //         type: 'POST',
            //         data: formData,
            //         async: true,
            //         success: function (res) {
            //             alert(res)
            //         },
            //         cache: false,
            //         contentType: false,
            //         processData: false
            //     });
            //
            //     return false;
            // });

        });

    </script>

    <style>
        .blokimg {
            position: relative;
        }

        .overlay {
            display: none;
            height: auto;
            left: 25%;
            position: absolute;
            top: -100%;
            width: auto;
            z-index: 999;
        }

        .overlay .overlay_container {
            display: table-cell;
            vertical-align: middle;
        }

        .overlay_container img {
            background-color: #AB5;
            padding: 10px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
        }

        .overlay:target {
            display: table;
        }
    </style>

</head>
<body>


<h1>Рассчет сметы</h1>
<br><br>


<form:form action="previewEstimate" id="newEstimate" modelAttribute="estimate" method="post"
           enctype="multipart/form-data">

    <form:hidden path="id" value="${estimate.id}"/>

    <table id="myTable">

        <th>Кострукция</th>
        <th>Материал</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Стоимость</th>

        <c:forEach var="c" items="${estimate.constructionList}">
            <tr>
                <td>
                    <input type="hidden" name="${c.id}" value="${c.name}"/>
                        ${c.name}
                </td>
            </tr>
            <c:forEach var="materialist" items="${c.materialsList}">
                <tr>
                    <td><input type="hidden" name="${c.id}id" value="${materialist.id}"/></td>
                    <td>
                        <input type="hidden" name="${c.id}name" value="${materialist.name}"/>
                            ${materialist.name}
                    </td>
                    <td>
                        <input type="hidden" name="${c.id}price" value="${materialist.price}"/>
                            ${materialist.price}
                    </td>
                    <td><label for=type="number"></label>
                        <input id=${materialist.id} type="number" min="0" value="${materialist.countMaterial}"
                               name="${c.id}count" step="0.01"
                               oninput="showResult(${materialist.id}, ${materialist.price}, '${materialist.id}out'
                                       , 'resultPrint', '${c.id}money');">
                    </td>
                    <td>
                        <input type="hidden" name="${c.id}money"/>
                        <output id='${materialist.id}out'>${materialist.money}</output>
                    </td>

                </tr>
            </c:forEach>

        </c:forEach>
    </table>
    <br><br>
    <h3>Итоговая стоимость
        <output id="resultPrint">${estimate.money}</output>
    </h3>
    <form:hidden path="money" id="result" value="${estimate.money}" name="result"/>
    <form:errors path="money"/>
    <br><br>
    <form:input path="name"/>
    <form:errors path="name"/>
    <br>
    <%--    <input type="file">--%>


    <%--    <input type="file" id="imgload" multiple>--%>
    <%--    <img src="#" id="imgshow" align="left">--%>
    <%--    <form  action="previewEstimate">--%>
    <input type="file" id="addImages" name="file">


    <%--    <input type="hidden" name="azaza" value="zazaza">--%>

    <ul id="uploadImagesList">
        <li class="item template">
            <span class="img-wrap">
<%--                <img src="image.jpg" alt="" height="200" width="200">--%>	<div class="blokimg">
	<div class="overlay" id="contenedor1">
	<div class="overlay_container">
		<a href="#close">
			<img src="image.jpg" height="600" width="800"/>
		</a>
	</div>
	</div>
	<a href="#contenedor1">
		<img src="image.jpg" height="200" width="200"/>
	</a>
	</div>
            </span>
            <span class="delete-link" title="Удалить">Удалить</span>
        </li>
    </ul>

    <div class="clear"></div>
    <%--    </form>--%>

    <%--    <div>--%>
    <%--        <input type="submit" value="Отправить">--%>
    <%--    </div>--%>

    <input type="submit" value="Сохранить">
</form:form>
</body>
</html>