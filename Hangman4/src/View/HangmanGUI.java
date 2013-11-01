package View;
import Controller.*;

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
		String secretWord;
		public String getSecretWord() {
			return secretWord;
		}
		public void setSecretWord(String secretWord) {
			this.secretWord = secretWord;
		}
		public hangmanSecondFrame()
		{
			
		}
		public hangmanSecondFrame(JFrame currentFrame)
		{				
			this.currentFrame = currentFrame;
		}
		public void actionPerformed(ActionEvent e)
		{
			//Closes the previous frame
			this.currentFrame.dispatchEvent(new WindowEvent(this.currentFrame, WindowEvent.WINDOW_CLOSING));
			//Creation of current Frame
			final JFrame frame2 =  new JFrame("Press Enter after typing text");
			frame2.setVisible(true);
			frame2.setSize(new Dimension(300, 200));
			JPanel panel2 = new JPanel();
			JLabel label2 = new JLabel("Enter Secret Word:");						
			frame2.add(panel2);
			panel2.add(label2);
			
			//Secret words should be maximum 15 characters long
			final TextField secretWordEnter = new TextField(15);
			secretWordEnter.setEchoChar('*');		
			panel2.add(secretWordEnter);
			
			//Validating the secret word with respect to user requirements
			//At the end navigates to the hangman Third frame
			secretWordEnter.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String input = secretWordEnter.getText();
					hangmanSecondFrame hSF = new hangmanSecondFrame();
					hSF.setSecretWord(input);
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
						hangmanThirdFrame hTF = new hangmanThirdFrame(frame2, input);																								
					}
				}
			});
			
		}		
	}
	class hangmanThirdFrame
	{		
		String guessInput = "";
		String tempSecretWord = "";
		List<String> temp = null;
		JLabel blank[] = new JLabel[20];
		public hangmanThirdFrame(JFrame frame2, String input)
		{
			tempSecretWord = input;
			frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
			final JFrame frame3 = new JFrame();
			frame3.setVisible(true);
			frame3.setSize(new Dimension(300,200));						
			final JPanel panel3 = new JPanel();
			frame3.add(panel3);	
			
			panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
									
			JLabel noOfTries = new JLabel("Tries Remaining:");
			panel3.add(noOfTries);
			final JTextField triesRemaining = new JTextField(4);
			triesRemaining.setEditable(false);
			triesRemaining.setText("12");
			panel3.add(triesRemaining);
			//layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(noOfTries));
			
			JLabel guess = new JLabel("Guess Letter:");						
			panel3.add(guess);
			final JTextField guessLetter = new JTextField(1);
			panel3.add(guessLetter);
															
			final Game hangmanGame = new Game();
			for(int space = 0; space < 15; space++)
			{
				JLabel space1 = new JLabel(" ");
				panel3.add(space1);
			}
			for(int insBlank = 0; insBlank < input.length(); insBlank++)
			{
				JLabel temp = new JLabel("_");
				blank[insBlank] = temp;
				panel3.add(blank[insBlank]);
			}
			hangmanGame.setWord(input);
			guessLetter.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					guessInput = guessLetter.getText();
					
					boolean tet = hangmanGame.guessLetter(guessInput); 														
					if(tet) 
					{
						JOptionPane.showMessageDialog(null, "Correct Guess");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Wrong guess");
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
						else
						{
							JOptionPane.showMessageDialog(null, "Close this dialog box and Enter only one character in textfield");
						}
					}
					if(hangmanGame.isGameOver()!=null && temp.size() == tempSecretWord.length() )
					{
						JOptionPane.showMessageDialog(null, "You Win");
						frame3.dispatchEvent(new WindowEvent(frame3, WindowEvent.WINDOW_CLOSING));
					}
					else if(hangmanGame.isGameOver()==null && temp.size() != tempSecretWord.length())					
					{
						JOptionPane.showMessageDialog(null, "You Lose");
						frame3.dispatchEvent(new WindowEvent(frame3, WindowEvent.WINDOW_CLOSING));
					}
					
				}
			});
						
		}
	}
}
