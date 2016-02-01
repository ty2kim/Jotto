package main;

import javax.swing.JFrame;
import model.JottoModel;
import view.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Main {
	private static JFrame menu;
	private static JFrame game;
	private static JFrame option;
	
	public static void main(String[] args) {
		setUI();
	}
	
	public static void setUI(){
		JottoModel model = new JottoModel();
		MenuView vMenu = new MenuView(model);
		OptionView vOption = new OptionView(model);
		ButtonView vButton = new ButtonView(model);
		TextView vText = new TextView(model);
		TableView vTable = new TableView(model);
		
		menu = new JFrame("Jotto-Menu");
		menu.getContentPane().setLayout(new BorderLayout());
		menu.setBounds(600,250,200,230);
		menu.getContentPane().add(vMenu, BorderLayout.CENTER);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
		
		option = new JFrame("Jotto-Option");
		option.getContentPane().setLayout(new BorderLayout());
		option.setBounds(815,250,200,200);
		option.getContentPane().add(vOption,BorderLayout.CENTER);
		option.setResizable(false);
		option.setVisible(false);
		
		game = new JFrame("Jotto-Game");
		game.getContentPane().setLayout(new FlowLayout());
		game.setBounds(600,250,440,310);
		game.getContentPane().add(vButton);
		game.getContentPane().add(vText);
		game.getContentPane().add(vTable);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(false);
	}
	
	public static void makeOptionVisible(){
		option.setVisible(true);
	}
	
	public static void makeGameVisible(){
		game.setVisible(true);
	}
	
	public static void disposeGame(){
		game.dispose();
	}
	
	public static void disposeMenu(){
		menu.dispose();
	}
	
	public static void disposeOption(){
		option.dispose();
	}
	
	public static void makeOptionNotVisible(){
		option.setVisible(false);
	}
}
