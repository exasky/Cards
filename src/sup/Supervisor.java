package sup;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import data.Card;
import data.Colors;
import data.Types;
import game.Players;
import hmi.BoardFrame;
import hmi.SetPlayersFrame;
import hmi.SetRuleFrame;
import rules.GameRule;

public class Supervisor {
	
	public static Image allCardImage = null;
	public static Players players = new Players();
	public static String gameName = "";
	public static GameRule gameRule = new GameRule();
	
	public static int cardHeight = 97;
	public static int cardWidth = 72;
	
	public static void setRules(){
		new SetRuleFrame();
	}
	
	public static void setPlayers(){
		new SetPlayersFrame();
	}
	
	public static void runGame(){
		Supervisor.players.resetPlayers();
		new BoardFrame(Supervisor.gameRule);
	}
	
	public static void resetGame(){
		Supervisor.players.resetPlayers();
	}
	
	public static void setDefaultLocation(JFrame frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		frame.setLocation(screenSize.width/2 - size.width/2, screenSize.height/2 - size.height/2);
	}
	
	public static Image getCardImage(Card card){
		List<Image> cardsImages = getCardsImages(Arrays.asList(card));
		if (cardsImages.size() == 1){
			return cardsImages.get(0);
		} else {
			return null;
		}
	}
	
	public static List<Image> getCardsImages(List<Card> cards){
		if (allCardImage == null){
			try {
				allCardImage = ImageIO.read(Supervisor.class.getClassLoader().getResourceAsStream("cards.png"));
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
		List<Image> resultList = new ArrayList<>();
		for(Card currentCard: cards){
			int yPos = Colors.getPos(currentCard.color) * 98;
			int xPos = Types.getPos(currentCard.type) * 73;
			resultList.add(((BufferedImage)allCardImage).getSubimage(xPos, yPos, cardWidth, cardHeight));
		}
		return resultList;
	}

}
