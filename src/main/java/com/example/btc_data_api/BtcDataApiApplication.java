package com.example.btc_data_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BtcDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcDataApiApplication.class, args);
	}

}
