package com.netgroup.exceldemo.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtils {

	public BasicTextEncryptor textEncryptor(){
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
		return textEncryptor;
	}
	
	
	
	 public String encrypt(String data){
	        return textEncryptor().encrypt(data);
	    }

	    /*
	    this method provides a decryption of the String passed in input
	    */
	    public String decrypt(String encriptedData){
	        return textEncryptor().decrypt(encriptedData);
	    }

}
