package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.gameServer;

public class userList {
	private HashMap<String, gameUser> userNamesAll;
	private ArrayList<String> bigu = new ArrayList<String>();
	//public static String userList = "userList-";
	public static String userList;

	public userList() {
		userNamesAll = gameServer.getUser();
		userList = "userList-";
		Iterator<String> keys = userNamesAll.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();

			if (!bigu.contains(key) || bigu == null) {
				bigu.add(key);				
				userList = userList + key + "-";

			}
		}
		//userNamesAll.clear();
		bigu.clear();
	}

}
