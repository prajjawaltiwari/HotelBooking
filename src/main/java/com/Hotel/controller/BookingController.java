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
import com.Hotel.model.BookingDetails;
import com.Hotel.model.Room;
import com.Hotel.proxy.LoginProxy;
import com.Hotel.repository.RoomRepository;
import com.Hotel.service.BookingService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookingController {

	@Autowired
	LoginProxy loginProxy;

	@Autowired
	BookingService bookingService;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	private AuthClient authClient;

	@PostMapping("/addRoom")
	public String addRoom(@RequestHeader(name = "Authorization") String token, @RequestBody Room room) {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			roomRepository.save(room);
			return "Details added successfully";

		} else {
			log.debug("Access Denied : {}", "formulateTreatmentTimeTable");
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
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			roomRepository.deleteById(room.getRoomNo());
			return "Deleted successfully";

		} else {
			return "Access Denied";
		}

	}

	@PostMapping("/updateRoom")
	public String updateRoomDetail(@RequestHeader(name = "Authorization") String token, @RequestBody Room room) {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			roomRepository.save(room);
			return "Updated successfully";

		} else {
			return "Access Denied";
		}

	}

	@PostMapping("/booking")
	public String bookRoom(@RequestHeader(name = "Authorization") String token, @RequestBody BookingDetails details) {
		AuthResponse authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			String result = bookingService.saveBookingDetails(details);
			return result;

		} else {
			return "Access Denied";
		}

	}

}
