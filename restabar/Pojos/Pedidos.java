package com.burguer.manrique.restabar.Pojos;

/**
 * Created by onbh4 on 19/01/2018.
 */

public class Pedidos {
    private int codigoProducto,codigoMesa, codigoCamarero;
    private String pagado, fechaPedido;
    public Pedidos(){}

    public Pedidos(int codigoProducto, int codigoMesa, int codigoCamarero, String pagado, String fechaPedido) {
        this.codigoProducto = codigoProducto;
        this.codigoMesa = codigoMesa;
        this.codigoCamarero = codigoCamarero;
        this.pagado = pagado;
        this.fechaPedido = fechaPedido;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(int codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public int getCodigoCamarero() {
        return codigoCamarero;
    }

    public void setCodigoCamarero(int codigoCamarero) {
        this.codigoCamarero = codigoCamarero;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
