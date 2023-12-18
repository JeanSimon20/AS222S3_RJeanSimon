package vg.service;

import vg.db.AccesoDB;
import vg.model.Bienes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BienesService extends AccesoDB implements IBienesService{

    private static final String SQL_SELECT_BY_ID =
            "SELECT B.bienesid, B.cantidad, B.code, B.detalle, B.valorlibro, B.fecha_ingreso, B.fecha_depreciacion, B.depreciacion_anual, B.depreciacion_mensual, B.status, " +
                    "P.name AS personName, P.dni, P.email, P.gender, P.fecha_cumpleanos, " +
                    "A.name AS areaName " +
                    "FROM BIENES B " +
                    "INNER JOIN PERSON P ON B.personid = P.personid " +
                    "INNER JOIN AREA A ON B.areaid = A.areaid " +
                    "WHERE B.bienesid = ?";

    @Override
    public List<Bienes> getAll() throws Exception {
        List<Bienes> ListAllActive = new ArrayList<>();
        Bienes bienes;
        String sql = "Select * from BIENES";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                bienes = new Bienes();
                bienes.setBienesid(rs.getInt("bienesid"));
                bienes.setCantidad(rs.getString("cantidad"));
                bienes.setCode(rs.getString("code"));
                bienes.setDetalle(rs.getString("detalle"));
                bienes.setValorlibro(rs.getString("valorlibro"));
                bienes.setFecha_ingreso(rs.getString("fecha_ingreso"));
                bienes.setFecha_depreciacion(rs.getString("fecha_depreciacion"));
                bienes.setDepreciacion_anual(rs.getString("depreciacion_anual"));
                bienes.setDepreciacion_mensual(rs.getString("depreciacion_mensual"));
                bienes.setDepreciacion_acumulada(rs.getString("depreciacion_acumulada"));
                bienes.setStatus(rs.getString("status"));
                ListAllActive.add(bienes);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en traer la lista getAll" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return ListAllActive;
    }


    @Override
    public List<Bienes> getAllActive() throws Exception {
        List<Bienes> listAllActive = new ArrayList<>();
        Bienes bienesDetail;
        String sql = "SELECT B.bienesid, B.cantidad, B.code, B.detalle, B.valorlibro, B.fecha_ingreso, B.fecha_depreciacion, B.depreciacion_anual, B.depreciacion_mensual, B.depreciacion_acumulada, B.status, " +
                "P.name AS personName, A.name AS areaName " +
                "FROM BIENES B " +
                "INNER JOIN PERSON P ON B.personid = P.personid " +
                "INNER JOIN AREA A ON B.areaid = A.areaid " +
                "WHERE B.status = 'Alta' " +
                "ORDER BY B.bienesid DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                bienesDetail = new Bienes();
                bienesDetail.setBienesid(rs.getInt("bienesid"));
                bienesDetail.setCantidad(rs.getString("cantidad"));
                bienesDetail.setCode(rs.getString("code"));
                bienesDetail.setDetalle(rs.getString("detalle"));
                bienesDetail.setValorlibro(rs.getString("valorlibro"));
                bienesDetail.setFecha_ingreso(rs.getString("fecha_ingreso"));
                bienesDetail.setFecha_depreciacion(rs.getString("fecha_depreciacion"));
                bienesDetail.setDepreciacion_anual(rs.getString("depreciacion_anual"));
                bienesDetail.setDepreciacion_mensual(rs.getString("depreciacion_mensual"));
                bienesDetail.setStatus(rs.getString("status"));
                // Campos adicionales de PERSON y AREA
                bienesDetail.setPersonName(rs.getString("personName"));
                bienesDetail.setAreaName(rs.getString("areaName"));

                listAllActive.add(bienesDetail);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en traer la lista getAllActive: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listAllActive;
    }



    @Override
    public List<Bienes> getAllInactive() throws Exception {
        List<Bienes> listAllInactive = new ArrayList<>();
        Bienes bienesDetail;
        String sql = "SELECT B.bienesid, B.cantidad, B.code, B.detalle, B.valorlibro, B.fecha_ingreso, B.fecha_depreciacion, B.depreciacion_anual, B.depreciacion_mensual, B.status, " +
                "P.name AS personName, A.name AS areaName " +
                "FROM BIENES B " +
                "INNER JOIN PERSON P ON B.personid = P.personid " +
                "INNER JOIN AREA A ON B.areaid = A.areaid " +
                "WHERE B.status = 'Baja' " +
                "ORDER BY B.detalle";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                bienesDetail = new Bienes();
                bienesDetail.setBienesid(rs.getInt("bienesid"));
                bienesDetail.setCantidad(rs.getString("cantidad"));
                bienesDetail.setCode(rs.getString("code"));
                bienesDetail.setDetalle(rs.getString("detalle"));
                bienesDetail.setValorlibro(rs.getString("valorlibro"));
                bienesDetail.setFecha_ingreso(rs.getString("fecha_ingreso"));
                bienesDetail.setFecha_depreciacion(rs.getString("fecha_depreciacion"));
                bienesDetail.setDepreciacion_anual(rs.getString("depreciacion_anual"));
                bienesDetail.setDepreciacion_mensual(rs.getString("depreciacion_mensual"));
                bienesDetail.setStatus(rs.getString("status"));
                // Campos adicionales de PERSON y AREA
                bienesDetail.setPersonName(rs.getString("personName"));
                bienesDetail.setAreaName(rs.getString("areaName"));

                listAllInactive.add(bienesDetail);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en traer la lista getAllInactive: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listAllInactive;
    }

    @Override
    public Bienes getForId(Integer id) {
        Bienes bienesDetail = null;
        try (Connection conn = AccesoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bienesDetail = new Bienes();
                    bienesDetail.setBienesid(rs.getInt("bienesid"));
                    bienesDetail.setCantidad(rs.getString("cantidad"));
                    bienesDetail.setCode(rs.getString("code"));
                    bienesDetail.setDetalle(rs.getString("detalle"));
                    bienesDetail.setValorlibro(rs.getString("valorlibro"));
                    bienesDetail.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    bienesDetail.setFecha_depreciacion(rs.getString("fecha_depreciacion"));
                    bienesDetail.setDepreciacion_anual(rs.getString("depreciacion_anual"));
                    bienesDetail.setDepreciacion_mensual(rs.getString("depreciacion_mensual"));
                    bienesDetail.setStatus(rs.getString("status"));
                    // Campos adicionales de PERSON y AREA
                    bienesDetail.setPersonName(rs.getString("personName"));
                    bienesDetail.setAreaName(rs.getString("areaName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(BienesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bienesDetail;
    }

    @Override
    public void insert(Bienes bienes) throws Exception {
        String sql = "INSERT INTO bienes (personid, areaid, cantidad, code, detalle, valorlibro, fecha_ingreso, fecha_depreciacion, status) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement insertBienes = this.conectar().prepareStatement(sql);
            insertBienes.setInt(1, bienes.getPersonid()); // Agregado
            insertBienes.setInt(2, bienes.getAreaid());   // Agregado
            insertBienes.setString(3, bienes.getCantidad());
            insertBienes.setString(4, bienes.getCode());
            insertBienes.setString(5, bienes.getDetalle());
            insertBienes.setString(6, bienes.getValorlibro());
            insertBienes.setString(7, bienes.getFecha_ingreso());
            insertBienes.setString(8, bienes.getFecha_depreciacion());
            insertBienes.setString(9, bienes.getStatus());
            insertBienes.executeUpdate();
            insertBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar: " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void update(Bienes bienes) throws Exception {
        String sql = "UPDATE BIENES SET personid = ?, areaid = ?, cantidad = ?, code = ?, detalle = ?, valorlibro = ?, fecha_ingreso = ?, fecha_depreciacion = ?, status = ? WHERE bienesid = ?";
        try {
            PreparedStatement updateBienes = this.conectar().prepareStatement(sql);
            updateBienes.setInt(1, bienes.getPersonid()); // Agregado
            updateBienes.setInt(2, bienes.getAreaid());   // Agregado
            updateBienes.setString(3, bienes.getCantidad());
            updateBienes.setString(4, bienes.getCode());
            updateBienes.setString(5, bienes.getDetalle());
            updateBienes.setString(6, bienes.getValorlibro());
            updateBienes.setString(7, bienes.getFecha_ingreso());
            updateBienes.setString(8, bienes.getFecha_depreciacion());
            updateBienes.setString(9, bienes.getStatus());
            updateBienes.setInt(10, bienes.getBienesid());
            updateBienes.executeUpdate();
            updateBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en actualizar: " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void delete(Bienes id) throws Exception {
        String sql = "UPDATE BIENES SET status = 'Baja' WHERE bienesid = ?";
        try {
            PreparedStatement deleteBienes = this.conectar().prepareStatement(sql);
            deleteBienes.setInt(1, id.getBienesid());
            deleteBienes.executeUpdate();
            deleteBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Eliminar" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    @Override
    public void restore(Bienes id) throws Exception {
        String sql = "UPDATE BIENES SET status = 'Alta' WHERE bienesid = ?";
        try {
            PreparedStatement restoreBienes = this.conectar().prepareStatement(sql);
            restoreBienes.setInt(1, id.getBienesid());
            restoreBienes.executeUpdate();
            restoreBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Eliminar" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void getAllPeople() throws Exception {

    }
}
