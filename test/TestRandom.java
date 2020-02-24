package test;

import org.apache.commons.lang3.RandomStringUtils;

public class TestRandom {
	public static void main(String [] args){
		String generatedString = RandomStringUtils.randomAlphanumeric(32);
		 
	    System.out.println(generatedString);
	}
}
