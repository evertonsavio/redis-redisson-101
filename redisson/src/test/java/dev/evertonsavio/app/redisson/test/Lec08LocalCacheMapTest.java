package dev.evertonsavio.app.redisson.test;

import dev.evertonsavio.app.redisson.dto.Student;
import dev.evertonsavio.app.redisson.test.config.RedissonConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec08LocalCacheMapTest extends BaseTest{

    private RLocalCachedMap<Integer, Student> studentsMap;

    @BeforeAll
    public void setupClient(){
        RedissonConfig config = new RedissonConfig();
        RedissonClient redissonClient = config.getClient();

        //SYNC STRATEGY (NONE, INVALIDADE, UPDATE)
        //RECONNECT STRATEGY (CLEAR, NONE)
        LocalCachedMapOptions<Integer, Student> mapOptions = LocalCachedMapOptions.<Integer, Student>defaults()
                .syncStrategy(LocalCachedMapOptions.SyncStrategy.UPDATE)
                .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.NONE);
                //.timeToLive(10, TimeUnit.DAYS);

         this.studentsMap = redissonClient.getLocalCachedMap(
                "students",
                new TypedJsonJacksonCodec(Integer.class, Student.class),
                mapOptions
         );
    }

    @Test
    public void appServer1(){
        Student student1 = new Student("sam", 10, "atlanta", List.of(1,2,3));
        Student student2 = new Student("jake", 12, "miami", List.of(2,1,3));

        this.studentsMap.put(1, student1);
        this.studentsMap.put(2, student2);

        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println(i + " ===> " + studentsMap.get(1)))
                .subscribe();

        sleep(600000);
    }

    @Test
    public void appServer2(){
        Student student1 = new Student("sam-updated", 10, "atlanta", List.of(1,2,3));
        this.studentsMap.put(1, student1);
    }

}
