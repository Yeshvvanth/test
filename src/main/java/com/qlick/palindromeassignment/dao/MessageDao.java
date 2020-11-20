package com.qlick.palindromeassignment.dao;

import java.util.List;

import com.qlick.palindromeassignment.entity.Message;

public interface MessageDao {
	
	Message findById(int id);
	
	List<Message> findAll();
	
	void delete(int id);
	
	Message save(Message message);


}
