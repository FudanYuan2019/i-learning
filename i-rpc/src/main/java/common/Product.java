package common;

import java.io.Serializable;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 13:02
 */
public class Product implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;

    private String name;

    public Product(int id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
