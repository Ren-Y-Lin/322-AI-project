package ubc.cosc322;

import java.util.ArrayList;

public class MinmaxEvaluator {
	
	//This class contains the static method to evaluate a board position based on the potential next few moves using minmax
	
	
	//input: state of the board, the amount of moves to look into the future, whose turn it is
	public static double evaluateBoard(BoardState b, int searchDept, int turn) {
		
		if(turn == 0) {
			return BoardStateEvaluator.evaluateBoard(b);
		}
		
		ArrayList<BoardState> nextMove = MoveGenerator.getMoves(b);
		
		double maxVal = BoardStateEvaluator.evaluateBoard(b);
		double minVal = BoardStateEvaluator.evaluateBoard(b);
		
		//do we need this?
		int maxPos;
		int minPos;
		
		for (int i = 0; i< nextMove.size();i++) {
			double temp = evaluateBoard(b,searchDept-1,turn*-1);
			
			if(temp>maxVal) {
				maxVal = temp;
				maxPos = i;
			}else if(temp<minVal) {
				
				minVal = temp;
				minPos = i;
			}
			
		}
		
		
		
		
		
		return turn == 1?maxVal:minVal;
		
		
	}

}
