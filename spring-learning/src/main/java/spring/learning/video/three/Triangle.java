package spring.learning.video.three;

import java.util.List;

public class Triangle {

    private List<Point> points;

    public void draw() {
        for(Point point : points) {
            System.out.println("Point = (" + point.getX() +", " + point.getY() +")");
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}