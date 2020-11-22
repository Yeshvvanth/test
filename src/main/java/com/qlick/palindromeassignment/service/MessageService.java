package com.qlick.palindromeassignment.service;

import java.util.List;

import com.qlick.palindromeassignment.entity.Message;

public interface MessageService {
	
	
	/**
	 * This method returns the message object for the given id.
	 * @param id the message Id
	 * @return the message object, or {@code null} if none
	 */
	Message findById(int id);
	
	/**
	 * This method returns a {@code List} with the message objects contained in this {@code List}.
	 * @return the {@code List} with message objects, or {@code null} if none
	 */
	List<Message> findAll();
	
	/**
	 * This method deletes the message object for the given id.
	 * @param id the message Id
	 */
	void delete(int id);
	
	
	/**
	 * This method determines if a message is palindrome or not and
	 * saves the message object.
	 * @param message the message object
	 * @return the message object, or {@code null} if none
	 */
	Message save(Message message);
		


}
