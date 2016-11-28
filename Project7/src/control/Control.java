package control;

import boundary.InOut;
import entity.Expression;
import entity.Judge;
import entity.Order;
import entity.Part;

public class Control {

	private InOut io = new InOut();
	private Judge j = new Judge();
	private Expression exp = new Expression();
	private Order ord = new Order();
	private Part part = new Part();
	
	private String Calc(){
		String data[] = ord.CutForCalc();
		String tmp = exp.TakeIn(data);
		//System.out.println("After takein : "+tmp);
		String single_exp[] = exp.CutToPart(tmp);
		part.setPart(single_exp);
		char symbol[] = exp.getSym();
		
		return part.CalcPart(symbol);
	}
	
	private String Deri(){
		String var = ord.CutForDeri();
		if (!ord.CheckForDeri(exp.getExp())){
			return "No "+var;
		}
		String expre = exp.getExp();
		String single_exp[] = exp.CutToPart(expre);
		part.setPart(single_exp);
		char symbol[] = exp.getSym();
		
		return part.DeriPart(var,symbol);
	}
	
	public void Main(){
		
		while(true){
			io.setInString();
			j.SetStr(io.getInString());
			int tmp = j.CheckStr();
			if (tmp == 4){
				if (j.CheckExpression()){
					String t = io.getInString().replaceAll("\\s*", "");
					exp.setExp(t);
					io.PrintString(exp.getExp());
				}else{
					io.PrintString("wrong expression");
				}
			}else if (tmp == 1 || tmp == 2){
				ord.setOrder(io.getInString());
				if (tmp == 1){
					io.PrintString(Calc());
				}else if(tmp == 2){
					io.PrintString(Deri());
				}
			}else if (tmp == 3)
				io.PrintString("Wrong order");
		}
	}
}
