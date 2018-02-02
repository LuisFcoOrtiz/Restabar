package com.burguer.manrique.restabar.Pojos;

/**
 * Created by onbh4 on 19/01/2018.
 */

public class Camareros {
    private int codigoCamarero;
    private String nombreCamarero, puesto, fechaInicio, telefono, correo, contra;
    public Camareros(){}

    public Camareros(int codigoCamarero, String nombreCamarero, String puesto, String fechaInicio, String telefono, String correo, String contra) {
        this.codigoCamarero = codigoCamarero;
        this.nombreCamarero = nombreCamarero;
        this.puesto = puesto;
        this.fechaInicio = fechaInicio;
        this.telefono = telefono;
        this.correo = correo;
        this.contra=contra;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getCodigoCamarero() {
        return codigoCamarero;
    }

    public void setCodigoCamarero(int codigoCamarero) {
        this.codigoCamarero = codigoCamarero;
    }

    public String getNombreCamarero() {
        return nombreCamarero;
    }

    public void setNombreCamarero(String nombreCamarero) {
        this.nombreCamarero = nombreCamarero;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
