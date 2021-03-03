package ubc.cosc322;

import java.util.Scanner;

public class MainController {

	// This class will work similar to a command line

	// 1. request inputs, inputs types: train X iteration, save model, load model,
	// play vs user, play on server
	// 2

	public static void main(String[] args) {
		MarkovTree mt = new MarkovTree();
		boolean loaded = false;
		
		while (true) {
			System.out.println("1 = train, 2 = save, 3 = load, 4 = play vs human, 5 = play vs pc");
			System.out.println("Input command:");
			Scanner sc = new Scanner(System.in);
			
			int command = sc.nextInt();
			
			switch(command) {
			
			case 1:
				break;
				
			case 2: 
				System.out.println("input filename to save under");
				String output = sc.nextLine();
				
				mt.saveTree(output);
				break;
			
			case 3: 
				System.out.println("input filename to load");
				String input = sc.nextLine();
				
				mt.saveTree(input);
				break;
			
			case 4: 
				break;
			
			case 5: 
				break;
			
			default: System.out.println("Choice unavailable");
			continue;
			
			}
			
			
			

		}
	}

}
