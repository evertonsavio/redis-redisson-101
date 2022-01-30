package dev.evertonsavio.app.redisson.test;

import dev.evertonsavio.app.redisson.dto.Student;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Lec06KeyMapTest extends BaseTest{

    @Test
    public void mapTest(){

        RMapReactive<Object, Object> map = this.client.getMap("user:1", StringCodec.INSTANCE);
        Mono<Object> name = map.put("name", "sam");
        Mono<Object> age = map.put("age", "10");
        Mono<Object> city = map.put("city", "atlanta");

        StepVerifier.create(name.concatWith(age).concatWith(city).then())
                .verifyComplete();

        //keys *
        // hgetall user:1
        // hget user:1 age

    }

    @Test
    public void mapTest2(){

        RMapReactive<Object, Object> map = this.client.getMap("user:2", StringCodec.INSTANCE);
        Map<String, String> javaMap = Map.of(
                "name", "jake",
                "age", "30",
                "city", "miami"
        );

        StepVerifier.create(map.putAll(javaMap).then())
                .verifyComplete();
    }

    @Test
    public void mapTest3(){
        // Map<Integer,Student>
        TypedJsonJacksonCodec codec = new TypedJsonJacksonCodec(Integer.class, Student.class);
        RMapReactive<Integer, Student> map = this.client.getMap("users", codec);

        Student student1 = new Student("sam", 10, "atlanta", List.of(1,2,3));
        Student student2 = new Student("jake", 12, "miami", List.of(2,1,3));

        Mono<Student> mono1 = map.put(1, student1);
        Mono<Student> mono2 = map.put(2, student2);

        StepVerifier.create(mono1.concatWith(mono2).then())
                .verifyComplete();
    }

}
