package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.R;

import java.util.ArrayList;

/**
 * @author Jesús Pallares on 05/12/2015.
 */
public class PeladoAdapter extends ArrayAdapter<Boolean> {
    private final Context context;
    private final ArrayList<Boolean> values;

    public PeladoAdapter(Context context, ArrayList<Boolean> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutAlumnoAInyectar = inflater.inflate(R.layout.grid_view_pelado, parent, false);

        ImageView image_round = (ImageView) layoutAlumnoAInyectar.findViewById(R.id.imageViewRound);
        if((values.get(position))){
            image_round.setImageResource(R.drawable.ic_check);
        }else{
            image_round.setImageResource(R.drawable.ic_uncheck);
        }


        return layoutAlumnoAInyectar;
    }
}
