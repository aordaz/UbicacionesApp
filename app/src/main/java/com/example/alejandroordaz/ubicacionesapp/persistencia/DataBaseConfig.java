package com.example.alejandroordaz.ubicacionesapp.persistencia;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Alejandro Ordaz on 22/07/2017.
 */

public class DataBaseConfig extends OrmLiteConfigUtil {

    public static void main(String[] args) throws IOException, SQLException {

        writeConfigFile("config_file.txt");

    }

}
