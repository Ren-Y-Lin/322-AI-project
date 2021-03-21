package ubc.cosc322;

import java.util.ArrayList;

public class MinmaxEvaluator {
	
	//This class contains the static method to evaluate a board position based on the potential next few moves using minmax
	
	
	//input: state of the board, the amount of moves to look into the future, whose turn it is
	public static BoardPackage evaluateBoard(BoardState b, int searchDepth) {
		
		if(searchDepth == 0 ) {
			return new BoardPackage(null,BoardStateEvaluator.evaluateBoard(b));
		}
		
		//might be empty array, might be null
		ArrayList<BoardState> nextMove = MoveGenerator.getMoves(b);
		
		if(nextMove==null || nextMove.size()<1) {
			return new BoardPackage(null,-9999*b.turn);
		}
		
		double maxVal = -9999;
		double minVal = 9999;
		BoardState bbs = null,wbs = null;
		
		//do we need this?
		int maxPos;
		int minPos;
		
		for (int i = 0; i< nextMove.size();i++) {

			BoardPackage temp = evaluateBoard(nextMove.get(i),searchDepth-1);
			
			if(temp.value>maxVal) {
				maxVal = temp.value;
				maxPos = i;
				bbs = temp.bs;
			}else if(temp.value<minVal) {
				
				minVal = temp.value;
				minPos = i;
				wbs = temp.bs;
			}
			
			
		}
		
		BoardPackage returnObj = new BoardPackage(b.turn == 1?bbs:wbs,b.turn == 1?maxVal:minVal);
		
		
		
		return returnObj;
		
		
	}

}
