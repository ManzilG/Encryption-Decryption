package com.encrypt.service;

import java.util.HashMap;
import java.util.Map;

import com.encrypt.exception.StringFormatException;

public class EncryptedAndDecryptedNames {
	
	private Map<String,String> encryptedNames;//map to store real names as keys and encrypted names as values
	
	private Map<String,String> decryptedNames;/*map to store encrypted names as keys and real or decrypted names as values
	so that access would be faster during decryption if encrypted values are in cache as keys */
	
	public EncryptedAndDecryptedNames() { //Constructor to initialize cache
		encryptedNames=new HashMap<String, String>();
		decryptedNames=new HashMap<String, String>();
	}

	public Map<String, String> getEncryptedNames() {
		return encryptedNames;
	}



	public void setEncryptedNames(Map<String, String> encryptedNames) {
		this.encryptedNames = encryptedNames;
	}



	public Map<String, String> getDecryptedNames() {
		return decryptedNames;
	}



	public void setDecryptedNames(Map<String, String> decryptedNames) {
		this.decryptedNames = decryptedNames;
	}

	
	
	public String encrypt(String input) throws StringFormatException{// StringFormatException is a custom Exception class 
		
		
		if(input.isEmpty() || input==null){
			throw new StringFormatException("String cannot be null or empty");
		}
		
		if(!input.matches("[A-z]+")){ // Validating that the name entered consists of only alphabets
			throw new StringFormatException("Invalid String format: Name can be only with alphabets");
		}
		
		System.out.println("Encrypting "+ input);
		String encryptedName=encryptedNames.get(input);//Getting name from cache 
		if(encryptedName!=null){//Checking if result is found in cache
			
			System.out.println(input+ "found in cache");
			return encryptedName;
		}
		System.out.println("Adding to cache");
		
		//Encrypting if encrypted name is not found in cache
		
		String key=input;
		input= input.length()%2==0 ? input: input+"#";//Adding # at the end of odd length name
		
		encryptedName= splitAndJoin(input); // Passing to private function to get encrypted value
		
		encryptedNames.put(key, encryptedName); // Adding in cache with real name as key and cipher as value
		decryptedNames.put(encryptedName, key);//Adding in cache with cipher as key and real name as value
		
		return encryptedName;
		
	}
	
	public String decrypt(String input) throws StringFormatException{
		
		if(input.isEmpty() || input==null){
			throw new StringFormatException("Input name can't be empty or null");
		}
		
		//CHecking cipher length which must be even, It should contain only one hash if it contains hash
		if(input.length()%2!=0   || !input.matches("[^#]*(#[^#]+)*#?")){
			throw new StringFormatException("Invalid input for decryption");
		}
		
		if(input.contains("#")){ //Checking # in cipher whether the hash is in middle and has only one hash
			if(input.indexOf("#")!=input.length()/2-1 || countHash(input)!=1){
				throw new StringFormatException("Invalid input for decryption");
			}
		}
		

		
		
		
		System.out.println("Decrypting "+input);
		
		String decryptedName=decryptedNames.get(input);
		
		if(decryptedName!=null){
			System.out.println(input+ " found in cache");
			return decryptedName;
		}
		
		
		decryptedName= splitAndJoin(input); //Decrypting through a private function
		
		decryptedName=decryptedName.contains("#") ? decryptedName.replace("#",""): decryptedName;
		
		System.out.println("Adding to cache");
		
		decryptedNames.put(input,decryptedName);
		encryptedNames.put(decryptedName,input);
		
		return decryptedName;
	}
	
	
	
	


	private String splitAndJoin(String input){ //Splitting and joining algorithm for encryption and decryption both
		
		String firstPart= input.substring(0,input.length()/2);
		String secondPart=input.substring(input.length()/2);
		
		return secondPart+firstPart;
		
	}
	
	private int countHash(String input){
		int count=0;
		for(int i=0;i<input.length();i++){
			if(input.charAt(i)=='#'){
				count++;
			}
		}
		return count;
	}
	
	
	

}
