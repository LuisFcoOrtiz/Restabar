package com.burguer.manrique.restabar;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.burguer.manrique.restabar.JSON.DevuelveJson;
import com.burguer.manrique.restabar.Pojos.Productos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Pedido_Activity extends AppCompatActivity {
    private RecyclerView rvCajonPedido;
    private GridView grBebidas, grTapas;
    private ScrollView scroll;
    private String url_consulta="";
    private final int State_Network=1;
    private final int internet=2;
    private JSONArray jSONArray;
    private JSONObject jsonObject = new JSONObject();
    private DevuelveJson devuelveJSON;
    private Productos producto;
    private String ip="192.168.1.145";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        grBebidas= (GridView)findViewById(R.id.grBebidas);
        grTapas=(GridView)findViewById(R.id.grTapas);
        scroll = (ScrollView)findViewById(R.id.scroll);
        url_consulta="http://192.168.1.145/restabar/querys/select.php";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new ListaProductos().execute();
        /*listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));
        listaBebidas.add(new Productos(1, "Cocacola","",10,1));*/
      /*  GridAdapterProductos grp = new GridAdapterProductos(Pedido_Activity.this,listaBebidas);
        grBebidas.setAdapter(grp);*/
        scroll.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.grBebidas).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        grBebidas.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
       // grTapas.setAdapter(grp);
        grTapas.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        rvCajonPedido= (RecyclerView) findViewById(R.id.rvCajonPedidos);
        grBebidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            ArrayList<Productos> lista = new ArrayList<>();

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Productos p = (Productos) grBebidas.getItemAtPosition(i);
                Toast.makeText(Pedido_Activity.this, p.getUrlFoto(), Toast.LENGTH_SHORT).show();
                lista.add(p);
                //AdaptadorR rva = new AdaptadorR(lista, Pedido_Activity.this);
                rvCajonPedido.setLayoutManager(new LinearLayoutManager(Pedido_Activity.this, LinearLayoutManager.HORIZONTAL,false ));
               // rvCajonPedido.setAdapter(rva);
            }
        });



    }//---------------------------------------------------------------------------------------------
    class ListaProductos extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(Pedido_Activity.this);
            pDialog.setMessage("Bajando datos del servidor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){}
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
            ArrayList<Productos> listaBebidas= new ArrayList<>();
            ArrayList<Productos> listaTapas= new ArrayList<>();
            ArrayList<Productos> listaCombinados= new ArrayList<>();
            ArrayList<Productos> listaPostres= new ArrayList<>();

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                ProgressDialog pDialog1;
                pDialog1 = new ProgressDialog(Pedido_Activity.this);
                pDialog1.setMessage("Cargando, por favor espere...");
                pDialog1.setIndeterminate(false);
                pDialog1.setCancelable(true);
                pDialog1.show();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        producto = new Productos();
                        producto.setCodigoProducto(jsonObject.getInt("ncodprod"));
                        producto.setDescripcion(jsonObject.getString("cdesc"));
                        producto.setPrecio((float) jsonObject.getDouble("nprecio"));
                        producto.setStock((float) jsonObject.getDouble("nstock"));
                        producto.setTipo(jsonObject.getString("ctipoprod"));
                        producto.setUrlFoto("http://"+ip+jsonObject.getString("curlfoto"));
                        if(producto.getTipo().equals("bebida")) {
                            listaBebidas.add(producto);
                        }
                        if(producto.getTipo().equals("tapa")){
                            listaTapas.add(producto);
                        }
                        //Toast.makeText(ScrollingActivity.this, usuario.getNombre(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /*GridAdapterProductos ga= new GridAdapterProductos(Pedido_Activity.this, listaBebidas);
                grBebidas.setAdapter(ga);
                pDialog1.dismiss();*/
            } else {
                Toast.makeText(Pedido_Activity.this, "JSON Array nulo, Introduzca una URL válida o compruebe su conexión",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
}
