package View;

import Controller.*; 
import java.util.Scanner; 
import java.util.List; 

public class HangmanText { 
	
	public static void main(String[] args) { 
		Game game1 = new Game(); 
		Scanner sc = new Scanner(System.in); 
		System.out.println("Welcome to Hangman!"+'\n'); 

		System.out.print("Player 1, enter secret word: " ); 
		game1.setWord(sc.nextLine());
		
		//Add some spaces to clear the secret word off the screen.
		for(int i = 0; i < 100; i++){
			System.out.println("");
		}
		
		System.out.println("GUESSING TIME!!"); 
		List<String> temp; 	
		
		do{ 
			System.out.print("Player 2, enter letter: "); 
			boolean tet = game1.guessLetter(sc.nextLine()); 
			System.out.println("");
			
			if(tet) 
				System.out.println("Correct Guess!"+'\n'); 
			else
				System.out.println("Wrong"+'\n'); 
			
			temp = game1.statusOfGame().get("Misses"); 
			System.out.println("Misses Remaining: "+ (6 - game1.getTotalMisses())); 
			System.out.print("Missed Letters: "); 
			
			for(int x=0;x<temp.size();x++) 
				System.out.print(temp.get(x)+", "); 
			
			System.out.println(""); 
			temp = game1.statusOfGame().get("Progress"); 

			for (int x = 0; x < temp.size(); x++)  
				System.out.print(temp.get(x)); 
			System.out.println(""); 
		
		}while(game1.isGameOver() == null);
	} 
} 
