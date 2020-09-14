package util;

/**
 * @Author: Jeremy
 * @Date: 2020/9/14 14:57
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        if (point == null) {
            return false;
        }
        return point.x == this.x && point.y == this.y;
    }

    @Override
    public int hashCode() {
        return this.x >> 2 + this.y >> 1;
    }
}