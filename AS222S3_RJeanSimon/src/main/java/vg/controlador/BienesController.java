package vg.controlador;

import com.google.gson.Gson;
import vg.model.Bienes;
import vg.service.BienesService;
import vg.service.IBienesService;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                // Procesar la actualización
                int bienesId = Integer.parseInt(request.getParameter("bienesId"));
                Bienes bien = new Bienes();
                bien.setBienesid(bienesId);
                bien.setCantidad(request.getParameter("cantidad"));
                bien.setDetalle(request.getParameter("detalle"));
                bien.setValorlibro(request.getParameter("valorlibro"));
                bien.setFecha_ingreso(request.getParameter("fecha_ingreso"));
                bien.setFecha_depreciacion(request.getParameter("fecha_depreciacion"));
                bien.setStatus(request.getParameter("status"));
                bienesService.update(bien);
                request.getSession().setAttribute("successMessage", "Bien actualizado con éxito.");
            } else if ("delete".equals(action)) {
                // Proceso de eliminar
                String bienesIdStr = request.getParameter("bienesid");
                if (bienesIdStr != null && !bienesIdStr.isEmpty()) {
                    int bienesId = Integer.parseInt(bienesIdStr);
                    Bienes bien = new Bienes();
                    bien.setBienesid(bienesId);
                    bienesService.delete(bien);
                    request.getSession().setAttribute("successMessage", "Bien eliminado con éxito.");
                } else {
                    request.getSession().setAttribute("errorMessage", "ID del bien no proporcionado.");
                }
            } else {
                // Procesar la inserción
                Bienes bien = new Bienes();
                bien.setCantidad(request.getParameter("cantidad"));
                bien.setDetalle(request.getParameter("detalle"));
                bien.setValorlibro(request.getParameter("valorlibro"));
                bien.setFecha_ingreso(request.getParameter("fecha_ingreso"));
                bien.setFecha_depreciacion(request.getParameter("fecha_depreciacion"));
                bien.setStatus(request.getParameter("status"));
                // Insertar bien
                bienesService.insert(bien);
                request.getSession().setAttribute("successMessage", "Bien registrado con éxito.");
            }
            response.sendRedirect("Vista/Bienes/bienesList.jsp"); // Redirige a la lista de bienes
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error en la operación: " + e.getMessage());
            response.sendRedirect("Vista/Bienes/bienesError.jsp"); // Redirige a la página de error
        }
    }

}
