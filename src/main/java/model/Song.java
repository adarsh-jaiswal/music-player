package model;

import java.io.File;

public class Song {
	
	private File song;
	
	public Song(String filePath) {
		this.song = new File(filePath);
	}

	public File getSong() {
		return song;
	}

	public void setSong(File song) {
		this.song = song;
	}

}
