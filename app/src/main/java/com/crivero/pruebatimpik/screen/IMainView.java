package com.crivero.pruebatimpik.screen;

import android.os.AsyncTask;

import com.crivero.pruebatimpik.model.Employee;

import java.net.URL;
import java.util.List;

/**
 * Created by Christopher on 05/04/2016.
 */
public interface IMainView {
    /**
     * Metodo para obtener json
     */
    String obtenerJson(URL url);

    /**
     * Metodo para inflar clase desde objeto json
     */
    List<Employee> inflarObjeto(String json);
}
