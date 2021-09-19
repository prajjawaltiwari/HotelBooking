package com.Hotel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel.model.Booking;
import com.Hotel.model.BookingDetails;
import com.Hotel.model.Dates;
import com.Hotel.model.Room;
import com.Hotel.repository.BookRepository;
import com.Hotel.repository.DateRepository;
import com.Hotel.repository.RoomRepository;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class BookingServiceImpl implements BookingService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	DateRepository dateRepository;
	
	
	/*saving real estate details*/
	public String saveBookingDetails(BookingDetails details,String uname) {
		Booking book=new Booking ();
		Dates dateobj=new Dates();
		dateobj.setUname(uname);
		book.setUname(uname);
		double price=0;
		try {
		for (Entry<String,String> pair : details.getDet().entrySet()) {
			List<String> myList = new ArrayList<String>(Arrays.asList(pair.getValue().split(",")));
			Room room=roomRepository.find(pair.getKey());
			for(int j=0;j<myList.size();j++) {
			Dates obj=dateRepository.getDateDetails(myList.get(j));
			if(obj==null) {
			book.setRoomNo(room.getRoomNo());
			book.setType(room.getType());
			int size=myList.size();
			String p1=room.getPrice();
			price+=(size*(Double.valueOf(p1)));
			}
			else {
				return "Sorry specified room not available at this day"+obj.getDate()+" for room no "+room.getRoomNo();
			}
			}
			for ( int i=0;i<myList.size();i++) {
			dateobj.setRoomNo(room.getRoomNo());
			dateobj.setDate(myList.get(i));
			
			}
		}
		}
		 catch (Exception e) {
			System.out.print(e);
		}
		book.setTotalPrice(String.valueOf(price));
		book.setDate(dateobj.toString());
		bookRepository.save(book);
		dateRepository.save(dateobj);
		return "Booking successfull total price is "+price;
	}

}
