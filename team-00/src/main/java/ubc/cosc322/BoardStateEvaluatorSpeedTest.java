package ubc.cosc322;
import java.util.*;

public class BoardStateEvaluatorSpeedTest {

	public static void main(String[] args) {
		Random random = new Random();
		
		int[][][] boards = new int[100][][];
		
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
					if(random.nextInt(10) == 0 && newBoard[j][k] == 0) {
						newBoard[j][k] = 3;
					}
				}
			}
			
			boards[i] = newBoard;
			
		}
		
		
		for(int i = 0; i < boards.length; i++) {
			printBoard(boards[i]);
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
