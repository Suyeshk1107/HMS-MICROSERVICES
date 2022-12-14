package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.bean.Login;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginService {

	@Autowired
	private RestTemplate restTemplate;

	@CircuitBreaker(name = "login", fallbackMethod = "getLoginFallBack")
	public Login getLogin(String userId) {
		return restTemplate.getForObject("http://login-service/logins/" + userId, Login.class);
	}

	@CircuitBreaker(name = "login", fallbackMethod = "saveLoginFallback")
	public Login saveLogin(String userId, String password) {

		return restTemplate.postForObject("http://login-service/logins", new Login(userId, password), Login.class);
	}

	public Login getLoginFallBack(Exception e) {
		return new Login("no user ID", "no password");
	}

	public Login saveLoginFallback(Exception e) {

		return new Login("no user ID", "no password");

	}

	@CircuitBreaker(name = "login", fallbackMethod = "deleteLoginFallback")
	public Login deleteLogin(String userId) {

		restTemplate.delete("http://login-service/logins/" + userId);
		return restTemplate.getForObject("http://login-service/logins/" + userId, Login.class);
	}

	public Login deleteLoginFallback(Exception e) {

		return new Login("no user ID", "no password");

	}

}