package ubc.cosc322;

public class BoardStateHead extends BoardState{
	
	//Generates the Head of the markov tree
	public BoardStateHead() {
		
		super(null,null);
		int[][] tboard = {{0,0,0,2,0,0,2,0,0,0},{0,0,0,0,0,0,0,0,0,0},{2,0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
		super.board=tboard;
		
		
		
	}
	
	BoardState[] getNextStates() {
		return super.nextStates;
	}
	
	public void createNextStates(int[][][] boardStates) {
		
		for (int i = 0; i<boardStates.length ; i++) {
			super.nextStates[i] = new BoardState(boardStates[i],this);
		}
		
	}

}
