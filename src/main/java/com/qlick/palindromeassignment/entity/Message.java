package com.qlick.palindromeassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Message")
public class Message {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotEmpty(message="Word is Mandatory.")
	private String word;
	
	@Column
	private boolean palindrome;
	
	
	
	public Message()
	{
		
	}
	
	
	


	public Message(String word, boolean palindrome) {
		this.word = word;
		this.palindrome = palindrome;
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
