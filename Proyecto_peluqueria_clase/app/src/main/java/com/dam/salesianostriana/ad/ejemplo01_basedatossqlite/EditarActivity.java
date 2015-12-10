package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.ConnectDB;
import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.Utils;


public class EditarActivity extends AppCompatActivity {

    EditText nombre, apellidos, telefono;
    ImageView editar;

    String primer_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nombre = (EditText) findViewById(R.id.editTextNombreEdit);
        apellidos = (EditText) findViewById(R.id.editTextApellidosEdit);
        telefono = (EditText) findViewById(R.id.editTextNumeroTlfEdit);
        editar = (ImageView) findViewById(R.id.imageViewGuardar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            String nom = extras.getString("nombre");
            primer_nombre = nom;
            String ape = extras.getString("apellidos");
            String tlf = extras.getString("telefono");

            nombre.setText(nom);
            apellidos.setText(ape);
            telefono.setText(tlf);

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nuevo_nombre = Utils.capitalizePalabra(nombre.getText().toString());
                    String nuevo_ape = Utils.capitalizePalabra(apellidos.getText().toString());
                    String nuevo_num = telefono.getText().toString();

                    if (!nuevo_nombre.isEmpty() && !nuevo_num.isEmpty()) {

                        if (ConnectDB.con(EditarActivity.this) != null) {
                            ContentValues valores = new ContentValues();
                            valores.put("nombre", nuevo_nombre.trim());
                            valores.put("apellidos", nuevo_ape.trim());
                            valores.put("telefono", nuevo_num.trim());

                            String[] args = new String[]{primer_nombre};
                            ConnectDB.con(EditarActivity.this).update("Cliente", valores, "nombre=?", args);

                        }

                        ConnectDB.con(EditarActivity.this).close();

                        Intent intent = new Intent(EditarActivity.this, ClienteActivity.class);
                        intent.putExtra("nombre", nuevo_nombre);
                        intent.putExtra("apellidos", nuevo_ape);
                        intent.putExtra("telefono", nuevo_num);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        EditarActivity.this.finish();

                    } else {
                        Toast.makeText(EditarActivity.this, "Rellene los campos vacíos", Toast.LENGTH_SHORT).show();
                    }

                }


            });

        }


    }
}
