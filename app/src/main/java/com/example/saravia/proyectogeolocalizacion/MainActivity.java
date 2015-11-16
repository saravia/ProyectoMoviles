package com.example.saravia.proyectogeolocalizacion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText pass;
    private Button login;
    private Button registrar;

    //data login
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> userLista;
    private static String url_get_users =
            "http://valkie.pe.hu/PDM/get_users.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TBL_USERS = "TBL_USERS";
    private static final String TAG_ID = "id";
    private static final String TAG_NOMBRE = "name";
    private static final String TAG_PASSWORD = "password";
    JSONArray users = null;
    //
    //

    class cargaUsuario extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            List params = new ArrayList();
            JSONObject json =
                    jParser.makeHttpRequest(url_get_users, "GET", params);
            Log.d("USER: ", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    users=json.getJSONArray(TAG_TBL_USERS);
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NOMBRE);
                        String password = c.getString(TAG_PASSWORD);

                        HashMap map = new HashMap();
                        map.put(TAG_ID, id);
                        map.put(TAG_NOMBRE, name);
                        map.put(TAG_PASSWORD, password);
                        userLista.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText)findViewById(R.id.loginPassword);
        login = (Button)findViewById(R.id.btnLogin);
        registrar=(Button)findViewById(R.id.btnRegister);
        userLista = new ArrayList<HashMap<String, String>>();

        // Hace Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = username.getText().toString();
                String passw = pass.getText().toString();
                // Verifica el usuario y su password
                new cargaUsuario().execute();
                try{
                    if (checklogindata(usuario, passw) == true) {
                        Intent i = new Intent(MainActivity.this, MenuAplicacion.class);
                        startActivity(i);
                    } else {
                        err_login();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
            // Te envia a que te registres
       registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Registro.class);
                    startActivity(i);

            }
        });
    }

    public boolean checklogindata (String username ,String password ) throws JSONException{
        Context context = getApplicationContext();
        String encryptedPass="";
        int duration = Toast.LENGTH_SHORT;
        try{
            encryptedPass=(Utilitarios.SHA1(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<userLista.size();i++){
            if(username.equals(userLista.get(i).get("name"))){
                if(encryptedPass.equals(userLista.get(i).get("password"))) {
                    CharSequence text = "Bienvenido " + username;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return true;
                }
            }
        }
        return false;

    }

    public void err_login(){
        Toast toast1 = Toast.makeText(getApplicationContext(), "Error: Nombre de usuario o password incorrectos", Toast.LENGTH_SHORT);
        toast1.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
