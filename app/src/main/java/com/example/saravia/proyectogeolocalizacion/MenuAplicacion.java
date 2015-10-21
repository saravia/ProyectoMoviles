package com.example.saravia.proyectogeolocalizacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAplicacion extends Activity {
    private Button btnShowLocation;
    private Button salir;
    private TextView txtName;
    private TextView txtEmail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupresentacion);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);

        String name = "pepito";
        String email = "pepito@uni.edu.pe";
        txtName.setText(name);
        txtEmail.setText(email);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        salir= (Button) findViewById(R.id.btnSalir);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ConfGPS gps = new ConfGPS(MenuAplicacion.this);
                if(gps.canGetLocation()){
                    Toast.makeText(getApplicationContext(), "GPS Activo", Toast.LENGTH_LONG).show();
                }else{
                    gps.showSettingsAlert();
                }

            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MenuAplicacion.this, MainActivity.class);
                startActivity(i);

            }
        });
    }

}