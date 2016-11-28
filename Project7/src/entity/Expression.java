package entity;

public class Expression {

	private String exp = "";
	
	public void setExp(String tmp){
		exp = tmp;
	}
	public String getExp(){
		return exp;
	}
	public String[] CutToPart(String str){
		String single_exp[] = str.split("((?<!\\+|\\*)-)|[+]");
		String tmp = single_exp[0];
		int len = single_exp.length;
		if (tmp == null || tmp.isEmpty()){
			String[] str0 = new String[len-1];
			for (int i = 1;i < len;i++){
				str0[i-1] = single_exp[i];
			}
			return str0;
		}
		
		return single_exp;
	}
	public char[] getSym(){
		int size = 0;
		for (int i = 0;i < exp.length();i++)
			if (exp.charAt(i) == '+' || exp.charAt(i) == '-')
				size++;
		if (exp.charAt(0) != '-')
			size++;
		
		char sym[] = new char[size];
		if (exp.charAt(0) != '-')
			sym[0] = '+';
		else
			sym[0] = '-';
		
		int cnt = 1;
		for (int i = 1;i < exp.length();i++)
			if (exp.charAt(i) == '+' || exp.charAt(i) == '-')
				sym[cnt++] = exp.charAt(i);
		
		return sym;
	}
	private boolean IsSym(char ch){
		return ch == '+' || ch == '-' || ch == '*';
	}
	private String MyreplaceFirst(String str,String ostr,String nstr,int cnt){
		String tmp = str.substring(cnt);
		String head = str.substring(0, cnt);
		return head + tmp.replaceFirst(ostr, nstr);
	}
	public String TakeIn(String data[]){
		String tmp = exp;
		int count = 0;
		int index = 0;
		for (int i = 0;i < data.length;i+=2){
			do{
			//	System.out.println("tmp is "+tmp);
				index = tmp.indexOf(data[i],count);
				if (index != -1 &&(index-1<0 || IsSym(tmp.charAt(index-1)) ) && (index+data[i].length() >= tmp.length()|| IsSym(tmp.charAt(index+data[i].length())) ))
					tmp = MyreplaceFirst(tmp,data[i], data[i+1],index);
				else
					count = index + data[i].length();
			}while (index != -1);
		}
		return tmp;
	}
}
