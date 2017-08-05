package com.example.alejandroordaz.ubicacionesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.example.alejandroordaz.ubicacionesapp.persistencia.DataBaseHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class EditActivity extends AppCompatActivity {

    private DataBaseHelper helper;
    private EditText editarNombre;
    private EditText editarAltitud;
    private EditText editarLongitud;

    Coordenada coordenada;

    //sobre escribo el metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.helper = new DataBaseHelper(this);

        RuntimeExceptionDao<Coordenada, Integer> daoCoordenada = this.helper.getDaoCoordenadaPoderoso();

        //le pido me traiga la coordenada por su id
        this.coordenada = daoCoordenada.queryForId(getIntent().getIntExtra("idCoordenada", 1));

        //verifico que la coordenada no traiga un valor nulo
        if (coordenada != null) {


            this.editarNombre = (EditText) findViewById(R.id.edtEditNombre);
            this.editarAltitud = (EditText) findViewById(R.id.edtEditAltitud);
            this.editarLongitud = (EditText) findViewById(R.id.edtEditLongitud);

            //seteo los valores en los EditText
            editarNombre.setText(this.coordenada.getNombre());
            editarAltitud.setText("" + this.coordenada.getLat());
            editarLongitud.setText("" + this.coordenada.getLongi());

        }


        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);

        //ejecuto el boton con el metodo setOnClickListener
        btnActualizar.setOnClickListener(new View.OnClickListener() {

            //sobre escribo el metodo onClick
            @Override
            public void onClick(View view) {

                //mando llamar al metodo actualizar que me actualizara la coordenada
                actualizar(view);
            }
        });
    }

        //creo el metodo actualizar

        public void actualizar ( View view ) {

            //Asigno los nuevos valores de la coordenada que se contienen en los EditText de mi actividad
            coordenada.setNombre(editarNombre.getText().toString());
            coordenada.setLat(Double.parseDouble(editarAltitud.getText().toString()));
            coordenada.setLongi(Double.parseDouble(editarLongitud.getText().toString()));

            //usando mi DaoPoderoso actualizo los valores de la coordenada
            this.helper.getDaoCoordenadaPoderoso().update(coordenada);

            //muestro un mensaje de actualización de la coordenada
            Toast.makeText(this, "Coordenada Actualizada", Toast.LENGTH_LONG).show();

        }



    public DataBaseHelper getHelper() {
        return helper;
    }

    //sobre escribo el metodo onDestroy para finalizar la actividad
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //cierro la base de datos
        this.helper.close();
    }
}
