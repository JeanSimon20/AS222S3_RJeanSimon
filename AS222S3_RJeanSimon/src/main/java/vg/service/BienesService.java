package vg.service;

import vg.db.AccesoDB;
import vg.model.Bienes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BienesService extends AccesoDB implements IBienesService{

    private static final String SQL_SELECT_BY_ID = "SELECT bienesid, cantidad, code, detalle, valorlibro, fecha_ingreso, fecha_depreciacion, depreciacion_anual, depreciacion_mensual, depreciacion_acumulada, status \n" +
            "FROM BIENES \n" +
            "WHERE bienesid = ?\n";
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
        List<Bienes> ListAllActive = new ArrayList<>();
        Bienes bienes;
        String sql = "Select * from BIENES where BIENES.status = 'Alta' Order by bienesid desc";
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
            System.out.println("Error en traer la lista getAllActive" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return ListAllActive;
    }


    @Override
    public List<Bienes> getAllInactive() throws Exception {
        List<Bienes> ListAllInactive = new ArrayList<>();
        Bienes bienes;
        String sql = "Select * from BIENES where BIENES.status = 'Baja' Order by detalle";
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
                ListAllInactive.add(bienes);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en traer la lista getAllInactive" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return ListAllInactive;
    }
    @Override
    public Bienes getForId(Integer id) {
        Bienes bienes = null;
        try (Connection conn = AccesoDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception ex) {
            Logger.getLogger(BienesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bienes;
    }


    @Override
    public void insert(Bienes bienes) throws Exception {
        String sql = "INSERT INTO bienes (cantidad, code, detalle, valorlibro, fecha_ingreso, fecha_depreciacion, status) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement insertBienes = this.conectar().prepareStatement(sql);
            insertBienes.setString(1, bienes.getCantidad());
            insertBienes.setString(2, bienes.getCode());
            insertBienes.setString(3, bienes.getDetalle());
            insertBienes.setString(4, bienes.getValorlibro());
            insertBienes.setString(5, bienes.getFecha_ingreso());
            insertBienes.setString(6, bienes.getFecha_depreciacion());
            insertBienes.setString(7, bienes.getStatus());
            insertBienes.executeUpdate();
            insertBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    @Override
    public void update(Bienes bienes) throws Exception {
        String sql = "UPDATE BIENES SET cantidad = ?, code = ?, detalle = ?, valorlibro = ?, fecha_ingreso = ?, fecha_depreciacion = ?, status = ? WHERE bienesid = ?";
        try {
            PreparedStatement updateBienes = this.conectar().prepareStatement(sql);
            updateBienes.setString(1, bienes.getCantidad());
            updateBienes.setString(2, bienes.getCode());
            updateBienes.setString(3, bienes.getDetalle());
            updateBienes.setString(4, bienes.getValorlibro());
            updateBienes.setString(5, bienes.getFecha_ingreso());
            updateBienes.setString(6, bienes.getFecha_depreciacion());
            updateBienes.setString(7, bienes.getStatus());
            updateBienes.setInt(8, bienes.getBienesid());
            updateBienes.executeUpdate();
            updateBienes.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en actualizar" + e.getMessage());
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
}
