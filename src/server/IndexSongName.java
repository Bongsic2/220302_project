package server;

public class IndexSongName {

	private int songIndex;
	private String songName;
	
	public IndexSongName(int songIndex,String songName) {
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
