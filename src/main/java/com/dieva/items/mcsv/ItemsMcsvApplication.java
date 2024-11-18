package com.dieva.items.mcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ItemsMcsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemsMcsvApplication.class, args);
	}

}
