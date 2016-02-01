package view;

import model.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class ButtonView extends JPanel {
	private JottoModel model;
	private JButton reset = new JButton("Return to Menu");
	private JButton peek = new JButton("Peek Answer");
	private JPanel returnPanel = new JPanel();
	private JPanel peekPanel = new JPanel();
	private JPanel emptyPanel = new JPanel();
	
	public ButtonView(JottoModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		this.model.addView(new IView() {
			public void updateView() {
				reset.setEnabled(true);
			}
		});
	}
	
	private void layoutView() {
		this.setLayout(new GridLayout(1,2,0,0));
		
		this.returnPanel.setLayout(new BorderLayout());
		this.returnPanel.add(reset, BorderLayout.NORTH);
		
		this.peekPanel.setLayout(new BorderLayout());
		this.peekPanel.add(peek, BorderLayout.NORTH);
		
		this.add(returnPanel);
		this.add(emptyPanel);
		this.add(peekPanel);
		
		reset.setEnabled(false);
		peek.setEnabled(true);
	}
	
	private void registerControllers() {
		this.reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				main.Main.disposeGame();
				main.Main.setUI();
			}
		});
		this.peek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				peek.setText(model.getHint().toString());
				peek.setEnabled(false);
			}
		});
		
	}
}
