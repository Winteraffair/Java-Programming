package myjava.homework;

public class id_information {
	public int sex;
	public int[] product;
	
	public id_information(String product,String sex){
		this.product = new int[4];
		
		if(sex.equals("F")){
			this.sex = 0;
		}else if(sex.equals("M")){
			this.sex = 1;
		}
		
		if(product.equals("Product_A")){
			this.product[1] = 1;
		}
		if(product.equals("Product_B")){
			this.product[2] = 1;
		}
		if(product.equals("Product_C")){
			this.product[3] = 1;
		}
	}
}
