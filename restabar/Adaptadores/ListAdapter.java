package com.burguer.manrique.restabar.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by onbh4 on 25/01/2018.
 */

public class ListAdapter extends BaseAdapter {
    private Activity a;
    private ArrayList<Productos> prod;
    public ListAdapter(Activity a, ArrayList<Productos> prod){
        this.a=a;
        this.prod=prod;
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
            v = inf.inflate(R.layout.lista_dialog, null);
        }
        TextView tnom=(TextView)v.findViewById(R.id.tvNombre);
        TextView tpre =(TextView)v.findViewById(R.id.tvPrecio);
        tnom.setText(p.getDescripcion());
        tpre.setText(p.getPrecio()+"");
        return v;
    }
}
