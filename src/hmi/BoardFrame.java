package hmi;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Main;
import rules.GameRule;
import sup.Supervisor;
import data.Card;
import data.Player;

public class BoardFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CardPanel cardPanel;
	private JTextArea currentRule;
	private JButton drawCardButton;
	private JTextField currentPlayerTextField;
	private PlayedCardsFrame playedCardsFrame;
	
	private Game game;
	private GameRule gameRule;
	
	public BoardFrame(GameRule gameRule){
		this.game = new Game();
		this.gameRule = gameRule;
		this.cardPanel = new CardPanel(true);
		this.currentRule = new JTextArea();
		this.drawCardButton = new JButton();
		this.currentPlayerTextField = new JTextField();
		
		this.setSize(380,172);		
	
		this.drawCardButton.setText("Piocher une carte");
		this.drawCardButton.addActionListener(this);
		this.drawCardButton.setPreferredSize(new Dimension(140, 100));
		
		this.currentRule.setPreferredSize(new Dimension(150, 90));
		this.currentRule.setLineWrap(true);
		this.currentRule.setWrapStyleWord(true);
		JScrollPane currentRulePane = new JScrollPane (this.currentRule);
		currentRulePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.cardPanel.setPreferredSize(new Dimension(73, 100));
		this.cardPanel.setAlignmentX(50);
	
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.drawCardButton, BorderLayout.WEST);
		mainPanel.add(currentRulePane, BorderLayout.EAST);
		mainPanel.add(this.cardPanel, BorderLayout.CENTER);
		
		if(Supervisor.players.hasPlayers()){
			mainPanel.add(this.currentPlayerTextField, BorderLayout.SOUTH);
			this.setSize(380,175);
			this.playedCardsFrame = new PlayedCardsFrame(getSize().width);
		} else {
			this.setSize(380,155);
		}
		
		Supervisor.setDefaultLocation(this);
		
		this.setContentPane(mainPanel);
		
		MenuItem goToMainItem = new MenuItem("Go back to the main screen");
		goToMainItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.mainFrame.setVisible(true);
				if (BoardFrame.this.playedCardsFrame != null)
					BoardFrame.this.playedCardsFrame.dispose();
				BoardFrame.this.dispose();
			}
		});
		
		MenuItem aboutItem = new MenuItem("?");
		aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(BoardFrame.this, "Written by Jeremy Simar", "", JOptionPane.NO_OPTION);
			}
		});
		
		Menu menu = new Menu("Menu");
		menu.setName("Menu");
		menu.add(goToMainItem);
		menu.add(aboutItem);
		
		MenuBar menuBar = new MenuBar();
		menuBar.setName("menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Supervisor.gameName);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!game.isFinished()){
			Card drawnCard = game.drawCard();
			this.drawCardButton.setText(drawnCard.toString());
			
			String rule = this.gameRule.getRule(drawnCard);
			this.currentRule.setText(rule);
			this.currentRule.setFont(new Font("Arial",2,20));
			
			Image cardImage = Supervisor.getCardImage(drawnCard);
			this.cardPanel.drawImage(cardImage);
			
			if(Supervisor.players.hasPlayers()){
				Player currentPlayer = Supervisor.players.getCurrentPlayer();
				currentPlayer.addCard(drawnCard);
				playedCardsFrame.drawImages(Supervisor.getCardsImages(currentPlayer.getCards()));
				Player nextPlayer = Supervisor.players.nextPlayer();
				currentPlayerTextField.setText(currentPlayer.getName() + " turn. Next turn: " + nextPlayer.getName() + ".");
			}
		} else {
			if(Supervisor.players.hasPlayers())
				currentPlayerTextField.setText("");
			int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Game over, play again?", "Game over", JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.OK_OPTION){
				Supervisor.resetGame();
				this.game.resetGame();
				this.cardPanel.drawImage(null);
				this.drawCardButton.setText("Piocher une carte");
			} else {
				System.exit(0);
			}
		}
	}
	
	

}

