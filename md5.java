import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class md5{
	public static void main(String args[]){
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter message");
		String msg=sc.nextLine();	
		String hash=getHash(msg);
		System.out.println("Message Digest for given message is : " + hash);
	}
	public static String getHash(String msg){
		String result="";
		try{
			MessageDigest digest=MessageDigest.getInstance("MD5");
			byte[] data=digest.digest(msg.getBytes("UTF-8"));
			return DatatypeConverter.printHexBinary(data);
		}catch (Exception e){
			
		}
		return result;
	}
}