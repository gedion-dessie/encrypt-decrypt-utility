package com.mycompany.util;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gedionz on 3/30/17.
 */
public class EncryptionDecryptionUtil {
	
	private EncryptionDecryptionAlgorithm algorithm;
	private Map<String, String> cache;
	
	public EncryptionDecryptionUtil(int cacheSize, EncryptionDecryptionAlgorithm algorithm) {
		this.algorithm = algorithm;
		this.cache = Collections.synchronizedMap(new LeastRecentlyUsedCache<String, String>(cacheSize));
	}
	
	public String encrypt(String plainText) {
		synchronized(this.cache) {
			String encryptedName = this.cache.get(plainText);
			if(encryptedName != null) {
				return encryptedName;
			}else {
				encryptedName = this.algorithm.encrypt(plainText);
				this.cache.put(plainText, encryptedName);
				return encryptedName;
			}
		}
	}
	
	public String decrypt(String cipherText) {
		synchronized(this.cache) {
			String name = this.cache.get(cipherText);
			if(name != null) {
				return name;
			}else {
				name = this.algorithm.decrypt(cipherText);
				this.cache.put(cipherText, name);
				return name;
			}
		}
	}
	
	
	private class LeastRecentlyUsedCache<K,V> extends LinkedHashMap<K,V> {
		private int cacheSize;
		
		public LeastRecentlyUsedCache(int cacheSize) {
			super(cacheSize, 0.75f, true);
			this.cacheSize = cacheSize;
		}
		
		@Override
		protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
			return size() > cacheSize;
		}
		
	}
}
