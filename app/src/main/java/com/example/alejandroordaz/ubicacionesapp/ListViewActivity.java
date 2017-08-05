package com.example.alejandroordaz.ubicacionesapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alejandroordaz.ubicacionesapp.adapters.AdaptadorCoordenada;
import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.persistencia.DataBaseHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Date;

public class ListViewActivity extends AppCompatActivity {

    private ListView lista;
    private DataBaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //se referencia el listview
        this.lista = (ListView) findViewById(R.id.listViewCoordenadas);
        this.helper = new DataBaseHelper(this);

/**
        //pedimos el bundle de los extras al intento
        Bundle extras = getIntent().getExtras();
        // desmenusamos el bundle

        Coordenada coor = (Coordenada) extras.getSerializable("coordenada0");

        ArrayList <Coordenada> coordenas = new ArrayList<>();

               // System.out.println(extras.size());
        //String [] items = new String[5];
       //llenando la coleccion de coordenadas con los que tenia el bundle

        for(int x=0; x< extras.size(); x++){

            coordenas.add((Coordenada)extras.getSerializable("coordenada"+x));
          // items[x]= coordenas.get(x).toString();

        }

        //instanciamos el adaptador no piñata jeje
**/


    }

    public void  MostrarCoordenadas(){
        RuntimeExceptionDao<Coordenada,Integer> daoCoordenada = this.helper.getDaoCoordenadaPoderoso();
        ArrayList <Coordenada> coordenas = (ArrayList<Coordenada>)daoCoordenada.queryForAll();
        //ArrayList<Coordenada> coords = (ArrayList<Coordenada>)daoCoordenada.
        AdaptadorCoordenada adapCoordenada= new AdaptadorCoordenada(this,coordenas );

        //String [] items = new String[]{"pedro","pablo","eran","hermanos"};

       /* ArrayAdapter <Coordenada> itemsAdapter =
                new ArrayAdapter<Coordenada>(this, android.R.layout.simple_list_item_1, coordenas);*/

        //seteamos el nuevo adaptador al listview
        this.lista.setAdapter(adapCoordenada);
    }

    public DataBaseHelper getHelper(){
        return  helper;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.helper.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.helper = new DataBaseHelper(this);
        MostrarCoordenadas();
    }
}
