package ubc.cosc322;

public class tester1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BoardState bs = new BoardStateHead();
		
		for(int i = 0 ; i < 10; i++) {
			printBoard(bs.board);
			bs = MinmaxEvaluator.evaluateBoard(bs, 1).bs;
		}
		

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

}
