package server;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Info.MusicInfo;
import client.gameClient;

public class Test {
	static String bufferMusic="bufferMusic"+" ";
	static JSONObject jsonObject;
//	public static void main(String[] args) {
//		try {
//			gameClient.getBw().write(bufferMusic+"\n");
//			gameClient.getBw().flush();
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	public static  void sendMusicToServer() throws IOException{
		
		JSONArray musicInfoArray = null;
		ArrayList<MusicInfo> musicInfo = null;
		String str=null;
		
		try {
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader("../LineNo5/src/model/song_json_data.json");
			jsonObject = (JSONObject) jsonParser.parse(reader);
			musicInfoArray = (JSONArray) jsonObject.get("songs");
			musicInfo = new ArrayList<>();

			for (int i = 0; i < musicInfoArray.size(); i++) {
				JSONObject musicObject = (JSONObject) musicInfoArray.get(i);

				String song = musicObject.get("song").toString();
			
				str=i +" "+song+" ";
				
				bufferMusic=bufferMusic.concat(str);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		try {
			gameClient.getBw().write(bufferMusic+"\n");
			gameClient.getBw().flush();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
