package prototype;

import java.io.Serializable;

/**
 * @Author: Jeremy
 * @Date: 2020/8/21 16:52
 */
public class GoldenHoop implements Serializable {
    private Double height;
    private Double weight;

    public GoldenHoop(Double height, Double weight) {
        this.height = height;
        this.weight = weight;
    }
}
