package com.burguer.manrique.restabar.Pojos;

/**
 * Created by onbh4 on 19/01/2018.
 */

public class Mesas {
    private int codigoMesa;
    private String ocupada;
    public Mesas(){}

    public Mesas(int codigoMesa, String ocupada) {
        this.codigoMesa = codigoMesa;
        this.ocupada = ocupada;
    }

    public int getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(int codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public String getOcupada() {
        return ocupada;
    }

    public void setOcupada(String ocupada) {
        this.ocupada = ocupada;
    }
}
