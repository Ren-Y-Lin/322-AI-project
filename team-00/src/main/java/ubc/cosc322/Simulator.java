package ubc.cosc322;

import java.util.ArrayList;

public class Simulator {
	
	
	
	
	
	public static int playTillEnd(BoardState bs) {
		//Generate the next set of Valid moves

		BoardState temp = bs;
		double tempVal = MinmaxEvaluator.evaluateBoard(bs, 4).value;
		while(tempVal<9999 || tempVal>-9999) {
			
			BoardPackage tempP = MinmaxEvaluator.evaluateBoard(bs, 4);
			tempVal = tempP.value;
			
		}
		
		
		//

		

		if(tempVal==9999) {
			return 1;
		}else {
			return -1;
		}
		
		
		
	}
	
	public static double nextPlay(BoardState bs,double randomness) {
		
		
		int turn = bs.turn;
		
		double finalValue = 0;
		
		if(turn == 1) {
			
			ArrayList <BoardState> bsl = MoveGenerator.getMoves(bs);
			for (int i = 0; i< bsl.size();i++) {
				
			}
			
		}else {
			

		}
		
		return randomness;
	
	}

}
