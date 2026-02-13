package org.example.base.dto;

public class SimpleCoordination {

    private String x;
    private String y;

    public SimpleCoordination() {
    }

    public SimpleCoordination(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "\"x\":" + (x == null ? "null" : "\"" + x + "\"") + ", " +
                "\"y\":" + (y == null ? "null" : "\"" + y + "\"") +
                "}";
    }
}
