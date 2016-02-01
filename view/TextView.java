package view;

import model.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class TextView extends JPanel {
	private JottoModel model;
	private JTextField guessTF = new JTextField(5);
	private JLabel guessLB = new JLabel("Guess:");
	private JPanel textPanel = new JPanel();
	private JLabel livesLB = new JLabel("Life: ");
	private JLabel livesCountLB = new JLabel();

	private static final NumberFormat formatter = NumberFormat
			.getNumberInstance();

	public TextView(JottoModel jModel) {
		super();
		this.model = jModel;
		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				guessTF.setText("");
				livesCountLB.setText(formatter.format(model.getLifeCount()));
			}
		});
	}

	private void layoutView() {
		this.setLayout(new BorderLayout());
		Box group = Box.createHorizontalBox();
		group.add(livesLB);
		group.add(livesCountLB);
		this.textPanel.setLayout(new FlowLayout());
		this.textPanel.add(this.guessLB);
		this.textPanel.add(this.guessTF);
		this.textPanel.add(group);
		this.add(textPanel,BorderLayout.CENTER);
	}

	private void registerControllers() {
		this.guessTF.addActionListener(new GuessController());
		this.guessTF.addFocusListener(new GuessFocusController());
	}

	private class GuessController implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String guess = guessTF.getText();
			if(guess.length() != model.NUM_LETTERS){
				JOptionPane.showMessageDialog(null,"Your guess should be length of 5, not "+guess.length());
				guessTF.setText("");
			}
			else if(!Pattern.matches("[a-zA-z]+", guess)){
				JOptionPane.showMessageDialog(null,"Only alphabets are allowed, you typed "+guess);
				guessTF.setText("");
			}
			else if(model.isAlreadyGuessed(guess.toUpperCase())){
				JOptionPane.showMessageDialog(null,"Already guessed the word "+guess);
				guessTF.setText("");
			}
			else{
				guess = guess.toUpperCase();
				model.setGuess(guess);
				if(model.getExact() == 5){
					JOptionPane.showMessageDialog(null,"You Won!, the target word was "+guess+"\nPress Return to Menu to play again");
					guessTF.setEnabled(false);
				}
				else if(model.getGuessCount() == 10){
					JOptionPane.showMessageDialog(null,"You Lost!, the target word was "+model.getWord()+"\nPress Return to Menu to play again");
					guessTF.setEnabled(false);
				}
			}
		}
	}
	
	private class GuessFocusController implements FocusListener {
		public void focusGained(FocusEvent evt) {
		}

		public void focusLost(FocusEvent evt) {
		}
	}
}
