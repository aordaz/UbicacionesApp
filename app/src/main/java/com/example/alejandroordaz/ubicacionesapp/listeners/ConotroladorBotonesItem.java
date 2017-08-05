package com.example.alejandroordaz.ubicacionesapp.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.example.alejandroordaz.ubicacionesapp.EditActivity;
import com.example.alejandroordaz.ubicacionesapp.ListViewActivity;
import com.example.alejandroordaz.ubicacionesapp.MainActivity;
import com.example.alejandroordaz.ubicacionesapp.R;
import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.persistencia.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by Alejandro Ordaz on 29/07/2017.
 */

public class ConotroladorBotonesItem  implements View.OnClickListener{


    ArrayAdapter <Coordenada> contexto;
    DataBaseHelper helper;
    Coordenada coordenada;

    public ConotroladorBotonesItem(ArrayAdapter<Coordenada> contexto, DataBaseHelper helper, Coordenada coordenada) {
        this.contexto = contexto;
        this.helper = helper;
        this.coordenada = coordenada;
    }

    @Override
    public void onClick(View view) {
        ImageButton btn = (ImageButton) view;

        switch (btn.getId()){

            case R.id.imageButtonEliminar:{
                //necesito eliminar la coordenada
                this.helper.getDaoCoordenadaPoderoso().delete(coordenada);
                this.contexto.remove(coordenada);
            }
        }

        switch (btn.getId()){

            case R.id.imageButtonEditar:{
               //cierra la conexion
                this.helper.close();

                Intent intento = new Intent((ListViewActivity) contexto.getContext(), EditActivity.class);
                intento.putExtra("idCoordenada",coordenada.getId());
                contexto.getContext().startActivity(intento);
            }
        }

    }
}
