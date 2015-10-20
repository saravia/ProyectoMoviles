package com.example.saravia.proyectogeolocalizacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText pass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText)findViewById(R.id.loginPassword);
        login = (Button)findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String usuario=username.getText().toString();
                String passw=pass.getText().toString();


                if( checklogindata( usuario , passw )==true){
                   Intent i = new Intent(MainActivity.this, Registro.class);
                    startActivity(i);
                  //  System.out.print("hoala");
                }else{
                    err_login();
                }
            }
        });
    }

    public boolean checklogindata(String username ,String password ){

        if 	(username.equals("") || password.equals("")){
            Log.e("Login ui", "checklogindata user or pass error");
            return false;

        }
        else{
           if(username.equals("pepe") && password.equals("pepe"))
                return true;
            else
                return false;
        }

    }

    public void err_login(){
       /* AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Acepta la ejecución de este programa en modo prueba ?");
        dialogo1.setCancelable(false);
        dialogo1.show();*/
        Toast toast1 = Toast.makeText(getApplicationContext(), "Error:Nombre de usuario o password incorrectos", Toast.LENGTH_SHORT);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
