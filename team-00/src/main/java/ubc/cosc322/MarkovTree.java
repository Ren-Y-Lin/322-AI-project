package ubc.cosc322;

public class MarkovTree {
	BoardStateHead bsh = new BoardStateHead();
	
	public MarkovTree() {
		
		
		
		
		
	}
	
	
	
	public Move getBestMove() {
		MoveGenerator mg = new MoveGenerator(); 
		int [][][] possibleStates = mg.getMoves(null, 0);
		
		return null;
		
		
	}
	
	
	//takes a new position and plays till it ends while updating the tree values
	public void playToFinish(BoardState bs) {
		
		
		
		
	}
	

}
