package vg.service;

import vg.model.Bienes;
import java.util.List;

public interface IBienesService {

    List<Bienes> getAll() throws Exception;

    List<Bienes> getAllActive() throws Exception;

    List<Bienes> getAllInactive() throws Exception;

    Bienes getForId(Integer id) throws Exception;

    void insert(Bienes product) throws Exception;

    void update(Bienes product) throws Exception;

    void delete(Bienes id) throws Exception;

    void restore(Bienes id) throws Exception;

    void getAllPeople() throws Exception;

}
