package com.example.saravia.proyectogeolocalizacion;

import android.app.FragmentManager;
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

public class ListaAmigos extends AppCompatActivity {

    private ListView lstOpciones;
    private Amigos[] comida =
            new Amigos[]{
                    new Amigos("Zutano", "96566425"),
                    new Amigos("Perengano", "95646587"),
                    new Amigos("Mengano", "966236425"),
                    new Amigos("Fulano", "901001587"),
                    new Amigos("Chiquito", "94562425"),
                    new Amigos("Kazeta", "94654547"),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amigos);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        AdaptadorTitulares adaptador =new AdaptadorTitulares(this, comida);

        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               showDialog(view);
            }
        });

    }

    public void showDialog(View view) {

        FragmentManager manager = getFragmentManager();

        MyDialogFragment dialog = new MyDialogFragment();
        dialog.show(manager, "dialog");

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

    class AdaptadorTitulares extends ArrayAdapter<Amigos> {

        public AdaptadorTitulares(Context context, Amigos[] datos) {
            super(context, R.layout.lista_amigos, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_amigos, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(comida[position].getNombre());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(comida[position].getNum());


            return(item);
        }
    }

}