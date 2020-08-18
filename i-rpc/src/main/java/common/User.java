package common;

import java.io.Serializable;

/**
 * @Author: Jeremy
 * @Date: 2020/3/13 21:49
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("Id: %d, Name: %s", id, name);
    }
}
