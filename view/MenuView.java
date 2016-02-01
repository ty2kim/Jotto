package view;

import model.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

public class MenuView extends JPanel {
	private JottoModel model;
	private JPanel titlePanel = new JPanel();
	private JLabel titleLabel = new JLabel("JOTTO");
	private JPanel buttonPanel = new JPanel();
	private JPanel beginPanel = new JPanel();
	private JPanel optionPanel = new JPanel();
	private JPanel endPanel = new JPanel();
	private JButton begin = new JButton("START");
	private JButton option = new JButton("OPTION");
	private JButton end = new JButton("END");
	
	public MenuView(JottoModel aModel) {
		super();
		this.model = aModel;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				begin.setEnabled(true);
				option.setEnabled(true);
				end.setEnabled(true);
			}
		});
	}
	
	private void layoutView() {
		this.setLayout(new BorderLayout());
		
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
		titleLabel.setForeground(Color.BLUE);
		
		this.titlePanel.setLayout(new FlowLayout());		
		this.titlePanel.add(titleLabel);
		
		this.buttonPanel.setLayout(new GridLayout(3,1,0,0));
		this.beginPanel.setLayout(new FlowLayout());
		this.optionPanel.setLayout(new FlowLayout());
		this.endPanel.setLayout(new FlowLayout());
		
		this.beginPanel.add(begin);
		this.optionPanel.add(option);
		this.endPanel.add(end);
		
		this.buttonPanel.add(beginPanel);
		this.buttonPanel.add(optionPanel);
		this.buttonPanel.add(endPanel);
		
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(buttonPanel,BorderLayout.CENTER);
		
		begin.setEnabled(false);
		option.setEnabled(false);
		end.setEnabled(false);
	}
	
	private void registerControllers() {
		this.begin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				main.Main.disposeMenu();
				main.Main.disposeOption();
				main.Main.makeGameVisible();
				model.setTarget();
			}
		});
		
		this.option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				main.Main.makeOptionVisible();
			}
		});
		
		this.end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
	}
}
