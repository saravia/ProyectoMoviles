package com.example.saravia.proyectogeolocalizacion;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MenuEvento extends AppCompatActivity{
    private Button btnIrCrearEvent;


    private ListView listView;
    private Evento[] evento =
            new Evento[]{
                    new Evento("Presentacion de Trabajo", "18/10/2015"),
                    new Evento("Reunion Confraternidad", "26/11/2014"),
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuevent);

        btnIrCrearEvent = (Button)findViewById(R.id.btnIrCrearEvent);

        btnIrCrearEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuEvento.this, CrearEvento.class);
                startActivity(i);

            }
        });


        listView = (ListView)findViewById(R.id.listView);
        AdaptadorTitulares1 adaptador =
        new AdaptadorTitulares1(this, evento);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog1(view);
            }
        });



    }

    public void showDialog1(View view) {

        FragmentManager manager = getFragmentManager();

        MyDialogFragmentEvento dialog = new MyDialogFragmentEvento();
        dialog.show(manager, "dialog");

    }


    class AdaptadorTitulares1 extends ArrayAdapter<Evento> {

        public AdaptadorTitulares1(Context context, Evento[] datos) {
            super(context, R.layout.menuevent, datos);
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

