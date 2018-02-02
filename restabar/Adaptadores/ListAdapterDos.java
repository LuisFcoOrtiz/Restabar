package com.burguer.manrique.restabar.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by onbh4 on 01/02/2018.
 */

public class ListAdapterDos extends BaseAdapter {
    private Activity a;
    private ArrayList<Productos> prod;
    private String ip;
    public ListAdapterDos(Activity a, ArrayList<Productos> prod, String ip){
        this.a=a;
        this.prod=prod;
        this.ip=ip;
    }
    @Override
    public int getCount() {
        return prod.size();
    }

    @Override
    public Object getItem(int i) {
        return prod.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;
        Productos p =(Productos) getItem(i);
        if(view ==null){
            LayoutInflater inf = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista_hijo, null);
        }
        ImageView ivHijo = (ImageView)v.findViewById(R.id.ivHijoD);
        TextView tvnombre = (TextView)v.findViewById(R.id.tvNombreD);
        TextView tvPrecio = (TextView)v.findViewById(R.id.tvPrecioD);
        tvnombre.setText(p.getDescripcion());
        tvPrecio.setText(""+p.getPrecio());
        Picasso.with(ivHijo.getContext()).load("http://"+ip+p.getUrlFoto()).into(ivHijo);
        return v;
    }
}

