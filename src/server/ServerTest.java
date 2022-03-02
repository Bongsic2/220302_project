package server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerTest {

	static ArrayList<ServerIndexSongName> severIndexSongNameArr;
	public static void compareClientServer() throws IOException, ParseException {
		JSONArray musicInfoArray = null;
		try {
			JSONParser jsonParser=new JSONParser();
			FileReader reader=new FileReader("../LineNo5/src/model/song_json_data.json");
			JSONObject jsonObject=(JSONObject) jsonParser.parse(reader);
			musicInfoArray = (JSONArray) jsonObject.get("songs");
			severIndexSongNameArr = new ArrayList<ServerIndexSongName>();
			
			for (int i = 0; i < musicInfoArray.size(); i++) {
				JSONObject musicObject = (JSONObject) musicInfoArray.get(i);

				String song = musicObject.get("song").toString();
				
				severIndexSongNameArr.add(new ServerIndexSongName(i, song));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
