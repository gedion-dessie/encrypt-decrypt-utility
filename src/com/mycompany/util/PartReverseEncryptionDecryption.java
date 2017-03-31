package com.mycompany.util;

/**
 * Created by gedionz on 3/30/17.
 */
public class PartReverseEncryptionDecryption implements EncryptionDecryptionAlgorithm {
	
	@Override
	public String encrypt(String plainText) {
		if(plainText.length() % 2 != 0) {
			plainText += "#";
		}
		int middle = plainText.length()/2;
		String firstHalf = plainText.substring(0, middle);
		String secondHalf = plainText.substring(middle);
		
		return secondHalf + firstHalf;
	}
	
	@Override
	public String decrypt(String cipherText) {
		int middle = cipherText.length()/2;
		
		String firstHalf = cipherText.substring(0, middle);
		String secondHalf = cipherText.substring(middle);
		
		if(firstHalf.contains("#")) {
			firstHalf = firstHalf.substring(0, middle - 1);
		}
		return secondHalf + firstHalf;
	}
	
}
