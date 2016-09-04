package myjava.homework;

public class Permutation extends fourStarGame {
	
	public void checkOfWin() {
		int check_win=0;
		for(int i =0;i<4;i++){
			if(!(this.getUserNums().get(i).equals(this.getWinNums().get(i)))){
				check_win = 1;
			}
		}
		if(check_win == 0){
			System.out.println("**You win!");
		}
		else{
			System.out.println("**You lose!");
		}
	}
}
