package com.bin.aircondition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bin"})
@MapperScan(basePackages = {"com.bin.aircondition.mapper"})
public class AirConditonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirConditonApplication.class, args);

    }

}
