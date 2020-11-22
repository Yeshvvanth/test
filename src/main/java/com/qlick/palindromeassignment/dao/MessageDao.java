package com.qlick.palindromeassignment.dao;

import java.util.List;

import com.qlick.palindromeassignment.entity.Message;

public interface MessageDao {
	
	/**
	 * This method returns the message object for the given id.
	 * @param id the message Id
	 * @return the message object, or {@code null} if none
	 */
	Message findById(int id);
	
	
	/**
	 * This method returns a {@code List} with the message objects contained in this {@code List}.
	 * @return the message objects, or {@code null} if none
	 */
	List<Message> findAll();
	
	
	/**
	 * This method deletes the message object for the given id.
	 * @param id the message Id
	 */
	void delete(int id);
	
	
	/**
	 * This method saves the message object.
	 * @param message the message object
	 * @return the Message object, or {@code null} if none
	 */
	Message save(Message message);


}
