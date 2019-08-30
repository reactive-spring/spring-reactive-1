package com.example.reactive.reactiveweb;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Базовый пример:
 * Билдер ответа
 * ServerResponse. - куда кладем ответ
 * ok().	- статус ответа
 * body(  -  место куда кладется содержимое тела ответа - тело, (или заголовки)
 * Flux.just - генерация данных -  только одно значение
 *
 * http://localhost:8080/route/hello - - одно значение потому что Just
 * http://localhost:8080/route/hi - одно значение потому что Just
 *
 */


@Configuration
public class FunctionalJavaConfiguration {

	@Bean
	RouterFunction<ServerResponse> routes(){
		return route(GET("/route/hello"), request -> ServerResponse.ok().body(Flux.just("Hello World"),String.class))
				.andRoute(GET("/route/hi"), request -> ServerResponse.ok().body(Flux.just("Hi", "Hi 2", "Hi 3"),String.class));
	}

}
