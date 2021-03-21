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

	}

	// train tree
	public void train(int iterations) {
		for (int i = 0; i < 92; i++) {
			ArrayList<BoardState> nextStates;
			nextStates = currentBoard.getNextStates();
			while (nextStates != null) {
				double maxVal = -90;
				BoardState bbs = null;
				for (BoardState bs : nextStates) {
					if (maxVal < ((double) bs.timesWon) / bs.timesPlayed) {
						maxVal = ((double) bs.timesWon) / bs.timesPlayed;
						bbs = bs;
					}

				}
				
				currentBoard = bbs;

			}
			currentBoard = bsh;

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

	public void updateBoard(int[] move) throws InvalidMoveException {
		ArrayList<BoardState> nextStates = currentBoard.getNextStates();

		if (nextStates == null || nextStates.size() == 0) {
			throw new InvalidMoveException("No valid move left: Loss");
		}
		boolean moveExist = false;

		for (BoardState bs : nextStates) {
			if (bs.isLastMoveEqual(move)) {
				currentBoard = bs;
				moveExist = true;

			}
		}
		if (!moveExist) {
			throw new InvalidMoveException("No Valid Next Move");
		}

	}

}
