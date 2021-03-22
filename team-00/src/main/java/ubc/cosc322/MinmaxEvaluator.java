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
		ArrayList<BoardState> nextMove = MoveGenerator.getMoves(b);
		//System.out.println("PRINTING MOVES");
		for (int i = 0; i < nextMove.size(); i++) {
			//printBoard(nextMove.get(i).board);
		}
		
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

			//System.out.println("breakpoint 3 size:"+ nextMove.size());
			BoardPackage temp = evaluateBoard(nextMove.get(i),searchDepth-1);
			
			if(temp.bs == null) {
				System.out.println("bs Empty??");
			}
			
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
		

		if(returnObj.bs == null) {
			System.out.println("return board empty");
		}
		//System.out.println("breakpoint 5");
		
		
		return returnObj;
		
		
	}
	public static void printBoard(int[][] board) {
		System.out.println("Board:");
		for(int i = 0;board.length>i;i++) {
			for(int j = 0;board[0].length>j;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

}
