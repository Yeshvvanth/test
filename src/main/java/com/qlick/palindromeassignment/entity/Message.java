package com.qlick.palindromeassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="Message")
@ApiModel()
public class Message extends RepresentationModel<Message> {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes="unique identifier for a Message")
	private int id;
	
	@Column
	@NotEmpty(message="Word is Mandatory.")
	@ApiModelProperty(notes="word cannot be empty",required=true)
	private String word;
	
	@Column
    @ApiModelProperty(value = "some description", readOnly = true)
	private boolean palindrome;
	
	
	
	public Message()
	{
		
	}
	

	public Message(String word, boolean palindrome) {
		this.word = word;
		this.palindrome = palindrome;
	}


	public Message(int id, String word, boolean b) {
		this.id = id;
		this.word = word;
		this.palindrome = b;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	

	public boolean isPalindrome() {
		return palindrome;
	}





	public void setPalindrome(boolean palindrome) {
		this.palindrome = palindrome;
	}





	@Override
	public String toString() {
		return "Messages [id=" + id + ", word=" + word + "]";
	}
	
	
	
	

}
