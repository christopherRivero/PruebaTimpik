package com.crivero.pruebatimpik.screen;

import com.crivero.pruebatimpik.model.Employee;

import java.net.URL;
import java.util.List;

/**
 * Created by Christopher on 05/04/2016.
 */
public interface IMainPresenterInterface {
    /**
     * Metodo que obtiene json
     */
    String obtenerJson(URL url);

    /**
     * Metodo para inflar clase desde objeto json
     * @param json
     */
    List<Employee> inflarObjeto(String json);
}
