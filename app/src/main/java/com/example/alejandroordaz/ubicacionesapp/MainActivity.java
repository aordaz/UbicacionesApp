package com.example.alejandroordaz.ubicacionesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

//import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;

import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.listeners.ListenerBotones;
import com.example.alejandroordaz.ubicacionesapp.persistencia.DataBaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //generar atributos
    private Button btnAgregar, btnVercoordenadas;
    private EditText edtNombre, edtLati, edtLongi;
    private ArrayList <Coordenada> coordenadas;
    private DataBaseHelper helper;


    public DataBaseHelper getHelper(){
        return  helper;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.helper = new DataBaseHelper(this);
        //referenciar los views
        refComponentes();

        this.coordenadas = new ArrayList<Coordenada>();
    }

    private void  refComponentes(){
        //referencia los diferentes views

        //se referencia para poder usar y definir que lo que esta en el layout lo puedo usar

        this.edtNombre = (EditText) findViewById(R.id.editTextLugar);
        this.edtLati = (EditText) findViewById(R.id.editTextLatitud);
        this.edtLongi = (EditText) findViewById(R.id.editTextLongitud);

        this.btnAgregar = (Button)findViewById(R.id.btnAgregar);
        this.btnVercoordenadas = (Button) findViewById(R.id.btnVercoordenadas);

        ListenerBotones lis = new ListenerBotones(this);
        this.btnAgregar.setOnClickListener(lis);
        this.btnVercoordenadas.setOnClickListener(lis);

    }

    public void setEdtNombre(EditText edtNombre) {
        this.edtNombre = edtNombre;
    }

    public void setEdtLati(EditText edtLati) {
        this.edtLati = edtLati;
    }

    public void setEdtLongi(EditText edtLongi) {
        this.edtLongi = edtLongi;
    }

    public EditText getEdtNombre() {

        return edtNombre;
    }

    public EditText getEdtLati() {
        return edtLati;
    }

    public EditText getEdtLongi() {
        return edtLongi;
    }

    public void setCoordenadas(ArrayList<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;

    }

    public ArrayList<Coordenada> getCoordenadas() {

        return coordenadas;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.helper = new DataBaseHelper(this);
        ListenerBotones lis = new ListenerBotones(this);
        this.btnAgregar.setOnClickListener(lis);
        this.btnVercoordenadas.setOnClickListener(lis);
    }
}
