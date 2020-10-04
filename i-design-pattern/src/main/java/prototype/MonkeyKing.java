package prototype;

import java.io.*;

/**
 * @Author: Jeremy
 * @Date: 2020/8/21 16:50
 */
public class MonkeyKing implements Cloneable, Serializable {
    private Long id;
    private Double height;
    private Double weight;
    private GoldenHoop goldenHoop;

    public Long getId() {
        return id;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public GoldenHoop getGoldenHoop() {
        return goldenHoop;
    }

    public MonkeyKing(Double height, Double weight, GoldenHoop goldenHoop) {
        this.id = 0L;
        this.height = height;
        this.weight = weight;
        this.goldenHoop = goldenHoop;
    }

    public MonkeyKing clone(Long id) {
        MonkeyKing monkeyKing = null;
        try {
            monkeyKing = (MonkeyKing) super.clone();
            monkeyKing.id = id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monkeyKing;
    }

    public MonkeyKing deepClone(Long id) throws IOException, ClassNotFoundException {
        // write the object to stream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        // read the object from stream
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        MonkeyKing monkeyKing = (MonkeyKing) ois.readObject();
        monkeyKing.id = id;
        return monkeyKing;
    }
}
