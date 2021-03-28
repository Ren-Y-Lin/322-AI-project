package ubc.cosc322;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MarkovTree {
	BoardStateHead bsh = new BoardStateHead();
	BoardState currentBoard = bsh;

	public MarkovTree() {
		//loadTree("save.txt");
	}
	
	public static void main(String[] args) {
		MarkovTree mt = new MarkovTree();
		mt.train(4);
		System.out.println("trained");
	}

	 // train tree
    public void train(int iterations) {
        for (int i = 0; i < iterations; i++) {
            ArrayList<BoardState> nextStates;
            nextStates = currentBoard.getNextStates();
            int count = 0;
            System.out.println("in loop :"+ count++);
            while (nextStates != null) {
            	
                //Evalaute Each Board State and pick the best one
                double maxVal = -90;
                BoardState bbs = nextStates.get(0);
                for (BoardState bs : nextStates) {
                    if (maxVal < ((double) bs.timesWon) / bs.timesPlayed) {
                        maxVal = ((double) bs.timesWon) / bs.timesPlayed;
                        bbs = bs;
                    }
                    
                    
                }
                
                //Go down 1 layer on the best board state
                
                currentBoard = bbs;
                printBoard(currentBoard.board);
                
            }
            
            currentBoard = bsh;
            
        }
        
        saveTree("save.txt");
    }
    
    public BoardState play(BoardState currentBoard){
        if(currentBoard.timesPlayed > 0) 
        {
            int high = 0;
            BoardState highState = null;
            for(BoardState bs : currentBoard.nextStates) 
            {
                if(bs.timesPlayed > high) 
                {
                    high = bs.timesPlayed;
                    highState = bs;
                }
            }
            return highState;
            
        } else
        {
            return MinmaxEvaluator.evaluateBoard(currentBoard, 4).bs;
        }
    }
	// Save trained tree to file
	public void saveTree(String fileName) {

		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bsh);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + fileName);
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	// Load trees
	public void loadTree(String fileName) {

		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bsh = (BoardStateHead) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
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

	public void updateBoard(int[] move) throws InvalidMoveException {
		ArrayList<BoardState> nextStates = currentBoard.getNextStates();

		if (nextStates == null || nextStates.size() == 0) {
			throw new InvalidMoveException("No valid move left: Loss");
		}
		boolean moveExist = false;

		for (BoardState bs : nextStates) {
			//if (bs.isLastMoveEqual(move)) {
			//	currentBoard = bs;
			//	moveExist = true;

			//}
		}
		if (!moveExist) {
			throw new InvalidMoveException("No Valid Next Move");
		}

	}

}