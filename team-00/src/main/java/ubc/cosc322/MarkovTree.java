package ubc.cosc322;

public class MarkovTree {
	BoardStateHead bsh = new BoardStateHead();
	
	
	public MarkovTree() {
		
		
		
		
		
	}
	
	//Next iteration of training
	public void nextIteration() {
		
	}
	
	
	
	public Move getBestMove() {
		MoveGenerator mg = new MoveGenerator(); 
		int [][][] possibleStates = mg.getMoves(null, 0);
		
		return null;
		
		
	}
	
	
	//takes a new position and plays till it ends while updating the tree values
	public boolean playToFinish(BoardState bs) {
		
		
		return true;
		
	}
	

}
