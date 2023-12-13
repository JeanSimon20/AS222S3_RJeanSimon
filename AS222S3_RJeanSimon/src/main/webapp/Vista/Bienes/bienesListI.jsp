<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="vg.service.BienesService" %>
<%@ page import="vg.model.Bienes" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos Eliminados</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

    <!-- Incluir DataTables CSS -->
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <!-- Incluir DataTables Buttons CSS -->
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css">

    <!-- Incluir jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <!-- Incluir DataTables JS -->
    <script
            src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <!-- Incluir DataTables Buttons JS -->
    <script
            src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
    <!-- Incluir JSZip (necesario para exportar a Excel) -->
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <!-- Incluir pdfmake (necesario para exportar a PDF) -->
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <!-- Incluir HTML5 Buttons (necesario para los botones de exportar a Excel y CSV) -->
    <script
            src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
    <!-- Incluir Print Button -->
    <script
            src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"></script>
</head>

<body>

<div class="container mt-5">
    <!-- Verificar si hay un mensaje de éxito en la sesión -->
    <%
        if (session.getAttribute("successMessage") != null) {
    %>
    <div class="alert alert-success" role="alert" id="successAlert">
        <%=session.getAttribute("successMessage")%>
    </div>
    <%
        session.removeAttribute("successMessage");
    %>
    <script>
        setTimeout(function() {
            document.getElementById('successAlert').style.display = 'none';
        }, 3000); // 3000 milisegundos = 3 segundos
    </script>
    <%
        }
    %>
    <h2 class="mt-5">Lista de Bienes Registrados</h2>
    <table class="table table-striped" id="bienesTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Cantidad</th>
            <th>Detalle</th>
            <th>Valor Libro</th>
            <th>Fecha Ingreso</th>
            <th>Fecha Depreciación</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Cambiar a BienesService o el nombre de tu servicio de bienes
            BienesService service = new BienesService();
            List<Bienes> bienesRegistrados = service.getAllInactive(); // Cambiar a método adecuado
            for (Bienes bien : bienesRegistrados) {
        %>
        <tr>
            <td><%= bien.getBienesid() %></td>
            <td><%= bien.getCantidad() %></td>
            <td><%= bien.getDetalle() %></td>
            <td><%= bien.getValorlibro() %></td>
            <td><%= bien.getFecha_ingreso() %></td>
            <td><%= bien.getFecha_depreciacion() %></td>
            <td><%= bien.getStatus() %></td>
            <td>
                <!-- Botón para abrir el modal de actualización -->
                <!-- Botón para eliminar el bien -->
                <button type='button' class='btn btn-danger btn-delete' onclick='restoreBien(<%= bien.getBienesid() %>)'>Restaurar</button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JavaScript Bundle with Popper -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Inicializa DataTables en tu tabla -->
<script>
    $(document).ready(function() {
        // Función para llenar el modal de actualización
        window.fillUpdateModal = function(bienesId) {
            // Busca la fila con el ID específico
            var tr = $('#bienesTable').find('tr').filter(function() {
                return $(this).find('td:first').text() == bienesId;
            });

            var cantidad = tr.find('td').eq(1).text();
            var detalle = tr.find('td').eq(2).text();
            var valorLibro = tr.find('td').eq(3).text();
            var fechaIngreso = tr.find('td').eq(4).text();
            var fechaDepreciacion = tr.find('td').eq(5).text();
            var status = tr.find('td').eq(6).text();

            // Llena el modal con los datos
            $('#updateBienId').val(bienesId);
            $('#updateBienCantidad').val(cantidad);
            $('#updateBienDetalle').val(detalle);
            $('#updateBienValorLibro').val(valorLibro);
            $('#updateBienFechaIngreso').val(fechaIngreso);
            $('#updateBienFechaDepreciacion').val(fechaDepreciacion);
            $('#updateBienStatus').val(status);
        };
    });
</script>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function() {
        $('#productTable').DataTable({
            "language" : {
                "search" : "Búsqueda:",
                "lengthMenu" : "Mostrar _MENU_ entradas",
                "zeroRecords" : "No se encontraron registros coincidentes",
                "info" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
                "infoEmpty" : "No hay registros disponibles",
                "infoFiltered" : "(filtrado de _MAX_ registros totales)",
                "loadingRecords" : "Cargando...",
                "processing" : "Procesando...",
                "paginate" : {
                    "first" : "Primero",
                    "last" : "Último",
                    "next" : "Siguiente",
                    "previous" : "Anterior"
                }
            },
            "dom" : 'Blfrtip',
            "buttons" : [ 'csv', 'excel', 'pdf' ]
        });
    });
</script>

<script>
    function restoreBien(bienesId) {
        var confirmed = confirm('¿Está seguro de que desea restaurar este producto?');
        if (confirmed) {
            var form = $('<form>', {
                'method' : 'post',
                'action' : '../../bienes' // Asegúrate de que la acción corresponda a la URL correcta de tu servidor
            }).append($('<input>', {
                'type' : 'hidden',
                'name' : 'action',
                'value' : 'delete'
            })).append($('<input>', {
                'type' : 'hidden',
                'name' : 'bienesId', // Asegúrate de que el nombre del campo coincida con lo que espera tu servidor
                'value' : bienesId
            }));
            form.appendTo('body').submit();
        }
    }
</script>
</body>
</html>