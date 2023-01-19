package com.example.SpringTdd.repository;

import com.example.SpringTdd.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<BookingModel, String> {
    @Transactional(readOnly = true)
    @Query(" SELECT * FROM booking_model bm WHERE bm.reserve_name = :reserveName ORDER BY bm.reserve_name")
    Optional<BookingModel> findByReserveName(@Param("reserveName") String reserveName);

}
