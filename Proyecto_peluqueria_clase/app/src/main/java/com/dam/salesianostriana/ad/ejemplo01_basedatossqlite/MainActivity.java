package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.adaptadores.ClienteAdapter;
import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.pojo_lista.Cliente;
import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.ConnectDB;
import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades.Consultas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    ArrayList<Cliente> lista_clientes;
    ClienteAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.listView);

        //Trae la lista de todos los clientes que están almacenados en la BBDD
        lista_clientes = Consultas.consultarTodos(this);

        //Se adapta el listview en función de la lista obtenida de la BBDD
        adaptador = new ClienteAdapter(this, lista_clientes);
        lista.setAdapter(adaptador);

        //Se le asocia el evento click a la lista.
        lista.setOnItemClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
        AdapterView.AdapterContextMenuInfo info;
        try {
            info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        } catch (ClassCastException e) {
            return;
        }
        String cliente_selec = lista_clientes.get(info.position).getNombre();
        menu.setHeaderTitle(cliente_selec);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int op_selecc = item.getItemId();
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente cliente_seleccionado = lista_clientes.get(info.position);

        if (op_selecc == R.id.Eliminar) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                            if (ConnectDB.con(MainActivity.this) != null) {
                                String[] args = new String[]{cliente_seleccionado.getNombre()};
                                ConnectDB.con(MainActivity.this).execSQL("DELETE FROM Cliente WHERE nombre=?", args);
                                lista_clientes.remove(lista_clientes.get(info.position));
                                Toast.makeText(MainActivity.this, "Eliminado " + cliente_seleccionado.getNombre(), Toast.LENGTH_LONG).show();
                                adaptador.notifyDataSetChanged();
                            }
                            ConnectDB.con(MainActivity.this).close();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("¿Estás seguro de borrarlo?").setPositiveButton("Sí", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        } else if (op_selecc == R.id.editar_contextual) {

            Intent i = new Intent(MainActivity.this, EditarActivity.class);
            i.putExtra("nombre", cliente_seleccionado.getNombre());
            i.putExtra("apellidos", cliente_seleccionado.getApellidos());
            i.putExtra("telefono", cliente_seleccionado.getTelefono());
            startActivity(i);

        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /**BÚSQUEDA**/

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Buscar cliente...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {

                if (newText.isEmpty()) {

                    //La limpio y se rellena de nuevo la lista con la lista de clientes nueva
                    lista_clientes.clear();
                    lista_clientes = Consultas.consultarTodos(MainActivity.this);

                    adaptador = new ClienteAdapter(MainActivity.this, lista_clientes);
                    lista.setAdapter(adaptador);
                    adaptador.notifyDataSetChanged();

                } else {

                    //La limpio y se rellena de nuevo la lista con los datos actuales.
                    lista_clientes.clear();
                    lista_clientes = Consultas.buscarPorNomYape(MainActivity.this, newText);

                    adaptador = new ClienteAdapter(MainActivity.this, lista_clientes);
                    lista.setAdapter(adaptador);
                    adaptador.notifyDataSetChanged();

                }
                return false;
            }
        });
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cliente cliente_seleccionado = lista_clientes.get(position);
        Intent i = new Intent(MainActivity.this, ClienteActivity.class);
        i.putExtra("nombre", cliente_seleccionado.getNombre());
        startActivity(i);
    }
}
