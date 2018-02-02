package com.burguer.manrique.restabar.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by onbh4 on 23/01/2018.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    // Sample data set. children[i] contains the children (String[]) for
    // groups[i].
    Activity a;
    public ArrayList<String> groupItem, tempChild;
    public HashMap<String, ArrayList<Productos>> lista;
    String ip;
    public MyExpandableListAdapter(Activity a, ArrayList<String> groupItem, HashMap<String, ArrayList<Productos>> lista, String ip){
        this.a=a;
        this.groupItem=groupItem;
        this.lista=lista;
        this.ip=ip;
    }

    public Object getChild(int groupPosition, int childPosition) {
       // return Childtem.get(childPosition);
        return lista.get(groupItem.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    public int getChildrenCount(int groupPosition) {
        return lista.get(groupItem.get(groupPosition)).size();
    }

    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Productos p = (Productos) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.a
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lista_hijo, null);
        }
            ImageView ivHijo = (ImageView)convertView.findViewById(R.id.ivHijoD);
            TextView tvnombre = (TextView)convertView.findViewById(R.id.tvNombreD);
            TextView tvPrecio = (TextView)convertView.findViewById(R.id.tvPrecioD);
            Picasso.with(ivHijo.getContext()).load("http://"+ip+p.getUrlFoto()).into(ivHijo);
            tvnombre.setText(p.getDescripcion());
            tvPrecio.setText(""+p.getPrecio());
         // Toast.makeText(a, groupPosition + " "+ childPosition, Toast.LENGTH_SHORT).show();
        return convertView;
    }

    public Object getGroup(int groupPosition) {
        return groupItem.get(groupPosition);
    }

    public int getGroupCount() {
        return groupItem.size();
    }

    public long getGroupId(int groupPosition) {
        return 0;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Bitmap b = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.a
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_padre, null);
        }
        ImageView ivPadre = (ImageView)convertView.findViewById(R.id.ivPadre);
        TextView tvPadre= (TextView)convertView.findViewById(R.id.tvBebidas);
        String tipo = (String)getGroup(groupPosition);
        if(tipo.equals("BEBIDAS")){
            Picasso.with(a).load(R.drawable.bebidssssss).into(ivPadre);
            tvPadre.setText("BEBIDAS");
        }else if(tipo.equals("TAPAS")){
            Picasso.with(a).load(R.drawable.tapassssssssss).into(ivPadre);
            tvPadre.setText("TAPAS");
        }else if(tipo.equals("PLATOS COMBINADOS")){
            Picasso.with(a).load(R.drawable.combinadosss).into(ivPadre);
            tvPadre.setText("PLATOS COMBINADOS");
        }else if(tipo.equals("POSTRES")){
            Picasso.with(a).load(R.drawable.postressss).into(ivPadre);
            tvPadre.setText("POSTRES");
        }
        // ivPadre.setImageBitmap(b);
        return  convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }


}
