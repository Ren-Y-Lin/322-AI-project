package ubc.cosc322;

import java.util.ArrayList;

public class MinmaxEvaluator {
	
	//This class contains the static method to evaluate a board position based on the potential next few moves using minmax
	
	
	//input: state of the board, the amount of moves to look into the future, whose turn it is
	public static BoardPackage evaluateBoard(BoardState b, int searchDepth) {
		
		//System.out.println("evaluating board at dept: " + searchDepth);
		if(b == null) {
			return null;
		}
		
		if(searchDepth < 1 ) {
			//System.out.println("breakpoint 6");
			BoardPackage bp = new BoardPackage(b,BoardStateEvaluator.evaluateBoard(b));
			//System.out.println("breakpoint 7");
			return bp;
			
		}
		
		//might be empty array, might be null
		//System.out.println("Called Move Gonerator");
		ArrayList<BoardState> nextMove = MoveGenerator.getMoves(b);
		
		if(nextMove==null || nextMove.size()<1) {
			return new BoardPackage(b,-9999*b.turn);
		}
		//System.out.println("breakpoint 1");
		double maxVal = -9999;
		double minVal = 9999;
		BoardState bbs = null,wbs = null;
		
		//do we need this?
		int maxPos;
		int minPos;
		//System.out.println("breakpoint 2");
		for (int i = 0; i< nextMove.size();i++) {
			//System.out.println("breakpoint 3 size:"+ nextMove.size());
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
			//System.out.println("breakpoint 4");
			
		}
		
		BoardPackage returnObj = new BoardPackage(b.turn == 1?bbs:wbs,b.turn == 1?maxVal:minVal);
		//System.out.println("breakpoint 5");
		
		
		return returnObj;
		
		
	}

}
