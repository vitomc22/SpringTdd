package com.example.SpringTdd.service;

import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingrepository;

    public int daysCalculatorWithDatabase(String name) {
        Optional<BookingModel> bookingModelOptional = bookingrepository.findByReserveName(name);
        return Period.between(bookingModelOptional.get().getCheckIn(), bookingModelOptional.get().getCheckOut()).getDays();
    }

    public List<BookingModel> findAll() {
        return bookingrepository.findAll();
    }

    public ResponseEntity<BookingModel> findByReserveName(String reserveName) {
        return bookingrepository.findByReserveName(reserveName).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<BookingModel> createReserve(@RequestBody BookingModel bookingModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingrepository.save(bookingModel));
    }

    public ResponseEntity<BookingModel> updateReserve(@PathVariable String reserveName, @RequestBody BookingModel bookingModel) {
        return bookingrepository.findByReserveName(reserveName).map(recordFound -> {
            recordFound.setReserveName(bookingModel.getReserveName());
            recordFound.setNumberGuests(bookingModel.getNumberGuests());
            recordFound.setCheckOut(bookingModel.getCheckOut());
            recordFound.setCheckIn(bookingModel.getCheckIn());
            BookingModel updated = bookingrepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteReserve(@PathVariable String id) {
        return bookingrepository.findById(id).map(recordFound -> {
            bookingrepository.delete(recordFound);
            return ResponseEntity.noContent().<Void>build(); //necessário cast para vazio pois delete retorna VOID
        }).orElse(ResponseEntity.notFound().build());
    }
}
