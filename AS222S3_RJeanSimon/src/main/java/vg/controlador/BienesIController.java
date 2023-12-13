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

@WebServlet("/bienesi")
public class BienesIController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IBienesService bienesService;

    public BienesIController() {
        this.bienesService = new BienesService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Bienes> activeProducts = bienesService.getAllInactive();
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
            if ("restore".equals(action)) {
                int bienesId = Integer.parseInt(request.getParameter("bienesid"));
                Bienes bien = new Bienes();
                bien.setBienesid(bienesId);
                bienesService.restore(bien);
                request.getSession().setAttribute("successMessage", "Bien Restaurado con éxito.");
            }
            response.sendRedirect("Vista/Bienes/bienesListI.jsp"); // Redirige a la lista de productos
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error en la operación de restauración: " + e.getMessage());
            response.sendRedirect("Vista/Bienes/bienesError.jsp"); // Redirige a la página de error
        }
    }
}
