import java.util.*;
public class Transposition{
	static String key = "star";
	static char tMatrix [][];
	static int row,col;
	static int index[];
	static String str="";
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String pText = new String();
		String cText = new String();
		
		System.out.print("Enter plain text : ");
		pText = (sc.nextLine());
		
		pText = pText.replace(' ','$');
		col = key.length();
		row = pText.length() / col;
		
		if((pText.length() % col) != 0){
			row+=1;
		}
	
		tMatrix = new char[row][col];
		
		cText = encryption(pText);
		
		for(int i=0 ;i<tMatrix.length ;i++){
			for(int j=0 ;j<tMatrix[i].length ;j++){
				System.out.print(tMatrix[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("Cipher text : " + cText);
		
		pText = decryption(cText);
		
		for(int i=0 ;i<tMatrix.length ;i++){
			for(int j=0 ;j<tMatrix[i].length ;j++){
				System.out.print(tMatrix[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("Plain text : " + pText);
	}
	
	public static String encryption(String pText){
		String cText = new String();
		char[] charArray = key.toCharArray();
		index = new int[key.length()];
		Arrays.sort(charArray);
		
		int cnt=1;
		for(char ch : charArray){
			int idx = key.indexOf(ch);
			index[idx] = cnt;
			cnt++;
		}
		
		int k=0;
		for(int i=0 ;i<tMatrix.length ;i++){
			for(int j=0 ;j<tMatrix[i].length ;j++){
				if(k < pText.length()){
					tMatrix[i][j] = pText.charAt(k++);
				}
				else{
					tMatrix[i][j] = '$';
				}
			}
		}
		
		for(int i : index) {
            str+=Integer.toString(i);
        }
		
		int idx;
		k = 1;
		for(int i=0 ;i<col ;i++){
			idx = str.indexOf(Integer.toString(k));
			for(int j=0 ;j<row ;j++){
				cText += tMatrix[j][idx];
			}
			k++;
		}
		return cText;
	}
	
	
	public static String decryption(String cText){
		String pText="";
		int sc=0,ec=row;
		String str2 = cText.substring(sc,ec);
		
		int idx,k=1,l=0;
		for(int i=0 ; i<col ;i++){
			str2 = cText.substring(sc,ec);
			idx = str.indexOf(Integer.toString(k));
			
			l=0;
			for(int j=0; j<row ;j++){
				tMatrix[j][idx] = str2.charAt(l++);
				//pText += Character.toString(tMatrix[j][idx]);
			}
			sc = ec;
			ec += row;
			k++;
		}
		
		for(int i=0 ;i<tMatrix.length ;i++){
			for(int j=0 ;j<tMatrix[i].length ;j++){
				if(tMatrix[i][j] == '$'){
						pText += " ";
					}
					else{
						pText += tMatrix[i][j];
				}
			}
		}
		
		return pText;
	}
}