package com.example.SpringTdd;

import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TddApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddApplication.class, args);
    }

    @Bean
    CommandLineRunner initdatabase(BookingRepository bookingRepository) {
        return args -> {
            bookingRepository.deleteAll();
            BookingModel reserve = new BookingModel();
            LocalDate checkIn = LocalDate.parse("2022-11-10");
            LocalDate checkOut = LocalDate.parse("2022-11-20");
            reserve.setId("1");
            reserve.setCheckIn(checkIn);
            reserve.setCheckOut(checkOut);
            reserve.setNumberGuests("2");
            reserve.setReserveName("Victor e Carolyne");
            bookingRepository.save(reserve);
        };

    }

}



