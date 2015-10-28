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
    private Button btnIrEven;
    private Button btnIrCont;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuapp);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);

        String name = "pepito";
        String email = "pepito@uni.edu.pe";
        txtName.setText(name);
        txtEmail.setText(email);
        btnIrEven=(Button) findViewById(R.id.btnIrEven);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnIrCont=(Button)findViewById(R.id.btnIrCont);
        salir= (Button) findViewById(R.id.btnSalir);
        btnIrEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MenuAplicacion.this, MenuEvento.class);
                startActivity(i);

            }
        });


        btnIrCont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuAplicacion.this,MenuLocalizacion.class);
                startActivity(i);

            }
        });
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