package vg.service;

import vg.db.AccesoDB;
import vg.model.Area;
import vg.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaService extends AccesoDB implements IAreaService {
    @Override
    public List<Area> getAllArea() throws SQLException {
        List<Area> people = new ArrayList<>();
        String sql = "SELECT areaid, name FROM area"; // Ajusta los campos según tu esquema de base de datos
        try (Connection conn = AccesoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Area person = new Area();
                person.setAreaid(rs.getInt("areaid"));
                person.setName(rs.getString("name"));
                // Suponiendo que tu objeto Person tiene estos dos campos
                // Agrega aquí más campos si es necesario

                people.add(person);
            }

        } catch (SQLException e) {
            // Manejo de la excepción de SQL
            throw new SQLException("Error al obtener personas de la base de datos", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Asegúrate de cerrar la conexión
            if (AccesoDB.cnx != null) {
                AccesoDB.cnx.close();
            }
        }

        return people;
    }
}
