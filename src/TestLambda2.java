import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda2 {

    @Test
    public void test1(){
        Runnable r=() -> System.out.println("lambda");
        r.run();

        Consumer c=x -> System.out.println(x);
        c.accept("acc");
    }

    @Test
    public void Test2(){
        Comparator<Integer> c=(x,y)->{
            System.out.println("jiekou");
            return Integer.compare(x,y);
        };
        System.out.println(c.compare(4,2));
    }

    List<Employee> emps= Arrays.asList(
            new Employee("zhang",12,5555.5),
            new Employee("li",15,7555.5),
            new Employee("wang",22,5995.5),
            new Employee("tang",32,4555.5),
            new Employee("www",8,2555.5)
    );

    @Test
    public void test3(){
        Collections.sort(emps,(e1,e2)->{
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            }else{
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        });

        for(Employee emp:emps){
            System.out.println(emp);
        }
    }

    @Test
    public void Test4(){
        String s = strHandler("aaaaa", str -> str.toUpperCase());
        System.out.println(s);
    }

    public String strHandler(String str,MyFunction myFunction){
        return myFunction.getValue(str);
    }
}
