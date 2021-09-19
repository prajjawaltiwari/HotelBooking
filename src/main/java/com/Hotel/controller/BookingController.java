package com.Hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel.client.AuthClient;
import com.Hotel.client.AuthResponse;
import com.Hotel.exception.TokenInvalidException;
import com.Hotel.model.BookingDetails;
import com.Hotel.model.Room;
import com.Hotel.repository.RoomRepository;
import com.Hotel.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	private AuthClient authClient;

	@PostMapping("/addRoom")
	public String addRoom(@RequestHeader(name = "Authorization") String token, @RequestBody Room room) throws TokenInvalidException{
		try {
		AuthResponse authResponse = authClient.verifyToken(token);
		if (authResponse.isValid() && authResponse.getRole().equals("admin")) {
			roomRepository.save(room);
			return "Details added successfully";

		} 
		else {
			throw new TokenInvalidException("Access denied");
			}
		}
		catch(TokenInvalidException e) {
			log.debug("Access Denied : {}",e);
			return "Access Denied";
		}
	}

	@GetMapping("/viewRooms")
	public List<Room> getCollateralId() {
		List<Room> obj = roomRepository.findAll();
		return obj;
	}

	@PostMapping("/removeRoom")
	public String removeRoom(@RequestHeader(name = "Authorization") String token, @RequestBody Room room) {
		try {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid() && authResponse.getRole().equals("admin")) {
			roomRepository.deleteById(room.getRoomNo());
			return "Deleted successfully";
		}
			else {
				throw new TokenInvalidException("Access denied");
				}
			}
			catch(TokenInvalidException e) {
				log.debug("Access Denied : {}",e);
				return "Access Denied";
			}	}

	@PostMapping("/updateRoom")
	public String updateRoomDetail(@RequestHeader(name = "Authorization") String token, @RequestBody Room room) {
		try {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid() && authResponse.getRole().equals("admin")) {
			roomRepository.save(room);
			return "Updated successfully";

		} else {
			throw new TokenInvalidException("Access denied");
			}
		}
		catch(TokenInvalidException e) {
			log.debug("Access Denied : {}",e);
			return "Access Denied";
		}	}

	@PostMapping("/booking")
	public String bookRoom(@RequestHeader(name = "Authorization") String token, @RequestBody BookingDetails details) {
		try {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			String result = bookingService.saveBookingDetails(details,authResponse.getUid());
			return result;

		} else {
			throw new TokenInvalidException("Access denied");
			}
		}
		catch(TokenInvalidException e) {
			log.debug("Access Denied : {}",e);
			return "Access Denied";
		}

	}

}
