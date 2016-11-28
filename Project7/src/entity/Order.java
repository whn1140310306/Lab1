package entity;

public class Order {
	private String order = "";
	
	public void setOrder(String tmp){
		order = tmp;
	}
	
	public String[] CutForCalc(){
		return order.substring(10).split("[=]|[ ]");
	}
	public String CutForDeri(){
		return order.substring(4);
	}
	
	public boolean CheckForDeri(String str){
		String var = CutForDeri();
		String data[] = str.split("[+]|[-]|[*]");
		for (int i = 0;i < data.length;i++)
			if (data[i].equals(var))
				return true;
		return false;
	}
}
