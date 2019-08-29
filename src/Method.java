import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 对象：：实例方法名
 * 类：：静态方法名
 * 类：：实例方法名
 * 构造器引用
 */
public class Method {

    @Test
    public void test7(){
        Function<Integer,String[]> fun=String[]::new ;
    }
    @Test
    public void Test5(){
        //构造器引用
        Supplier<Employee> supo=Employee::new;

        //调用有一个String的构造器
        Function<String,Employee> sup2=Employee::new;
    }

    //类：：实例方法名
    @Test
    public void Test1(){
        BiPredicate<String,String> bp=(x,y)->x.equals(y);

        BiPredicate<String,String> bp2=String::equals;

    }

    //类：：静态方法名
    @Test
    public void Test2(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        Comparator<Integer> com2=Integer::compare;
    }

    //对象：：实例方法名
    @Test
    public void Test3(){

        PrintStream ps=System.out;
        Consumer<String> con1=ps::println;

        Consumer<String> con2=System.out::println;
    }
}
