package vg.service;

import vg.db.AccesoDB;
import vg.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonService extends AccesoDB implements IPersonService{
    @Override
    public List<Person> getAllPeople() throws SQLException {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT personid, name FROM PERSON"; // Ajusta los campos según tu esquema de base de datos

        try (Connection conn = AccesoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Person person = new Person();
                person.setPersonid(rs.getInt("personid"));
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

