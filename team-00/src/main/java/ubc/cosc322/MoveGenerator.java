package ubc.cosc322;

public class MoveGenerator {
	
	
	public MoveGenerator() {
		
		
		
		
		
		
		
		
	}
	
	//Returns an array containing all the reachable board states
	//generates board state and checks if each move wins but doesnt evaluate board state
	public static BoardState[] getMoves(BoardState inputBoard){
		//Generate Queen Moves
		int[][] customBoard = copyBoard(inputBoard.board);
		
		// 1 = queens1, 2 = queens2, 3 = fire
		
		//Queens have 8 directions so calculating all 8 directions for 4 queens
		
		//validMoves: 0=invalid, 1=valid, first array is which queen
		int[][][] validMoves = new int[4][inputBoard.board.length][inputBoard.board[0].length];
		
		int[][] currentQueenPos = ((inputBoard.turn == 1) ? inputBoard.queenPos1 : inputBoard.queenPos2);
		
		int[] validMovesCounter = new int[4];
		
		//Calculating All Queen Moves
		//direction: 0-7 
		for(int queen = 0; queen < 4; queen++) {
			int queenX = currentQueenPos[queen][0];
			int queenY = currentQueenPos[queen][1];
			//N
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//NE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//E
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//SE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//S
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//SW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//W
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
			//NW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {
					validMoves[queen][checkX][checkY] = 1;
					validMovesCounter[queen]++;
				}
				else break;
			}
		}
		
		//Generate Fires/Arrows
		
		//validFires: [Queen][Queen Position Number][ValidFiresX][ValidFiresY]
		int[][][][] validFires = new int[4][][][];
		for(int i = 0; i < 4; i++) {
			validFires[i] = new int[validMovesCounter[i]][inputBoard.board.length][inputBoard.board[0].length];
		}
		
		for(int queen = 0; queen<4; queen++) {
			int queenX = currentQueenPos[queen][0];
			int queenY = currentQueenPos[queen][1];
			
			int queenMovesTotal = validFires[queen].length;
			
			for(int fire = 0; fire<queenMovesTotal; fire++) {
				int[][] currentQueenStateBoard = copyBoard(customBoard);
				
				int currentQueenX=0,currentQueenY=0;
				
				//Finding Current Queen Move
				boolean toBreak = false;
				int toBreakCounter = 0;
				for(int i = 0; i < currentQueenStateBoard.length; i++) {
					for(int j = 0; j < currentQueenStateBoard[0].length; j++) {
						if(validMoves[queen][i][j] == 1) {
							currentQueenX = i;
							currentQueenY = j;
							toBreakCounter++;
							if(toBreakCounter==fire+1) {
								toBreak = true;
								break;
							}
						}
					}
					if(toBreak)
						break;
				}
				
				//Move the Queen
				currentQueenStateBoard[queenX][queenY] = 0;
				currentQueenStateBoard[currentQueenX][currentQueenY] = (inputBoard.turn==1) ? 1 : 2;
				
				//Calculating All Fires
				
				//N
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX - distance;
					int checkY = currentQueenY;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//NE
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX - distance;
					int checkY = currentQueenY + distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//E
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX;
					int checkY = currentQueenY + distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//SE
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX + distance;
					int checkY = currentQueenY + distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//S
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX + distance;
					int checkY = currentQueenY;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//SW
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX + distance;
					int checkY = currentQueenY - distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//W
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX;
					int checkY = currentQueenY - distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
				//NW
				for(int distance = 1; distance < 10; distance++) {
					int checkX = currentQueenX - distance;
					int checkY = currentQueenY - distance;
					if(checkValid(currentQueenStateBoard,checkX,checkY)) {
						validFires[queen][fire][checkX][checkY] = 1;
					}
					else break;
				}
			}
		}
		
		//TODO
		//Make new BoardStates:
		
		
		
		//For debugging:
		for(int i = 0;i<4;i++) {
			System.out.print("Queen" + (i+1) + " ");
			printBoard(validMoves[i]);
			
			System.out.println("Fire Locations for each move");
			for (int[][] validFiresBoard : validFires[i]) {
				System.out.print("Fire ");
				printBoard(validFiresBoard);
			}
		}
		return null;
	}
	
	//Takes the board and returns whether the location is valid (valid if 0). Returns false otherwise
	public static boolean checkValid(int[][] board, int x, int y) {
		//System.out.println(x + "," + y);
		if(!(x>board.length-1|| x<0 || y>board[x].length-1 || y<0 || board[x][y]!=0)) {
			return true;
		}
		else return false;
	}
	
	//Make a Board Copy. Use this to prevent bad pointers
	public static int[][] copyBoard(int[][] toCopy) {
		int[][] newBoard = new int[toCopy.length][toCopy[0].length];
		
		for(int i = 0; toCopy.length > i; i++) {
			for (int j = 0; toCopy.length > j; j++) {
				newBoard[i][j] = toCopy[i][j];
			}
		}
		return newBoard;
	}
	
	//Methods for debugging:
	public static void main(String[] args) {
		System.out.println("test");
		
		BoardState tester = new BoardStateHead();
		
		MoveGenerator.getMoves(tester);
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
	public static void printBoard(BoardState board) {
		printBoard(board.board);
	}

}
