package server;

public class ServerIndexSongName {

	private int songIndex;
	private String songName;
	
	public ServerIndexSongName(int songIndex,String songName) {
		this.songIndex=songIndex;
		this.songName=songName;
	}
	public int getSongIndex() {
		return songIndex;
	}
	public void setSongIndex(int songIndex) {
		this.songIndex = songIndex;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
}
