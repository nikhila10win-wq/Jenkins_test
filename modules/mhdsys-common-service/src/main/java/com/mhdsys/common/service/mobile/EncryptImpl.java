package com.mhdsys.common.service.mobile;

import com.mhdsys.common.api.mobile.EncryptAPI;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
		},
		service = EncryptAPI.class
	)
public class EncryptImpl implements EncryptAPI{
	private static final String ENRYPTION_TYPE = "AES";
	public static String secretKey = "mhdsys2025abcdef";
	private static byte[] keyBytes = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
	private static SecretKeySpec key = new SecretKeySpec(keyBytes, ENRYPTION_TYPE);
	/**
	 * Encryption function
	 * @param text
	 * @return
	 * @throws Exception
	 */
	@Override
	public  byte[] encrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance(ENRYPTION_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
	}
	/**
	 * Encryption function
	 * @param text
	 * @return
	 * @throws Exception
	 */
	@Override
	public  String encryptToString(String text) throws Exception {
		Cipher cipher = Cipher.getInstance(ENRYPTION_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return  Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
	}
	/**
	 * Encryption function
	 *
	 * @param text
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public  byte[] encrypt(String text, String secretKey) throws Exception {
		byte[] keyBytes = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
		SecretKeySpec key = new SecretKeySpec(keyBytes, ENRYPTION_TYPE);
		Cipher cipher = Cipher.getInstance(ENRYPTION_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
	}
	
	/**
	 * Decryption function
	 * 
	 * @param encryptedString
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public  String decrypt(String encryptedString, String secretKey) throws Exception {
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
		byte[] keyBytes = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
		SecretKeySpec key = new SecretKeySpec(keyBytes, ENRYPTION_TYPE);
		Cipher cipher = Cipher.getInstance(ENRYPTION_TYPE);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	@Override
	public  String decrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance(ENRYPTION_TYPE);
		byte[] keyBytes = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
		SecretKeySpec key = new SecretKeySpec(keyBytes, ENRYPTION_TYPE);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] plainText = cipher.doFinal(Base64.getDecoder()
	        .decode(text));
	    return new String(plainText);
	}
	
	/**
	 * Function to convert a byte array to a hexadecimal string
	 * 
	 * @param bytes
	 * @return
	 */
	@Override
    public  String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

	/**
     * Function to convert a hexadecimal string to a byte array
     * 
     * @param hexString
     * @return
     */
	@Override
    public  byte[] hexToBytes(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
}
