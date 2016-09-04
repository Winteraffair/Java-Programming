package myjava.homework;

public class Combination extends fourStarGame{
	
	public void checkOfWin(){
		int check_win=0;
		for(int i=0;i<4;i++){
			if(this.getUserNums().contains(this.getWinNums().get(i))){
				check_win++;
			}
		}
		
		if(check_win == 4){
			System.out.println("**You win!");
		}
		else{
			System.out.println("**You lose!");
		}
	}
}
