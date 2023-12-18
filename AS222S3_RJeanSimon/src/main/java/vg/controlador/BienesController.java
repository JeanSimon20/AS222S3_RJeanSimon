package vg.controlador;

import com.google.gson.Gson;
import vg.model.Bienes;
import vg.service.BienesService;
import vg.service.IBienesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/bienes")
public class BienesController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IBienesService bienesService;
    public BienesController() {
        this.bienesService = new BienesService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Bienes> activeProducts = bienesService.getAllActive();
            String json = new Gson().toJson(activeProducts);
            out.print(json);
            out.flush();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setea el tipo de contenido y codificación de caracteres para la respuesta
        response.setContentType("text/html;charset=UTF-8");
        // Crear instancia de la clase Bienes
        Bienes nuevoBien = new Bienes();
        // Obtener los valores de los parámetros y asignarlos al objeto nuevoBien
        try {
            // Asumiendo que los identificadores de persona y área son enteros
            nuevoBien.setPersonid(Integer.parseInt(request.getParameter("personid")));
            nuevoBien.setAreaid(Integer.parseInt(request.getParameter("areaid")));
            nuevoBien.setCantidad(request.getParameter("cantidad"));
            nuevoBien.setCode(request.getParameter("code"));
            nuevoBien.setDetalle(request.getParameter("detalle"));
            nuevoBien.setValorlibro(request.getParameter("valorlibro"));
            nuevoBien.setFecha_ingreso(request.getParameter("fecha_ingreso"));
            nuevoBien.setFecha_depreciacion(request.getParameter("fecha_depreciacion"));
            nuevoBien.setStatus(request.getParameter("status"));

            // Llama al servicio para insertar el nuevo bien
            bienesService.insert(nuevoBien);

            // Redirecciona a la página donde se lista los bienes o muestra algún mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/Vista/Bienes/bienesList.jsp");


        } catch (Exception e) {
            // Manejo de excepciones y errores
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Puedes mostrar un mensaje de error o redirigir a una página de error personalizada
            request.setAttribute("errorMessage", "Error al insertar el bien: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp"); // Asumiendo que tienes una página de error
            dispatcher.forward(request, response);
        }
    }
}
