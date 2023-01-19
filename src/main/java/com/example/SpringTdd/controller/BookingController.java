package com.example.SpringTdd.controller;

import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingModel> list() {
        return bookingService.findAll();
    }

    @GetMapping("/{reserveName}")
    public ResponseEntity<BookingModel> getReserve(String reserveName) {
        return bookingService.findByReserveName(reserveName);
    }

    @PostMapping
    public ResponseEntity<BookingModel> save(BookingModel bookingModel) {
        return bookingService.createReserve(bookingModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingModel> update(@PathVariable String id, @RequestBody BookingModel bookingModel) {
        return bookingService.updateReserve(id, bookingModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return bookingService.deleteReserve(id);
    }
}
