package com.example.saravia.proyectogeolocalizacion;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.AsyncTask;
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

                for(int i=0;i<userLista.size();i++){
                    double x= Double.parseDouble(userLista.get(i).get(TAG_LATITUD));
                    double y= Double.parseDouble(userLista.get(i).get(TAG_LONGITUD));
                    LatLng pos = new LatLng(x, y);
                    mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user_16))
                                    .position(pos)
                                    .title(userLista.get(i).get(TAG_NAME))
                    );

                    CameraPosition myPos = new CameraPosition.Builder()
                            .target(pos)
                            .zoom(14).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPos));
                }
            }
        }
        cargaPosiciones sendPostReqAsyncTask = new cargaPosiciones();
        sendPostReqAsyncTask.execute();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        ID=(Integer) getIntent().getExtras().get("ID");
        Log.i("ID", ID.toString());
        userLista = new ArrayList<HashMap<String, String>>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
