package ubc.cosc322;

import java.util.ArrayList;

public class BoardStateHead extends BoardState {

	// Generates the Head of the markov tree
	public BoardStateHead() {

		super(null, 1, null, null);
		this.queenPos2.add(new int[] { 0, 3 });
		this.queenPos2.add(new int[] { 0, 6 });
		this.queenPos2.add(new int[] { 2, 0 });
		this.queenPos2.add(new int[] { 2, 9 });
		this.queenPos1.add(new int[] { 9, 3 });
		this.queenPos1.add(new int[] { 9, 6 });
		this.queenPos1.add(new int[] { 6, 0 });
		this.queenPos1.add(new int[] { 6, 9 });
		int[][] tboard = { { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 0, 2}, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 0, 0, 2, 0, 0, 0 } };
		super.board = tboard;

	}

}
