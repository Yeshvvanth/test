package com.qlick.palindromeassignment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.exceptions.MessageNotFoundException;
import com.qlick.palindromeassignment.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;

	@GetMapping("/messages")
	public List<Message> getMessages()
	{
		return messageService.findAll();
	}
	@PostMapping("/messages")
	@ResponseStatus(HttpStatus.CREATED)
	public Message addMessage(@Valid @RequestBody Message message)
	{
		//just in case if user passes an id
		message.setId(0);
		
		 messageService.save(message);
		 
		 return message;
		 
		 
		
	}
	@PutMapping("/messages/{id}")
	public Message updateMessages(@PathVariable int id, @RequestBody Message message)
	{
		
		message.setId(id);
		 messageService.save(message);
		 return message;
	}
	@GetMapping("/messages/{id}")
	public Message getMessages(@PathVariable int id)
	{
		
		Message message = messageService.findById(id);
		
		// throw exception if null
		
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
		return message;
	}
	@DeleteMapping("/messages/{id}")
	public String deleteMessages(@PathVariable int id)
	{
		Message message = messageService.findById(id);
		
		// throw exception if null
		
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
 		
		messageService.delete(id);
			 
		return "Deleted message id - " + id;


		
	}
	

	
	
	
}
