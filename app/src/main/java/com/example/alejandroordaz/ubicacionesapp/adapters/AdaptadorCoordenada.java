package com.example.alejandroordaz.ubicacionesapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alejandroordaz.ubicacionesapp.ListViewActivity;
import com.example.alejandroordaz.ubicacionesapp.R;
import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.listeners.ConotroladorBotonesItem;

import java.util.ArrayList;

/**
 * Created by Alejandro Ordaz on 15/07/2017.
 */

public class AdaptadorCoordenada extends ArrayAdapter<Coordenada>{

    ListViewActivity context;

    public AdaptadorCoordenada(Context context, ArrayList<Coordenada> resource) {
        super(context,0, resource);
        this.context = (ListViewActivity) context;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        //obtenemos la coordenada dependiendo de su posicion
        Coordenada coordenada= getItem(position);

        //en sus caso reciclamos los views
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.coordenada_item_view, parent, false);
        }

        //mandamos los valores al nuevo view inflado (con el layout nuevo)
        //hacemos la referencia
        TextView nombre = (TextView) convertView.findViewById(R.id.tvItemNombre);
        TextView latitud = (TextView) convertView.findViewById(R.id.tvItemLatitud);
        TextView longitud = (TextView) convertView.findViewById(R.id.tvItemLongitud);

        //referencio los imageBotton

        ImageButton ibtnEliminar = (ImageButton) convertView.findViewById(R.id.imageButtonEliminar);
        ImageButton ibtnEditar = (ImageButton)convertView.findViewById(R.id.imageButtonEditar);
        ImageButton ibtnVerMapa = (ImageButton) convertView.findViewById(R.id.imageButtonVer);

        ConotroladorBotonesItem controladorBotonesItem = new ConotroladorBotonesItem(this,context.getHelper(),coordenada);
        ibtnEditar.setOnClickListener(controladorBotonesItem);
        ibtnEliminar.setOnClickListener(controladorBotonesItem);
        ibtnVerMapa.setOnClickListener(controladorBotonesItem);

        //mandamos el nombre de la coordenada completa
        nombre.setText(coordenada.getNombre());
        latitud.setText("Latitud: "+coordenada.getLat()+"");
        longitud.setText("Longitud: "+coordenada.getLongi()+"");



        return convertView;
    }

    @NonNull
    @Override
    public ListViewActivity getContext() {
        return context;
    }
}
