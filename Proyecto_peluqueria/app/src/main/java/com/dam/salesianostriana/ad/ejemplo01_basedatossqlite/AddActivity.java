package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.ConnectDB;

public class AddActivity extends AppCompatActivity {

    EditText nombre, apellidos, telefono;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        apellidos = (EditText) findViewById(R.id.editTextApellidos);
        telefono = (EditText) findViewById(R.id.editTextNumeroTlf);

        add = (ImageView) findViewById(R.id.imageViewEditar);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();

                String nom_capitalze = nom.substring(0, 1).toUpperCase();
                String nom_final = nom_capitalze + nom.substring(1,nom.length());

                String ape = apellidos.getText().toString();
                String tlf = telefono.getText().toString();

                if (!nom.isEmpty() && !tlf.isEmpty()) {
                    if (ConnectDB.con(AddActivity.this) != null) {
                        ConnectDB.con(AddActivity.this).execSQL("INSERT INTO Cliente (nombre, apellidos, telefono, num_pelados) VALUES ('" + nom_final.trim() + "', '" + ape.trim() + "', " + tlf.trim() + ", 0)");
                    }
                    ConnectDB.con(AddActivity.this).close();
                    Intent i = new Intent(AddActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    AddActivity.this.finish();
                } else {
                    Toast.makeText(AddActivity.this, "Rellene los campos vacíos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
