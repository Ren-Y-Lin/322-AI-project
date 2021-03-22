package ubc.cosc322;

import java.util.ArrayList;
import java.util.Collections;

public class BoardStateEvaluator {
	
	//This class evaluates how good a single board is
	
	public static int evaluateBoard(BoardState b) {
		//Need: how many empty squares each side has access to
		
		//board = copy of original board to work on. int[x][y]. x = up/down. y = left/right
		int[][] board = MoveGenerator.copyBoard(b.board);
		
		/*
		//Testing Code Start 
		resultNeighbors resultTest = getNeighbors(board,2,1);
		
		printBoard(resultTest.board);
		for(int[] cords : resultTest.neighbors) {
			System.out.println("cord: " + cords[0] + "," + cords[1]);
		}
		System.out.println("Player1:" + resultTest.players[0]);
		System.out.println("Player2:" + resultTest.players[1]);
		//Testing Code End 
		*/
		
		//Counting each player's squares
		int player1 = 0,player2 = 0;
		
		//Loop through the board
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				if(board[x][y] == 0) {
					resultNeighbors result = getNeighbors(board,x,y);
					board = result.board;
					int squares = result.neighbors.size();
					if(result.players[0])
						player1 += squares;
					if(result.players[1])
						player2 += squares;
					
				}
			}
		}
		
		//System.out.println("Player1 Score:" + player1);
		//System.out.println("Player2 Score:" + player2);
		
		//Assuming turn is either 1 for player1 or 2 for player2
		if(b.turn == 1)
			if(player1>0) {
				return player1 - player2;
			}else {
				return -9999;
			}
			
		else
			if(player2>0) {
				return player2 - player1;
			}else {
				return 9999;
			}
			
		
	}
	
	//Get all the neighbors of the cell. ArrayList<coordinate[x,y]>
	private static resultNeighbors getNeighbors(int[][] board, int x, int y) {
		//resultNeighbors emptyResult = new resultNeighbors(board);
		
		resultNeighbors Output = getNeighborsHelper(x, y,0,new resultNeighbors(board));
		
		return Output;
	}
	
	
	//Recursive method with counter as recursion limit or until all neighbors are found
	//players = boolean[player1,player2]. true means the area contains that player's queen.
	//This method counts player's queen as walls so sections blocked by a queen will be separate sections
	private static resultNeighbors getNeighborsHelper(int x, int y, int counter, resultNeighbors result) {
		//If the recursion limit is reached, return neighbors list without checking anything. Currently kind of broken so if we want to use this it needs a small rework
		if(counter > 100 ) {
			return result;
		}
		
		//Checking all 8 directions
		for(int i = 0; i < 8; i++) {
			int newX = x, newY = y;
			switch(i)  {
			case 0: //N
				newX = x-1;
				newY = y;
				break;
			case 1: //NE
				newX = x-1;
				newY = y+1;
				break;
			case 2: //E
				newX = x;
				newY = y+1;
				break;
			case 3: //SE
				newX = x+1;
				newY = y+1;
				break;
			case 4: //S
				newX = x+1;
				newY = y;
				break;
			case 5: //SW
				newX = x+1;
				newY = y-1;
				break;
			case 6: //W
				newX = x;
				newY = y-1;
				break;
			case 7: //NW
				newX = x-1;
				newY = y-1;
				break;
			}
			
			//Check if the square is a valid square on the board
			if(checkValid(result.board,newX,newY)) {
				//Empty Square found
				if(result.board[newX][newY] == 0) {
					int[] toAdd = {newX,newY};
					
					result.neighbors.add(toAdd);
					//mark found location with 3
					result.board[newX][newY] = 3;
					
					result = getNeighborsHelper(newX, newY, counter+1, result);
				}
				//If not 0 or 3 then it's a queen. so mark the found queen in players
				else if(result.board[newX][newY] != 3) {
					result.players[result.board[newX][newY]-1] = true;
				}
				//If fire found then do nothing
				
			}
		}
		
		return result;
	}
	
	//neighbors = array list of all neighbors connected to the input
	//players = size 2 boolean array where players[0] means that player1 has access to this neighborhood. players[1] for player2
	private static class resultNeighbors {
		ArrayList<int[]> neighbors = new ArrayList<int[]>();
		boolean[] players = new boolean[2];
		int[][] board;
		
		public resultNeighbors(int[][] board) {
			this.board = board;
		}
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
	
	//check if the coordinate is out of bounds
	public static boolean checkValid(int[][] board, int x, int y) {
		//System.out.println(x + "," + y);
		if(!(x>board.length-1|| x<0 || y>board[x].length-1 || y<0)) {
			return true;
		}
		else return false;
	}

}
