package ubc.cosc322;

import java.util.ArrayList;

public class MoveGenerator {

	//Returns an array containing all the reachable board states
	//generates board state and checks if each move wins but doesnt evaluate board state
	public static ArrayList<BoardState> getMoves(BoardState inputBoard){
		if(inputBoard == null) {
			return null;
		}
		//Generate Queen Moves
		int[][] customBoard = copyBoard(inputBoard.board);
		
		// 1 = queens1, 2 = queens2, 3 = fire
		
		//Queens have 8 directions so calculating all 8 directions for 4 queens
		
		//validMoves: 0=invalid, 1=valid, first array is which queen

		ArrayList<int[][]> validMovesQBoardStates = new ArrayList<int[][]>();
		ArrayList<int[]> queenPositions = new ArrayList<int[]>();
		
		ArrayList<int[]> newQueenPos1 = new ArrayList<int[]>();
		ArrayList<int[]> newQueenPos2 = new ArrayList<int[]>();
		for(int i = 0; i < customBoard.length; i++) {
			for(int i2 = 0; i2 < customBoard.length; i2++) {
				if(customBoard[i][i2] == 1) {
					int[] queenPos = {i,i2};
					newQueenPos1.add(queenPos);
				}
				if(customBoard[i][i2] == 2) {
					int[] queenPos = {i,i2};
					newQueenPos2.add(queenPos);
				}
			}
		}
		
		inputBoard.queenPos1 = newQueenPos1;
		inputBoard.queenPos2 = newQueenPos2;
		
		
		
		ArrayList<BoardState> validMovesBoardStates = new ArrayList<BoardState>();
		
		ArrayList<int[]> currentQueenPos = ((inputBoard.turn == 1) ? inputBoard.queenPos1 : inputBoard.queenPos2);
		
		//Checking if queenPos is correct
		for(int i = 0; i < currentQueenPos.size(); i++) {
			int[] pos = currentQueenPos.get(i);
			int check = customBoard[pos[0]][pos[1]];
			int queenNumber;
			if(inputBoard.turn == 1) {
				queenNumber = 1;
			}else {
				queenNumber = 2;
			}
			if(queenNumber != check) {
				System.out.println("ERROR: QUEEN MISMATCH");
			}
		}
		
		//Calculating All Queen Moves
		//direction: 0-7 
		for(int queen = 0; queen < 4; queen++) {
			//get queen that needs change's location
			int queenX = currentQueenPos.get(queen)[0];
			int queenY = currentQueenPos.get(queen)[1];
			
			//clone the current board and remove the queen that is moving
			int[][] defaultBoard = copyBoard(customBoard);
			defaultBoard[queenX][queenY] = 0;
			
			//N
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY;
				//verify if new location works
				if(checkValid(defaultBoard,checkX,checkY)) {

					//create new board with valid new queen locations
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));

				}
				else break;
			}
			//NE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY + distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//E
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY + distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//SE
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY + distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//S
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//SW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX + distance;
				int checkY = queenY - distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//W
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX;
				int checkY = queenY - distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
			//NW
			for(int distance = 1; distance < 10; distance++) {
				int checkX = queenX - distance;
				int checkY = queenY - distance;
				if(checkValid(defaultBoard,checkX,checkY)) {

					
					int[][] newBoard = copyBoard(defaultBoard);
					newBoard[checkX][checkY] = inputBoard.turn == 1 ? 1:2;
					validMovesQBoardStates.add(newBoard);
					
					for(int i =0; i< 4; i++) {
						if(i!= queen) {
							queenPositions.add(currentQueenPos.get(i));
						}
					}
					queenPositions.add(new int[] {checkX,checkY});
					queenPositions.add(currentQueenPos.get(queen));


				}
				else break;
			}
		}
		

		
		
		//TODO
		//Make new BoardStates:
		
		for (int i = 0; i < validMovesQBoardStates.size();i++) {
			int[][] evalState = copyBoard(validMovesQBoardStates.get(i));
			int[] queenLocation = queenPositions.get(i*5);
			int[] queenLocation2 = queenPositions.get(i*5+1);
			int[] queenLocation3 = queenPositions.get(i*5+2);
			int[] queenLocation4 = queenPositions.get(i*5+3);
			int[] lastQueenLoc = queenPositions.get(i*5+4);
			
			ArrayList<int[]> tempQueens = new ArrayList<int[]>();
			tempQueens.add(queenLocation);
			tempQueens.add(queenLocation2);
			tempQueens.add(queenLocation3);
			tempQueens.add(queenLocation4);
			
			if(inputBoard.turn==1) {
				//UP
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0],queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0],queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0],queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0],queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//UP Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1])) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1])) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//UP Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(tempQueens), copyList(inputBoard.queenPos2), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}

			}else {
				//UP
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0],queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0],queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0],queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0],queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//UP Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1])) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1])) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//UP Left
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]-j,queenLocation4[1]+j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]-j,queenLocation4[1]+j));
						newBoard.board[queenLocation4[0]-j][queenLocation4[1]+j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				//Down Right
				for(int j = 1; j<9; j++) {
					if(checkValid(evalState,queenLocation4[0]+j,queenLocation4[1]-j)) {
						BoardState newBoard = new BoardState(copyBoard(evalState), copyList(inputBoard.queenPos1), copyList(tempQueens), inputBoard.turn*-1, inputBoard.lastState, new Move(lastQueenLoc[0],lastQueenLoc[1],queenLocation4[0],queenLocation4[1],queenLocation4[0]+j,queenLocation4[1]-j));
						newBoard.board[queenLocation4[0]+j][queenLocation4[1]-j]=3;
						validMovesBoardStates.add(newBoard);
					}else {
						break;
					}
					
				}
				

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
	
	//Copy Arraylist
	public static ArrayList<int[]> copyList(ArrayList<int[]> list){
		
		ArrayList<int[]> al = new ArrayList<int[]>();
		for (int[] item: list) {
			
			al.add(item.clone());
		}
		
		
		return al;
		
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
		
		int[][] manual = { { 2, 3, 0, 0, 0, 3, 3, 3, 3, 2 }, { 3, 3, 0, 0, 0, 0, 3, 0, 3, 3 },
                { 0, 3, 0, 0, 0, 0, 3, 2, 3, 2 }, { 1, 0, 3, 0, 3, 0, 0, 3, 3, 3 }, { 0, 0, 3, 0, 0, 0, 0, 3, 3, 0 },
                { 0, 0, 3, 1, 0, 3, 1, 3, 0, 1 }, { 0, 0, 3, 0, 3, 3, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 3, 0, 0, 3, 0 },
                { 0, 0, 0, 0, 0, 3, 0, 0, 3, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		tester.board = manual;
		
		
		ArrayList<BoardState> moves = MoveGenerator.getMoves(tester);
		
		for(int i = 0; i< moves.size(); i++) {
			printBoard(moves.get(i).board);
		}
		
		System.out.println(moves.size());
		printBoard(manual);
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
