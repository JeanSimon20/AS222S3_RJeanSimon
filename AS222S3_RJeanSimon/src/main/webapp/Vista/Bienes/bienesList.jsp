<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="vg.model.Bienes"%>
<%@ page import="vg.service.BienesService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Bienes Activos</title>
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
<%@include file="/index.jsp" %>
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

    <!-- Botón para abrir la ventana modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#addBienModal">Agregar Bien</button>
    <button type="button" class="btn btn-info mb-3" onclick="openBienesList()">Bienes Eliminados</button>

    <!-- Ventana modal para agregar bien -->
    <div class="modal fade" id="addBienModal" tabindex="-1"
         aria-labelledby="addBienModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addBienModalLabel">Agregar Bien</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario para agregar bien dentro de la ventana modal -->
                    <form id="addBienForm" action="../../bienes" method="post">
                        <div class="mb-3">
                            <label for="bienCantidadModal" class="form-label">Cantidad</label>
                            <input type="number" class="form-control" id="bienCantidadModal"
                                   name="cantidad" required min="1">
                        </div>
                        <div class="mb-3">
                            <label for="bienDetalleModal" class="form-label">Detalle</label>
                            <input type="text" class="form-control" id="bienDetalleModal"
                                   name="detalle" required>
                        </div>
                        <div class="mb-3">
                            <label for="bienValorLibroModal" class="form-label">Valor Libro</label>
                            <input type="text" class="form-control" id="bienValorLibroModal"
                                   name="valorlibro" required>
                        </div>
                        <div class="mb-3">
                            <label for="bienFechaIngresoModal" class="form-label">Fecha de Ingreso</label>
                            <input type="date" class="form-control" id="bienFechaIngresoModal"
                                   name="fecha_ingreso" required>
                        </div>
                        <div class="mb-3">
                            <label for="bienFechaDepreciacionModal" class="form-label">Fecha de Depreciación</label>
                            <input type="date" class="form-control" id="bienFechaDepreciacionModal"
                                   name="fecha_depreciacion" required>
                        </div>
                        <div class="mb-3">
                            <label for="bienStatusModal" class="form-label">Estado</label>
                            <select class="form-control" id="bienStatusModal" name="status" required>
                                <option value="">Seleccione un estado</option>
                                <option value="Alta">Alta</option>
                                <option value="Baja">Baja</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-bs-dismiss="modal">Cerrar</button>
                    <!-- Botón para enviar el formulario de la ventana modal -->
                    <button type="submit" form="addBienForm"
                            class="btn btn-primary">Agregar Bien</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal de Actualización de Bien -->
    <div class="modal fade" id="updateBienModal" tabindex="-1"
         aria-labelledby="updateBienModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateBienModalLabel">Actualizar Bien</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <!-- Formulario para actualizar bien dentro de la ventana modal -->
                    <form id="updateBienForm" action="../../bienes" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" id="updateBienId" name="bienesId">

                        <div class="mb-3">
                            <label for="updateBienCantidad" class="form-label">Cantidad</label>
                            <input type="number" class="form-control" id="updateBienCantidad"
                                   name="cantidad" required min="1">
                        </div>
                        <div class="mb-3">
                            <label for="updateBienDetalle" class="form-label">Detalle</label>
                            <input type="text" class="form-control" id="updateBienDetalle"
                                   name="detalle" required>
                        </div>
                        <div class="mb-3">
                            <label for="updateBienValorLibro" class="form-label">Valor Libro</label>
                            <input type="text" class="form-control" id="updateBienValorLibro"
                                   name="valorlibro" required>
                        </div>
                        <div class="mb-3">
                            <label for="updateBienFechaIngreso" class="form-label">Fecha de Ingreso</label>
                            <input type="date" class="form-control" id="updateBienFechaIngreso"
                                   name="fecha_ingreso" required>
                        </div>
                        <div class="mb-3">
                            <label for="updateBienFechaDepreciacion" class="form-label">Fecha de Depreciación</label>
                            <input type="date" class="form-control" id="updateBienFechaDepreciacion"
                                   name="fecha_depreciacion" required>
                        </div>
                        <div class="mb-3">
                            <label for="updateBienStatus" class="form-label">Estado</label>
                            <select class="form-control" id="updateBienStatus" name="status" required>
                                <option value="">Seleccione un estado</option>
                                <option value="Alta">Alta</option>
                                <option value="Baja">Baja</option>
                            </select>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Cerrar</button>
                            <input type="submit" class="btn btn-primary"
                                   value="Actualizar Bien">
                        </div>
                    </form>

                </div>

            </div>
        </div>
    </div>


    <h2 class="mt-5">Lista de Bienes Registrados</h2>
    <table class="table table-striped" id="bienesTable">
        <thead>
        <tr>
            <th>Cantidad</th>
            <th>Detalle</th>
            <th>Valor Libro</th>
            <th>Fecha Ingreso</th>
            <th>Fecha Depreciación</th>
            <th>Deprecacion Anual</th>
            <th>Deprecacion Mensual</th>
            <th>Deprecacion Acumulada</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Cambiar a BienesService o el nombre de tu servicio de bienes
            BienesService service = new BienesService();
            List<Bienes> bienesRegistrados = service.getAllActive(); // Cambiar a método adecuado
            for (Bienes bien : bienesRegistrados) {
        %>
        <tr>
            <td><%= bien.getCantidad() %></td>
            <td><%= bien.getDetalle() %></td>
            <td><%= bien.getValorlibro() %></td>
            <td><%= bien.getFecha_ingreso() %></td>
            <td><%= bien.getFecha_depreciacion() %></td>
            <td><%= bien.getDepreciacion_anual() %></td>
            <td><%= bien.getDepreciacion_mensual() %></td>
            <td><%= bien.getDepreciacion_acumulada() %></td>
            <td><%= bien.getStatus() %></td>
            <td>
                <!-- Botón para abrir el modal de actualización -->
                <button type='button' class='btn btn-primary btn-update' data-bs-toggle='modal' data-bs-target='#updateBienModal' onclick='fillUpdateModal(<%= bien.getBienesid() %>)'>Actualizar</button>
                <!-- Botón para eliminar el bien -->
                <button type='button' class='btn btn-danger btn-delete' onclick='deleteBien(<%= bien.getBienesid() %>)'>Eliminar</button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

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


<!-- Bootstrap JavaScript Bundle with Popper -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Inicializa DataTables en tu tabla -->
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
    function fillUpdateModal(button) {
        // Encuentra la fila de la tabla en la que se hizo clic
        var tr = $(button).closest('tr');

        // Recoge los datos de esa fila
        var bienesId = tr.find('td:eq(0)').text();
        var cantidad = tr.find('td:eq(1)').text();
        var detalle = tr.find('td:eq(2)').text();
        var valorLibro = tr.find('td:eq(3)').text();
        var fechaIngreso = tr.find('td:eq(4)').text();
        var fechaDepreciacion = tr.find('td:eq(5)').text();
        var status = tr.find('td:eq(6)').text();

        // Llena el modal con los datos
        $('#updateBienId').val(bienesId);
        $('#updateBienCantidad').val(cantidad);
        $('#updateBienDetalle').val(detalle);
        $('#updateBienValorLibro').val(valorLibro);
        $('#updateBienFechaIngreso').val(fechaIngreso);
        $('#updateBienFechaDepreciacion').val(fechaDepreciacion);
        $('#updateBienStatus').val(status);
    }
</script>

<script>
    function deleteBien(bienesId) {
        // Confirmar eliminación
        var confirmed = confirm('¿Está seguro de que desea eliminar este bien?');
        if (confirmed) {
            // Crear un formulario para enviar la solicitud de eliminación
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

            // Añadir el formulario al body y enviarlo
            form.appendTo('body').submit();
        }
    }
</script>


<script>
    var popupWindow;

    // Función para abrir una nueva ventana emergente con la lista de bienes
    function openBienesList() {
        var url = "http://localhost:8080/AS222S3_RJeanSimon/Bienes/bienesListI.jsp"; // Cambia la URL a la página de lista de bienes
        popupWindow = window.open(url, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");

        // Verifica si la ventana emergente se ha cerrado cada 500 milisegundos
        var checkPopupClosed = setInterval(function() {
            if (popupWindow.closed) {
                clearInterval(checkPopupClosed);
                window.location.reload(true); // Forza la recarga desde el servidor
            }
        }, 500);
    }
</script>
</body>
</html>