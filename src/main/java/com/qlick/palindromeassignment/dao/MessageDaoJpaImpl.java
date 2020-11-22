package com.qlick.palindromeassignment.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qlick.palindromeassignment.entity.Message;

/**
 * @author yeshwanthreddygaddam
 *
 */
@Repository
public class MessageDaoJpaImpl implements MessageDao {
	
	/**
	 * The <code>EntityManager</code> API is used 
	 * to create and remove persistent entity instances, to find entities 
	 * by their primary key, and to query over entities.
	 */
	@Autowired
	private EntityManager entityManager;
	
	

	/**
	 * This method returns the message object for the given id.
	 * @param id the message Id
	 * @return the message object, or {@code null} if none
	 */
	@Override
	public Message findById(int id) {
		
		
		return entityManager.find(Message.class,id);
	}
	
	/**
	 * This method returns a {@code List} with the message objects contained in this {@code List}.
	 * @return the message objects, or {@code null} if none
	 */
	@Override
	public List<Message> findAll() {
		
		
		//create a query
		TypedQuery<Message> theQuery = entityManager.createQuery("from Message",Message.class);
		
		//execute query and get result set
		List<Message> messages = theQuery.getResultList();
		
		return messages;
	}

	/**
	 * This method deletes the message object for the given id.
	 * @param id the message Id
	 */
	@Override
	public void delete(int id) {
		
		Query theQuery = entityManager.createQuery("delete from Message where id=:messageId");
		theQuery.setParameter("messageId",id);
		theQuery.executeUpdate();

	}

	/**
	 * This method saves the message object.
	 * @param message the message object
	 * @return the Message object, or {@code null} if none
	 */
	@Override
	public Message save(Message message) {
		
		Message savedMessage = entityManager.merge(message);
		return savedMessage;
	}


}
