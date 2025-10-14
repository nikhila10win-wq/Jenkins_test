package com.mhdsys.common.api.mobile;

public interface EncryptAPI {

	public byte[] encrypt(String text) throws Exception;
	
	public String encryptToString(String text) throws Exception;

	public byte[] encrypt(String text, String secretKey) throws Exception;

	public String decrypt(String encryptedString, String secretKey) throws Exception;

	public String decrypt(String text) throws Exception;

	public String bytesToHex(byte[] bytes);

	public byte[] hexToBytes(String hexString);

}
