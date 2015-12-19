package rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sup.Supervisor;
import data.Card;
import data.Colors;
import data.Types;

public class GameRule {
	
	private Map<Card,String> gameRules;

	public void loadRulesFromProperty(String ruleFile) {
		this.gameRules = new HashMap<Card, String>();
		
		URL resource = this.getClass().getClassLoader().getResource("Rules/" + ruleFile);
		
		if (resource == null){
			try {
				resource= new URL(this.getClass().getProtectionDomain().getCodeSource().getLocation(),"Rules/" + ruleFile);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		BufferedReader bufferedReader = null;
		try {
			File file = new File(resource.toURI());
			FileInputStream fileInputStream = new FileInputStream(file);
			bufferedReader  = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = bufferedReader.readLine();
			if (line != null){
				Supervisor.gameName = line;
			}
			while((line =bufferedReader.readLine()) != null){
				String[] split = line.split("-");
				if (split != null && split.length == 3){
					Card card = new Card();
					card.type = Types.valueOf(split[0]);
					card.color = Colors.valueOf(split[1]);
					gameRules.put(card, split[2]);
				}
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String getRule(Card card){
		if (gameRules != null){
			Set<Card> keySet = gameRules.keySet();
			Iterator<Card> iterator = keySet.iterator();
			while(iterator.hasNext()){
				Card next = iterator.next();
				if (next.equals(card)){
					return this.gameRules.get(next);
				}
			}
		}
		return null;
	}
}
