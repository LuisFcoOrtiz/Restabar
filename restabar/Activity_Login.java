package com.burguer.manrique.restabar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.burguer.manrique.restabar.JSON.DevuelveJson;
import com.burguer.manrique.restabar.Pojos.Camareros;
import com.burguer.manrique.restabar.Pojos.Mesas;
import com.burguer.manrique.restabar.Preferences.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity_Login extends AppCompatActivity {
    private Button bAcceder;
    private TextView etPass, etNombre;
    private String url_consulta="";
    private final int State_Network=1;
    private final int internet=2;
    private JSONArray jSONArray;
    private JSONObject jsonObject = new JSONObject();
    private DevuelveJson devuelveJSON;
    private String ip = "";
    private Camareros camarero;
    private Button bCOnf;
    private String nombre, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //change notification bar color

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        //variables
      bCOnf= (Button)findViewById(R.id.bConf);
        bCOnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_Login.this, Preferences.class);
                startActivity(i);
            }
        });

        etPass=(TextView)findViewById(R.id.etPass);
        etNombre=(TextView)findViewById(R.id.etNombre);
        bAcceder=(Button)findViewById(R.id.bAcceder);
        bAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombre.getText().length()==0 || etPass.getText().length()==0){
                    Toast.makeText(Activity_Login.this, "Rellene los campos vacíos", Toast.LENGTH_SHORT).show();
                }else{
                    obtenerIp();
                   pass= etPass.getText().toString();
                    nombre= etNombre.getText().toString();
                    url_consulta="http://"+ip+"/restabar/querys/select.php";
                    System.out.println("-------------------------------------------------------------------------------------------"+url_consulta);
                    new ComprobarUsuario().execute();
                   /* Intent in = new Intent(Activity_Login.this, MainActivity.class);
                    startActivity(in);*/
                }

            }
        });

    }
    public void obtenerIp(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        ip= sp.getString("ip","127.0.0.1");
    }
    //----------------------------------------------------------------------------------------------
    class ComprobarUsuario extends AsyncTask<String, String, JSONArray> {//clase de asyntask
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(Activity_Login.this);
            pDialog.setMessage("Comprobando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("sql","Select * from camareros");
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
            ArrayList<Camareros> lista= new ArrayList<>();

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        camarero= new Camareros();
                        camarero.setCodigoCamarero(jsonObject.getInt("ncodcam"));
                        camarero.setCorreo(jsonObject.getString("ccorreo"));
                        camarero.setTelefono(jsonObject.getString("tfno"));
                        camarero.setContra(jsonObject.getString("cpasscam"));
                        camarero.setFechaInicio(jsonObject.getString("dfecIni"));
                        camarero.setNombreCamarero(jsonObject.getString("cnomcam"));
                        camarero.setPuesto(jsonObject.getString("cpuesto"));

                        lista.add(camarero);
                        //Toast.makeText(ScrollingActivity.this, usuario.getNombre(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //GridAdapter ga= new GridAdapter(MainActivity.this, lista);
                for(int i =0; i<lista.size(); i++){
                    Camareros c = lista.get(i);
                    if(!pass.equals(c.getContra())&&!nombre.equals(c.getNombreCamarero())){


                    }else{
                        Intent in = new Intent(Activity_Login.this, MainActivity.class);
                        in.putExtra("codigo", c.getCodigoCamarero());
                        startActivity(in);
                    }
                }


            } else {
                Toast.makeText(Activity_Login.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
