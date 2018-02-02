package com.burguer.manrique.restabar.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by onbh4 on 19/01/2018.
 */

public class Productos implements Parcelable {
    private int codigoProducto;
    private String descripcion, urlFoto, tipo;
    private float stock, precio;

    public Productos(int codigoProducto, String descripcion,String urlFoto,String tipo, float stock, float precio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.urlFoto=urlFoto;
        this.precio = precio;
        this.tipo=tipo;
    }
    public Productos(String descripcion, float precio, String urlFoto){
        this.descripcion=descripcion;
        this.precio=precio;
        this.urlFoto=urlFoto;
    }
    public Productos(){}

    protected Productos(Parcel in) {
        codigoProducto = in.readInt();
        descripcion = in.readString();
        urlFoto = in.readString();
        tipo = in.readString();
        stock = in.readFloat();
        precio = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codigoProducto);
        dest.writeString(descripcion);
        dest.writeString(urlFoto);
        dest.writeString(tipo);
        dest.writeFloat(stock);
        dest.writeFloat(precio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Productos> CREATOR = new Creator<Productos>() {
        @Override
        public Productos createFromParcel(Parcel in) {
            return new Productos(in);
        }

        @Override
        public Productos[] newArray(int size) {
            return new Productos[size];
        }
    };

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
