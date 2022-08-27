package com.service;

import org.springframework.stereotype.Service;

import com.bean.Login;

@Service
public interface LoginService {
//	public boolean isValid(String id, String Password);

	Login getLoginById(String id);

	Login saveLogin(Login login);

	Login deleteLogin(String id);
}
