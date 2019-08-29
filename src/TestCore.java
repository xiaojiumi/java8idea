import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *     Consumer<t> void accept(T t) 消费型
 *     Supplier<T> T get() 供给型
 *     Function<T,R> R apply(T t) 函数型
 *     Predicate<T> boolean test(T t) 断言型
 */
public class TestCore {
    @Test
    public void testc(){
            h(100,m-> System.out.println("xiaofei"+m));
    }
    public void h(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void tests(){
        List<Integer> nums = getN(10, () ->  (int)(Math.random()*100));
        for (Integer a:nums)
            System.out.println(a);
    }
    public List<Integer> getN(int num, Supplier<Integer> sup){
        List<Integer> list =new ArrayList<>();
        for (int i=0;i<num;i++){
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
    public String sh(String str ,Function<String,String> func){
        return func.apply(str);
    }

    @Test
    public void testsh(){
        String sh = sh("\t\t\t aaaaa", s -> s.trim());
        System.out.println(sh);
    }
    public List<String> pre(List<String> list, Predicate<String> p){
        List<String> lists=new ArrayList<>();
        for(String str:list){
            if(p.test(str))
                lists.add(str);
        }
        return lists;
    }
    @Test
    public void test4(){
        List<String> aaa= Arrays.asList("jhhhhhh","as","aaaa","a");
        List<String> aaaqq = pre(aaa,s -> s.length()>3);
        for (String aaaq:aaaqq
             ) {
            System.out.println(aaaq);
        }
        System.out.println("--------------------------");
        PrintStream ps=System.out;
        Consumer<String> con1=ps::println;
        con1.accept("aaaa");
    }
}
