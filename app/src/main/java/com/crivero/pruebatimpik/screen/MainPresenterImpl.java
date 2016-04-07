package com.crivero.pruebatimpik.screen;

import android.support.v7.app.AppCompatActivity;

import com.crivero.pruebatimpik.model.Employee;

import java.net.URL;
import java.util.List;

/**
 * Created by Christopher on 05/04/2016.
 */
public class MainPresenterImpl implements IMainPresenterInterface{
    IMainView iMainView;
    AppCompatActivity activity;

    public MainPresenterImpl(IMainView iMainView, AppCompatActivity activity) {
        this.iMainView = iMainView;
        this.activity = activity;
    }

    @Override
    public String obtenerJson(URL url) {
        String json = iMainView.obtenerJson(url);
        return json;
    }

    @Override
    public List<Employee> inflarObjeto(String json) {
        List<Employee> employees = iMainView.inflarObjeto(json);
        return employees;
    }
}
