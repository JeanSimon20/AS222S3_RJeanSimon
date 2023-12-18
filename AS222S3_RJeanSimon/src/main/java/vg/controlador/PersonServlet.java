package vg.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vg.model.Person;
import vg.service.IPersonService;
import vg.service.PersonService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/persons") // Ajusta la URL según tu configuración
public class PersonServlet extends HttpServlet {
    private IPersonService personService;

    public PersonServlet() {
        this.personService = new PersonService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Person> people = personService.getAllPeople();

            // Configura Gson para formatear la salida JSON de manera legible
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(people);

            out.print(json);
            out.flush();
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}