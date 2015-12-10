package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jesús Pallares on 06/12/2015.
 */
public class ConnectDB {

    public static SQLiteDatabase con(Context contexto){
        ClienteSQLite usdbh =new ClienteSQLite(contexto, "DBClientes", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        return db;
    }

}
