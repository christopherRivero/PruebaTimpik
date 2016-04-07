package com.crivero.pruebatimpik.connection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Christopher on 05/04/2016.
 */
public class Connection {
    /**
     * Conexón base para todas las peticiones al servidor json
     * @param url pasamos url por parametro
     * @return devuelve objeto json para posterior parseo a Class.
     */
    public static String baseConection(URL url) {
        String inputStr = null;
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //Acciones a realizar con el flujo
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            //urlConnection.setConnectTimeout(5000);
            inputStr = streamReader.readLine();
            responseStrBuilder.append(inputStr);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            urlConnection.disconnect();
        }
        return inputStr;
    }
}
