package dev.evertonsavio.app.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RListReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Lec09ListQueueStackTest extends BaseTest{

    @Test
    public void listTest(){
        //lrange number-input 0 -1
        RListReactive<Long> redisList = this.client.getList("number-input", LongCodec.INSTANCE);

        List<Long> longList = LongStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

//        Not Sequencial
//        Flux<Void> longList = Flux.range(1, 10)
//                .map(Long::valueOf)
//                .flatMap(i -> redisList.add(i).then());

        StepVerifier.create(redisList.addAll(longList).then())
                .verifyComplete();

        StepVerifier.create(redisList.size())
                .expectNext(10)
                .verifyComplete();

    }

}
