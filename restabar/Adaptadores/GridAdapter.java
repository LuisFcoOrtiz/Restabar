package com.burguer.manrique.restabar.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.burguer.manrique.restabar.Pojos.Mesas;
import com.burguer.manrique.restabar.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by onbh4 on 16/01/2018.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Mesas> lista;
    URL imageUrl = null;
    HttpURLConnection conn = null;
    public GridAdapter(Context context, ArrayList<Mesas> lista){
        this.context=context;
        this.lista=lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_mesa_item, viewGroup, false);
        }
        Mesas item = (Mesas) getItem(i);

        TextView tMesa = (TextView)view.findViewById(R.id.mesa);
        TextView tOcupada = (TextView)view.findViewById(R.id.tvOcupada);
        if(item.getOcupada().equals("s")){
            tOcupada.setText("Ocupada");
        }else{
            tOcupada.setText("Libre");
        }

        tMesa.setText("Mesa "+item.getCodigoMesa());
        return view;
    }
}
