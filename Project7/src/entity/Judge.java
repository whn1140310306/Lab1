package entity;

public class Judge {

	private String str = "";
	
	public void SetStr(String tmp){
		str = tmp;
	}
	public boolean CheckExpression(){
		int sym_num = 0,exp_num = 0;
		for (int i = 0;i < str.length();i++){
			
			if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '.' || str.charAt(i) >= '0' && str.charAt(i) <= '9' || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')){
				if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*')
					sym_num ++;
			}
			else
				return false;	
		}
		String str2[] = str.split("[+]|[-]|[*]");
		for (int i = 0;i < str2.length;i++)
			if (str2[i] != null && !str2[i].isEmpty())
				exp_num++;

		if (str.charAt(0) != '-')
			return sym_num + 1 == exp_num;
		else
			return sym_num == exp_num;
	}
	public int CheckStr(){
		if (str.substring(0,1).equals("!")){
			if (str.length() >= 4 && str.substring(1,4).equals("d/d"))
				return 2;
			else if (str.length() >= 9 && str.substring(1,9).equals("simplify"))
				return 1;
			else return 3;
		}
		return 4;
	}
}
