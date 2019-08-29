import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnoation {

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str){

    }

    @Test
    public void test1()throws  Exception{
        Class<TestAnnoation> clazz=TestAnnoation.class;
        Method show = clazz.getMethod("show");

        MyAnnotation[] mas= show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation ma:mas
             ) {
            System.out.println(ma.value());
            
        }
    }
}
