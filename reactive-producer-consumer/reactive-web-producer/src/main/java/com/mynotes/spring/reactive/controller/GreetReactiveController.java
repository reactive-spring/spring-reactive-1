package com.mynotes.spring.reactive.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import static java.lang.Thread.sleep;

@RestController
public class GreetReactiveController {

	/**
	 * Выводим 50 сообщений
	 *
	 */
	@GetMapping("/greetings")
	public Publisher<Greeting> greetingPublisher() {
		Flux<Greeting> greetingFlux = Flux.<Greeting>generate(sink -> sink.next(new Greeting("Hello"))).
				take(50);
		return greetingFlux;
	}

	/**
	 * Выводим сообщения, через 1 секунду
	 *
	 */

	@GetMapping(value = "/greetings/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Publisher<Greeting> sseGreetings() {
		Flux<Greeting> delayElements = Flux
				.<Greeting>generate(sink -> sink.next(new Greeting("Hello @" + Instant.now().toString())))
				.delayElements(Duration.ofSeconds(1));
		return delayElements;
	}

	@GetMapping(value = "/greetings/sse2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Publisher<Greeting> sseGreetings2() {
		Flux<Greeting> delayElements = Flux
				.<Greeting>generate(sink -> sink.next( new Greeting("Hello @" + Instant.now().toString() + " "+  sleepRandom())))
				.delayElements(Duration.ofSeconds(1));
		return delayElements;
	}

	int sleepRandom(){

		Random r = new Random();
		try {
			int next = r.nextInt(1000);
			sleep(next);
			return next;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
