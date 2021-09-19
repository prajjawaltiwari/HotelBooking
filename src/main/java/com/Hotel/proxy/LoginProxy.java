package com.Hotel.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@FeignClient(name = "login-service", url = "http://localhost:8084")
public interface LoginProxy {
	
	@GetMapping("/login/validatetoken/{token}")
	public String validatetoken(@PathVariable String token);

	
	
}
