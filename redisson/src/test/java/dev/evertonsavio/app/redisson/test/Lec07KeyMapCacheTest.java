package dev.evertonsavio.app.redisson.test;

import dev.evertonsavio.app.redisson.dto.Student;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lec07KeyMapCacheTest extends BaseTest{

    @Test
    public void mapCacheTest(){

        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, Student.class);
        RMapCacheReactive<Integer, Student> mapCache = this.client.getMapCache("users:cache", codec);
        // Map<Integer,Student>

        Student student1 = new Student("sam", 10, "atlanta", List.of(1,2,3));
        Student student2 = new Student("jake", 12, "miami", List.of(2,1,3));

        Mono<Student> cache1 = mapCache.put(1, student1, 5, TimeUnit.SECONDS);
        Mono<Student> cache2 = mapCache.put(2, student2, 10, TimeUnit.SECONDS);

        StepVerifier.create(cache1.concatWith(cache2).then())
                .verifyComplete();

        sleep(3000);

        mapCache.get(1).doOnNext(System.out::println).subscribe();
        mapCache.get(2).doOnNext(System.out::println).subscribe();

        sleep(3000);

        mapCache.get(1).doOnNext(System.out::println).subscribe();
        mapCache.get(2).doOnNext(System.out::println).subscribe();
    }

}
