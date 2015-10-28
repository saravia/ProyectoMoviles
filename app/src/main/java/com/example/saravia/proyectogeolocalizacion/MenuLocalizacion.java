package com.example.saravia.proyectogeolocalizacion;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
public class MenuLocalizacion extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menulocalizacion);
        Resources ressources = getResources();

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        // Tab MAPA
        Intent intentAndroid = new Intent().setClass(this, MapsActivity.class);
        TabHost.TabSpec tabSpecAndroid = tabHost
                .newTabSpec("Android")
                .setIndicator("", ressources.getDrawable(R.drawable.mapa))
                .setContent(intentAndroid);
        //Tab CONTACTO
        Intent intentApple = new Intent().setClass(this, MenuAplicacion.class);
        TabHost.TabSpec tabSpecApple = tabHost
                .newTabSpec("Apple")
                .setIndicator("", ressources.getDrawable(R.drawable.contacto))
                .setContent(intentApple);
        //tab CHAT
        Intent intentWindows = new Intent().setClass(this, Registro.class);
        TabHost.TabSpec tabSpecWindows = tabHost
                .newTabSpec("Windows")
                .setIndicator("", ressources.getDrawable(R.drawable.chat))
                .setContent(intentWindows);
        // colocamos Tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecApple);
        tabHost.addTab(tabSpecWindows);
        // que empiese por defecto con el tab 0(MAPA)
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}