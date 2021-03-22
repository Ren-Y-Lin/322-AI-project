
package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.Amazon;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

/**
 * An example illustrating how to implement a GamePlayer
 * @author Yong Gao (yong.gao@ubc.ca)
 * Jan 5, 2021
 *
 */
public class COSC322Test extends GamePlayer{

    private GameClient gameClient = null; 
    private BaseGameGUI gamegui = null;
	
    private String userName = "Username";
    private String passwd = "Password";
    BoardState bsh = new BoardStateHead();
    int player = -1;

    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {				 
    	COSC322Test player = new COSC322Test(args[0], args[1]);
    	
    	if(player.getGameGUI() == null) {
    		player.Go();
    	}
    	else {
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
     * @param userName
      * @param passwd
     */
    public COSC322Test(String userName, String passwd) {
    	this.userName = userName;
    	this.passwd = passwd;
    	
    	//To make a GUI-based player, create an instance of BaseGameGUI
    	//and implement the method getGameGUI() accordingly
    	this.gamegui = new BaseGameGUI(this);
    }
 


    @Override
    public void onLogin() {
    	
    	
    	userName = gameClient.getUserName();
    	if(gamegui != null) {
    		gamegui.setRoomInformation(gameClient.getRoomList());
    	}
    	
    	/*System.out.println("Congratualations!!! "
    			+ "I am called because the server indicated that the login is successfully");
    	
    	
    	List<Room> roomList = gameClient.getRoomList();
    	String roomName =roomList.get(0).getName();
    	
    	

    	
    	System.out.println(""+roomList);
    	System.out.println("The next step is to find a room and join it: "
    			+ "the gameClient instance created in my constructor knows how!");
    	
    	GameClient gameClient = getGameClient();
    	if(gamegui != null) {
    		gamegui.setRoomInformation(gameClient.getRoomList());
    	}
    	
    	GameClient gameClient = getGameClient();
    	userName = gameClient.getUserName();
    	if(gamegui != null) {
    		gamegui.setRoomInformation(gameClient.getRoomList());
    	}*/
    	

    }

    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
    	//This method will be called by the GameClient when it receives a game-related message
    	//from the server.
    	//System.out.println("yomies   "+messageType+ "  "+ GameMessage.GAME_STATE_BOARD);
    	
    	System.out.println("DETAILS BRO: " + msgDetails);
    	//gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
    	//gamegui.updateGameState(msgDetails);
    	//System.out.println(msgDetails.toString());
    	//gamegui.updateGameState(msgDetails);
    	if(messageType.equals(GameMessage.GAME_ACTION_START)) {
    		if(msgDetails.get("player-white").toString().equals(userName)) {
    			player = -1;
    			
    		}else {
    			
    			player = 1;
    		}
    		System.out.println(player);
    		if(player==1) {
    			
        		System.out.println("WERE STARTING");
        		//bsh = minimax(bsh, 1, 1);
        		bsh=bsh.returnNewStates().get(0);
        		System.out.println("MY MOVE");
        		Move move = bsh.lastMove;
        		//updateHead(move.getQueenPos(),move.getQueenMove(),move.getArrowPos());
        		System.out.println("PreMOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() +" TURN: " + bsh.turn);
        		move = move.sendFormat();
        		System.out.println("MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() +" TURN: " + bsh.turn);
        		gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
        		gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
        		//gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
    			
    			
    		}
    		

    
    
    		
    	}
    	//System.out.println("YOUR MESSAGE: " + messageType);
    	if(messageType.equals(GameMessage.GAME_STATE_BOARD)) {
    		System.out.println("GAME STATE BOARD");
    		gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
    		bsh = new BoardStateHead();

    		//gamegui.updateGameState(msgDetails);
    		
    	//	System.out.println("homies" + msgDetails.get("game-state"));

    	//	System.out.println("GAMESTATEBOARD - Game state is: " + gs);
    	//
    	}
    	
    	
    	if(messageType.equals(GameMessage.GAME_ACTION_MOVE)) {
    	//	
    		System.out.println("GAME ACTION MOVE");
    	//	gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
    		gamegui.updateGameState(msgDetails);
    		
    		Move oppMove = new Move ((ArrayList<Integer>)msgDetails.get("queen-position-current"), (ArrayList<Integer>)msgDetails.get("queen-position-next"),(ArrayList<Integer>) msgDetails.get("arrow-position"), true);
    		System.out.println("MOVE: " + oppMove.getQueenPos() + oppMove.getQueenMove() + oppMove.getArrowPos() +" TURN: " + bsh.turn);
    		System.out.println("THEIR MOVE");
    		updateHead(oppMove.getQueenPos(), oppMove.getQueenMove(), oppMove.getArrowPos());
    	//	System.out.print("GAME ACTION MOVE");
    	//	ArrayList<Integer> gs = (ArrayList<Integer>) msgDetails.get("game-state");
    	//	System.out.println("Game state is: " + gs);
    		
    		// bsh = minimax(bsh, 3, 3);
    		int max = -9999;
    		int min = 9999;
    		BoardState bbs = bsh.returnNewStates().get(0);
    		BoardState wbs = bsh.returnNewStates().get(0);
    		for(int i = 0 ; i < bsh.returnNewStates().size(); i++) {
    			if(BoardStateEvaluator.evaluateBoard(bsh.returnNewStates().get(i))>max) {
    				max = BoardStateEvaluator.evaluateBoard(bsh.returnNewStates().get(i));
    				bbs = bsh.returnNewStates().get(i);
    			}else if(BoardStateEvaluator.evaluateBoard(bsh.returnNewStates().get(i))<min){
    				min = BoardStateEvaluator.evaluateBoard(bsh.returnNewStates().get(i));
    				wbs = bsh.returnNewStates().get(i);
    			}
    		}
    		BoardState temp;
    		if(player == 1) {
    			temp = bbs;
    		}else {
    			temp = wbs;
    		}
    		 
    		bsh = temp;
    		Move move = bsh.lastMove;

    		System.out.println("MY MOVE");
    		//updateHead(move.getQueenPos(),move.getQueenMove(),move.getArrowPos());
    	//	
    		
    		move = move.sendFormat();
    		System.out.println("MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() +" TURN: " + bsh.turn);
    		gameClient.sendMoveMessage(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
    		gamegui.updateGameState(move.getQueenPos(), move.getQueenMove(), move.getArrowPos());
    		
    	//	gamegui.updateGameState(mv.getQueenPos(), mv.getQueenMove(), mv.getArrowPos());
    	//	gamegui.setGameState((ArrayList<Integer>) msgDetails.get("game-state"));
    	//	//gameClient.sendMoveMessage(temp.lastMove.getQueenPos(), temp.lastMove.getQueenMove(), temp.lastMove.getArrowPos());
    	//	gamegui.updateGameState(temp.lastMove.getQueenPos(), temp.lastMove.getQueenMove(), temp.lastMove.getArrowPos());
    	//	
    	}

		//gameClient.leaveCurrentRoom();
		//System.out.println("MessageType: "+messageType);
		//gamegui.updateGameState(msgDetails);
    	//For a detailed description of the message types and format, 
    	//see the method GamePlayer.handleGameMessage() in the game-client-api document. 
    	    	
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
		return  this.gamegui;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
    	gameClient = new GameClient(userName, passwd, this);			
	}
	
	public void updateHead(ArrayList<Integer> queenCur, ArrayList<Integer> queenNew, ArrayList<Integer> arrow) { //throws InvalidMoveException {
		ArrayList<BoardState> nextStates = bsh.returnNewStates();
		Move move = new Move(queenCur, queenNew, arrow, false);

		
		
		for (BoardState bs : nextStates) {
			
			if (bs.isLastMoveEqual(move)) {
				bsh = bs;
				System.out.println("FOUND BOARD -- NEW BORD IS:");
				BoardStateEvaluator.printBoard(bs);
				System.out.println("MOVE: " + bsh.lastMove.getQueenPos() + bsh.lastMove.getQueenMove() + bsh.lastMove.getArrowPos() +" TURN: " + bsh.turn);
				return;
			}
		}
	
		System.out.println("COULD NOT FIND BOARD MOVE... RETURNING FIRST OPTION");
		System.out.println("Failed to find MOVE: " + move.getQueenPos() + move.getQueenMove() + move.getArrowPos() +" TURN: " + bsh.turn);
		bsh = nextStates.get(0);
		BoardStateEvaluator.printBoard(bsh);
		
		System.out.println("MOVE: " + bsh.lastMove.getQueenPos() + bsh.lastMove.getQueenMove() + bsh.lastMove.getArrowPos() +" TURN: " + bsh.turn);
		
		

	}
	
	public BoardState minimax(BoardState bs, int depth,int maxdepth) {
		
		
		if(depth < 1 ) {
			//System.out.println("breakpoint 6");
			BoardState out = bs;
			bs.value = BoardStateEvaluator.evaluateBoard(bs);
			//System.out.println("breakpoint 7");
			return bs;
		}
			

		
		//might be empty array, might be null
		ArrayList<BoardState> nextMoves = bs.returnNewStates();
		
		if(nextMoves==null || nextMoves.size()<1) {
			System.out.println("GAME LOSS");
			return null;
		}
		
		
		BoardState bbs = null;
		
	
	
		
		if(depth%2 == 0) {
			double minVal = 9999;
		for (BoardState board : nextMoves) {

			board.value = minimax(board,depth-1, maxdepth).value;
			
			if(minVal > board.value) {
				minVal = board.value;
				bbs = board;
			}
			
		
		}

			
		} else {
			double maxVal = -9999;
			for (BoardState board : nextMoves) {

				board.value = minimax(board,depth-1, maxdepth).value;
				
				
				if(maxVal < board.value) {
					maxVal = board.value;
					bbs = board;
				}
		}
			
		}
		
		if(maxdepth == depth) {
			
		return bbs;
		} else {
			bs.value = bbs.value;
			return bs;
		}
		
		
		
		
		
	}
	
	
	

	

}//end of class
