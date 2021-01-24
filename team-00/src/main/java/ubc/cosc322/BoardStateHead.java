package ubc.cosc322;

public class BoardStateHead extends BoardState{
	static int[][] initQueenPos2 = {{0,3},{0,6},{2,0},{2,9}};
	static int[][] initQueenPos1 = {{9,3},{9,6},{7,0},{7,9}};
	//Generates the Head of the markov tree
	public BoardStateHead() {
		
		super(null,null,initQueenPos1,initQueenPos2,1,null,null);
		int[][] tboard = {{0,0,0,2,0,0,2,0,0,0},{0,0,0,0,0,0,0,0,0,0},{2,0,0,0,0,0,0,0,0,2},
				{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
		super.board=tboard;
		
		
		
	}
	

	
	
	
	

}
