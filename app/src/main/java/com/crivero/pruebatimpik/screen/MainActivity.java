package com.crivero.pruebatimpik.screen;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.crivero.pruebatimpik.R;
import com.crivero.pruebatimpik.connection.Connection;
import com.crivero.pruebatimpik.model.Empleados;
import com.crivero.pruebatimpik.model.Employee;
import com.crivero.pruebatimpik.screen.adapter.RecyclerViewAdapter;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView{

    @Bind(R.id.lst_empleados)
    RecyclerView listaEmpleados;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    MainPresenterImpl presenter;
    AppCompatActivity activity;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        activity = MainActivity.this;
        presenter = new MainPresenterImpl(MainActivity.this,activity);
        //Llamada al asynktask
        ObtenerEmpleados obtenerEmpleados = new ObtenerEmpleados();
        obtenerEmpleados.execute();

    }

    @Override
    public String obtenerJson(URL url) {
        String json = Connection.baseConection(url);
        return json;
    }

    @Override
    public List<Employee> inflarObjeto(String json) {
        Gson gson = new Gson();
        Empleados discos = new Empleados();
        discos = gson.fromJson(json,Empleados.class);
        List<Employee> employees = discos.getEmployees();
        return employees;
    }


    private class ObtenerEmpleados extends AsyncTask<String, Void, String> {

        String json = null;
        List<Employee> employees;
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... params) {
            try {

                Thread.sleep(2500);
                URL url = new URL("http://www.timpik.com/mobileapp/webapp?getEmployees=1&render=json");
                json = presenter.obtenerJson(url);
                //inflamos objeto, lo hacemos aqui para que la obtención de imagenes de las urls, no se realice en el Main Thread.
                employees = presenter.inflarObjeto(json);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(activity);
            listaEmpleados.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new RecyclerViewAdapter(employees);
            listaEmpleados.setAdapter(mAdapter);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Por favor espera ...", "Cargando empleados ...", true);
            progressDialog.setCancelable(true);

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
