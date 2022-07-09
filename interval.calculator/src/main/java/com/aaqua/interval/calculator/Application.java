package com.aaqua.interval.calculator;

import com.aaqua.interval.calculator.model.IntervalDTO;
import com.aaqua.interval.calculator.service.IntervalCalculatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(IntervalCalculatorService intervalCalculatorService) {
        return args -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Set<IntervalDTO> intervalDTOSet = Set.of(
                    IntervalDTO.builder()
                            .id(0)
                            .start(LocalDateTime.parse("24/06/2014 08:22:07", dateTimeFormatter))
                            .end(LocalDateTime.parse("28/01/2016 12:10:14", dateTimeFormatter))
                            .build(),
                    IntervalDTO.builder()
                            .id(1)
                            .start(LocalDateTime.parse("05/08/2017 15:38:55", dateTimeFormatter))
                            .end(LocalDateTime.parse("18/01/2019 08:34:26", dateTimeFormatter))
                            .build(),
                    IntervalDTO.builder()
                            .id(2)
                            .start(LocalDateTime.parse("06/06/2019 04:12:34", dateTimeFormatter))
                            .end(LocalDateTime.parse("12/07/2019 08:56:34", dateTimeFormatter))
                            .build(),
                    IntervalDTO.builder()
                            .id(3)
                            .start(LocalDateTime.parse("21/10/2020 14:14:59", dateTimeFormatter))
                            .end(LocalDateTime.parse("23/03/2021 19:43:55", dateTimeFormatter))
                            .build(),
                    IntervalDTO.builder()
                            .id(4)
                            .start(LocalDateTime.parse("17/10/2021 16:14:29", dateTimeFormatter))
                            .end(LocalDateTime.parse("30/01/2022 08:35:50", dateTimeFormatter))
                            .build()
            );

            intervalDTOSet.forEach(intervalCalculatorService::addAnIntervalReq);
        };
    }
}
