package ubc.cosc322;

import java.util.ArrayList;

public class Simulator {
	
	
	
	
	
	public static int playTillEnd(BoardState bs) {
		//Generate the next set of Valid moves

        //Generate the next set of Valid moves

        //BoardState temp = bs;
        //double tempVal = MinmaxEvaluator.evaluateBoard(bs, 5).value;      
		//System.out.println("minmax loop");
		
        BoardPackage tempP = MinmaxEvaluator.evaluateBoard(bs, 2);
        //System.out.println("loop end");
        
        //System.out.println("pre loop");
        
        //int count = 0;
        while(tempP.value<9998 && tempP.value>-9998) {
        	//System.out.println("in loop :"+ count++);
            if(tempP.bs==null) {
            	System.out.println("null");
            }
        	
            tempP = MinmaxEvaluator.evaluateBoard(tempP.bs, 2);
            //temp = tempP.bs;
            //tempVal = tempP.value;
            
        }
		
		
		//

		

		if(tempP.value==9999) {
			return 1;
		}else {
			return -1;
		}
		
		
		
	}
	
	public static double nextPlay(BoardState bs,double randomness) {
		
		
		int turn = bs.turn;
		
		double finalValue = 0;
		
		if(turn == 1) {
			
			ArrayList <BoardState> bsl = MoveGenerator.getMoves(bs);
			for (int i = 0; i< bsl.size();i++) {
				
			}
			
		}else {
			

		}
		
		return randomness;
	
	}

}
