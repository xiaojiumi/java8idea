import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewTime {
    public static void main(String[] args) throws Exception{
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        Callable<Date> task=new Callable<Date>() {
//            @Override
//            public Date call() throws Exception {
//                return simpleDateFormat.parse("20161218");
//            }
//        };
//        ExecutorService pool= Executors.newFixedThreadPool(10);
//        List<Future<Date>> results=new ArrayList<>();
//
//        for(int i=0;i<10;i++){
//            results.add(pool.submit(task));
//        }
//        for (Future<Date> future:results)
//            System.out.println(future.get());
//        pool.shutdown();


        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task=new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20161218",dft);
            }
        };
        ExecutorService pool= Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results=new ArrayList<>();

        for(int i=0;i<10;i++){
            results.add(pool.submit(task));
        }
        for (Future<LocalDate> future:results)
            System.out.println(future.get());
        pool.shutdown();
    }

    @Test
    public void test1(){
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2= LocalDateTime.of(2018,11,12,13,14,15);
        System.out.println(ldt2);
    }

    @Test
    public void test2(){
        Instant i1= Instant.now(); //默认获取UTC时区
        OffsetDateTime odt=i1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        System.out.println(i1.toEpochMilli());
    }

    //Duration 计算时间之间的间隔
    //Period计算日期之间的间隔

    @Test
    public void test3(){
        Instant i1=Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant i2=Instant.now();
        Duration dur=Duration.between(i1,i2);
        System.out.println(dur.toMillis());
    }

    //TemporalAdjuster 时间校正器
    @Test
    public void test4(){
        LocalDateTime ldt=LocalDateTime.now();

        LocalDateTime ldt1= ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt1);

        LocalDateTime ldt3= ldt.with(l->{
            LocalDateTime ldt2= (LocalDateTime) l;
            DayOfWeek dow=ldt2.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY))
                return ldt2.plusDays(3);
            else if(dow.equals(DayOfWeek.SATURDAY))
                return ldt2.plusDays(2);
            else
                return ldt2.plusDays(1);
        });
        System.out.println(ldt3);
    }

    //ZoneId 时区
    @Test
    public void test5(){
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
        System.out.println("=================================");
        System.out.println(LocalDateTime.now(ZoneId.of("Asia/Novosibirsk")));
    }
}
