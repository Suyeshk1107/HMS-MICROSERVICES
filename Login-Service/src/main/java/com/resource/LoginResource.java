package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Login;
import com.service.LoginService;

@RestController
public class LoginResource {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping(path = "/logins/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Login getLoginDetailsById(@PathVariable("id") String id) {
		return loginService.getLoginById(id);
	}

	@PostMapping(path="/logins",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Login saveLoginResource(@RequestBody Login login) {
		return loginService.saveLogin(login);
	}
	
	@DeleteMapping(path = "/logins/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Login deleteLoginResource(@PathVariable("id") String id) {
		return loginService.deleteLogin(id);
	}
	
}
