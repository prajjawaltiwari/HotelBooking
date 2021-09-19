package com.Hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Hotel.model.Dates;
import com.Hotel.model.Room;

@Repository
public interface DateRepository extends JpaRepository<Dates, Integer>{
	
	
	@Query(value="select * from Dates r where r.date=?1",nativeQuery=true)
	public Dates getDateDetails(String dates);
	
	}
