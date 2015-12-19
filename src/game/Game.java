package game;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import data.Card;
import data.Types;
import data.Colors;

public class Game {
	public static Set<Card> playedCards;
	
	public Game(){
		Game.playedCards = new HashSet<Card>();
	}
	
	public Card drawCard(){
		Random rand = new Random();
		Card card = new Card();
		int nextInt;
		do {
			nextInt = rand.nextInt(Types.values().length);
			card.type = Types.values()[nextInt];
				
			nextInt = rand.nextInt(Colors.values().length);
			card.color = Colors.values()[nextInt];
		} while (isInSet(card));
		playedCards.add(card);
		return card;
	}
	
	public void resetGame(){
		Game.playedCards.clear();
	}
	
	private boolean isInSet(Card card){
		Card currentCard;
		Iterator<Card> iterator = playedCards.iterator();
		while(iterator.hasNext()){
			currentCard = iterator.next();
			if (currentCard.equals(card)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isFinished(){
		return Game.playedCards.size() == Types.values().length * Colors.values().length;
	}
}
