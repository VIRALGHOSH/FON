import java.security.*;
import javax.crypto.Cipher;
import java.util.Scanner;
/*
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Scanner;
*/
public class RSA{
	public static void main(String args[])throws Exception{
		KeyPair kp=buildKey();
		PublicKey pub=kp.getPublic();
		PrivateKey pri=kp.getPrivate();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter text");
		String text=sc.nextLine();
		
		byte[] encrypted=encrypt(pub,text);
		System.out.println(new String(encrypted));
		
		byte[] secret=decrypt(pri,encrypted);
		System.out.println(new String(secret));
	}
	
	public static KeyPair buildKey()throws NoSuchAlgorithmException{
		KeyPairGenerator gen=KeyPairGenerator.getInstance("RSA");
		gen.initialize(1024);
		return gen.genKeyPair();
	}
	
	public static byte[] encrypt(PublicKey pub,String text)throws Exception{
		Cipher c=Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE,pub);
		return c.doFinal(text.getBytes());
	}
	
	public static byte[] decrypt(PrivateKey pri,byte[] encrypted) throws Exception{
		Cipher c =Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE,pri);
		return c.doFinal(encrypted);
	}
}
