package com.qlick.palindromeassignment.utility;

public class Palindrome {
	
	public static boolean isPalindrome(String word)
	{
		if(word.length()==0||word.length()==1)
		{
			return true;
		}
		// "[\\s.,]" pattern is used to match tab spaces (,) comma and fullstop
		//"[\\w]" pattern is used to remove 
		
		String stripped = word.toLowerCase().replaceAll("[\\W+]", "");
		
		int len = stripped.length();
		
		for (int start = 0; start < (len / 2) + 1; ++start) {
			
		    if (stripped.charAt(start) != stripped.charAt(len - start - 1)) {
		    	
		    		return false;
		    	}
			}
		return true;
		
		}

}
