package com.example.saravia.proyectogeolocalizacion;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Integer ID;
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> userLista;
    private static String url_get_users =
            "http://valkie.pe.hu/PDM/get_users_name_pos.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TBL_USERS_DATA = "TBL_USERS_DATA";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_LATITUD = "latitud";
    private static final String TAG_LONGITUD = "longitud";
    JSONArray users = null;


    private void cargaPosiciones() {
        class cargaPosiciones extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(MapsActivity.this);
                pDialog.setMessage("Cargando. Por favor espere...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
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
                        users = json.getJSONArray(TAG_TBL_USERS_DATA);
                        for (int i = 0; i < users.length(); i++) {
                            JSONObject c = users.getJSONObject(i);
                            String id = c.getString(TAG_ID);
                            String name = c.getString(TAG_NAME);
                            String latitud = c.getString(TAG_LATITUD);
                            String longitud = c.getString(TAG_LONGITUD);

                            HashMap map = new HashMap();
                            map.put(TAG_ID, id);
                            map.put(TAG_NAME, name);
                            map.put(TAG_LATITUD, latitud);
                            map.put(TAG_LONGITUD, longitud);
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

                for (int i = 0; i < userLista.size(); i++) {
                    double x = Double.parseDouble(userLista.get(i).get(TAG_LATITUD));
                    double y = Double.parseDouble(userLista.get(i).get(TAG_LONGITUD));
                    LatLng pos = new LatLng(x, y);
                    mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                                    .position(pos)
                                    .title(userLista.get(i).get(TAG_NAME))
                    );

//                    CameraPosition myPos = new CameraPosition.Builder()
//                            .target(pos)
//                            .zoom(14).build();
//                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPos));
                }
                //Busca mi posición
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                //checkPermission();

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Por favor active el GPS", Toast.LENGTH_SHORT);
                    toast.show();
                 }
                try{
                    Location location = locationManager.getLastKnownLocation(provider);
                    LatLng myPos=new LatLng(location.getLatitude(),location.getLongitude());
                    CameraPosition myCam = new CameraPosition.Builder()
                            .target(myPos)
                            .zoom(14).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myCam));
                    //getLocation(location);
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }
            }
        }
        cargaPosiciones sendPostReqAsyncTask = new cargaPosiciones();
        sendPostReqAsyncTask.execute();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!isGooglePlayServicesAvailable()) {
//            finish();
//        }
        setContentView(R.layout.mapa);
        ID = (Integer) getIntent().getExtras().get("ID");
        Log.i("ID", ID.toString());
        userLista = new ArrayList<HashMap<String, String>>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //richard 16/12
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, true);
//        //PERMISO PARA OBTENER POSICION!
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//        if (location != null) {
//            onLocationChanged(location);
//        }
        //locationManager.requestLocationUpdates(bestProvider, 2000, 0, activi);

    }


    public void getLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }


    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        cargaPosiciones();
        //Positions
//        map.moveCamera(CameraUpdateFactory.newLatLng(dosDeMayo));
//        CameraPosition myPos = new CameraPosition.Builder()
//                .target(dosDeMayo)
//                .zoom(14).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPos));

        //System.out.println(userLista.get(0).get("latitud"));
        //System.out.println(userLista.size());

        LatLng posReu = new LatLng(-12.02478756, -77.05208659);
        map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_grupo_16))
                        .position(posReu)
                        .title("Evento 1")
                        .snippet("31 de octubre")
        );

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            public void onMapLongClick(LatLng point) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        MapsActivity.this,
                        "Click Largo\n" +
                                "Lat: " + point.latitude + "\n" +
                                "Lng: " + point.longitude + "\n" +
                                "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });

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
