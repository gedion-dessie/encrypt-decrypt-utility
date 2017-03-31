package com.mycompany.util;

/**
 * Created by gedionz on 3/30/17.
 */
public interface EncryptionDecryptionAlgorithm {
	public abstract String encrypt(String plainText);
	public abstract String decrypt(String cipherText);
}
