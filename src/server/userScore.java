package server;

import java.util.HashMap;
import java.util.Iterator;

import main.gameServer;

public class userScore {
	HashMap<String, gameUser> user = gameServer.getUser();
	String score = "score";
	
	public userScore() {
		
		Iterator<String> keys = user.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			String keyScore = String.valueOf(user.get(key).getScore());
			score = score + "-" + key + "-" + keyScore;
			user.get(key).resetScore();
		}
		
		new broadcast(score+"\n");
		
	}
}
