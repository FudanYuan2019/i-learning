package flyweight;

/**
 * @Author: Jeremy
 * @Date: 2020/8/28 21:10
 */
public class Poker implements Comparable{
    private Integer num;
    private Color color;
    private Shape shape;

    public Poker(Integer num, Color color, Shape shape) {
        this.num = num;
        this.color = color;
        this.shape = shape;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", num, color, shape);
    }

    @Override
    public int compareTo(Object o) {
        Poker poker = (Poker) o;
        if (this == o) {
            return 0;
        }

        if (this.getNum() > poker.getNum()) {
            return 1;
        } else if (this.getNum() < poker.getNum()) {
            return -1;
        } else {
            return 0;
        }
    }

    public enum Color {
        RED("red"),
        BLACK("black");
        private String color;

        public String getColor() {
            return this.color;
        }

        Color(String color) {
            this.color = color;
        }
    }

    public enum Shape {
        DIAMOND("diamond"),

        PLUM("plum"),

        PEACH("peach"),

        JOKER("joker");

        private String shape;

        public String getShape() {
            return this.shape;
        }

        Shape(String shape) {
            this.shape = shape;
        }
    }
}
