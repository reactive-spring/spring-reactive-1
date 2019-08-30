package com.example.reactive.reactiveweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FluxSamples {

    @Bean
    RouterFunction<ServerResponse> routes(){
        return route(GET("/route/hello"), request -> ServerResponse.ok().body(Flux.just("Hello World"),String.class))
                .andRoute(GET("/route/hi"), request -> ServerResponse.ok().body(generateFromList(),String.class));
    }

    ArrayList<String> getSomeLongList(){
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arr.add("4");
        return arr;
    }

    static void someObserver(Object i){

    }

    private static void incrementTerminate() {

    }

    public Flux generateFromList() {
        return Flux.fromIterable(getSomeLongList())
                .delayElements(Duration.ofMillis(100))
                .doOnNext(FluxSamples::someObserver)
                .map(d -> d + " map ")
                //.interval(Duration.ofSeconds(4))
                .take(3)
                //.onErrorResumeWith(errorHandler::fallback)
                .doAfterTerminate(FluxSamples::incrementTerminate);
        //.subscribe(System.out::println);
    }

    Flux<Integer> f(){

        Flux.just(1, 6, 2, 8, 3, 1, 5, 1)
                .collectSortedList(Comparator.reverseOrder())
                .subscribe(System.out::println);

        return Flux.range(1, 100) // (1)
                .repeat(); // (2)
        //.collectList().flux(); // (3)
        //.block();

    }




    public Flux<Integer> generateFibonacciWithTuples() {
        return Flux.generate(
                () -> Tuples.of(0, 1),
                (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }
        );
    }
}
