import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
class aes{
public static void main(String args[]) throws Exception{
	String Plaintext = "Akshat Shah";
	SecretKey key = getKey();
	
	byte[] encrypted =encrypt(key,Plaintext);
	System.out.println("Encryted :- " + new String(encrypted));
	
	byte[] decrypted = decrypt(key,encrypted);
	System.out.println("Decrypted:- " + new String(decrypted));
	
}
	public static SecretKey getKey() throws Exception{
		KeyGenerator generate = KeyGenerator.getInstance("AES");
		generate.init(128);
		SecretKey key = generate.generateKey();
		return key;
	}
	
	public static byte[] encrypt(SecretKey seckey, String message)throws Exception {
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, seckey);
		return aesCipher.doFinal(message.getBytes());
	}
	public static byte[] decrypt(SecretKey key,byte[] encrypted)throws Exception{
		Cipher aeschiper = Cipher.getInstance("AES");
		aeschiper.init(Cipher.DECRYPT_MODE,key);
		return aeschiper.doFinal(encrypted);
	}
}