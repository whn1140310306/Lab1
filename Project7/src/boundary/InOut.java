package boundary;

import java.util.Scanner;

public class InOut {
	private String InString;
	
	public String getInString() {
		return InString;
	}
	public void setInString() {
		Scanner sc = new Scanner(System.in);
		InString = sc.nextLine();
	//	sc.close();
		
	}
	
	public void PrintString(String tmp){
		System.out.println(tmp);
	}
	
}
