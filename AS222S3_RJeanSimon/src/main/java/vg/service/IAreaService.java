package vg.service;

import vg.model.Area;

import java.sql.SQLException;
import java.util.List;

public interface IAreaService {

        List<Area> getAllArea() throws SQLException;

}
