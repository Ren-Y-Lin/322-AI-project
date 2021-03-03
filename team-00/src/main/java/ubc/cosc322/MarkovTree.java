package ubc.cosc322;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MarkovTree {
	BoardStateHead bsh = new BoardStateHead();
	
	
	public MarkovTree() {
		
		
		
		
		
	}
	
	//train tree
	public void train(int iterations) {
		for (int i = 0; i< iterations; i++) {
			
			
			
		}
	}
	
	
	
	//Save trained tree to file
	public void saveTree(String fileName) {
		
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(fileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bsh);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in "+fileName);
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		
		
	}
	
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
	
	


}
