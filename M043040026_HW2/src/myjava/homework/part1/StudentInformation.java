package myjava.homework.part1;

public class StudentInformation {
	private String id;
	private String name;
	private int score;
	
	public StudentInformation(String id,String name,int score){
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	
	
	public void setID(String id_set){
		id = id_set;
	}
	public void setName(String name_set){
		name = name_set;
	}
	public void setScore(int score_set){
		score = score_set;
	}
	public String getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public int getScore(){
		return score;
	}
	public void setData(String id_set,String name_set,int score_set){
		id = id_set;
		name = name_set;
		score = score_set;
	}
	public void show_data(){
		System.out.printf("Student ID : %s\nStudent Name: %s \n",id,name);
		if(score>=70){
			System.out.printf("Student %s pass this project\n",name);
		}
		else{
			System.out.printf("Student %s fail this project\n",name);
		}
	}
	
}
