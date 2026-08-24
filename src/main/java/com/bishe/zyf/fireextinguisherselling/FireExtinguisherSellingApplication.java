package com.bishe.zyf.fireextinguisherselling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bishe.zyf.fireextinguisherselling.mapper")
public class FireExtinguisherSellingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireExtinguisherSellingApplication.class, args);
    }

}
