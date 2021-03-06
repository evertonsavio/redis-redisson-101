package dev.evertonsavio.app.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RDequeReactive;
import org.redisson.api.RListReactive;
import org.redisson.api.RQueueReactive;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.function.Function;
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

    @Test
    public void queueTest(){
        //lrange number-input 0 -1
        RQueueReactive<Long> queue = this.client.getQueue("number-input", LongCodec.INSTANCE);

        Mono<Void> queuePool = queue.poll() //1 //2
                .repeat(4)
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(queuePool)
                .verifyComplete();

        StepVerifier.create(queue.size())
                .expectNext(6)
                .verifyComplete();
    }

    @Test
    public void stackTest(){ //Deque
        //lrange number-input 0 -1
        RDequeReactive<Long> deque = this.client.getDeque("number-input", LongCodec.INSTANCE);

        Mono<Void> dequePool = deque.pollLast()
                .repeat(4)
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(dequePool)
                .verifyComplete();

        StepVerifier.create(deque.size())
                .expectNext(5)
                .verifyComplete();
    }

}
