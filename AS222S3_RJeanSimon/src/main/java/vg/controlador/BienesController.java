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
}
