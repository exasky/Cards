package data;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private List<Card> playedCards;
	
	public Player(String name){
		this.name = name;
		this.playedCards = new ArrayList<>();
	}
	
	public String getName(){
		return name;
	}
	
	public void addCard(Card card){
		this.playedCards.add(card);
	}
	
	public List<Card> getCards(){
		return this.playedCards;
	}
}
