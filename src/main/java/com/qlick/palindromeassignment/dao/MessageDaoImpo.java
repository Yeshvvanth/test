package com.qlick.palindromeassignment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlick.palindromeassignment.entity.Message;

@Repository
public class MessageDaoImpo implements MessageDao {
	
	private static List<Message> messages = new ArrayList();

	@Override
	public Message findById(int id) {
		
		
		for(Message message:messages)
		{
			if(id == message.getId())
			{
				return message;
			}
		}
		
		return null;
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return messages;
	}

	@Override
	public void delete(int id) {
		
		for(Message message:messages)
		{
			if(id == message.getId())
			{
				messages.remove(message);
			}
		}

	}

	@Override
	public void save(Message message) {
		if(message.getId()==0)
		{
			messages.add(message);
		}
		else
		{
			for(Message TempMessage:messages)
			{
				if(TempMessage.getId()==message.getId())
				messages.set(messages.indexOf(TempMessage),message);
			}
		}

	}



}
