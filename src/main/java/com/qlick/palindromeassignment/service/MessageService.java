package com.qlick.palindromeassignment.service;

import java.util.List;

import com.qlick.palindromeassignment.entity.Message;

public interface MessageService {
	
	Message findById(String id);
	
	List<Message> findAll();
	
	void delete(int id);
	
	Message save(Message message);
		


}
