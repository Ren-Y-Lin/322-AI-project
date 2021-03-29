package ubc.cosc322;

public class BoardStateEvaluator0 {
	public static int evaluateBoard(BoardState b) {
		int[][] board = copyBoard(b.board);
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

		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				int val =evaluateBoard(queen1pos[i][0], queen1pos[i][1], copyBoard(b.board));
				if(val > 0) {
					t1v +=val;
				}else {
					t1v-=10;
				}
				  
			} else {
				int val =evaluateBoard(queen2pos[i-4][0], queen2pos[i-4][1], copyBoard(b.board));
				if(val > 0) {
					t1v +=val;
				}else {
					t1v-=10;
				}
			}

		}
		
		//System.out.println(""+t1v+" v "+t2v);
		return t1v-t2v;

	}

	public static int evaluateBoard(int x, int y, int[][] board) {
		int points = 0;
		
		//
		for(int i = 1; i< 10; i++) {
			if(x+i>9) {
				break;
			}
			if(board[x+i][y]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(x-i<0) {
				break;
			}
			if(board[x-i][y]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(y+i>9) {
				break;
			}
			if(board[x][y+i]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(y-i<0) {
				break;
			}
			if(board[x][y-i]==0) {
				points++;
			}else {
				break;
			}
		}
		
		////////////////////////////
		for(int i = 1; i< 10; i++) {
			if(x+i>9 || y+i>9) {
				break;
			}
			if(board[x+i][y+i]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(x+i>9||y-i<0) {
				break;
			}
			if(board[x+i][y-i]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(x-i<0 || y+i>9) {
				break;
			}
			if(board[x-i][y+i]==0) {
				points++;
			}else {
				break;
			}
		}
		for(int i = 1; i< 10; i++) {
			if(x-i<0 || y-i<0) {
				break;
			}
			if(board[x-i][y-i]==0) {
				points++;
			}else {
				break;
			}
		}
		
		return points;
		
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
