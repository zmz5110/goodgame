package org.lastsurprise.goodgame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lastsurprise.goodgame.mapper")
public class GoodgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodgameApplication.class, args);
	}

}
