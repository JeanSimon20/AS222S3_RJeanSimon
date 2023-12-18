package vg.service;

import vg.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface IPersonService {
    List<Person> getAllPeople() throws SQLException;
}
