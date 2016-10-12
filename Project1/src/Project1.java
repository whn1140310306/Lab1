
import java.util.Scanner;
import java.lang.*;

public class Project1{
	
	static int order(String stra){
		if (stra.substring(0,1).equals("!")){
			if (stra.length() >= 4 && stra.substring(1,4).equals("d/d"))
				return 2;
			else if (stra.length() >= 9 && stra.substring(1,9).equals("simplify"))
				return 1;
			else return 3;
		}
		return 4;
	}
	
	static char[] getSym(String str){
		int size = 0;
		for (int i = 0;i < str.length();i++)
			if (str.charAt(i) == '+' || str.charAt(i) == '-')
				size++;
		if (str.charAt(0) != '-')
			size++;
		
		char sym[] = new char[size];
		if (str.charAt(0) != '-')
			sym[0] = '+';
		else
			sym[0] = '-';
		
		int cnt = 1;
		for (int i = 1;i < str.length();i++)
			if (str.charAt(i) == '+' || str.charAt(i) == '-')
				sym[cnt++] = str.charAt(i);
		
		return sym;
	}
	
	static void calculate(String str,String ord){
		String data[] = ord.substring(10).split("[=]|[ ]");	
		
		String single_exp[] = str.split("[+]|[-]");
	/*	for (int i = 0;i < single_exp.length;i++){
			for (int j = 0;j < data.length;j += 2)
				single_exp[i] = single_exp[i].replaceAll(data[j], data[j+1]);			
		}*/
		
		char symbol[] = getSym(str);
		
		if (symbol.length != single_exp.length)
			for (int i = 0;i < single_exp.length-1;i++)
				single_exp[i] = single_exp[i+1];
		
		int single_exp_num = symbol.length;
		
		for (int i = 0;i < single_exp_num;i++){
			
			float pro = 1;
			String var[] = new String[10];
			int cntforvar = 0;
			
			String single[] = single_exp[i].split("[*]"); 
			for (int j = 0;j < single.length;j++){
				for (int k = 0;k < data.length;k += 2){
					if (single[j].equals(data[k]))
						single[j] = data[k+1];
				}
			}
			for (int j = 0;j < single.length;j++){
				try{
					float tmp = Float.parseFloat(single[j]);
					pro *= tmp;
				}catch(NumberFormatException e){
					var[cntforvar++] = single[j];
				}
			}
			String s = "";
			if (cntforvar == 0)
				s += Float.toString(pro);
			else if (pro != 1)
				s += Float.toString(pro)+"*";
			for (int j = 0;j < cntforvar;j++){
				if (j == cntforvar - 1)
					s += var[j];
				else
					s += var[j] + "*";
			}
			single_exp[i] = s;
		}
		
	/*	for (int i = 0;i < single_exp_num;i++)
			System.out.println(single_exp[i]);*/
		
		String finalstr = "";
		float sum = 0;
		
		for (int i = 0;i < single_exp_num;i++){
			try{
				float tmp = Float.parseFloat(single_exp[i]);
				if (symbol[i] == '+')
					sum += tmp;
				else
					sum -= tmp;
			}catch(NumberFormatException e){
				finalstr += symbol[i] + single_exp[i];
			}
		}
		if (sum != 0)
			finalstr += (sum > 0)? "+" + Float.toString(sum) : Float.toString(sum);		
		
		if (finalstr.charAt(0) == '+')
			finalstr = finalstr.substring(1);
		
		System.out.println(finalstr);
	}

	static void deri(String str,String ord){
		String var = ord.substring(4);
		
		String single_exp[] = str.split("[+]|[-]");
		char symbol[] = getSym(str);
		
		if (symbol.length != single_exp.length)
			for (int i = 0;i < single_exp.length-1;i++)
				single_exp[i] = single_exp[i+1];
		
		int single_exp_num = symbol.length;
		
		boolean exist = false;
		String all[] = str.split("[+]|[-]|[*]");
		for (int i = 0;i < all.length;i++)
			if (all[i].equals(var))
				exist = true;
		if (!exist){
			System.out.println("No "+var);
			return;
		}
		
		for (int i = 0;i < single_exp_num;i++){
			boolean ext = false;
			String data[] = single_exp[i].split("[*]");
			for (int j = 0;j < data.length;j++)
				if (data[j].equals(var))
					ext = true;
			if (!ext)
				single_exp[i] = "0";
			else{
				int cnt = 0,index = 0;
				float f = 1;
				String othervar[] = new String[10];
				for (int j = 0;j < data.length;j++){
					if (data[j].equals(var))
						cnt++;
					else{
						try{
							float tmp = Float.parseFloat(data[j]);
							f *= tmp;
						}catch(NumberFormatException e){
							othervar[index++] = data[j];
						}
					}
				}
				String s = "";
				if (index == 0 || cnt*f != 1)
					s += Float.toString(cnt*f);
				if (cnt > 1)
					s += (s.isEmpty())? var : "*" + var;
				for (int j = 0;j < index;j++)
					s += (s.isEmpty()) ? othervar[j] : "*" + othervar[j];
				single_exp[i] = s;
			}
		}

		String finalstr = "";
		float sum = 0;
		for (int i = 0;i < single_exp_num;i++){
			if (!single_exp[i].equals("0")){
				try{
					float tmp = Float.parseFloat(single_exp[i]);
					if (symbol[i] == '+')
						sum += tmp;
					else
						sum -= tmp;
				}catch(NumberFormatException e){
					finalstr += symbol[i] + single_exp[i];
				}
			}
		}
		if (sum != 0)
			finalstr += (sum > 0)? "+" + Float.toString(sum) : Float.toString(sum);		
		
		if (finalstr.charAt(0) == '+')
			finalstr = finalstr.substring(1);
		
		System.out.println(finalstr);
	}
	
	static boolean check(String str){
		int sym_num = 0,exp_num = 0;
		for (int i = 0;i < str.length();i++)
			if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*')
				sym_num ++;
		String str2[] = str.split("[+]|[-]|[*]");
		for (int i = 0;i < str2.length;i++)
			if (str2[i] != null && !str2[i].isEmpty())
				exp_num++;
		//System.out.println(sym_num + " " + exp_num);

		if (str.charAt(0) != '-')
			return sym_num + 1 == exp_num;
		else
			return sym_num == exp_num;
	}
	
	public static void main(String[] args){
		String str = new String("");
		Scanner sc = new Scanner(System.in);
		while (true){			
			String str1 = sc.nextLine();
			if (order(str1) == 4 ){
				str = str1.replaceAll("\\s*", "");
				if (check(str))
					System.out.println(str);
				else{
					System.out.println("wrong expression");
					str = "";
				}
			}else if (order(str1) == 1 || order(str1) == 2){
				if (order(str1) == 1){
		//			System.out.println(str+" "+str1);
					calculate(str,str1);
				}else if(order(str1) == 2){
					deri(str,str1);
				}
			}else if (order(str1) == 3)
				System.out.println("Wrong order");	
//			sc.close();
			
			
		}
	}
	
}