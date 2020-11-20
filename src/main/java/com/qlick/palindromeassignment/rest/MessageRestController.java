package com.qlick.palindromeassignment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.exceptions.MessageNotFoundException;
import com.qlick.palindromeassignment.service.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;

	@GetMapping("/messages")
	@ApiOperation(value="Returns the list of Messages along with respective properties"
					)
	public List<Message> getMessages()
	{
		return messageService.findAll();
	}
	
	
	@PostMapping("/messages")
	@ResponseStatus(code=HttpStatus.CREATED)
	@ApiOperation(value="Creates a New Message and determines if it's a palindrome")
	@ApiResponses(value = {
			@ApiResponse(code=400,message="Invalid Request Body"),
			@ApiResponse(code=400,message="Empty Request Body"),
			@ApiResponse(code=405,message="Invalid Http Method")

		
	})
	public Message addMessage(@Valid @RequestBody Message message)
	{
		//just in case if user passes an id
		message.setId(0);
		
		return  messageService.save(message);
		 
		 
		
	}
	
	
	@PutMapping("/messages/{id}")
	@ApiOperation(value="Updates a Message and determines if it's a palindrome")
	public Message updateMessage(@PathVariable int id, @RequestBody Message message)
	{
		
		message.setId(id);
		return messageService.save(message);
		
	}
	
	@GetMapping("/messages/{id}")
	@ApiOperation(value="Returns a specific Message with respective properties")
	@ApiResponses(value = {
			@ApiResponse(code=404,message="Message not found"),
			@ApiResponse(code=400,message="Only Number can be passed as paramter")
		
	})
	public EntityModel<Message> getSpecificMessage(@PathVariable String id)
	{
		Message message = null;
		try {
			int id1= Integer.valueOf(id);
			 message = messageService.findById(id1);

		}
		catch(Exception e)
		{
			throw new NumberFormatException();
		}
		
		// throw exception if null
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
		
		//hateoas
		//retrive all users
		EntityModel<Message> resource = EntityModel.of(message);
		WebMvcLinkBuilder linkTo = 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMessages());
		resource.add(linkTo.withRel("all-users"));

		
		return resource;
	}
	
	@DeleteMapping("/messages/{id}")
	@ApiOperation(value="Deletes a  specific Message")
	@ApiResponses(value = {
			
			@ApiResponse(code=200,message="Message successfully deleted"),
			@ApiResponse(code=404,message="Message not found")
		
	})
	public String deleteMessage(@PathVariable String id)
	{
		Message message = null;
		
		try {
			
			int id1= Integer.valueOf(id);
			
			message = messageService.findById(id1);
			

		}
		catch(Exception e)
		{
			throw new NumberFormatException();
		}
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
			 
		return "Deleted message id - " + id;


		
	}
	

	
	
	
}
