import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
class des{
	public static void main(String args[]) throws Exception{
		String plaintext = "viral";
		SecretKey key = getKey();
		byte[] encrypted = encrypt(key,plaintext);
		System.out.println("Encrypted :-- " + new String(encrypted));
		byte[] decrypted =decrypt(key,encrypted);
		System.out.println("Decrypted :-- "+ new String(decrypted));
	}
	public static SecretKey getKey()throws Exception{
		KeyGenerator generate = KeyGenerator.getInstance("DES");
		generate.init(56);
		SecretKey key = generate.generateKey();
		return key;
	} 
	public static byte[] encrypt(SecretKey key, String message)throws Exception{
		Cipher aescipher = Cipher.getInstance("DES");
		aescipher.init(Cipher.ENCRYPT_MODE,key);
		return aescipher.doFinal(message.getBytes());
	}
	public static byte[] decrypt(SecretKey key, byte[] encrypted)throws Exception{
		Cipher aescipher = Cipher.getInstance("DES");
		aescipher.init(Cipher.DECRYPT_MODE,key);
		return aescipher.doFinal(encrypted);
	}
	
}