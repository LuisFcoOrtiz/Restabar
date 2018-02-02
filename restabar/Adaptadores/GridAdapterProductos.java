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
import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by onbh4 on 16/01/2018.
 */

public class GridAdapterProductos extends BaseAdapter {
    private Context context;
    private ArrayList<Productos> lista;
    URL imageUrl = null;
    HttpURLConnection conn = null;
    public GridAdapterProductos(Context context, ArrayList<Productos> lista){
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
            view = inflater.inflate(R.layout.grid_producto_item, viewGroup, false);
        }
        Productos item = (Productos)getItem(i);
        ImageView imagen = (ImageView)view.findViewById(R.id.ivProducto);
        if(item.getTipo().equals("bebida")) {
            try {

                imageUrl = new URL(item.getUrlFoto());
                conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

                Bitmap imagenn = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
                imagen.setImageBitmap(imagenn);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }
        return view;
    }
}
