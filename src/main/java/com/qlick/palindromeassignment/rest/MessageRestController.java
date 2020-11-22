package com.qlick.palindromeassignment.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	
	
	
	/**
	 *  Injects MessageService instance 
	 *  Handles business logic
	 */
	@Autowired
	private MessageService messageService;

	
	
	
	
	/** Return a JSON {@code Array} with the Message Objects contained in this {@code Array}.
	 * @return List of message objects
	 */
	@GetMapping("/messages")
	@ApiOperation(value="Returns the list of Messages along with respective properties"
					)
	public List<Message> getMessages()
	{
		List<Message> messages =  messageService.findAll();
		
		
		//hateoas links
		for(Message tempMessage:messages)
		{
			
			
			Link selfLink = linkTo(methodOn(MessageRestController.class)
		                .getSpecificMessage(Integer.toString(tempMessage.getId()))).withSelfRel();
	        Link messagesLink = linkTo(methodOn(MessageRestController.class)
	                .getMessages()).withRel("messages");
	        Link deleteLink = linkTo(methodOn(MessageRestController.class)
	                .deleteMessage(Integer.toString(tempMessage.getId()))).withRel("delete");
        
			tempMessage.add(selfLink);
			tempMessage.add(messagesLink);
			tempMessage.add(deleteLink);

		}
		
		return messages;
	}
	
	
	
	
	/** This method creates a Message Object
	 *  Stores the created Message Object in database
	 * @param message  Message Object sent by client using Post Request
	 * @return message  Reference of created Message Object
	 */
	
	@PostMapping("/messages")
	@ResponseStatus(code=HttpStatus.CREATED)
	@ApiOperation(value="Creates a New Message and determines if it's a palindrome")
	@ApiResponses(value = {
			@ApiResponse(code=400,message="Invalid Request Body"),
			@ApiResponse(code=400,message="Empty Request Body"),
			@ApiResponse(code=405,message="Invalid Http Method")})
	public Message addMessage(@Valid @RequestBody Message message)
	{
		//just in case if user passes an id
		message.setId(0);
		
		Message createdMessage = messageService.save(message);;
		
		Link messageLink = linkTo(methodOn(MessageRestController.class)
                .getSpecificMessage(Integer.toString(createdMessage.getId()))).withRel("message");
		
        Link messagesLink = linkTo(methodOn(MessageRestController.class)
                .getMessages()).withRel("messages");
        
        Link deleteLink = linkTo(methodOn(MessageRestController.class)
                .deleteMessage(Integer.toString(createdMessage.getId()))).withRel("delete");
        
        createdMessage.add(messageLink);
        createdMessage.add(messagesLink);
        createdMessage.add(deleteLink);
		
		return createdMessage;
		 
		 
		
	}
	
	
	   /**
	   * Return the Message object for the given id.
	   * @param  id     Parameter is used for identifying a message from database
	   * @return message   Returns the Message Object for the specified Id
	   */

	@GetMapping("/messages/{id}")
	@ApiOperation(value="Returns a specific Message with respective properties")
	@ApiResponses(value = {
			@ApiResponse(code=404,message="Message not found"),
			@ApiResponse(code=400,message="Only Number can be passed as paramter")})
	public Message getSpecificMessage(@PathVariable String id)
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
		
	    Link selfLink = linkTo(methodOn(MessageRestController.class)
	                .getSpecificMessage(id)).withSelfRel();
        Link messagesLink = linkTo(methodOn(MessageRestController.class)
                .getMessages()).withRel("messages");
        Link deleteLink = linkTo(methodOn(MessageRestController.class)
                .deleteMessage(id)).withRel("delete");
        
        
        message.add(selfLink);
        message.add(messagesLink);
        message.add(deleteLink);
		
		
		return message;
	}
	
	
	
	
	
	/** This method delete Message from database
	 * @param id       Paramter is used for identifying Message for deletion
	 * @return HTTP Status code      Returns HTTP Status code on successful deletion
	 */
	@DeleteMapping("/messages/{id}")
	@ApiOperation(value="Deletes a  specific Message")
	@ApiResponses(value = {
			
			@ApiResponse(code=200,message="Message successfully deleted"),
			@ApiResponse(code=404,message="Message not found")
		
	})
	public ResponseEntity<String> deleteMessage(@PathVariable String id)
	{
		
			
		int messageId = Integer.valueOf(id);;
		
	
		Message message = messageService.findById(messageId);
	
		if (message == null) {
			throw new MessageNotFoundException("Message not found - " + id);
		}
		
		messageService.delete(messageId);
			 
		return new ResponseEntity<String>("Deleted message id - " + id,HttpStatus.OK);

		
	}
	

	
	
	
}
