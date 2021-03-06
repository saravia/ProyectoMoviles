package com.example.saravia.proyectogeolocalizacion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaEvento extends AppCompatActivity {

    private Integer ID=0;
    private ListView lstOpciones;
    private Evento[] evento =
            new Evento[]{
                    new Evento("Presentacion de Trabajo", "18/10/2015"),
                    new Evento("Reunion Confraternidad", "26/11/2014"),
                                 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento);
        ID=(Integer) getIntent().getExtras().get("ID");

        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        AdaptadorTitulares adaptador =
                new AdaptadorTitulares(this, evento);

        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent myIntent = new Intent(view.getContext(), MenuLocalizacion.class);
               myIntent.putExtra("ID",ID);
               startActivity(myIntent);
           }
       });

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

    class AdaptadorTitulares extends ArrayAdapter<Evento> {

        public AdaptadorTitulares(Context context, Evento[] datos) {
            super(context, R.layout.lista_evento, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_evento, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(evento[position].getNombre());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(evento[position].getIngrediente());


            return(item);
        }
    }

}