package ubc.cosc322;

public class BoardState {
	int[][] board;
	int[][] queenPos1;
	int[][] queenPos2;
	Move lastMove;
	
	BoardState head;
	int timesWon=0;
	int timesPlayed=0;
	public BoardState[] nextStates = new BoardState[1000];
	public BoardState lastState;
	int turn;
	
	public BoardState(int[][] board,BoardState head, int[][] QueenPos1, int[][] QueenPos2,int turn,BoardState lastState, Move lastMove) {
		this.board=board;
		this.head = head;
		this.queenPos1=QueenPos1;
		this.queenPos2=QueenPos2;
		this.turn=turn;
		this.lastMove=lastMove;
		
	}
	
	BoardState[] getNextStates() {
		if(nextStates == null) {
			nextStates = MoveGenerator.getMoves(board,turn);
		}
		return nextStates;
	}
	
	public void backpropagate(int wins, int games) {
		timesWon+=wins;
		timesPlayed+=games;
		if(lastState!=null) {
			
			lastState.backpropagate(wins, games);
			
		}
		
		
	}

}
