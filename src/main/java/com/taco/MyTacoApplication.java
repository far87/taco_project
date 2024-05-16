package com.taco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
/*
{@SpringBootConfiguration:definisce questa classe una classe di configurazione , 
@EnableAutoConfiguration: abilita l'auto configurazione, 
@ComponentScanning: abilita il component scanning}
*/
public class MyTacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTacoApplication.class, args);
	}

}