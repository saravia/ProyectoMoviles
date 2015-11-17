package com.example.saravia.proyectogeolocalizacion;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Integer ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        ID=(Integer) getIntent().getExtras().get("ID");
        Log.i("ID", ID.toString());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng dosDeMayo = new LatLng(-12.046374, -77.0427934);
        LatLng bolognesi = new LatLng(-12.06030544, -77.0415616);
        LatLng posRandom1 = new LatLng(-12.02244752, -77.05248356);
        LatLng posRandom2 = new LatLng(-12.01816614, -77.05336332);
        LatLng posReu = new LatLng(-12.02478756, -77.05208659);

        LatLng myPosLL = new LatLng(-12.01712464, -77.05044508);
        //Markers
        //map.addMarker(new MarkerOptions().position(dosDeMayo).title("Pepito"));
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                        .position(dosDeMayo)
                        .title("Fulano")
                );
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                        .position(bolognesi)
                        .title("Mengano")
                );
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                        .position(posRandom2)
                        .title("Zutano")
                );
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                        .position(posRandom1)
                        .title("Perengano")
                );
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_grupo_16))
                        .position(posReu)
                        .title("Evento 1")
                        .snippet("31 de octubre")
            );
        //Positions
        //map.moveCamera(CameraUpdateFactory.newLatLng(dosDeMayo));
        CameraPosition myPos = new CameraPosition.Builder()
                .target(myPosLL)
                .zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPos));

        //Busca mi posición
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        //checkPermission();
        //Fin mi posicion
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
