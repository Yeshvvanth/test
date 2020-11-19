package com.qlick.palindromeassignment.service;

import java.util.List;

import com.qlick.palindromeassignment.entity.Message;

public interface MessageService {
	
	Message findById(int id);
	
	List<Message> findAll();
	
	void delete(int id);
	
	void save(Message message);
		


}
