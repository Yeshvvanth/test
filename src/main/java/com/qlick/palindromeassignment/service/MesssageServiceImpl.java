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

	@Override
	@Transactional
	public Message findById(int id) {
		
		return messageDao.findById(id);
	}

	@Override
	@Transactional
	public List<Message> findAll() {
		
		return messageDao.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		
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
