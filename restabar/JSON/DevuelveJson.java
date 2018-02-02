package com.burguer.manrique.restabar.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by onbh4 on 16/01/2018.
 */

public class DevuelveJson {
    private HttpURLConnection conn;
    public static final int CONNECTION_TIMEOUT = 20 * 1000;
    public JSONArray sendRequest(String link, HashMap<String, String> values)
            throws JSONException {


        JSONArray jArray = null;
        try {
            URL url = new URL(link);
            String result = "";
            System.out.println("---------------------------------------------------------------AQUI ESTOY");
            System.out.println("---------------------------------------------------------------ERROR 1: "+ result);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(CONNECTION_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            if (values != null) {
                System.out.println("----------------------------------------------------------------ERROR 2: "+ result);

                OutputStream os = conn.getOutputStream();
                OutputStreamWriter osWriter = new OutputStreamWriter(os,
                        "UTF-8");
                BufferedWriter writer = new BufferedWriter(osWriter);
                writer.write(getPostData(values));
                writer.flush();
                writer.close();
                os.close();
            }
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("---------------------------------------------------------------ERROR 3: "+ result);

                InputStream is = conn.getInputStream();
                InputStreamReader isReader = new InputStreamReader(is, "UTF-8");
                BufferedReader reader = new BufferedReader(isReader);

                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                    System.out.println("---------------------------------------------------------------"+ line);
                }
                is.close();
                result=sb.toString();
                System.out.println("-------------------------------------------------------------ERROR 4: "+ result);

                try{
                    jArray = new JSONArray(result);
                    return jArray;
                } catch(JSONException e){
                    Log.e("ERROR => ", "Error convirtiendo los datos a JSON : " +
                            e.toString());
                    e.printStackTrace();
                    return null;
                }
            }
        }
        catch (MalformedURLException e) {
            System.out.println("---------------------------------------------------------------AQUI ESTOY"+ e.getMessage());
        }
        catch (IOException e) {
            System.out.println("---------------------------------------------------ERROR;:" + e.getMessage());

        }
        return jArray;
    }
    public String getPostData(HashMap<String, String> values) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (first)
                first = false;
            else
                builder.append("&");
            try {
                builder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            catch (UnsupportedEncodingException e) {}
        }
        return builder.toString();
    }
}
