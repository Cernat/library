package spring.learning.video.three;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class DrawingApp {

    public static void main(String[] args) {
//        Triangle triangle = new Triangle();
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("D:\\library\\library\\library-backend\\library-api\\src\\main\\resources\\spring.xml"));
        Triangle triangle = (Triangle) factory.getBean("triangle");
        triangle.draw();
    }
}
