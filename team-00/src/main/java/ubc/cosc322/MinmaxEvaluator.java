package ubc.cosc322;

import java.util.ArrayList;

public class MinmaxEvaluator {
	
	//This class contains the static method to evaluate a board position based on the potential next few moves using minmax
	
	
	//input: state of the board, the amount of moves to look into the future, whose turn it is
	public static double evaluateBoard(BoardState b, int searchDept, int turn) {
		
		ArrayList<BoardState> nextMove = MoveGenerator.getMoves(b) ;
		
		
		
		
		
		return searchDept;
		
		
	}

}
