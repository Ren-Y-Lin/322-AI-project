package ubc.cosc322;

import java.util.ArrayList;

//Class for movement description
public class Move {
	int QX;
	int QY;
	int QmoveX;
	int QmoveY;
	int arrowX;
	int arrowY;
	
	public Move(int QX,int QY,int QmoveX, int QmoveY,int arrowX,int arrowY) {
		this.QX=QX;
		this.QY=QY;
		this.QmoveX =QmoveX;
		this.QmoveY = QmoveY;
		this.arrowX = arrowX;
		this.arrowY = arrowY;
		
		
	}
	public Move(ArrayList<Integer> queenCur, ArrayList<Integer> queenNew, ArrayList<Integer> arrow, boolean received) {
		if(received) {
		this.QX=9 - queenCur.get(0) - 1;
		this.QY=queenCur.get(1) - 1;
		this.QmoveX =9 - queenNew.get(0) - 1;
		this.QmoveY = queenNew.get(1) - 1;
		this.arrowX = 9 -arrow.get(0) - 1;
		this.arrowY = arrow.get(1) - 1;
		} else {
			this.QX=queenCur.get(0);
			this.QY=queenCur.get(1);
			this.QmoveX =queenNew.get(0);
			this.QmoveY = queenNew.get(1);
			this.arrowX = arrow.get(0);
			this.arrowY = arrow.get(1);
			
		}
		
		
	}
	
	
	public ArrayList<Integer> getQueenPos(){
		ArrayList<Integer> ar = new ArrayList(2);
		ar.add(QX);
		ar.add(QY);
		return ar;
	}
	
	public ArrayList<Integer> getQueenMove(){
		ArrayList<Integer> ar = new ArrayList(2);
		ar.add(QmoveX);
		ar.add(QmoveY);
		return ar;
	}
	
	public ArrayList<Integer> getArrowPos(){
		ArrayList<Integer> ar = new ArrayList(2);
		ar.add(arrowX);
		ar.add(arrowY);
		return ar;
	}
	
	public Move sendFormat(){
		
		ArrayList<Integer> ar = new ArrayList(2);
		ar.add(10 - (QX + 1));
		ar.add(QY+ 1);
		ArrayList<Integer> br = new ArrayList(2);
		br.add(10 - (QmoveX+ 1));
		br.add(QmoveY+ 1);
		ArrayList<Integer> cr = new ArrayList(2);
		cr.add(10 - (arrowX+ 1));
		cr.add(arrowY+ 1);
		Move move = new Move(ar,br,cr, false);
		return move;
	}
	
	


}
