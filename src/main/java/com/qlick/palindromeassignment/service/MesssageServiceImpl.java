package com.qlick.palindromeassignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qlick.palindromeassignment.dao.MessageDao;
import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.utility.Palindrome;

@Service
public class MesssageServiceImpl implements MessageService {
	
	
	@Autowired
	@Qualifier("messageDaoJpaImpl")
	private MessageDao messageDao;

	
	
	/**
	 * Return the Message object for the given id.
	 * @param id the Message Id
	 * @return the Message object, or {@code null} if none
	 */
	@Override
	@Transactional
	public Message findById(int id) {
		
		return messageDao.findById(id);
	}

	
	/**
	 * Return a {@code List} with the Message Objects contained in this {@code List}.
	 * @return the Message objects, or {@code null} if none
	 */
	@Override
	@Transactional
	public List<Message> findAll() {
		
		return messageDao.findAll();
	}

	
	/**
	 * Return the Message object for the given id.
	 * @param id the Message Id
	 * @return the Message object, or {@code null} if none
	 */
	@Override
	@Transactional
	public void delete(int id) {
		
		messageDao.delete(id);

	}

	/** This Method uses a utility class to determine
	 * whether a Message is palindrome or not. 
	 * Save the Message object.
	 * @param Message the Message Object
	 * @return the Message object, or {@code null} if none
	 */
	@Override
	@Transactional
	public Message save(Message message) {
		boolean value = Palindrome.isPalindrome(message.getWord());
		message.setPalindrome(value);
		return messageDao.save(message);
		

	}


}
