package com.example.saravia.proyectogeolocalizacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Inicio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(0000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMainActivity= new Intent(Inicio.this, MainActivity.class);
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
