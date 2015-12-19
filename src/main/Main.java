package main;

import hmi.SetPlayersFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sup.Supervisor;

public class Main {
	
	//TODO GENERATION AUTOMATIQUE DE REGLES (en f° de la carte d'avant ou des)
	
	public static JFrame mainFrame;
	
	public static JPanel mainPanel;

	public Main(){
		mainFrame= new JFrame();
		mainFrame.setSize(500,100);
		Supervisor.setDefaultLocation(mainFrame);
		mainPanel = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton addPlayersButton = new JButton();
		addPlayersButton.setText("Add players");
		addPlayersButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainFrame.setVisible(false);
				new SetPlayersFrame();
				
			}
		});
		mainPanel.add(addPlayersButton);
		
		JButton setRuleButton = new JButton();
		setRuleButton.setText("Choose the rule");
		setRuleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainFrame.setVisible(false);
				Supervisor.setRules();				
			}
		});
		mainPanel.add(setRuleButton); 
		
		JButton launchGameButton = new JButton();
		launchGameButton.setText("Launch the game");
		launchGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainFrame.setVisible(false);
				Supervisor.runGame();
				
			}
		});
		mainPanel.add(launchGameButton); 
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
