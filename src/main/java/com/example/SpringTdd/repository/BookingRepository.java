package com.example.SpringTdd.repository;

import com.example.SpringTdd.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<BookingModel, String> {
    @Transactional(readOnly = true)
    @Query("select b from BookingModel b where b.reserveName like ?1")
    Optional<BookingModel> findByReserveName(String reserveName);

}
