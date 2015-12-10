package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.adaptadores.PeladoAdapter;
import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.ConnectDB;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    TextView textNombre, textApellidos, textTelefono;
    ImageView llamada;
    int num_pelados;
    GridView grid_pelados;
    String nombre_final;

    final int PELADOS_MAXIMOS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Detalles del cliente");
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        String nombre = null;

        if (extras != null) {
            nombre = extras.getString("nombre");
            nombre_final = nombre;
        }

        textNombre = (TextView) findViewById(R.id.textViewNom);
        textApellidos = (TextView) findViewById(R.id.textViewApellidos);
        textTelefono = (TextView) findViewById(R.id.textViewTelefono);
        grid_pelados = (GridView) findViewById(R.id.gridViewPelados);
        llamada = (ImageView) findViewById(R.id.imageViewLLamada);

        if (ConnectDB.con(this) != null) {

            String[] campos = new String[]{"nombre", "apellidos", "telefono", "num_pelados"};
            String[] args = new String[]{nombre};

            Cursor c = ConnectDB.con(this).query("Cliente", campos, "nombre=?", args, null, null, null);

            if (c.moveToFirst()) {
                do {
                    textNombre.setText(c.getString(0));
                    textApellidos.setText(c.getString(1));
                    textTelefono.setText(String.valueOf(c.getInt(2)));
                    num_pelados = c.getInt(3);

                } while (c.moveToNext());
            }
            ConnectDB.con(this).close();
        }


        //Esto es lo que llena el grid de pelados acumulados
        final ArrayList<Boolean> listado = new ArrayList<>();
        for (int i = 0; i < num_pelados; i++) {
            listado.add(true);
        }
        for (int i = 0; i < PELADOS_MAXIMOS-num_pelados; i++) {
            listado.add(false);
        }

        PeladoAdapter peladoAdapter = new PeladoAdapter(this, listado);
        grid_pelados.setAdapter(peladoAdapter);

        grid_pelados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imageViewRound);

                boolean boleano = listado.get(position);
                Log.i("BOOLEANO", "RESULTADO: " + boleano);

                if (boleano) {
                    imageView.setImageDrawable(getDrawable(R.drawable.ic_uncheck));
                    listado.set(position, false);
                    if (num_pelados != 0) {
                        ConnectDB.con(ClienteActivity.this).execSQL("UPDATE Cliente SET num_pelados=num_pelados-1 WHERE nombre='" + nombre_final + "'");
                    }
                } else {
                    imageView.setImageDrawable(getDrawable(R.drawable.ic_check));
                    listado.set(position, true);
                    ConnectDB.con(ClienteActivity.this).execSQL("UPDATE Cliente SET num_pelados=num_pelados+1 WHERE nombre='" + nombre_final + "'");
                }
                ConnectDB.con(ClienteActivity.this).close();

            }
        });


        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri datos = Uri.parse("tel:" + textTelefono.getText().toString());
                Intent i = new Intent(Intent.ACTION_DIAL, datos);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_editar) {

            String nombre = textNombre.getText().toString();
            String apellidos = textApellidos.getText().toString();
            String telefono = textTelefono.getText().toString();

            Intent i = new Intent(ClienteActivity.this, EditarActivity.class);

            i.putExtra("nombre", nombre);
            i.putExtra("apellidos", apellidos);
            i.putExtra("telefono", telefono);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ClienteActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
