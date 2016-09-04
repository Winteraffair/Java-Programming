package myjava.homework.part2;

import java.util.ArrayList;

public class ItemList {
	private ArrayList<String> id; 
	
	public ItemList(){
		id = new ArrayList<String>();
	}
	public void addItem(String item_name){
		id.add(item_name);
	}
	public void remove(String item_name){
		id.remove(item_name);
	}	
	public void printList(){
		int list_size;
		list_size = id.size();
		String get_string;
		for(int i=0;i<list_size;i++){
			get_string = id.get(i);
			System.out.printf("%d : %s\n",i,get_string);
		}
		System.out.println("");
	}
}
