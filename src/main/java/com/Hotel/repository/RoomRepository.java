package com.Hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Hotel.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {

		
	@Query(value="select * from Room where roomNo=?1",nativeQuery=true)
	public Room find(String roomno);
		

	
	
}
