/**
 * @Author: Jeremy
 * @Date: 2020/8/22 22:08
 */
public class Person {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id = %s, name = %s", id, name);
    }
}
