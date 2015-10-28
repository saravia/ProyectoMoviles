package com.example.saravia.proyectogeolocalizacion;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MenuEvento extends AppCompatActivity{
    private Button btnIrCrearEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuevent);

        btnIrCrearEvent = (Button)findViewById(R.id.btnIrCrearEvent);

        btnIrCrearEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Intent i = new Intent(MenuEvento.this,CrearEvento.class);
                    startActivity(i);

            }
        });


    }


}

