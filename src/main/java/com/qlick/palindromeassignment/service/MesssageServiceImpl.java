package com.qlick.palindromeassignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qlick.palindromeassignment.dao.MessageDao;
import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.exceptions.MessageNotFoundException;
import com.qlick.palindromeassignment.utility.Palindrome;

@Service
public class MesssageServiceImpl implements MessageService {
	
	
	@Autowired
	@Qualifier("messageDaoJpaImpl")
	private MessageDao messageDao;

	@Override
	@Transactional
	public Message findById(String id) {
		
		Message message = null;
		
		try {
			
			int messageId= Integer.valueOf(id);
			 message = messageDao.findById(messageId);

		}	
		catch(Exception e)
		{
			throw new NumberFormatException();
		}
		// throw exception if null
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
		
		
		return message;
	}

	
	
	@Override
	@Transactional
	public List<Message> findAll() {
		
		return messageDao.findAll();
	}

	
	
	
	@Override
	@Transactional
	public void delete(int id) {
		
		Message message = null;
		
		try {
			
			int id1= Integer.valueOf(id);
			
			message = messageDao.findById(id1);

		}
		catch(Exception e)
		{
			throw new NumberFormatException();
		}
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
		messageDao.delete(id);

	}

	@Override
	@Transactional
	public Message save(Message message) {
		boolean value = Palindrome.isPalindrome(message.getWord());
		message.setPalindrome(value);
		return messageDao.save(message);
		

	}


}
