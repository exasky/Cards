package hmi;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import sup.Supervisor;

public class PlayedCardsFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private CardPanel playedCardsPanel;
	
	public PlayedCardsFrame(int mainFrameWidth){
		this.playedCardsPanel = new CardPanel(false);
		
		setTitle("Played Cards");
		setContentPane(this.playedCardsPanel);
		setSize(Supervisor.cardWidth, Supervisor.cardHeight * 5);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation(screenSize.width/2 - mainFrameWidth/2 - getSize().width*2, screenSize.height/2 - getSize().height/2);
		
		setVisible(true);
	}
	
	public void drawImages(List<Image> images){
		playedCardsPanel.drawImages(images);
	}
}
