package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import data.Player;

public class Players {
	
	private Map<Integer,Player> players;
	private Integer currentNumber;
	private Integer nbPlayers;
	
	public Players(){
		this.players = new HashMap<Integer, Player>();
		this.nbPlayers = 0;
		this.currentNumber = 0;
	}
	
	public void addPlayer(Player player){
		this.players.put(this.nbPlayers, player);
		this.nbPlayers++;
	}
	
	public void clearPlayers(){
		this.players.clear();
		this.nbPlayers = 0;
		this.currentNumber = 0;
	}
	
	public void resetPlayers(){
		Set<Integer> keySet = this.players.keySet();
		for (Integer playerNumber : keySet){
			this.players.get(playerNumber).getCards().clear();
		}
	}
	
	public Player getPlayer(Integer playerNumber){
		return this.players.get(playerNumber);
	}
	
	public Player getCurrentPlayer(){
		return getPlayer(this.currentNumber);
	}
	
	public Player nextPlayer(){
		this.currentNumber++;
		this.currentNumber = this.currentNumber % this.nbPlayers;
		Player player = getCurrentPlayer();
		return player;
	}

	public boolean hasPlayers(){
		return this.nbPlayers != 0;
	}
}
