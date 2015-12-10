package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.pojo_lista.Cliente;

import java.util.ArrayList;

/**
 * Created by Jesús Pallares on 04/12/2015.
 */
public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private final Context context;
    private final ArrayList<Cliente> lista_clientes;

    public ClienteAdapter(Context context, ArrayList<Cliente> lista_clientes) {
        super(context, -1, lista_clientes);
        this.context = context;
        this.lista_clientes = lista_clientes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout_cliente = inflater.inflate(R.layout.list_item_cliente, parent, false);

        TextView nombre = (TextView) layout_cliente.findViewById(R.id.textViewTextViewNombre);
        TextView apellidos = (TextView) layout_cliente.findViewById(R.id.textViewApellidos);
        TextView primera_letra = (TextView) layout_cliente.findViewById(R.id.textPrimeraLetra);

        Cliente cliente_actual = lista_clientes.get(position);

        nombre.setText(cliente_actual.getNombre());
        apellidos.setText(cliente_actual.getApellidos());

        primera_letra.setText(cliente_actual.getNombre().substring(0, 1).toUpperCase());

            if (primera_letra.getText().equals("A")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("B")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("C")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("D")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("E")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("F")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("G")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("H")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("I")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("J")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("K")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("L")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("M")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("N")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("Ñ")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("O")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("P")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("Q")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("R")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("S")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("T")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("U")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            } else if (primera_letra.getText().equals("V")) {
                //marron
                primera_letra.setBackgroundResource(R.drawable.redondeada_marron);
            } else if (primera_letra.getText().equals("W")) {
                //rojo
                primera_letra.setBackgroundResource(R.drawable.redondeada_rojo);
            } else if (primera_letra.getText().equals("Y")) {
                //amarillo
                primera_letra.setBackgroundResource(R.drawable.redondeada_amarillo);
            } else if (primera_letra.getText().equals("Z")) {
                //verde
                primera_letra.setBackgroundResource(R.drawable.redondeada_verde);
            }

        return layout_cliente;

    }
}
