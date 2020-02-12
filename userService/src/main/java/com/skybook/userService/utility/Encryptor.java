package com.skybook.userService.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.skybook.userService.exception.EncryptorException;

public class Encryptor {
	public static String encrypt(String password, String key) throws EncryptorException{
		String encryptedPassword = "";
		try{
			SecretKeySpec specs = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, specs);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			encryptedPassword = new String(encrypted);
		} catch (Exception exp) {
			
			throw new EncryptorException(exp.getMessage());
		}
		return encryptedPassword;
		
	}
	
	public static String decrypt(String encryptedPassword, String key) throws EncryptorException {
		String decryptedPassword = "";
		try {
			SecretKeySpec specs = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, specs);
			byte[] decrypted = cipher.doFinal(encryptedPassword.getBytes()); 
			decryptedPassword = new String(decrypted);
		} catch (Exception exp) {
			
			throw new EncryptorException(exp.getMessage());
		}
		return decryptedPassword;
	}

}
