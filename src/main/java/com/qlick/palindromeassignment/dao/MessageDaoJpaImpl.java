package com.qlick.palindromeassignment.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qlick.palindromeassignment.entity.Message;

@Repository
public class MessageDaoJpaImpl implements MessageDao {
	
	@Autowired
	private EntityManager entityManager;
	
	

	@Override
	public Message findById(int id) {
		
		
		return entityManager.find(Message.class,id);
	}
	
	@Override
	public List<Message> findAll() {
		
		
		//create a query
		TypedQuery<Message> theQuery = entityManager.createQuery("from Message",Message.class);
		
		//execute query and get result set
		List<Message> messages = theQuery.getResultList();
		
		return messages;
	}

	@Override
	public void delete(int id) {
		
		Query theQuery = entityManager.createQuery("delete from Message where id=:messageId");
		theQuery.setParameter("messageId",id);
		theQuery.executeUpdate();

	}

	@Override
	public Message save(Message message) {
		
		Message savedMessage = entityManager.merge(message);
		return savedMessage;
	}


}
