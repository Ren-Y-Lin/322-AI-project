package ubc.cosc322;

import java.util.ArrayList;

public class MinmaxEvaluator {
	
	//This class contains the static method to evaluate a board position based on the potential next few moves using minmax
	
	
	//input: state of the board, the amount of moves to look into the future, whose turn it is
	public static BoardState evaluateBoard(BoardState b, int searchDepth, int turn,long timestart ,long timelimit) {
		
		//System.out.println("evaluating board at dept: " + searchDepth);
		if(System.currentTimeMillis()-timestart>timelimit) {
			b.value = -99999;
		}
		
		if(searchDepth == 0) {
			b.value = BoardStateEvaluator2.evaluateBoard(b);
			return b;
		}
		
		ArrayList<BoardState> bs = MoveGenerator.getMoves(b); 
		if(bs.size()<1) {
			return evaluateBoard(b,0,turn*-1, timestart, timelimit);
		}
		b.nextStates = bs;
		
		int max = -999;
		int min = 999;
		BoardState savedState = b;

		
		for(int i = 0 ; i < b.nextStates.size();i++) {
			
			evaluateBoard(b.nextStates.get(i),searchDepth-1,turn*-1, timestart, timelimit);
			if(b.nextStates.get(i).value==-99999) {
				System.out.println("timeout");
				break;
			}
			
			if(turn == 1) {
				if(b.nextStates.get(i).value > max) {
					max = b.nextStates.get(i).value;
					savedState = b.nextStates.get(i);
				}
			}else {
				if(b.nextStates.get(i).value < min) {
					min = b.nextStates.get(i).value;
					savedState = b.nextStates.get(i);
				}
			}
		}
		
		
		
		return savedState;
		
		
	}
	public static void main(String[] args) {
		System.out.println("test");
		
		BoardState tester = new BoardStateHead();
		
		int[][] testBoard = {{0,0,0,2,0,0,2,0,0,0},{3,3,3,3,3,3,3,3,3,3},{2,0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
		tester.board = testBoard;
		
		for(int i = 0 ; i < 92;i++) {
			printBoard(tester.board);
			tester = evaluateBoard(tester, 1, 1, System.currentTimeMillis(), 18000);
		}
		
		
		
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
