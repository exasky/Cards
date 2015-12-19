package hmi;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import sup.Supervisor;

public class CardPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public List<Image> playedCardsImage;
	private boolean isOneCardPanel;
	
	public CardPanel(boolean isOneCardPanel){
		this.isOneCardPanel = isOneCardPanel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*if (this.currentImage != null && this.currentImage instanceof BufferedImage)
			currentImage = ((BufferedImage)this.currentImage).getScaledInstance(200, 200, Image.SCALE_SMOOTH);*/
		
		if(this.playedCardsImage != null){
			Dimension dimensions = this.getSize();
			int playedCardsNumber = this.playedCardsImage.size();
			for(int i = 0; i < playedCardsNumber; i++){
				int ypos = ((i+1) * (dimensions.height - Supervisor.cardHeight)/(playedCardsNumber+1));
				Image currentImage = this.playedCardsImage.get(i);
				if (this.isOneCardPanel){
					currentImage = currentImage.getScaledInstance(dimensions.width, dimensions.height, 1);
					g.drawImage(currentImage, 0, 0, null);
				} else {
					g.drawImage(currentImage, 
							(dimensions.width - Supervisor.cardWidth)/2, 
							ypos,
							null);
				}
				
			}
		}
	}
	
	public void drawImages(List<Image> images){
		this.playedCardsImage = images;
		this.repaint();
	}
	
	public void drawImage(Image image){
		drawImages(Arrays.asList(image));
	}

}
