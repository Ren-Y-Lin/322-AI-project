
package ubc.cosc322;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.Amazon;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

/**
 * An example illustrating how to implement a GamePlayer
 * 
 * @author Yong Gao (yong.gao@ubc.ca) Jan 5, 2021
 *
 */
public class COSC322Test extends GamePlayer {

	private GameClient gameClient = null;
	private BaseGameGUI gamegui = null;
	private int round = 0;
	public static long timeStart = System.currentTimeMillis();
	private boolean caution;

	private String userName = "Username";
	private String passwd = "Password";
	BoardState bsh = new BoardStateHead();
	int player = -1;

	/**
	 * The main method
	 * 
	 * @param args for name and passwd (current, any string would work)
	 */
	public static void main(String[] args) {
		COSC322Test player = new COSC322Test(args[0], args[1]);

		if (player.getGameGUI() == null) {
			player.Go();
		} else {
			BaseGameGUI.sys_setup();
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					player.Go();
				}
			});
		}
	}

	/**
	 * Any name and passwd
	 * 
	 * @param userName
	 * @param passwd
	 */
	public COSC322Test(String userName, String passwd) {
		this.userName = userName;
		this.passwd = passwd;

		// To make a GUI-based player, create an instance of BaseGameGUI
		// and implement the method getGameGUI() accordingly
		this.gamegui = new BaseGameGUI(this);
	}

	@Override
	public void onLogin() {

		userName = gameClient.getUserName();
		if (gamegui != null) {
			gamegui.setRoomInformation(gameClient.getRoomList());
		}

		/*
		 * System.out.println("Congratualations!!! " +
		 * "I am called because the server indicated that the login is successfully");
		 * 
		 * 
		 * List<Room> roomList = gameClient.getRoomList(); String roomName
		 * =roomList.get(0).getName();
		 * 
		 * 
		 * 
		 * 
		 * System.out.println(""+roomList);
		 * System.out.println("The next step is to find a room and join it: " +
		 * "the gameClient instance created in my constructor knows how!");
		 * 
		 * GameClient gameClient = getGameClient(); if(gamegui != null) {
		 * gamegui.setRoomInformation(gameClient.getRoomList()); }
		 * 
		 * GameClient gameClient = getGameClient(); userName = gameClient.getUserName();
		 * if(gamegui != null) { gamegui.setRoomInformation(gameClient.getRoomList()); }
		 */

	}

	@Override
	public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
		// This method will be called by the GameClient when it receives a game-related
		// message
		// from the server.
		// System.out.println("yomies "+messageType+ " "+ GameMessage.GAME_STATE_BOARD);
		round = 0;
		System.out.println("DETAILS BRO: " + msgDetails);
		// gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
		// gamegui.updateGameState(msgDetails);
		// System.out.println(msgDetails.toString());
		// gamegui.updateGameState(msgDetails);
		if (messageType.equals(GameMessage.GAME_ACTION_START)) {
			if (msgDetails.get("player-white").toString().equals(userName)) {
				player = -1;

			} else {

				player = 1;
			}
			System.out.println(player);
			if (player == 1) {

				System.out.println("WERE STARTING");
				// bsh = minimax(bsh, 1, 1);
				ArrayList<BoardState> newStates = bsh.returnNewStates();
				Collections.shuffle(newStates);
				bsh = newStates.get(0);
				System.out.println("MY MOVE");
				Move move = bsh.lastMove;
				// updateHead(move.getQueenPos(),move.getQueenMove(),move.getArrowPos());
				System.out.println("PreMOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos()
						+ " TURN: " + bsh.turn);
				move = move.sendFormat();
				System.out.println("MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() + " TURN: "
						+ bsh.turn);
				gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				// gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));

			}

		}
		// System.out.println("YOUR MESSAGE: " + messageType);
		if (messageType.equals(GameMessage.GAME_STATE_BOARD)) {
			timeStart = System.currentTimeMillis();
			System.out.println("GAME STATE BOARD");
			gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
			bsh = new BoardStateHead();

			// gamegui.updateGameState(msgDetails);

			// System.out.println("homies" + msgDetails.get("game-state"));

			// System.out.println("GAMESTATEBOARD - Game state is: " + gs);
			//
		}

		if (messageType.equals(GameMessage.GAME_ACTION_MOVE)) {
			//
			timeStart = System.currentTimeMillis();
			round += 2;
			caution = false;
			System.out.println("GAME ACTION MOVE");
			// gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
			gamegui.updateGameState(msgDetails);

			Move oppMove = new Move((ArrayList<Integer>) msgDetails.get("queen-position-current"),
					(ArrayList<Integer>) msgDetails.get("queen-position-next"),
					(ArrayList<Integer>) msgDetails.get("arrow-position"), true);
			System.out.println("MOVE: " + oppMove.getQueenPos() + oppMove.getQueenMove() + oppMove.getArrowPos()
					+ " TURN: " + bsh.turn);
			System.out.println("THEIR MOVE");
			try {
				updateHead(oppMove.getQueenPos(), oppMove.getQueenMove(), oppMove.getArrowPos());
			} catch (InvalidMoveException e) {
				return false;
			}

			// System.out.print("GAME ACTION MOVE");
			// ArrayList<Integer> gs = (ArrayList<Integer>) msgDetails.get("game-state");
			// System.out.println("Game state is: " + gs);

			// bsh = minimax(bsh, 3, 3);
			int max = -9999;
			int min = 9999;

			ArrayList<BoardState> newStates = bsh.returnNewStates();
			ArrayList<BoardState> newStates2 = newStates.get(0).returnNewStates();

			BoardState bbs = newStates.get(0);
			BoardState wbs = newStates.get(0);

//			if (newStates.size() > 250 ) {
//				for (int i = 0; i < newStates.size(); i++) {
//					if (BoardStateEvaluator2.evaluateBoard(newStates.get(i)) >= max) {
//						max = BoardStateEvaluator2.evaluateBoard(newStates.get(i));
//						bbs = newStates.get(i);
//					} else if (BoardStateEvaluator2.evaluateBoard(newStates.get(i)) <= min) {
//						min = BoardStateEvaluator2.evaluateBoard(newStates.get(i));
//						wbs = newStates.get(i);
//					}
//				}
//				BoardState temp;
//				if (player == 1) {
//					temp = bbs;
//				} else {
//					temp = wbs;
//				}
//				bsh = temp;
//			} else {
//			}

			counter = 0;
			Collections.shuffle(newStates);/////////// ????????????????????????????????????????????
			try {

				for (int i = 1; i < 15; i++) {
//					if (System.currentTimeMillis() - timeStart < 500) {
						counter = 0;
						bsh = minimaxstart(bsh, i, i);
//					}else {
//						caution = true;
//						bsh = minimaxstart(bsh, i, i);
//						break;
//					}
					System.out.println("Depth Iteration"+i);
				}

//				if(round<30) {
//					System.out.println("Early Round");
//					System.out.println("MINIMAX: 2");
//					bsh = minimaxstart(bsh, 3, 3);
//				}else if(round<40) {
//					System.out.println("Mid Round");
//					System.out.println("MINIMAX: 3");
//					bsh = minimaxstart(bsh, 4, 4);
//				}else
//				
//				if (newStates.size() > 50 && newStates2.size() > 50) {
//					System.out.println("Our size:" + newStates.size() + " Enemy size:" + newStates2.size());
//					System.out.println("MINIMAX: 3");
//					bsh = minimaxstart(bsh, 3, 3);
//				} else {
//					System.out.println("Our size:" + newStates.size() + " Enemy size:" + newStates2.size());
//					System.out.println("MINIMAX: 4");
//					bsh = minimaxstart(bsh, 4, 4);
//				}

			} catch (InvalidMoveException e) {

				for (int i = 0; i < newStates.size(); i++) {
					if (evaluateBoardBasedOnTurn(newStates.get(i)) > max) {
						max = evaluateBoardBasedOnTurn(newStates.get(i));
						bbs = newStates.get(i);
					} else if (evaluateBoardBasedOnTurn(newStates.get(i)) < min) {
						min = evaluateBoardBasedOnTurn(newStates.get(i));
						wbs = newStates.get(i);
					}
				}
				BoardState temp;
				if (player == 1) {
					temp = bbs;
				} else {
					temp = wbs;
				}
				bsh = temp;

			}

			if (newStates.size() < 1) {
				System.out.println("GAME IS REALLY OVER I THINK/HOPE");
			}
			Move move = bsh.lastMove;

			System.out.println("MY MOVE");
			// updateHead(move.getQueenPos(),move.getQueenMove(),move.getArrowPos());
			//

			move = move.sendFormat();
			System.out.println(
					"MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() + " TURN: " + bsh.turn);
			gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
			gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());

			// gamegui.updateGameState(mv.getQueenPos(), mv.getQueenMove(),
			// mv.getArrowPos());
			// gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
			// //gameClient.sendMoveMessage(temp.lastMove.getQueenPos(),
			// temp.lastMove.getQueenMove(), temp.lastMove.getArrowPos());
			// gamegui.updateGameState(temp.lastMove.getQueenPos(),
			// temp.lastMove.getQueenMove(), temp.lastMove.getArrowPos());
			//
		}

		// gameClient.leaveCurrentRoom();
		// System.out.println("MessageType: "+messageType);
		// gamegui.updateGameState(msgDetails);
		// For a detailed description of the message types and format,
		// see the method GamePlayer.handleGameMessage() in the game-client-api
		// document.

		return true;
	}

	@Override
	public String userName() {
		return userName;
	}

	@Override
	public GameClient getGameClient() {
		// TODO Auto-generated method stub
		return this.gameClient;
	}

	@Override
	public BaseGameGUI getGameGUI() {
		// TODO Auto-generated method stub
		return this.gamegui;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		gameClient = new GameClient(userName, passwd, this);
	}

	public void updateHead(ArrayList<Integer> queenCur, ArrayList<Integer> queenNew, ArrayList<Integer> arrow)
			throws InvalidMoveException { // throws
		// InvalidMoveException
		// {
		ArrayList<BoardState> nextStates = bsh.returnNewStates();
		Collections.shuffle(nextStates);// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADDED CHECK PERF
		Move move = new Move(queenCur, queenNew, arrow, false);

		for (BoardState bs : nextStates) {

			if (bs.isLastMoveEqual(move)) {
				bsh = bs;
				System.out.println("FOUND BOARD -- NEW BORD IS:");
				BoardStateEvaluator.printBoard(bs);
				System.out.println("MOVE: " + bsh.lastMove.getQueenPos() + bsh.lastMove.getQueenMove()
						+ bsh.lastMove.getArrowPos() + " TURN: " + bsh.turn);
				return;
			}
		}

		System.out.println("COULD NOT FIND BOARD MOVE... ILLEGAL MOVE");
		System.out.println("Failed to find MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos()
				+ " TURN: " + bsh.turn);
		System.out.println("EXITING CODE");
		throw new InvalidMoveException("");
//		bsh = nextStates.get(0);
//		BoardStateEvaluator2.printBoard(bsh);
//
//		System.out.println("MOVE: " + bsh.lastMove.getQueenPos() + bsh.lastMove.getQueenMove()
//				+ bsh.lastMove.getArrowPos() + " TURN: " + bsh.turn);

	}

	int counter = 0;

	public BoardState minimaxstart(BoardState bs, int depth, int maxdepth) throws InvalidMoveException {
		// System.out.println(counter++);
		counter++;
		if (counter % 100000 == 0) {
			System.out.println(counter);
		}
		if(counter > 8000000) {
			throw new InvalidMoveException("lol");
		}
//		if (counter > 7000000 && maxdepth == 3) {
//			throw new InvalidMoveException("lol");
//		}
//		if (counter > 7000000 && maxdepth == 4) {
//			throw new InvalidMoveException("lol");
//		}
		if (System.currentTimeMillis() - timeStart > 10000) {
			throw new InvalidMoveException("lol");
		}
		if (depth < 1) {
			// System.out.println("breakpoint 6");
			if (bs.turn == 1) {
				bs.value = -evaluateBoardBasedOnTurn(bs);
			} else {
				bs.value = evaluateBoardBasedOnTurn(bs);
			}

			// System.out.println("breakpoint 7");
			return bs;
		}

		// might be empty array, might be null
		ArrayList<BoardState> nextMoves = bs.returnNewStates();
		if (depth == maxdepth) {
			if (nextMoves == null || nextMoves.size() < 1) {
				System.out.println("GAME LOSS");
				Move move = bs.lastMove.sendFormat();
				gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				return bs;
			}
		}

		if (nextMoves == null || nextMoves.size() < 1) {
			if (bs.turn == player) {
				bs.value = -9998;
			} else {
				bs.value = 9998;
			}

			System.out.println("GAME OVER");
			return bs;
		}
//		else if(nextMoves.get(0).returnNewStates().size() < 1) {
//			bs.value = 9999;
//			return bs;
//		}
		AtomicInteger k = new AtomicInteger(-1);
		nextMoves.parallelStream().forEach(s -> {

			try {
				s = nextMoves.get(k.incrementAndGet());
				s.value = minimax(s, depth - 1, maxdepth, 9999, -9999).value;
				nextMoves.set(k.get(), s);

				// System.out.print("H");
				// System.out.println(s.value);
				// System.out.println("true value"+s.value+" expected value" + nextMoves);
			} catch (InvalidMoveException e) {
				// System.out.print("I");
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		});

		BoardState bbs = null;

		if (depth % 2 != maxdepth % 2) {
			double minVal = 9999;
			// System.out.println(nextMoves.get(10).value);

			// System.out.println("evaluated the list"+nextMoves.get(10).value);
			for (BoardState board : nextMoves) {

				if (minVal > board.value) {
					minVal = board.value;
					bbs = board;
				}

			}

		} else {
			double maxVal = -9999;
			// System.out.println(nextMoves.get(10).value);

			// System.out.println("evaluated the list"+nextMoves.get(10).value);
			for (BoardState board : nextMoves) {

				if (maxVal < board.value) {
					maxVal = board.value;
					bbs = board;
				}
			}

		}

		if (maxdepth == depth) {

			return bbs;
		} else {
			bs.value = bbs.value;
			return bs;
		}

	}

	public BoardState minimax(BoardState bs, int depth, int maxdepth, double max, double min)
			throws InvalidMoveException {
		// System.out.println(counter++);
		counter++;
		if (counter % 100000 == 0) {
			System.out.println(counter);
		}
		
		if(counter > 8000000) {
			throw new InvalidMoveException("lol");
		}
//		if (counter > 7000000 && maxdepth == 3) {
//		throw new InvalidMoveException("lol");
//	}
//	if (counter > 7000000 && maxdepth == 4) {
//		throw new InvalidMoveException("lol");
//	}
//		if (System.currentTimeMillis() - timeStart > 10000) {
//			throw new InvalidMoveException("lol");
//		}

		if (depth < 1) {
			// System.out.println("breakpoint 6");
			if (bs.turn == 1) {
				bs.value = -evaluateBoardBasedOnTurn(bs);
			} else {
				bs.value = evaluateBoardBasedOnTurn(bs);
			}

			// System.out.println("breakpoint 7");
			return bs;
		}

		// might be empty array, might be null
		ArrayList<BoardState> nextMoves = bs.returnNewStates();
		Collections.shuffle(nextMoves);// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADDED CHECK PERF
		if (depth == maxdepth) {
			if (nextMoves == null || nextMoves.size() < 1) {
				System.out.println("GAME LOSS");
				Move move = bs.lastMove.sendFormat();
				gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
				return bs;
			}
		}

		if (nextMoves == null || nextMoves.size() < 1) {
			if (bs.turn == player) {
				bs.value = -9998;
			} else {
				bs.value = 9998;
			}

			System.out.println("GAME OVER");
			return bs;
		}
//		else if(nextMoves.get(0).returnNewStates().size() < 1) {
//			bs.value = 9999;
//			return bs;
//		}

		BoardState bbs = null;

		if (depth % 2 != maxdepth % 2) {
			double minVal = 9999;
			double tempmin = 9999;

			for (BoardState board : nextMoves) {

				board.value = minimax(board, depth - 1, maxdepth, max, tempmin).value;

//				if(board.value>max) {
//					return board;
//				}
//				if(board.value<tempmin) {
//					tempmin = board.value;
//				}

				if (minVal > board.value) {
					minVal = board.value;
					bbs = board;
				}

			}

		} else {
			double maxVal = -9999;
			double tempmax = -9999;

			for (BoardState board : nextMoves) {

				board.value = minimax(board, depth - 1, maxdepth, tempmax, min).value;
//				if(board.value<min) {
//					return board;
//				}
//				if(board.value<tempmax) {
//					tempmax = board.value;
//				}
				if (maxVal < board.value) {
					maxVal = board.value;
					bbs = board;
				}
			}

		}

		if (maxdepth == depth) {

			return bbs;
		} else {
			bs.value = bbs.value;
			return bs;
		}

	}

	public int evaluateBoardBasedOnTurn(BoardState bs) {
		if (round < 25) {
			return BoardStateEvaluator0.evaluateBoard(bs);
		} else if (round < 50) {
			return BoardStateEvaluator2.evaluateBoard(bs);
		}

		return BoardStateEvaluator3.evaluateBoard(bs);

	}

}// end of class
