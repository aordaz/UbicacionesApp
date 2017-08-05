package com.example.alejandroordaz.ubicacionesapp.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.alejandroordaz.ubicacionesapp.ListViewActivity;
import com.example.alejandroordaz.ubicacionesapp.MainActivity;
import com.example.alejandroordaz.ubicacionesapp.R;
import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.persistencia.DataBaseHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

/**
 * Created by Alejandro Ordaz on 08/07/2017.
 */

public class ListenerBotones implements View.OnClickListener {

    MainActivity contexto;
    private DataBaseHelper helper;

        //agragamos un constructor por defecto
    public ListenerBotones( MainActivity contexto) {

        this.contexto = contexto;
        this.helper = contexto.getHelper();
    }

    @Override
    public void onClick(View view) {
        //

        Button btn = (Button) view;

        switch (btn.getId()){
            case R.id.btnAgregar: {
                //creamos la corrdenada
                //jalando los valores de los editText
                String nombre = contexto.getEdtNombre().getText().toString();
                Double lalti = Double.parseDouble(contexto.getEdtLati().getText().toString());
                Double longi = Double.parseDouble(contexto.getEdtLongi().getText().toString());

                //instanciar una nueva coordenada



                Coordenada co1 = new Coordenada(nombre, lalti, longi);

                //agragamos la coordenada a la base de datos
                RuntimeExceptionDao <Coordenada, Integer> daoCoordenadaChulo = this.helper.getDaoCoordenadaPoderoso();

                daoCoordenadaChulo.create(co1);

                /**
                //agregamos la coordenada a la coleccion
                contexto.getCoordenadas().add(co1);
                System.out.println();**/
                break;
            }

            case R.id.btnVercoordenadas:{

                //Intent se utiliza para ejecutar aplicaciones o servicios
                Intent intento = new Intent(contexto, ListViewActivity.class);

                //traer las coordedas


                // genero una referencia de la coleccion de coordenadas
                ArrayList<Coordenada> aux = contexto.getCoordenadas();

                Bundle extras = new Bundle();

                //agregar todas las coordenadas de la coleccion +
                //al objeto bundle llamadas extras
                // recorrer tofas las coordenas

                for ( int x=0; x< aux.size(); x++){

                    extras.putSerializable("coordenada"+x, aux.get(x));
                }

                intento.putExtras(extras);

                //se ejecuta la ctividad


                //hoy  this.helper.close();
                contexto.startActivity(intento);



                break;
            }

        }


    }
}
