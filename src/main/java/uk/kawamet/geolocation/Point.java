package uk.kawamet.geolocation;

public class Point {

    private double x;
    private double y;
    private String text;
    private String country;

    public Point() {
    }

    public Point(double x, double y, String text, String country) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.country = country;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
