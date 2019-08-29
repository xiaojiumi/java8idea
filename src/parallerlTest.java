import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class parallerlTest {

    @Test
    public void test(){
        Instant s=Instant.now();

        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant e=Instant.now();
        System.out.println("time:"+ Duration.between(s,e).toMillis());

    }
}

