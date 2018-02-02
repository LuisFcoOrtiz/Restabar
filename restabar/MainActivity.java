package com.burguer.manrique.restabar;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.burguer.manrique.restabar.Adaptadores.AdaptadorR;
import com.burguer.manrique.restabar.Adaptadores.ListAdapter;
import com.burguer.manrique.restabar.Adaptadores.MyExpandableListAdapter;
import com.burguer.manrique.restabar.JSON.DevuelveJson;
import com.burguer.manrique.restabar.Pojos.Mesas;
import com.burguer.manrique.restabar.Pojos.Productos;
import com.burguer.manrique.restabar.Preferences.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rvMesas;
    private String ip, url_consulta="", nombreProd;
    private final int State_Network=1;
    private final int internet=2;
    private JSONArray jSONArray;
    private JSONObject jsonObject = new JSONObject();
    private DevuelveJson devuelveJSON;
    private Productos producto;
    private ExpandableListView lista;
    private Mesas mesa;
    private ListView list;
    private Button bAceptarPedido;
    private ListAdapter listAdapter;
    private Bundle recogeDatos;
    private int codigoCamarero, numMesa;
    private static int codigoMesa=0;
    private ListView lProds;
    private FloatingActionButton fobNuevoP;
    ExpandableListAdapter mAdapter;
    private int ultimaPosicionExpList = -1;
    private String msg;
    private float importe;
    ArrayList<Productos> prodLista;
    private SwipeRefreshLayout refresh;
    ArrayList<Mesas> listaMesas = new ArrayList<>();
    ArrayList<String> groupItem = new ArrayList<String>();
    HashMap<String, ArrayList<Productos>> childItem = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        prodLista= new ArrayList<>();
        fobNuevoP= (FloatingActionButton)findViewById(R.id.fabNuevoP);
        fabSetOnclick();
        list= (ListView)findViewById(R.id.list);
        recogeDatos= getIntent().getExtras();
        codigoCamarero = recogeDatos.getInt("codigo");
        lista= (ExpandableListView) findViewById(R.id.listaProds);
        lista.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (ultimaPosicionExpList != -1 && groupPosition != ultimaPosicionExpList) {
                    lista.collapseGroup(ultimaPosicionExpList);
                }
                ultimaPosicionExpList = groupPosition;
            }
        });
        rvMesas = (RecyclerView) findViewById(R.id.rvMesas);
        lista.setGroupIndicator(null);
        lista.setClickable(true);
        setGroupData();
        final ArrayList<Productos> pr = new ArrayList<>();
        mAdapter = new MyExpandableListAdapter(MainActivity.this,groupItem, childItem, ip);
        lista.setAdapter(mAdapter);
        listaExpOnClick();
        permisos();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        devuelveJSON = new DevuelveJson();
       colorBarraNotificaciones();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        ip= sp.getString("ip","127.0.0.1");
        new RellenarProds().execute();
        new RellenarMesas().execute();

    }

    public void rellenarMesas(){
        new RellenarMesas().execute();
    }
    public void fabSetOnclick(){
        fobNuevoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this,codigoMesa+"",Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = getLayoutInflater();

                View dialoglayout = inflater.inflate(R.layout.dialog_pedidos, null);
                final TextView tvCod = (TextView)dialoglayout.findViewById(R.id.tvIdMesa);
                tvCod.setText(codigoMesa+"");
                lProds = (ListView)dialoglayout.findViewById(R.id.list);
                lProds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int in, long l) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("¿Borrar?");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                prodLista.remove(in);
                                listAdapter= new ListAdapter(MainActivity.this,prodLista);
                                lProds.setAdapter(listAdapter);
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();

                    }
                });
                bAceptarPedido=(Button)dialoglayout.findViewById(R.id.bAceptarPedido);
                bAceptarPedido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("¿Confirmar pedido?");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Productos produc;
                                numMesa=Integer.parseInt(tvCod.getText().toString());
                                new RealizarPedido().execute();
                                for (int z=0;z<lProds.getCount();z++){
                                    produc= (Productos) lProds.getItemAtPosition(z);
                                    nombreProd=produc.getDescripcion();
                                    new LineaPedido().execute(nombreProd);

                                }

                                new RellenarMesas().execute();
                                tvCod.setText("0");
                                prodLista.clear();
                                listAdapter= new ListAdapter(MainActivity.this,prodLista);
                                lProds.setAdapter(listAdapter);

                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }
                });
                listAdapter= new ListAdapter(MainActivity.this,prodLista);
                lProds.setAdapter(listAdapter);
                // final EditText ettipoamigo = (EditText) dialoglayout.findViewById(R.id.etModiTip);

                // Button baceptar = (Button) dialoglayout.findViewById(R.id.bAcepModi);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialoglayout);
                builder.show();
            }
        });

    }
    public void listaExpOnClick(){
        lista.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TextView tv = (TextView)view.findViewById(R.id.tvNombreD);
                TextView tvDin = (TextView)view.findViewById(R.id.tvPrecioD);
                float pre = Float.parseFloat(tvDin.getText().toString());

                Productos p =new Productos(tv.getText().toString(),pre,"");
                prodLista.add(p);
                Toast.makeText(MainActivity.this,tv.getText().toString()+" añadido!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    public void permisos(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, State_Network);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, internet);
    }
    public void colorBarraNotificaciones(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
    }
    public static void setCodigoMesa(int mesa){
        codigoMesa=mesa;
    }
    //----------------------------------------------------------------------------------------------
    public void setGroupData() {
        groupItem.add("BEBIDAS");
        groupItem.add("TAPAS");
        groupItem.add("PLATOS COMBINADOS");
        groupItem.add("POSTRES");
    }
    // class Asyntask
    class RellenarProds extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Bajando datos del servidor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            url_consulta="http://"+ip+"/restabar/querys/select.php";
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
            ArrayList<Productos> bebidas = new ArrayList<Productos>();
            ArrayList<Productos>tapas= new ArrayList<Productos>();
            ArrayList<Productos>combi = new ArrayList<Productos>();
            ArrayList<Productos>postres= new ArrayList<Productos>();

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
                        if(producto.getTipo().equals("bebida")){
                            bebidas.add(producto);
                        }else if (producto.getTipo().equals("tapa")){
                            tapas.add(producto);
                        }else if(producto.getTipo().equals("menu")){
                            combi.add(producto);
                        }else if(producto.getTipo().equals("postre")){
                            postres.add(producto);
                        }
                        //Toast.makeText(ScrollingActivity.this, usuario.getNombre(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                childItem.put(groupItem.get(0), bebidas);
                childItem.put(groupItem.get(1), tapas);
                childItem.put(groupItem.get(2), combi);
                childItem.put(groupItem.get(3), postres);
                mAdapter = new MyExpandableListAdapter(MainActivity.this,groupItem, childItem, ip);
                lista.setAdapter(mAdapter);

            } else {
                Toast.makeText(MainActivity.this, "JSON Array nulo, Introduzca una URL válida o compruebe su conexión",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    class RellenarMesas extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            url_consulta="http://"+ip+"/restabar/querys/select.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("sql","Select * from mesas");
                devuelveJSON= new DevuelveJson();
                jSONArray = devuelveJSON.sendRequest(url_consulta,
                        parametrosPost);
                if (jSONArray != null) {
                    return jSONArray;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(JSONArray json) {
            listaMesas.clear();
            if (pDialog != null && pDialog.isShowing()) {

            }
            if (json != null) {;
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        mesa = new Mesas();
                        mesa.setOcupada(jsonObject.getString("cocupada"));
                        mesa.setCodigoMesa(jsonObject.getInt("ncodmesa"));
                        listaMesas.add(mesa);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                AdaptadorR r = new AdaptadorR(listaMesas,MainActivity.this,ip);
                rvMesas.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                rvMesas.setAdapter(r);

            } else {
                Toast.makeText(MainActivity.this, "JSON Array nulo, Introduzca una URL válida o compruebe su conexión",
                        Toast.LENGTH_LONG).show();
            }

        }
    }
    //----------------------------------------------------------------------------------------------
    class EnviarMensaje extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Enviando Mensaje...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            url_consulta= "http://"+ip+"/restabar/querys/newMensaje.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("codcam",""+codigoCamarero);
                parametrosPost.put("msg",msg);
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
                Toast.makeText(MainActivity.this,"Mensaje enviado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    class RealizarPedido extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Creando pedido...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            url_consulta= "http://"+ip+"/restabar/querys/newPedido.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                System.out.println("-------------------------------------------------------------------------------------"+numMesa);
                parametrosPost.put("cod",""+numMesa);
                parametrosPost.put("mes",""+numMesa);
                parametrosPost.put("cam",""+codigoCamarero);
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
            new RellenarMesas().execute();
        }
    }
    //----------------------------------------------------------------------------------------------
    class LineaPedido extends AsyncTask<String, String, String> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Insertando lineas...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            url_consulta= "http://"+ip+"/restabar/querys/newLinPedido.php";
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("codPed",""+numMesa);
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
            new RellenarMesas().execute();
        }
    }
    //----------------------------------------------------------------------------------------------
    public void setIp(String ip){
        this.ip=ip;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mensaje) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Introduzca Mensaje");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(input.getText().length()==0){
                        Toast.makeText(MainActivity.this, "Escriba un mensaje", Toast.LENGTH_SHORT).show();
                    }else{
                        msg= input.getText().toString();
                        new EnviarMensaje().execute();
                    }

                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();

        } else if (id == R.id.nav_conf) {
            Intent i = new Intent(MainActivity.this, Preferences.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
