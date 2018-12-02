package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

public class SongList {

	private List<Song> playlist;
	private Player player;

	public SongList() {
		this.playlist = new ArrayList<>();
	}

	public List<Song> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Song> playlist) {
		this.playlist = playlist;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/*
	 * Reads all mp3 files from a folder
	 */
	public void initializePlayList(File folder)  {
		String fileName = null;
		if (folder != null && folder.listFiles() != null) {
			for (File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {
					if (fileEntry.isFile()) {
						fileName = folder.getAbsolutePath()+ "\\" + fileEntry.getName();
						if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).toLowerCase()).equals("mp3")) {
							playlist.add(new Song(fileName));
						} else {
							System.out.println("Only mp3 files allowed");
						}
					}
				} else {
					System.out.println("Please Enter the valid directory path");
					break;
				}
			}
		} else {
			System.out.println("Please check the directory");
		}
	}
	/*
	 * Plays song at current index
	 */
	public synchronized void playCurrentSong (int currentSongIndex) {
		int index = currentSongIndex;
		BufferedInputStream buffer = null;
		while (index <= 100 && index <= this.getPlaylist().size() - 1) {
			if (Thread.interrupted())  {
				break;
			}
			Song song = this.getPlaylist().get(index);
				try {
					buffer = new BufferedInputStream(new FileInputStream(song.getSong()));
					player = new Player(buffer);
					player.play();
				} catch (Exception e) {
					System.out.println("Error in Playing Track");
				}
			index++;
		}
	}
}