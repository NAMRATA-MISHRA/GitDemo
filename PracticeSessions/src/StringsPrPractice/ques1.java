package StringsPrPractice;

import java.util.HashMap;

public class ques1 {

	public static void main(String[] args) {
		String str="ancehdaaergttwsd";
		char a[]=str.toCharArray();
		String s="";
		HashMap<Character,Integer>hm=new HashMap<>();
		for(int i=0;i<str.length()-1;i++) {
			for(int j=i+1;j<str.length()-1;j++) {
				if(a[i]==a[j]) {
					s=s+str.charAt(i);
					
				}
			}
			
			
		}
		System.out.println(s);

	}

}
