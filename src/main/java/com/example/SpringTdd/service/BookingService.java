package com.example.SpringTdd.service;

import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingrepository;
    public int daysCalculatorWithDatabase(String name) {
        Optional<BookingModel> bookingModelOptional = bookingrepository.findByReserveName(name);
        return Period.between(bookingModelOptional.get().getCheckIn(),bookingModelOptional.get().getCheckOut()).getDays();
    }
}
