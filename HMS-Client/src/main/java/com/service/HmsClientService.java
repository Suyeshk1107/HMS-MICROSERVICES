package com.service;

public interface HmsClientService {

	boolean isValid(String id, String password);

	boolean registerUser(String userId, String password);

}
