package com.Hotel.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import org.springframework.stereotype.Repository;

import com.Hotel.model.Booking;


@Repository
public interface BookRepository extends JpaRepository<Booking,Integer>{
	
	
	
}
