package entity;

public class Part {
	private String part[];
	
	public void setPart(String tmp[]){
		part = tmp;
	}
	
	public String CalcPart(char sym[]){
		int single_exp_num = part.length;
		String single_exp[] = part;
		
		/*System.out.println("-------------");
		for (int i = 0;i < single_exp.length;i++)
			System.out.println(single_exp[i]);
		System.out.println("----------------");*/
		
		for (int i = 0;i < single_exp_num;i++){   //对每个块进行整理
			
			float pro = 1;                           //乘积
			String var[] = new String[10];    
			int cntforvar = 0;
			/*System.out.println("*******");
			System.out.println(i);
			System.out.println("*******");*/
			String single[] = single_exp[i].split("[*]");    // 把每个块按乘号分开
			
			for (int j = 0;j < single.length;j++){      //如果是浮点数就乘到乘积上，否则加入数组等待构造
				try{
					float tmp = Float.parseFloat(single[j]);
					pro *= tmp;
				}catch(NumberFormatException e){
					var[cntforvar++] = single[j];   // cntforvar 为计数，var数组是记录变量
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
		
		String finalstr = "";
		float sum = 0;
		
	/*	System.out.println("------------------");
		for (int i = 0;i < single_exp_num;i++)
			System.out.println(single_exp[i]);
		for (int i = 0;i < sym.length;i++)
			System.out.println(sym[i]);
		System.out.println("---------------------");*/
		
		for (int i = 0;i < single_exp_num;i++){
			try{
				float tmp = Float.parseFloat(single_exp[i]);
				if (sym[i] == '+')
					sum += tmp;
				else
					sum -= tmp;
			}catch(NumberFormatException e){
				finalstr += sym[i] + single_exp[i];
			}
		}
		if (sum != 0)
			finalstr += (sum > 0)? "+" + Float.toString(sum) : Float.toString(sum);		
		
		if (finalstr.charAt(0) == '+')
			finalstr = finalstr.substring(1);
		
		return finalstr;
	}
	public String DeriPart(String var,char symbol[]){
		
		String single_exp[] = part;
		
		int single_exp_num = symbol.length;
		
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
		
		return finalstr;
	}
}
