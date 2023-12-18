<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Bienes Activos</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Bienes Activos</h2>
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Detalle</th>
            <th>Cantidad</th>
            <th>Persona</th>
            <th>Área</th>
        </tr>
        </thead>
        <tbody id="bienesBody">
        <!-- Los datos de los bienes se llenarán aquí con AJAX -->
        </tbody>
    </table>
</div>

<!-- jQuery (Necesario para Bootstrap y AJAX) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.12/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $.ajax({
            url: '/AS222S3_RJeanSimon/bienes', // La URL del Servlet que devuelve el JSON
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var bienesBody = $("#bienesBody");
                $.each(data, function(index, bien) {
                    var row = $("<tr></tr>");
                    row.append($("<td></td>").text(bien.bienesid));
                    row.append($("<td></td>").text(bien.detalle));
                    row.append($("<td></td>").text(bien.cantidad));
                    row.append($("<td></td>").text(bien.personName));
                    row.append($("<td></td>").text(bien.areaName));
                    bienesBody.append(row);
                });
            },
            error: function() {
                alert('Error al cargar los datos.');
            }
        });
    });
</script>

</body>
</html>
