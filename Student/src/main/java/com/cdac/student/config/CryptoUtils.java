/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.config;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author hcdc
 */
public class CryptoUtils {
    private static final String  ALGORITHM = "AES";
    
    public static SecretKey generateKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
        generator.init(128);
        return generator.generateKey();
    }
    
    public static byte[] encrypt(byte [] data, SecretKey key) throws Exception { 
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    
    public static byte [] decrypt(byte [] encryptedData, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encryptedData);
    }
    
    public static String encodeKeyToBase64(SecretKey key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    
    public static SecretKey decodeKeyFromBase64(String encodedKey) {
        byte[] decoded = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decoded, 0, decoded.length, ALGORITHM);
    }
    
}
