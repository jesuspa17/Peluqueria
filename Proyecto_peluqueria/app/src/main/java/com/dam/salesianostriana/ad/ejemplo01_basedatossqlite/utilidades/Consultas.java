package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades;

import android.content.Context;
import android.database.Cursor;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.pojo_lista.Cliente;

import java.util.ArrayList;

/**
 * @author Jesús Pallares on 10/12/2015.
 */
public class Consultas {

    public static ArrayList<Cliente> consultarTodos(Context contexto){
        ArrayList<Cliente> lista_clientes = new ArrayList<>();
        if (ConnectDB.con(contexto) != null) {

            String[] campos = new String[]{"nombre", "apellidos", "telefono", "num_pelados"};
            Cursor c = ConnectDB.con(contexto).query("Cliente", campos, null, null, null, null, "nombre");
            if (c.moveToFirst()) {
                do {
                    lista_clientes.add(new Cliente(c.getString(0), c.getString(1), c.getString(2)));
                } while (c.moveToNext());
            }
            ConnectDB.con(contexto).close();
        }
        return lista_clientes;
    }

    public static ArrayList<Cliente> buscarPorNomYape(Context context, String cadena){
        ArrayList<Cliente> lista_clientes = new ArrayList<>();
        if (ConnectDB.con(context) != null) {
            Cursor c = ConnectDB
                    .con(context)
                    .rawQuery("SELECT nombre, apellidos, telefono, num_pelados " +
                            "FROM Cliente WHERE nombre " +
                            "LIKE '%" + cadena.trim() + "%'" +
                            "OR apellidos LIKE '%" + cadena.trim() + "%'" +
                            "ORDER BY nombre ASC", null);
            if (c.moveToFirst()) {
                do {
                    lista_clientes.add(new Cliente(c.getString(0), c.getString(1), c.getString(2)));
                } while (c.moveToNext());
            }
            ConnectDB.con(context).close();
        }

        return lista_clientes;
    }


}
