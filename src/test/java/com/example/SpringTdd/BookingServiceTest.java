package com.example.SpringTdd;


import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.repository.BookingRepository;
import com.example.SpringTdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
    @Before
    public void setup(){ //metodo mock para nao usar banco
        LocalDate checkIn = LocalDate.parse("2022-11-10");
        LocalDate checkOut = LocalDate.parse("2022-11-20");
        BookingModel bookingModel = new BookingModel("1","Victor",checkIn,checkOut,1);

        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName())) //quando necessario usar o repository o mock é acionado com metodo do repository
                .thenReturn(Optional.of(bookingModel));
    }

    @TestConfiguration //Only test Bean
    static class BookinfServiceTestConfiguration{  //injection point bean
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }

    }

    @Autowired
    BookingService bookingservice;
    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTesteServiceDaysCalculator(){
        String name = "Victor";
        int days = bookingservice.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days,10);

    }



}
