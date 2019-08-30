package com.example.reactive.reactiveweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run in browser:	http://localhost:8080/client.html
 * Смотрите асинхронный поток ответов
 *
 *
 */
@SpringBootApplication
public class ReactiveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebApplication.class, args);
	}
}
