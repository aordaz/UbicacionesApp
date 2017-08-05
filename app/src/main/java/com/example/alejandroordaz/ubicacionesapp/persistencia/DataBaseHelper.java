package com.example.alejandroordaz.ubicacionesapp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.alejandroordaz.ubicacionesapp.data.Coordenada;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Alejandro Ordaz on 22/07/2017.
 */


public class DataBaseHelper extends OrmLiteSqliteOpenHelper{


    private static final String DATABASE_NAME = "mapa.db";
    private static final int DATABASE_VERSION = 1;

    //el mono normal que hace crud

    private Dao<Coordenada, Integer> daoCoordenada = null;

    // el mono poderos que hace crud pero tambien controla las excepciones
    private RuntimeExceptionDao <Coordenada, Integer> daoCoordenadaPoderoso = null;



    public DataBaseHelper (Context contexto){


        super(contexto,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        //garantizar la creación de nuestra base de datos
        try {
            TableUtils.createTable(connectionSource, Coordenada.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        //eliminar las tablas
        try {
            TableUtils.dropTable(connectionSource, Coordenada.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //crear las tablas

        onCreate(sqLiteDatabase, connectionSource);

    }

    public Dao<Coordenada, Integer> getDaoCoordenada() throws SQLException {
        if (this.daoCoordenada == null) {

            this.daoCoordenada = getDao(Coordenada.class);
        }
        return daoCoordenada;
    }

    public RuntimeExceptionDao<Coordenada, Integer> getDaoCoordenadaPoderoso() {
        if (this.daoCoordenadaPoderoso == null) {

            this.daoCoordenadaPoderoso = getRuntimeExceptionDao(Coordenada.class);
        }
        return daoCoordenadaPoderoso;
    }

    @Override
    public void close() {
        super.close();
        this.daoCoordenada= null;
        this.daoCoordenadaPoderoso = null;

    }
}
