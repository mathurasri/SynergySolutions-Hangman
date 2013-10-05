package Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import Model.*;
import java.util.regex.Pattern;


public class Game 
{
	private ArrayList<String> missedLetters;
	private ArrayList<String> progress;
	private int totalMisses;
	private HashMap<String, List<String>> status;
	public FirstPlayer p1;
	public SecondPlayer p2;

	public Game() 
	{
		// TODO Auto-generated constructor stub
		this.missedLetters = new ArrayList<String>();
		this.totalMisses = 0;
		status = new HashMap<String, List<String>>();
		p1 = new FirstPlayer();
		p2 = new SecondPlayer();
	}
	//Standard getter and setters for secretWord
			public String getWord() {
				return p1.getSecretword();
			}

			public void setWord(String word) {
				p1.setSecretword(word);
				progress = new ArrayList<String>();
				for(int x=0;x<p1.getSecretword().length();x++){
					progress.add("_");
				}
				if(word.contains(" "))
					progressGame(" ");
			}

			public boolean guessLetter(String a)
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
						totalMisses += 1;						
					}
					if(progress.contains(a))
						return true;
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
				if (totalMisses >= 6)
					return "Lose";
				else if (!progress.contains("_"))
					return "Win";
				else
					return null;
			}

			/**
			 * @return the missedLetters
			 */
			public ArrayList<String> getMissedLetters() {
				return missedLetters;
			}

			//returns total misses
			public int getTotalMisses(){
				return totalMisses;
			}

}
