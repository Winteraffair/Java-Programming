package myjava.homework.part1;

public class Apple {
	@SuppressWarnings("unused")
	private String variety;
	private int price;
	private double weight;
	public Apple(int i, double d) {
		// TODO Auto-generated constructor stub
		this.price = i;
		this.weight = d;
	}

	public Apple(String string, int i, double d) {
		// TODO Auto-generated constructor stub
		this.variety = string;
		this.price = i;
		this.weight = d;
	}
	public int getPrice(){
		return this.price;
	}
	protected void setPrice(int price){
		this.price = price;
	}
	public double getWeight(){
		return this.weight;
	}
	public void setWeight(double weight){
		this.weight = weight;
	}
	protected void setVariety(String variety){
		this.variety = variety;
	}
	
	

}
