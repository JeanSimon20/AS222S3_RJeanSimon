package vg.model;

public class Bienes {
    private Integer bienesid;
    private String cantidad;
    private String code;
    private String detalle;
    private String valorlibro;
    private String fecha_ingreso;
    private String fecha_depreciacion;
    private String depreciacion_anual;
    private String depreciacion_mensual;
    private String depreciacion_acumulada;
    private String status;

    public Bienes() {
    }

    @Override
    public String toString() {
        return "Bienes{" +
                "bienesid=" + bienesid +
                ", cantidad='" + cantidad + '\'' +
                ", code='" + code + '\'' +
                ", detalle='" + detalle + '\'' +
                ", valorlibro='" + valorlibro + '\'' +
                ", fecha_ingreso='" + fecha_ingreso + '\'' +
                ", fecha_depreciacion='" + fecha_depreciacion + '\'' +
                ", depreciacion_anual='" + depreciacion_anual + '\'' +
                ", depreciacion_mensual='" + depreciacion_mensual + '\'' +
                ", depreciacion_acumulada='" + depreciacion_acumulada + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Bienes(Integer bienesid, String cantidad, String code, String detalle, String valorlibro, String fecha_ingreso, String fecha_depreciacion, String depreciacion_anual, String depreciacion_mensual, String depreciacion_acumulada, String status) {
        this.bienesid = bienesid;
        this.cantidad = cantidad;
        this.code = code;
        this.detalle = detalle;
        this.valorlibro = valorlibro;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_depreciacion = fecha_depreciacion;
        this.depreciacion_anual = depreciacion_anual;
        this.depreciacion_mensual = depreciacion_mensual;
        this.depreciacion_acumulada = depreciacion_acumulada;
        this.status = status;
    }

    public Integer getBienesid() {
        return bienesid;
    }

    public void setBienesid(Integer bienesid) {
        this.bienesid = bienesid;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getValorlibro() {
        return valorlibro;
    }

    public void setValorlibro(String valorlibro) {
        this.valorlibro = valorlibro;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_depreciacion() {
        return fecha_depreciacion;
    }

    public void setFecha_depreciacion(String fecha_depreciacion) {
        this.fecha_depreciacion = fecha_depreciacion;
    }

    public String getDepreciacion_anual() {
        return depreciacion_anual;
    }

    public void setDepreciacion_anual(String depreciacion_anual) {
        this.depreciacion_anual = depreciacion_anual;
    }

    public String getDepreciacion_mensual() {
        return depreciacion_mensual;
    }

    public void setDepreciacion_mensual(String depreciacion_mensual) {
        this.depreciacion_mensual = depreciacion_mensual;
    }

    public String getDepreciacion_acumulada() {
        return depreciacion_acumulada;
    }

    public void setDepreciacion_acumulada(String depreciacion_acumulada) {
        this.depreciacion_acumulada = depreciacion_acumulada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
