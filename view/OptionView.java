package view;

import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class OptionView extends JPanel {
	private JottoModel model;
	private JRadioButton rbtn1 = new JRadioButton("Easy");
	private JRadioButton rbtn2 = new JRadioButton("Normal");
	private JRadioButton rbtn3 = new JRadioButton("Hard");
	private JRadioButton rbtn4 = new JRadioButton("Random");
	private ButtonGroup bGroup = new ButtonGroup();
	private Border rbtnBorder = BorderFactory.createEtchedBorder();
	private JPanel rbtnPanel = new JPanel();
	private JPanel enterPanel = new JPanel();
	private JButton enter = new JButton("OK");
	
	public OptionView(JottoModel aModel) {
		super();
		this.model = aModel;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				rbtn1.setEnabled(true);
				rbtn2.setEnabled(true);
				rbtn3.setEnabled(true);
				rbtn4.setEnabled(true);
				rbtn2.setSelected(true); // set default radio button
				enter.setEnabled(true);
			}
		});
	}

	private void layoutView() {
		bGroup.add(rbtn1);
		bGroup.add(rbtn2);
		bGroup.add(rbtn3);
		bGroup.add(rbtn4);
		rbtnBorder = BorderFactory.createTitledBorder(rbtnBorder, "Select Difficulty");
		this.setLayout(new BorderLayout());
		this.rbtnPanel.setLayout(new BoxLayout(this.rbtnPanel, BoxLayout.PAGE_AXIS));
		this.rbtnPanel.setBorder(rbtnBorder);
		this.rbtnPanel.add(rbtn1);
		this.rbtnPanel.add(rbtn2);
		this.rbtnPanel.add(rbtn3);
		this.rbtnPanel.add(rbtn4);
		
		this.enterPanel.setLayout(new FlowLayout());
		this.enterPanel.add(enter);
		
		this.add(rbtnPanel,BorderLayout.CENTER);
		this.add(enterPanel,BorderLayout.SOUTH);
		
		rbtn1.setEnabled(false);
		rbtn2.setEnabled(false);
		rbtn3.setEnabled(false);
		rbtn4.setEnabled(false);
		enter.setEnabled(false);
	}

	private void registerControllers() {
		this.rbtn1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					model.setDifficulty(0);
				}
			}
		});
		this.rbtn2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					model.setDifficulty(1);
				}
			}
		});
		this.rbtn3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					model.setDifficulty(2);
				}
			}
		});
		this.rbtn4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					model.setDifficulty(3);
				}
			}
		});
		
		this.enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				main.Main.makeOptionNotVisible();
			}
		});
	}

}
