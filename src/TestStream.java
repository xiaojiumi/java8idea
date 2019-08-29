import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创建straeam
 * 中间操作
 * 终止操作
 */

public class TestStream {

    @Test
    public void test1(){
        List<String> list=new ArrayList<>();
        Stream<String> stream1=list.stream();

        Employee[] emps=new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        Stream<String> stream3 = Stream.of("aa", "aaa");

       // Stream.iterate(0,x->x+2).limit(10).forEach(System.out::println);//迭代

        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);
    }

    List<Employee> emps= Arrays.asList(
            new Employee("zhang",12,5555.5),
            new Employee("li",15,7555.5),
            new Employee("wang",22,5995.5),
            new Employee("tang",32,4555.5),
            new Employee("www",8,2555.5)
    );

    /**
     * filter 接受lambda
     * limit 截断流
     * skip 跳过元素
     * distinct 筛选 去重 通过Hashcode 和equals
     */
    @Test
    public void test2(){

       //中间操作
        Stream<Employee> employeeStream = emps.stream().filter(e -> e.getAge() > 15);

       //终止操作
        //只有在执行终止操作时，中间操作才会执行，如在中间操作插入sout语句，没有终止操作不会有任何输出
        employeeStream.forEach(System.out::println);
    }

    /**
     * map 接受lambda，将函数运用到每一个元素上
     * flatMap 将多个流组成的流扁平化 变成一个流，如Stream<Stream<Integer>>变成Stream<Integer>
     */
    @Test
    public void test3() {
        List<String> list= Arrays.asList("aaa","bbb","ccc","ddd");
        list.stream()
                .map(str->str.toUpperCase())
                .forEach(System.out::println);
    }

    /**
     * sorted（） 自然排序
     * sorted（Comparator com） 定制排序
     */
    @Test
    public void test4() {
        emps.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }
    /**
     * 终止操作
     * allMatch
     * anyMatch
     * noneMatch
     * findFirst  返回值为Optional<> op.get()获取对象
     * findAny 返回当前流的任意元素 stream（串行）变为parallelStream（并行流）会随机获取
     * count
     * max  返回值为Optional<> op.get()获取对象
     * min  返回值为Optional<> op.get()获取对象
     */
    @Test
    public void test5() {
        Optional<Double> op=emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op.get());
    }
    /**
     * 归约 reduce 把流中元素反复结合起来，变成一个值
     */
    @Test
    public void test6() {
            List<Integer> list=Arrays.asList(1,2,3,4);
           Integer sum= list.stream()
                    .reduce(0,(x,y)->x+y);
        System.out.println(sum);

       Optional<Double> op= emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }
    /**
     * collect 将流转换为其他形式1
     * .groupingBy 分组 .partitioningBy 分区
     */
    @Test
    public void test8() {
       emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList()).forEach(System.out::println);

      Double avg= emps.stream()
               .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
    }
}
