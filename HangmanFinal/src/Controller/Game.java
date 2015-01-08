/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author mathu
 */
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import Model.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.Iterator;


public class Game 
{
	private ArrayList<String> missedLetters;
	private ArrayList<String> progress;
	private int totalAttempts;
	private HashMap<String, List<String>> status;
        public Player[] players;
        //public ArrayList<Player[] players;
        Player p1, p2;
        private int numOfTotalAttempts;
        private int numOfPlayers;
        boolean shouldSwitch = true;
        int switched = 0;
        
	public Game() 
	{
		// TODO Auto-generated constructor stub
		this.missedLetters = new ArrayList<String>();
		this.totalAttempts = 0;
		status = new HashMap<String, List<String>>();
		p1 = new Player();
		p2 = new Player();
	}
        public Game(int noOfPlayers, int noOfTries) 
	{
		// TODO Auto-generated constructor stub
		this.missedLetters = new ArrayList<String>();
                this.numOfPlayers = noOfPlayers;
                p1 = new Player();
		this.totalAttempts = 0;
                this.numOfTotalAttempts = noOfTries;
                players = new Player[numOfPlayers];
		status = new HashMap<String, List<String>>();
                for (int i =0; i< players.length; i++) {
                    players[i] = (new Player(i+1));
                    players[i].setNumOfTries(noOfTries);
                    players[i].numOfMissed = 0;
                    players[i].outOfGame = false;
                }
	}
        
        public void deletePlayers() {
            for (int i =0; i< players.length; i++) {
                if(players[i].getNumOfTries() <= 0)
                {
                    players[i].outOfGame = true;
                }
            }
        }
                
        
        public int getTries(Player p) {
			return p.numOfTries;
		}
        
	//Standard getter and setters for secretWord
		public String getWord() {
			return p1.getSecretword();
		}

		public void setWord(String word) {
                    for(int i =0; i< players.length; i++) {
                    //players[i] = (new Player(i));
                    players[i].setSecretword(word);
                    }
                    
                    
                    p1.setSecretword(word);
                    
                    progress = new ArrayList<String>();
                    for(int x=0;x<word.length();x++){
                            progress.add("_");
                    }
                    if(word.contains(" "))
                            progressGame(" ");
		}

		public void setSwitch(Boolean b) {
                        shouldSwitch = b;
                    }
                
                
                public Player switchPlayers()
                {
                    deletePlayers();
                    
                    if(switched < numOfPlayers)
                    {                     
                        if(shouldSwitch)
                        {                         
                            shouldSwitch = false;
                                return players[switched ++];
                        }
                            else
                        {
                            shouldSwitch = true;
                                return players[switched];   
                        }
                    }
                    else
                    {
                        switched = 0;
                        switched = switched + 1;
                        shouldSwitch = false;
//                        for (int i =0; i< players.length; i++) {
//                            players[i].setNumOfTries(players[i].getNumOfTries()-players[i].numOfMissed);
//                        }
                        return players[0];
                    } 
                }
                
                
		public boolean guessLetter(String a, Player currentPlayer)
		{
			String tempSecretStore = p1.getSecretword();
			int forloopFlag = 0;		
			int asciiForGuess = (int)a.charAt(0);
			if(a.equals(" ") || Pattern.matches("[a-zA-Z]+",a))
			{
				for(int x = 0; x < p1.getSecretword().length(); x++)
				{
					if(((int)(tempSecretStore.toUpperCase().charAt(x)) == asciiForGuess || (int)(tempSecretStore.toLowerCase().charAt(x)) == asciiForGuess))
					{											
						progress.set(x, a);
						forloopFlag = 1;
					}		
					
				}				
				if(!(progress.contains(a)))
				{
					getMissedLetters().add(a);                                       					
                                        currentPlayer.numOfMissed += 1;                                                                               
                                        shouldSwitch = true;
                                        deletePlayers();
				}
				if(progress.contains(a))
                                {                                    
                                    shouldSwitch = false;
                                    return true;
                                }					
				else
					return false;
			}
			else
			{
				return false;
			}
		}

		private void progressGame(String a)
		{
			for(int x =0;x< p1.getSecretword().length();x++)
			{
				if(p1.getSecretword().charAt(x) == a.charAt(0))
					progress.set(x, a);
			}
		}

		/* 
		 * output: HashMap of missedLetters and current progress
		 */
		public HashMap<String, List<String>> statusOfGame(){
			status.clear();
			status.put("Misses", getMissedLetters());
			status.put("Progress", progress);
			return status;
		} 

		public String isGameOver(){
                    String result = null;
                    totalAttempts = 0;
                    
                    deletePlayers();
                    
                    for(int i =0; i< players.length; i++) 
                    {
                        totalAttempts += players[i].getNumOfTries();
                    }                    
			//if (totalAttempts > numOfTotalAttempts ) //players[i].getNumOfTries() * numOfPlayers
				//return result = "You Lose";
			if (!progress.contains("_"))
                            return result = "You Win";
                        if(totalAttempts <= 0)
                            return result = "You Lose";
                        else
                            return result;
		}

		/**
		 * @return the missedLetters
		 */
		public ArrayList<String> getMissedLetters() {
			return missedLetters;
		}

		//returns total misses
		public int getTotalMisses(){
			return totalAttempts;
		}

}
