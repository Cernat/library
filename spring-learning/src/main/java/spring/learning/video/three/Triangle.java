package spring.learning.video.three;

public class Triangle {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String type;
    public void draw() {
        System.out.println( getType() + " Triangle Drawn");
    }
}