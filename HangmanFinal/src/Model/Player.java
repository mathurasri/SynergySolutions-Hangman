/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author mathu
 */
public class Player {
    
    public Integer numOfTries;
    public Integer numOfMissed;
    public Boolean outOfGame;
    public String playerName;
    private int triesRemaining;

	public Player() {
		// TODO Auto-generated constructor stub
	}
        public Player(int playerNumber) {
            this.setPlayerName(Integer.toString(playerNumber));           
		// TODO Auto-generated constructor stub
	}
        
    private String secretword;
	public String getSecretword() {
		return secretword;
	}
	public void setSecretword(String secretword) {
		this.secretword = secretword;
	}
        public int getNumOfTries() {
			return numOfTries - numOfMissed;
		}
		public void setNumOfTries(int tries) {
			this.numOfTries = tries;
		}
                        
        public String getPlayerName() {
			return playerName;
		}
		public void setPlayerName(String name) 
		{
			this.playerName = name;
		}
}

