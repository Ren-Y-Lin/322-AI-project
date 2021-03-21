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
	
	


}
