package ubc.cosc322;

public class BoardStateEvaluator2 {
	public static int evaluateBoard(BoardState b) {
		int[][] board = MoveGenerator.copyBoard(b.board);
		int[][] queen1pos = new int[4][2];
		int q1c = 0;
		int[][] queen2pos = new int[4][2];
		int q2c = 0;
		int t1v = 0;
		int t2v = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (board[i][j] == 1) {
					queen1pos[q1c++] = new int[] { i, j };

				} else if (board[i][j] == 2) {
					queen2pos[q2c++] = new int[] { i, j };

				}

			}

		}

		for (int i = 0; i < 4; i++) {
			t1v += accessibleBlocks(queen1pos[i][0], queen1pos[i][1], MoveGenerator.copyBoard(b.board));
			t2v += accessibleBlocks(queen2pos[i][0], queen2pos[i][1], MoveGenerator.copyBoard(b.board));
		}
		//System.out.println(""+t1v+" v "+t2v);
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
		System.out.println("test");
		
		BoardState tester = new BoardStateHead();
		
		int[][] testBoard = {{0,0,0,2,0,0,2,0,0,0},{3,3,3,3,3,3,3,3,3,3},{2,0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
		tester.board = testBoard;
		printBoard(testBoard);
		evaluateBoard(tester);
		
		testBoard = new int[][]{{0,0,0,2,0,0,2,0,0,0},{3,3,3,3,3,3,3,3,3,3},{2,0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,3,0,0,0,0,0},{0,0,0,3,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,3,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
		tester.board = testBoard;
		printBoard(testBoard);
		evaluateBoard(tester);
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
