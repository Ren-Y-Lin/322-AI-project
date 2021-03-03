package ubc.cosc322;

import java.util.ArrayList;

public class MoveGenerator {
	
	
	public MoveGenerator() {
		
		
		
		
		
		
		
		
	}
	
	//Returns an array containing all the reachable board states
	//generates board state and checks if each move wins but doesnt evaluate board state
	public static ArrayList<BoardState> getMoves(BoardState inputBoard){
		//Generate Queen Moves
		int[][] customBoard = copyBoard(inputBoard.board);
		
		// 1 = queens1, 2 = queens2, 3 = fire
		
		//Queens have 8 directions so calculating all 8 directions for 4 queens
		
		//validMoves: 0=invalid, 1=valid, first array is which queen

		ArrayList<int[][]> validMovesQBoardStates = new ArrayList<int[][]>();
		ArrayList<int[]> queenPositions = new ArrayList<int[]>();
		

		
		ArrayList<BoardState> validMovesBoardStates = new ArrayList<BoardState>();
		
		ArrayList<int[]> currentQueenPos = ((inputBoard.turn == 1) ? inputBoard.queenPos1 : inputBoard.queenPos2);
		

		
		//Calculating All Queen Moves
		//direction: 0-7 
		for(int queen = 0; queen < 4; queen++) {
			//get queen that needs change's location
			int queenX = currentQueenPos.get(queen)[0];
			int queenY = currentQueenPos.get(queen)[1];
			
			//clone the current board and remove the queen that is moving
			int[][] defaultBoard = customBoard.clone();
			defaultBoard[queenX][queenY] = 0;
			//N
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY;
				//verify if new location works
				if(checkValid(defaultBoard,checkX,checkY)) {

					//create new board with valid new queen locations
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//NE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//E
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//SE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY + distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//S
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//SW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//W
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
			//NW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY - distance;
				if(checkValid(customBoard,checkX,checkY)) {

					
					int[][] newBoard = defaultBoard.clone();
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});

				}
				else break;
			}
		}
		

		
		
		//TODO
		//Make new BoardStates:
		
		for (int i = 0; i < validMovesQBoardStates.size();i++) {
			int[][] evalState = validMovesQBoardStates.get(i).clone();
			int[] queenLocation = queenPositions.get(i*4);
			int[] queenLocation2 = queenPositions.get(i*4+1);
			int[] queenLocation3 = queenPositions.get(i*4+2);
			int[] queenLocation4 = queenPositions.get(i*4+3);
			
			ArrayList<int[]> tempQueens = new ArrayList<int[]>();
			tempQueens.add(queenLocation);
			tempQueens.add(queenLocation2);
			tempQueens.add(queenLocation3);
			tempQueens.add(queenLocation4);
			
			if(inputBoard.turn==1) {
				BoardState newBoard = new BoardState(evalState, tempQueens, inputBoard.queenPos2, i, inputBoard.lastState, null);
				validMovesBoardStates.add(newBoard);
			}else {
				BoardState newBoard = new BoardState(evalState, inputBoard.queenPos1, tempQueens, i, inputBoard.lastState, null);
				validMovesBoardStates.add(newBoard);
			}

			
			
			
		}
		
		
		
		//For debugging:
//		for(int i = 0;i<4;i++) {
//			System.out.print("Queen" + (i+1) + " ");
//			printBoard(validMoves[i]);
//			
//			System.out.println("Fire Locations for each move");
//			for (int[][] validFiresBoard : validFires[i]) {
//				System.out.print("Fire ");
//				printBoard(validFiresBoard);
//			}
//		}
		return validMovesBoardStates;
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
