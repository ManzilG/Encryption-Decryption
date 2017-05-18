package com.encrypt.EncryptionAndDecryption;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.exception.StringFormatException;
import com.encrypt.service.EncryptedAndDecryptedNames;

//Test Class for various conditions of input
public class AppTest{
	EncryptedAndDecryptedNames eNames;
	
	@Before
	public void getInstance(){
		eNames=new EncryptedAndDecryptedNames();
	}
	
	@Test(expected=StringFormatException.class)
	public void testEncryptException() throws StringFormatException{
		eNames.encrypt("Manzil123");
	}
	
	@Test(expected=StringFormatException.class)
	public void testEncryptExceptionEmpty() throws StringFormatException{
		eNames.encrypt("");
	}
	
	@Test(expected=StringFormatException.class)
	public void testDecryptExceptionEmpty() throws StringFormatException{
		eNames.decrypt("");
	}
	
	@Test(expected=StringFormatException.class)
	public void testDecryptExceptionoddLength() throws StringFormatException{
		eNames.decrypt("Ram");
	}
	
	@Test(expected=StringFormatException.class)
	public void testDecryptExceptionSingleHash() throws StringFormatException{
		eNames.decrypt("Manzil#");
	}
	
	@Test(expected=StringFormatException.class)
	public void testDecryptExceptionDecryptMoreHash() throws StringFormatException{
		eNames.decrypt("Man#zil#");
	}
	
	@Test
	public void testDecryptwithhash() throws StringFormatException{
		assertEquals(eNames.decrypt("har#Srid"), "Sridhar");
	}
	
	@Test
	public void testDecrypt() throws StringFormatException{
		assertEquals(eNames.decrypt("inAm"), "Amin");
	}
	
	@Test
	public void testEncryptFirst() throws StringFormatException{
		assertEquals(eNames.encrypt("Sridhar"), "har#Srid");
	}
	
	@Test
	public void testEncryptCheckCache() throws StringFormatException{
		String e=eNames.encrypt("Sridhar");
		assertEquals(e,eNames.getEncryptedNames().get("Sridhar") );
	}
	
	@Test
	public void testEncryptFirstWithEven() throws StringFormatException{
		assertEquals(eNames.encrypt("Amin"), "inAm");
	}
	
	
	@Test
	public void testDecryptWithEven() throws StringFormatException{
		eNames.encrypt("Amin");
		assertEquals("Amin", eNames.getDecryptedNames().get("inAm"));
	}
	
	@Test
	public void testDecryptWithIlan() throws StringFormatException{
		assertEquals(eNames.decrypt("ilAn"),"Anil");
	}
	
	@Test
	public void testDecryptWithIlanAgain() throws StringFormatException{
		eNames.decrypt("ilAn");
		assertEquals("Anil", eNames.getDecryptedNames().get("ilAn"));
	}
	
	
	
	
	
	
	
	
	
	
}
