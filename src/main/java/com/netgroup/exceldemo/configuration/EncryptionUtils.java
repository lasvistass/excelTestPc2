package com.netgroup.exceldemo.configuration;

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
	 
	    public String decrypt(String encriptedData){
	        return textEncryptor().decrypt(encriptedData);
	    }

}
