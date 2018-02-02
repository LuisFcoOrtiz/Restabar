package com.burguer.manrique.restabar.Adaptadores;


import android.app.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.burguer.manrique.restabar.Adds.ImageHelper;

import com.burguer.manrique.restabar.Interfaces.ClickListener;
import com.burguer.manrique.restabar.JSON.DevuelveJson;
import com.burguer.manrique.restabar.MainActivity;
import com.burguer.manrique.restabar.Pojos.Mesas;
import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by onbh4 on 07/12/2017.
 */

public class AdaptadorR extends RecyclerView.Adapter<AdaptadorR.AdaptadorViewHolder> {
    public ArrayList<Mesas> lista;
    private static MainActivity activity;
    private ClickListener listener;
    private static String ip;
    private static DevuelveJson devuelveJSON;
    static ArrayList<Productos> prod;
    static ArrayList<Productos> prodDos= new ArrayList<>();
    private static JSONObject jsonObject = new JSONObject();
    static ListAdapterDos listAdapterDos;
    private static Productos producto;
    private static JSONArray jSONArray;
    private static String nombreProd;
    private static int numMesa;
    static float importe=0;
    private static boolean ocupar=false;
    public AdaptadorR(ArrayList<Mesas> lista,MainActivity activity, String ip) {
        this.lista=lista;
        this.activity=activity;
        this.listener=listener;
        this.ip=ip;
    }
    public void colocarImagenRedondeada(Bitmap myBitmap, ImageView imagev){
        // Llama al método encargado de cortar en forma cuadrada a la imagen.
        Bitmap croppedImage = ImageHelper.cropBitmapToSquare(myBitmap);

        // Llama al método encargado de redondear las esquinas de la imagen
        // previamente cortada. Recibe como parámetros el mapa de bits y el tamaño // de sus lados en pixeles.
        Bitmap roundedCornersImage = ImageHelper.getRoundedCornerBitmap(
                croppedImage, 600);


        //Asigna el mapa de bits resultante a la vista ImageView que lo mostrará.
        imagev.setImageBitmap(roundedCornersImage);
    }
    @Override
    public AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_mesa, parent, false);
                AdaptadorViewHolder avh = new AdaptadorViewHolder(itemView, activity, lista);

                return avh;
    }
    @Override
    public void onBindViewHolder(AdaptadorViewHolder holder, final int position) {
                Mesas m= lista.get(position);
        /*try {
            imageUrl = new URL(p.getUrlFoto());
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

            Bitmap imagenn = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            colocarImagenRedondeada(imagenn, holder.ivRecycler);
          //  holder.ivRecycler.setImageBitmap(imagenn);

        } catch (IOException e) {

            e.printStackTrace();

        }*/
        if(m.getOcupada().equals("s")){
            holder.tvMesaOc.setText("Ocupada");
            Picasso.with(activity).load(R.drawable.defimesados).into(holder.ivRecycler);
        }else{
            holder.tvMesaOc.setText("Libre");
        }
        holder.tvCod.setText(m.getCodigoMesa()+"");

        Picasso.with(activity).load(R.drawable.defimesa).into(holder.ivRecycler);


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public static class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MainActivity activity;
        private ImageView ivRecycler;
        private TextView tvCod, tvMesaOc;
        private Button bVer;
        private WeakReference<ClickListener> listenerRef;
        public AdaptadorViewHolder(final View itemView, final MainActivity activity, final ArrayList<Mesas> lista) {
            super(itemView);
            this.activity = activity;
            ivRecycler= (ImageView) itemView.findViewById(R.id.ivMesas);
            tvCod = (TextView)itemView.findViewById(R.id.tvCodMesa);
            tvMesaOc = (TextView)itemView.findViewById(R.id.tvMesaLibre);
            bVer=(Button)itemView.findViewById(R.id.bVer);
            bVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new RellenarProds().execute(tvCod.getText().toString());
                    String[] opc = new String[]{"Ver pedido"};
                    //Toast.makeText(getApplicationContext(),
                    //       "pos: " + pos, Toast.LENGTH_SHORT).show();
                    AlertDialog opciones = new AlertDialog.Builder(
                            activity)
                            .setTitle("Opciones")
                            .setItems(opc,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int selected) {
                                            if (selected == 0) {

                                                LayoutInflater inflater = activity.getLayoutInflater();

                                                  View dialoglayout = inflater.inflate(R.layout.dialog_ver_pedidos, null);
                                                  AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                                  final ListView listaVerPed= (ListView)dialoglayout.findViewById(R.id.listaVerPedido);
                                                  Button bPagar=(Button)dialoglayout.findViewById(R.id.bPagarFac);
                                                  Button bAdd=(Button)dialoglayout.findViewById(R.id.bAddprods);
                                                  Button bRefresh=(Button)dialoglayout.findViewById(R.id.bActualizar);
                                                  bRefresh.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          new BorrarLineas().execute(tvCod.getText().toString());
                                                          numMesa=Integer.parseInt(tvCod.getText().toString());
                                                          Productos produc;
                                                          for(int j=0;j<prod.size();j++){
                                                              produc=prod.get(j);
                                                              nombreProd=produc.getDescripcion();

                                                              new LineaPedido().execute(nombreProd);
                                                          }
                                                      }
                                                  });
                                                  bAdd.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          prodDos.clear();
                                                          new RellenarProdsDos().execute();
                                                          LayoutInflater inflaterD = activity.getLayoutInflater();
                                                          View dialoglayoutD = inflaterD.inflate(R.layout.dialog_prods_adapteer, null);
                                                          AlertDialog.Builder builderD = new AlertDialog.Builder(activity);
                                                          ListView listaAdapter = (ListView)dialoglayoutD.findViewById(R.id.listaProdsDos);
                                                          listaAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                              @Override
                                                              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                                  prod.add((Productos) prodDos.get(i));
                                                                  Productos prrr= prodDos.get(i);
                                                                  Toast.makeText(activity, prrr.getDescripcion()+" añadido!!", Toast.LENGTH_SHORT).show();
                                                                  ListAdapter adap= new ListAdapter(activity,prod);
                                                                  listaVerPed.setAdapter(adap);
                                                              }
                                                          });
                                                          listAdapterDos= new ListAdapterDos(activity,prodDos, ip);
                                                            listaAdapter.setAdapter(listAdapterDos);
                                                          builderD.setView(dialoglayoutD);
                                                          builderD.show();
                                                      }
                                                  });
                                                  final Productos[] pro = new Productos[1];
                                                  bPagar.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          importe=0;
                                                          for(int i=0; i<prod.size();i++){
                                                              pro[0] =prod.get(i);
                                                              importe=importe+pro[0].getPrecio();
                                                          }
                                                          //Toast.makeText(activity, "aaa"+importe, Toast.LENGTH_SHORT).show();
                                                          AlertDialog opciones = new AlertDialog.Builder(
                                                                  activity)
                                                                  .setTitle("Importe "+importe+" €")
                                                                  .setItems(new String[]{"Aceptar", "Cancelar"},
                                                                          new DialogInterface.OnClickListener() {
                                                                              public void onClick(DialogInterface dialog,
                                                                                                  int selected) {
                                                                                  if (selected == 0) {
                                                                                      new PagarImporte().execute(tvCod.getText().toString());
                                                                                      activity.rellenarMesas();
                                                                                      prod.clear();
                                                                                      ListAdapter adap= new ListAdapter(activity,prod);
                                                                                      listaVerPed.setAdapter(adap);

                                                                                  }
                                                                              }
                                                                          }).create();
                                                          opciones.show();

                                                      }
                                                  });
                                                  ListAdapter adap= new ListAdapter(activity,prod);
                                                  listaVerPed.setAdapter(adap);
                                                  builder.setView(dialoglayout);
                                                  builder.show();
                                            }
                                        }
                                    }).create();
                    opciones.show();
                }
            });
            ivRecycler.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    Mesas m = lista.get(pos);

                    if(tvMesaOc.getCurrentTextColor()==Color.WHITE && ocupar==false){
                        Picasso.with(activity).load(R.drawable.defimesa).into(ivRecycler);
                        tvCod.setTextColor(Color.BLACK);
                        tvMesaOc.setTextColor(Color.BLACK);
                        tvMesaOc.setText("Libre");
                        new MainActivity().setCodigoMesa(0);
                        ocupar=true;
                        return false;
                    }
                    if(m.getOcupada().equals("s")){
                        Toast.makeText(ivRecycler.getContext(), "Mesa ocupada",Toast.LENGTH_SHORT).show();
                    }else if(ocupar==true){
                        Picasso.with(activity).load(R.drawable.defimesados).into(ivRecycler);
                        tvCod.setTextColor(Color.WHITE);
                        tvMesaOc.setTextColor(Color.WHITE);
                        tvMesaOc.setText("Ocupada");
                        ocupar=false;
                        new MainActivity().setCodigoMesa(m.getCodigoMesa());
                    }
                    return false;
                }
            });

        }

        @Override
        public void onClick(View view) {


        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }
    static class PagarImporte extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Pagando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url_consulta= "http://"+ip+"/restabar/querys/pagarPedido.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("codMesa",strings[0]);
                //parametrosPost.put("import","0");
                devuelveJSON= new DevuelveJson();
                //System.out.println("------------------------------------------------------------"+ parametrosPost.get("consulta"));
                devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);

                System.out.println("-------------------------------------------------------------------"+ url_consulta);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "si";
        }
        protected void onPostExecute(String s) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }


        }
    }
    //----------------------------------------------------------------------------------------------
    static class RellenarProds extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        private JSONArray jSONArray;
        private JSONObject jsonObject = new JSONObject();
        private Productos producto;
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Bajando datos del servidor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            String url_consulta="http://"+ip+"/restabar/querys/select.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("sql","Select * from v_pedido_completo where ncodmesa="+strings[0]);
                devuelveJSON= new DevuelveJson();
                System.out.println("------------------------------------------------------------"+ parametrosPost.get("consulta"));
                jSONArray = devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);
                System.out.println("-------------------------------------------------------------------"+ url_consulta);
                if (jSONArray != null) {
                    return jSONArray;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(JSONArray json) {
            prod = new ArrayList<Productos>();
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        producto= new Productos();
                        producto.setCodigoProducto(jsonObject.getInt("ncodprod"));
                        producto.setPrecio((float)jsonObject.getDouble("nprecio"));
                        producto.setDescripcion(jsonObject.getString("cdesc"));
                        prod.add(producto);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(activity, "JSON Array nulo, Introduzca una URL válida o compruebe su conexión",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    static class RellenarProdsDos extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Bajando datos del servidor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            String url_consulta = "http://" + ip + "/restabar/querys/select.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("sql","Select * from productos");
                devuelveJSON= new DevuelveJson();
                System.out.println("------------------------------------------------------------"+ parametrosPost.get("consulta"));
                jSONArray = devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);
                System.out.println("-------------------------------------------------------------------"+ url_consulta);
                if (jSONArray != null) {
                    return jSONArray;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(JSONArray json) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        producto= new Productos();
                        producto.setUrlFoto(jsonObject.getString("curlfoto"));
                        producto.setTipo(jsonObject.getString("ctipoprod"));
                        producto.setStock((float)jsonObject.getDouble("nstock"));
                        producto.setPrecio((float)jsonObject.getDouble("nprecio"));
                        producto.setDescripcion(jsonObject.getString("cdesc"));

                        prodDos.add(producto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(activity, "JSON Array nulo, Introduzca una URL válida o compruebe su conexión",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    static class BorrarLineas extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Actualizando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url_consulta = "http://" + ip + "/restabar/querys/deleteLineasPed.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("codPed",""+strings[0]);
                devuelveJSON= new DevuelveJson();
                //System.out.println("------------------------------------------------------------"+ parametrosPost.get("consulta"));
                devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);

                System.out.println("-------------------------------------------------------------------"+ url_consulta);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "si";
        }
        protected void onPostExecute(String s) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    static class LineaPedido extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Insertando lineas...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url_consulta = "http://" + ip + "/restabar/querys/newLinPedido.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("codPed",numMesa+"");
                parametrosPost.put("cdesc",strings[0]);
                System.out.println("-----------AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa--------"+strings[0]);
                parametrosPost.put("cant","1");
                parametrosPost.put("obs","");
                devuelveJSON= new DevuelveJson();
                //System.out.println("------------------------------------------------------------"+ parametrosPost.get("consulta"));
                devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);

                System.out.println("-------------------------------------------------------------------"+ url_consulta);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "si";
        }
        protected void onPostExecute(String s) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
    }


