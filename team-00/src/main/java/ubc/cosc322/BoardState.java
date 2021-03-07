package ubc.cosc322;

import java.util.ArrayList;

public class BoardState implements java.io.Serializable {

	// evaluation
	double evaluation = -1;

	// board state
	int[][] board;

	
	//queen position for the two colors
	public ArrayList<int[]>queenPos1 = new ArrayList<int[]>();
	public ArrayList<int[]>queenPos2 = new ArrayList<int[]>();
	Move lastMove;

	// weight for the markov tree selection algorithm
	int weight = 0;

	// Tracks states
	int timesWon = 0;
	int timesPlayed = 0;

	// Tracks possible next moves
	public ArrayList<BoardState> nextStates;
	// Tracks last board state
	public BoardState lastState;

	// Tracks whose turn it is
	int turn;

	// Constructors
	public BoardState(int[][] board, ArrayList<int[]> QueenPos1, ArrayList<int[]> QueenPos2, int turn,
			BoardState lastState, Move lastMove) {
		this.board = board;

		this.queenPos1 = QueenPos1;
		this.queenPos2 = QueenPos2;
		this.turn = turn;
		this.lastMove = lastMove;
		this.lastState = lastState;

	}

	public BoardState(int[][] board, int turn, BoardState lastState, Move lastMove) {
		this.board = board;

		this.turn = turn;
		this.lastMove = lastMove;
		this.lastState = lastState;

	}

	// Returns all possible board variation
	// If none found, generates all of them and backpropagates instead
	ArrayList<BoardState> getNextStates() {
		if (nextStates == null) {
			nextStates = MoveGenerator.getMoves(this);
			int won = 0;
			int play = 0;

				
			for (BoardState i : nextStates) {
				
				Simulator.playTillEnd(i);
				won+=i.timesWon;
				play++;
			}


			backpropagate(won, play);
			return nextStates;
			
			
		} else {
			return nextStates;
		}

	}

	// propagates wins and games backwards
	public void backpropagate(int wins, int games) {
		timesWon += wins;
		timesPlayed += games;
		if (lastState != null) {

			lastState.backpropagate(wins, games);

		}

	}

	public double getEvaluation() {
		if (evaluation == -1) {
			evaluation = NeuralEvaluator.evaluateBoard(board);
		}
		return evaluation;

	}
	// TODO
	// The null below is caused by the lastState in the constructor. idk what the
	// intent is but I left it null for now. -Winter
//	public BoardState copy() {
//		BoardState copy = new BoardState(board,queenPos1,queenPos2,turn,lastState,lastMove);
//		return copy;
//	}

	public boolean isLastMoveEqual(int[] lastMoves) {
		if (lastMoves[0] == lastMove.QX && lastMoves[1] == lastMove.QY && lastMoves[2] == lastMove.QmoveX
				&& lastMoves[3] == lastMove.QmoveY && lastMoves[4] == lastMove.arrowX
				&& lastMoves[5] == lastMove.arrowY) {
			return true;
		}

		return false;
	}
	

}
