package dev.evertonsavio.app.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

}
