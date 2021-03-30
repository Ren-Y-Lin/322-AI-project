package ubc.cosc322;

public class BoardStateEvaluator2 {
	public static int evaluateBoard(BoardState b) {
		int[][] board = copyBoard(b.board);
		int[][] queen1pos = new int[4][2];
		int q1c = 0;
		int[][] queen2pos = new int[4][2];
		int q2c = 0;
		int t1v = 0;
		int t2v = 0;

		queen1pos = b.queenPos1.toArray(queen1pos);
		queen2pos = b.queenPos2.toArray(queen2pos);
		/*for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (board[i][j] == 1) {
					queen1pos[q1c++] = new int[] { i, j };

				} else if (board[i][j] == 2) {
					queen2pos[q2c++] = new int[] { i, j };

				}

			}

		}*/

		for (int i = 0; i < 4; i++) {
			t1v += accessibleBlocks(queen1pos[i][0], queen1pos[i][1], copyBoard(b.board));
			t2v += accessibleBlocks(queen2pos[i][0], queen2pos[i][1], copyBoard(b.board));
		}
		//System.out.println(""+t1v+" v "+t1v);
		return t1v-t2v;

	}

	private static int accessibleBlocks(int x, int y, int[][] board) {
		int sum = 0;

		if (x < 9 ) {
			if(board[x + 1][y] == 0) {
				board[x + 1][y] = 5;
				sum+= 1 + accessibleBlocks(x+1,y,board);
			}
			
		}
		if (x > 0  ) {
			if(board[x - 1][y] == 0) {
				board[x - 1][y] = 5;
				sum+= 1 + accessibleBlocks(x-1,y,board);
			}
			
		}
		if (y > 0 ) {
			if(board[x][y - 1] == 0) {
				board[x ][y-1] = 5;
				sum+= 1 + accessibleBlocks(x,y-1,board);
			}
			
		}
		if (y < 9 ) {
			if(board[x][y + 1] == 0) {
				board[x][y+1] = 5;
				sum+= 1 + accessibleBlocks(x,y+1,board);
			}
			

		}
		
		//Diagonal
		
		if (x < 9 && y > 0 ) {
			if(board[x + 1][y-1] == 0) {
				board[x + 1][y-1] = 5;
				sum+= 1 + accessibleBlocks(x+1,y-1,board);
			}
			
		}
		if (x > 0  && y < 9) {
			if(board[x - 1][y+1] == 0) {
				board[x - 1][y+1] = 5;
				sum+= 1 + accessibleBlocks(x-1,y+1,board);
			}
			
		}
		if (y > 0 && x > 0 ) {
			if(board[x-1][y - 1] == 0) {
				board[x -1][y-1] = 5;
				sum+= 1 + accessibleBlocks(x-1,y-1,board);
			}
			
		}
		if (y < 9 && x < 9) {
			if(board[x+1][y + 1] == 0) {
				board[x+1][y+1] = 5;
				sum+= 1 + accessibleBlocks(x+1,y+1,board);
			}
			

		}
		
		
		return sum;

	}
	
	public static void main(String[] args) {
		BoardStateHead bsh = new BoardStateHead();
		System.out.println(evaluateBoard(bsh));
	}
	
	public static int[][] copyBoard(int[][] toCopy) {
		int[][] newBoard = new int[toCopy.length][toCopy[0].length];
		
		for(int i = 0; toCopy.length > i; i++) {
			for (int j = 0; toCopy.length > j; j++) {
				newBoard[i][j] = toCopy[i][j];
			}
		}
		return newBoard;
	}


}
