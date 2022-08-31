package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Login;
import com.persistence.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	
//	@Override
//	public boolean isValid(String id, String Password) {
//		
//		Login login =  loginDao.findPasswordById(id);
//		if(login == null)
//			return false;
//		String password = login.getPassword();
//		if(Password.equals(password))
//			return true;
//		return false;
//			
//	}
//	
	@Override
	public Login getLoginById(String id) {
		
		Optional<Login> login = loginDao.findById(id);
		if(login.isPresent()) {
			return login.get();
		}else {
			return new Login();
		}
	}

	@Override
	public Login saveLogin(Login login) {
		return loginDao.save(login);
	}
	
	@Override
	public Login deleteLogin(String id) {
		Optional<Login> loginOptional=loginDao.findById(id);
		if(loginOptional.isPresent()) {
			loginDao.deleteById(id);
			return loginOptional.get();
		}
		return new Login();
	}
//	@Override
//	public boolean registerUser(String id, String password) {
//		if(loginDao.save(new Login(id,password)) != null)
//			return true;
//		return false;
//	}
}