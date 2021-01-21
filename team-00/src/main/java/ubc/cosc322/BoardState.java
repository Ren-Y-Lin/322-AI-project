package ubc.cosc322;

public class BoardState {
	int[][] board;
	BoardState head;
	int timesWon=0;
	int timesPlayed=0;
	public BoardState[] nextStates = new BoardState[400];
	
	public BoardState(int[][] board,BoardState head) {
		this.board=board;
		this.head = head;
		
	}

}
