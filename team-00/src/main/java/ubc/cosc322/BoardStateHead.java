package ubc.cosc322;

public class BoardStateHead {
	int[][] board = {{0,0,0,2,0,0,2,0,0,0},{0,0,0,0,0,0,0,0,0,0},{2,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
	private BoardState[] nextStates = new BoardState[400];

	
	public BoardStateHead() {
		
		
		
		
	}
	
	BoardState[] getNextStates() {
		return nextStates;
	}
	
	public void createNextStates() {
		
	}

}
