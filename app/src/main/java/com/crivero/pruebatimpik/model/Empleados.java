package com.crivero.pruebatimpik.model;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by Christopher on 05/04/2016.
 */
public class Empleados {

    private List<Employee> employees = new ArrayList<Employee>();

    /**
     *
     * @return
     * The employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     *
     * @param employees
     * The employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
