package View;
import Controller.*;
import Model.FirstPlayer;
import Model.SecondPlayer;

import javax.*;

import java.util.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
public class HangmanGUI {	
	public HangmanGUI() {
		// Ask the player to play hangman		
		hangmanFirstFrame();
	}
	//Agree to Play hangman and you will be navigated to the frame asking Secret word
	public void hangmanFirstFrame()
	{
		JFrame frame1 = new JFrame("Welcome to Hangman !");
		frame1.setVisible(true);
		frame1.setSize(new Dimension(300, 200));
		JPanel panel1 =new JPanel();
		frame1.add(panel1);
		JButton playButton = new JButton("Play Hangman");
		panel1.add(playButton);
		playButton.addActionListener(new hangmanSecondFrame(frame1));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HangmanGUI();
	}
	class hangmanSecondFrame implements ActionListener
	{
		JFrame currentFrame;						
		hangmanThirdFrame hTF;
		public JFrame getCurrentFrame() {
			return currentFrame;
		}
		public void setCurrentFrame(JFrame currentFrame) {
			this.currentFrame = currentFrame;
		}
		
		FirstPlayer firstPlayer = new FirstPlayer();
		
		
		final SecondPlayer secondPlayer = new SecondPlayer();		
		public hangmanSecondFrame()
		{					
		}
		public hangmanSecondFrame(JFrame currentFrame)
		{
			this.setCurrentFrame(currentFrame);
			hTF = new hangmanThirdFrame();
		}
		public void actionPerformed(ActionEvent e)
		{
			//Closes the previous frame
			this.getCurrentFrame().dispatchEvent(new WindowEvent(this.getCurrentFrame(), WindowEvent.WINDOW_CLOSING));
			final JFrame frame2 = new JFrame("Press Enter after typing text");			
			frame2.setVisible(true);
			frame2.setSize(new Dimension(300, 200));
			JPanel panel2 = new JPanel();
			JLabel label2Player1 = new JLabel("Player1's Name:");
			frame2.add(panel2);
			panel2.add(label2Player1);
			
			final TextField player1Name = new TextField(15);
			panel2.add(player1Name);
			JLabel label2Player2 = new JLabel("Player2's Name:");
			panel2.add(label2Player2);
			final TextField player2Name = new TextField(15);
			panel2.add(player2Name);
			player1Name.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String input = player1Name.getText();					
					//checks whether the name entered contains only alphabet
					//If Yes, store the name in FirstPlayer.java
					if(input.matches("[a-zA-z]+"))
					{
						JOptionPane.showMessageDialog(null, "Valid Name");
						firstPlayer.setName(input);
						hTF.setFirstPlayerCurrent(firstPlayer);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Enter Name with only alphabets");
					}
				}
			});			
			player2Name.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String input = player2Name.getText();															
					//checks whether the name entered contains only alphabet
					//If Yes, store the name in SecondPlayer.java
					if(input.matches("[a-zA-Z]+"))
					{
						JOptionPane.showMessageDialog(null, "Valid Name");
						secondPlayer.setName(input);						
						hTF.setCurrentFrame(frame2);
						hTF.setSecondPlayerCurrent(secondPlayer);
						hTF.buildThirdFrame(hTF);						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Enter Name with only alphabets");						
					}
				}
			});
		}
	}
	class hangmanThirdFrame implements ActionListener
	{
		JFrame currentFrame;
		public JFrame getCurrentFrame() {
			return currentFrame;
		}
		public void setCurrentFrame(JFrame currentFrame) {
			this.currentFrame = currentFrame;
		}

		FirstPlayer firstPlayerCurrent;
		public FirstPlayer getFirstPlayerCurrent() {
			return firstPlayerCurrent;
		}
		public void setFirstPlayerCurrent(FirstPlayer firstPlayerCurrent) {
			this.firstPlayerCurrent = firstPlayerCurrent;
		}
				
		SecondPlayer secondPlayerCurrent;
		public SecondPlayer getSecondPlayerCurrent() {
			return secondPlayerCurrent;
		}
		public void setSecondPlayerCurrent(SecondPlayer secondPlayerCurrent) {
			this.secondPlayerCurrent = secondPlayerCurrent;
		}
		
		public hangmanThirdFrame()
		{
			
		}
		
		public hangmanThirdFrame(SecondPlayer secondFrameContext)
		{				
		}
		
		public void buildThirdFrame(hangmanThirdFrame hTF)
		{			
			hTF.getCurrentFrame().dispatchEvent(new WindowEvent(hTF.getCurrentFrame(), WindowEvent.WINDOW_CLOSING));
			
			final JFrame frame3 =  new JFrame(hTF.getFirstPlayerCurrent().getName() + ", enter secret word");
			frame3.setVisible(true);
			frame3.setSize(new Dimension(300, 200));
			JPanel panel3 = new JPanel();
			JLabel label3 = new JLabel("Enter Secret Word:");						
			frame3.add(panel3);
			panel3.add(label3);
						
			//Secret words should be maximum 15 characters long
			final TextField secretWordEnter = new TextField(15);
			secretWordEnter.setEchoChar('*');		
			panel3.add(secretWordEnter);
			
			final JLabel instruction = new JLabel("(Press Enter after typing text)");
			panel3.add(instruction);
			//Validating the secret word with respect to user requirements
			//At the end navigates to the hangman Third frame
			secretWordEnter.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub					
					String input = secretWordEnter.getText();
					FirstPlayer thirdFrameContext = new FirstPlayer();					
					thirdFrameContext.setSecretword(input);
					if(input.length() < 3)
					{
						JOptionPane.showMessageDialog(null, "Word must be atleast 3 characters long");
					}
					else if(input.length() > 15)
					{
						JOptionPane.showMessageDialog(null, "Word must not be greater than 15 characters long");
					}
					else
					{
						hangmanFourthFrame hFF = new hangmanFourthFrame();
						hFF.setFirstPlayerCurrent(thirdFrameContext);
						hFF.setCurrentFrame(frame3);
						hFF.setTempSecretWord(input);
						hFF.setSecondPlayerCurrent(getSecondPlayerCurrent());
						hFF.buildFourthFrame(hFF);
					}
				}
			});
	
		}
		public void actionPerformed(ActionEvent e)
		{
			//Closes the previous frame			
			
			//Creation of current Frame
					
		}		
	}
	class hangmanFourthFrame
	{		
		JFrame currentFrame;
		public JFrame getCurrentFrame() {
			return currentFrame;
		}
		public void setCurrentFrame(JFrame currentFrame) {
			this.currentFrame = currentFrame;
		}
		
		FirstPlayer firstPlayerCurrent;
		public FirstPlayer getFirstPlayerCurrent() {
			return firstPlayerCurrent;
		}
		public void setFirstPlayerCurrent(FirstPlayer firstPlayerCurrent) {
			this.firstPlayerCurrent = firstPlayerCurrent;
		}

		SecondPlayer secondPlayerCurrent;
		public SecondPlayer getSecondPlayerCurrent() {
			return secondPlayerCurrent;
		}
		public void setSecondPlayerCurrent(SecondPlayer secondPlayerCurrent) {
			this.secondPlayerCurrent = secondPlayerCurrent;
		}
		
		String guessInput = "";
		String tempSecretWord;
		public String getTempSecretWord() {
			return tempSecretWord;
		}
		public void setTempSecretWord(String tempSecretWord) {
			this.tempSecretWord = tempSecretWord;
		}

		List<String> temp = null;
		JLabel blank[] = new JLabel[20];
		public hangmanFourthFrame()
		{						
		}
		
		public void buildFourthFrame(final hangmanFourthFrame hFF)
		{
			hFF.setTempSecretWord(tempSecretWord);
			hFF.getCurrentFrame().dispatchEvent(new WindowEvent(hFF.getCurrentFrame(), WindowEvent.WINDOW_CLOSING));
			final JFrame frame4 = new JFrame();
			frame4.setVisible(true);
			frame4.setSize(new Dimension(300,200));						
			final JPanel panel4 = new JPanel();
			frame4.add(panel4);	
			
			panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
									
			JLabel noOfTries = new JLabel("Tries Remaining:");
			panel4.add(noOfTries);
			final JTextField triesRemaining = new JTextField(4);
			triesRemaining.setEditable(false);
			triesRemaining.setText("12");
			panel4.add(triesRemaining);
			//layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(noOfTries));
			
			JLabel guess = new JLabel("Guess Letter:");						
			panel4.add(guess);
			final JTextField guessLetter = new JTextField(1);
			panel4.add(guessLetter);
															
			final Game hangmanGame = new Game();
			for(int space = 0; space < 15; space++)
			{
				JLabel space1 = new JLabel(" ");
				panel4.add(space1);
			}
			
			String secretInput = hFF.getTempSecretWord();
			for(int insBlank = 0; insBlank < secretInput.length(); insBlank++)
			{
				JLabel temp = new JLabel("_");
				blank[insBlank] = temp;
				panel4.add(blank[insBlank]);
			}
			hangmanGame.setWord(secretInput);
			guessLetter.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					guessInput = guessLetter.getText();
					
					boolean tet = hangmanGame.guessLetter(guessInput); 														
					if(tet) 
					{
						if(guessInput.length() == 1)
						{
							JOptionPane.showMessageDialog(null, "Correct Guess");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Correct guess but Enter only one character in 'Guess Letter'");
						}
					}
					else
					{
						if(guessInput.length() == 1)
						{
							JOptionPane.showMessageDialog(null, "Wrong guess");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Wrong guess and Enter only one character in 'Guess Letter'");
						}
					}
					temp = hangmanGame.statusOfGame().get("Misses"); 
					int missesRemaining = 12 - hangmanGame.getTotalMisses();
					StringBuilder sb = new StringBuilder();
					sb.append("");
					sb.append(missesRemaining);
					String misses = sb.toString();
					triesRemaining.setText(misses);
					temp = hangmanGame.statusOfGame().get("Progress");					

					for (int x = 0; x < temp.size(); x++)  
					{
						String a = temp.get(x);
						if(guessInput.length() == 1)
						{
							blank[x].setText(a);
						}
						
					}
					if(hangmanGame.isGameOver()!=null && temp.size() == tempSecretWord.length() )
					{
						JOptionPane.showMessageDialog(null, hFF.getSecondPlayerCurrent().getName() + ", You Win");
						frame4.dispatchEvent(new WindowEvent(frame4, WindowEvent.WINDOW_CLOSING));
					}
					else if(hangmanGame.isGameOver()==null && temp.size() != tempSecretWord.length())					
					{
						JOptionPane.showMessageDialog(null, hFF.getSecondPlayerCurrent().getName() + ", You Lose");
						frame4.dispatchEvent(new WindowEvent(frame4, WindowEvent.WINDOW_CLOSING));
					}
					
				}
			});
		}
	}
}
