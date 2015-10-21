package com.example.saravia.proyectogeolocalizacion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuAplicacion extends Activity {
    Button btnShowLocation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
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
    }

}