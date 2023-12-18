<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Bienes Activos</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Insertar Nuevo Bien</h2>
    <form action="${pageContext.request.contextPath}/bienes" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="personid">Persona:</label>
                <select class="form-control" id="personid" name="personid" required>
                    <option value="">Selecciona una persona</option>
                    <!-- Aquí se cargarán las opciones con AJAX -->
                </select>
            </div>

            <div class="form-group col-md-6">
                <label for="areaid">Área:</label>
                <select class="form-control" id="areaid" name="areaid" required>
                    <option value="">Selecciona un Area</option>
                    <!-- Aquí se cargarán las opciones con AJAX -->
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="cantidad">Cantidad:</label>
            <input type="text" class="form-control" id="cantidad" name="cantidad" required>
        </div>
        <div class="form-group">
            <label for="detalle">Detalle:</label>
            <input type="text" class="form-control" id="detalle" name="detalle" required>
        </div>
        <div class="form-group">
            <label for="valorlibro">Valor del Libro:</label>
            <input type="text" class="form-control" id="valorlibro" name="valorlibro" required>
        </div>
        <div class="form-group">
            <label for="fecha_ingreso">Fecha de Ingreso:</label>
            <input type="date" class="form-control" id="fecha_ingreso" name="fecha_ingreso" required>
        </div>
        <div class="form-group">
            <label for="fecha_depreciacion">Fecha de Depreciación:</label>
            <input type="date" class="form-control" id="fecha_depreciacion" name="fecha_depreciacion" required>
        </div>
        <div class="form-group">
            <label for="status">Estado:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="Alta">Alta</option>
                <option value="Baja">Baja</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Bien</button>
    </form>
</div>


<div class="container mt-5">
    <h2>Bienes Activos</h2>
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Persona</th>
            <th>Área</th>
            <th>Codigo</th>
            <th>Cantidad</th>
            <th>Detalle</th>
            <th>Valor</th>
            <th>Fecha de Ingreso</th>
            <th>Fecha de Depreciacion</th>
            <th>Depreciacion Anual</th>
            <th>Depreciacion Mensual</th>
            <th>Depreciacion Acumulada</th>
            <th>Estado</th>
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
                    row.append($("<td></td>").text(bien.personName));
                    row.append($("<td></td>").text(bien.areaName));
                    row.append($("<td></td>").text(bien.code));
                    row.append($("<td></td>").text(bien.cantidad));
                    row.append($("<td></td>").text(bien.detalle));
                    row.append($("<td></td>").text(bien.valorlibro));
                    row.append($("<td></td>").text(bien.fecha_ingreso));
                    row.append($("<td></td>").text(bien.fecha_depreciacion));
                    row.append($("<td></td>").text(bien.depreciacion_anual));
                    row.append($("<td></td>").text(bien.depreciacion_mensual));
                    row.append($("<td></td>").text(bien.depreciacion_anual));
                    row.append($("<td></td>").text(bien.status));
                    bienesBody.append(row);
                });
            },
            error: function() {
                alert('Error al cargar los datos.');
            }
        });
    });
</script>

<script>
    $(document).ready(function() {
        // Cargar las opciones del combobox de personas desde el servidor
        $.ajax({
            url: '/AS222S3_RJeanSimon/persons', // Cambia la URL al servlet que obtiene las personas
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var personSelect = $("#personid");
                // Recorre los datos y agrega opciones al combobox
                $.each(data, function(index, person) {
                    personSelect.append($("<option></option>")
                        .attr("value", person.personid)
                        .text(person.name));
                });
            },
            error: function() {
                alert('Error al cargar las opciones de personas.');
            }
        });

        // Resto del código...
    });

</script>

<script>
    $(document).ready(function() {
        // Cargar las opciones del combobox de personas desde el servidor
        $.ajax({
            url: '/AS222S3_RJeanSimon/area', // Cambia la URL al servlet que obtiene las personas
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var personSelect = $("#areaid");
                // Recorre los datos y agrega opciones al combobox
                $.each(data, function(index, person) {
                    personSelect.append($("<option></option>")
                        .attr("value", person.areaid)
                        .text(person.name));
                });
            },
            error: function() {
                alert('Error al cargar las opciones de personas.');
            }
        });

        // Resto del código...
    });

</script>

</body>
</html>
