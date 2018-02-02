package com.burguer.manrique.restabar.Pojos;

/**
 * Created by onbh4 on 19/01/2018.
 */

public class LinPedido {
    private int id, codigoProducto, codigoPedido, cantidad;
    private String observaciones;
    public LinPedido(){}

    public LinPedido(int id, int codigoProducto, int codigoPedido, int cantidad, String observaciones) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.codigoPedido = codigoPedido;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
