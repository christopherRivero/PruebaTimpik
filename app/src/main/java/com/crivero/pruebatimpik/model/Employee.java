package com.crivero.pruebatimpik.model;

/**
 * Created by Christopher on 05/04/2016.
 */
public class Employee {

    private Integer id;
    private String name;
    private String pic;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The pic
     */
    public String getPic() {
        return pic;
    }

    /**
     *
     * @param pic
     * The pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

}
