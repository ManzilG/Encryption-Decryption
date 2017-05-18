package com.encrypt.mainApp;

import java.util.Scanner;
import com.encrypt.exception.StringFormatException;
import com.encrypt.service.EncryptedAndDecryptedNames;

public class App 
{
    public static void main( String[] args )
    {
    	 EncryptedAndDecryptedNames enc=new EncryptedAndDecryptedNames();
    	
    	Scanner scanner=new Scanner(System.in);
    	String input;
    	while(true){
    		System.out.println("Please enter the name to encrypt or decrypt");
    		input=scanner.nextLine();
    		System.out.println("Do you want to encrypt or decrypt "+ input + " ? Press 1 for encryption or any other keys for decryption");
    		String check=scanner.nextLine();
    		try{
    			System.out.println("Result = "+ (check.equals("1") ? enc.encrypt(input)  :  enc.decrypt(input)));
    		}catch(StringFormatException e){
    			System.out.println("Exception occured "+e);
    		}
    		System.out.println("Want to enter more values? Press 1 for yes or any other keys for exit");
    		String more=scanner.nextLine();
    		if(!more.equals("1")){
    			System.out.println("You choose to exit");
    			break;
    		}
    	}
    	
       
    }
}
