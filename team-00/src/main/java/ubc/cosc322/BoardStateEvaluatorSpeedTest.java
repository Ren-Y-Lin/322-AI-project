package ubc.cosc322;
import java.util.*;

public class BoardStateEvaluatorSpeedTest {

	public static void main(String[] args) {
		Random random = new Random();
		
		int[][][] boards = new int[500000][][];
		
		//Make random boards
		for(int i = 0; i < boards.length; i++) {
			int[][] newBoard = new int[10][10];
			for(int player = 1; player < 3; player++) {
				for(int queen = 0; queen < 4; queen++) {
					boolean placed = false;
					while(!placed) {
						for(int x = 0; x < 10; x++) {
							for(int y = 0; y < 10; y++ ) {
								if(random.nextInt(100) == 0 && newBoard[x][y] == 0) {
									newBoard[x][y] = player;
									placed = true;
									break;
								}
								
							}
							if(placed) {
								break;
							}
						}
					}
				}
			}
			
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < 10; k++) {
					
					if(random.nextInt(random.nextInt(4)+1) == 0 && newBoard[j][k] == 0) {
						newBoard[j][k] = 3;
					}
				}
			}
			
			boards[i] = newBoard;
			
		}
		
		
		for(int i = 0; i < boards.length; i++) {
			//printBoard(boards[i]);
		}
		
		
		//Convert Boards to boardstates
		BoardState[] boardStates = new BoardState[boards.length];
		for(int i = 0; i < boards.length; i++) {
			BoardState toEval = new BoardStateHead();
			toEval.board = boards[i];
			boardStates[i] = toEval;
		}
		
		int[] results1 = new int[boards.length];
		int[] results2 = new int[boards.length];
		
		//Original Evaluator test
		long start = System.currentTimeMillis();
		for(int i = 0; i < boards.length; i++) {
			results1[i] = BoardStateEvaluator.evaluateBoard(boardStates[i]);
		}
		long elapsed = System.currentTimeMillis()-start;
		System.out.println("Test 1 took: " + elapsed + " miliseconds");
		
		
		//New Evaluator test
		start = System.currentTimeMillis();
		for(int i = 0; i < boards.length; i++) {
			results2[i] = BoardStateEvaluator2.evaluateBoard(boardStates[i]);
		}
		elapsed = System.currentTimeMillis()-start;
		System.out.println("Test 2 took: " + elapsed + " miliseconds");
		
		boolean failed = false;
		for(int i = 0; i < boards.length; i++) {
			//System.out.println("Board no." + (i+1) + ":");
			//printBoard(boards[i]);
			//System.out.println("result1: " + results1[i] + " result2: " + results2[i]);
			if(results1[i] != results2[i]) {
				failed = true;
				System.out.println("result mismatch. please check evaluator");
				break;
			}
		}
		if(!failed) {
			System.out.println("Result check completed successfully");
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
