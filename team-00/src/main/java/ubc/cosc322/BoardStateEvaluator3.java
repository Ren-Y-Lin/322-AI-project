package ubc.cosc322;

public class BoardStateEvaluator3 {
	public static int evaluateBoard(BoardState b) {
		int[][] board = copyBoard(b.board);
		int[][] queen1pos = new int[4][2];
		int q1c = 0;
		int[][] queen2pos = new int[4][2];
		int q2c = 0;
		int t1v = 0;
		int t2v = 0;
		int[][][] boards = new int[8][10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (board[i][j] == 1) {
					queen1pos[q1c++] = new int[] { i, j };

				} else if (board[i][j] == 2) {
					queen2pos[q2c++] = new int[] { i, j };

				}

			}

		}

		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				boards[i] = accessBoard(queen1pos[i][0], queen1pos[i][1], copyBoard(b.board));
			} else {
				boards[i] = accessBoard(queen2pos[i-4][0], queen2pos[i-4][1], copyBoard(b.board));
			}

		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int sum1 = 0;
				int sum2 = 0;
				for (int k = 0; k < 8; k++) {
					if (k < 4) {
						if (boards[k][i][j] == 5) {
							sum1++;
						}
					} else {
						if (boards[k][i][j] == 5) {
							sum2++;
						}
					}
				}
				
				if (sum1 == 0) {
					if(sum2==0) {
						
					}else {
						t2v+=4;
					}
					
				} else if (sum2 == 0) {
					t1v+=4;
				}else {
					t1v+=sum1;
					t2v+=sum2;
				}

			}

		}

		//System.out.println(""+t1v+" v "+t1v);
		return t1v - t2v;

	}

	private static int[][] accessBoard(int x, int y, int[][] board) {

		if (x < 9) {
			if (board[x + 1][y] == 0) {
				board[x + 1][y] = 5;
				board = accessBoard(x + 1, y, board);
			}

		}
		if (x > 0) {
			if (board[x - 1][y] == 0) {
				board[x - 1][y] = 5;
				board = accessBoard(x - 1, y, board);
			}

		}
		if (y > 0) {
			if (board[x][y - 1] == 0) {
				board[x][y - 1] = 5;
				board = accessBoard(x, y - 1, board);
			}

		}
		if (y < 9) {
			if (board[x][y + 1] == 0) {
				board[x][y + 1] = 5;
				board = accessBoard(x, y + 1, board);
			}

		}

		// Diagonal

		if (x < 9 && y > 0) {
			if (board[x + 1][y - 1] == 0) {
				board[x + 1][y - 1] = 5;
				board = accessBoard(x + 1, y - 1, board);
			}

		}
		if (x > 0 && y < 9) {
			if (board[x - 1][y + 1] == 0) {
				board[x - 1][y + 1] = 5;
				board = accessBoard(x - 1, y + 1, board);
			}

		}
		if (y > 0 && x > 0) {
			if (board[x - 1][y - 1] == 0) {
				board[x - 1][y - 1] = 5;
				board = accessBoard(x - 1, y - 1, board);
			}

		}
		if (y < 9 && x < 9) {
			if (board[x + 1][y + 1] == 0) {
				board[x + 1][y + 1] = 5;
				board = accessBoard(x + 1, y + 1, board);
			}

		}

		return board;

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
