package controller;

import java.io.File;
import java.util.Scanner;
import model.SongList;
import utility.ShufflePlayList;

public class MusicPlayerController extends Thread{

	private int currentSongIndex;
	private SongList songsList;

	public MusicPlayerController(SongList songsList,  int currentSongIndex) {
		this.songsList = songsList;
		this.currentSongIndex = currentSongIndex;
	}

	public void run() {
		songsList.playCurrentSong(currentSongIndex);
	}
	
	public void stopPlayer() {
		this.songsList.getPlayer().close();
		this.interrupt();
	}

	public void playPrevious() {
		this.start();
	}
	public void playNext() {
		this.start();
	}
	public static void playerController(Scanner sc, SongList songsList) {
		String lineChar = null;
		int index = 0;
		MusicPlayerController mp3Player = new MusicPlayerController(songsList, index);
		mp3Player.start();
		System.out.println("Music Started");
		System.out.println("Enter the choice p for Previous and n for Next :");
		System.out.println("Enter the choice :");
		while (sc.hasNextLine()) {
			System.out.println("Enter the choice :");
			lineChar = sc.nextLine();
			if (lineChar.length() != 0) {
				switch (lineChar.toUpperCase()) {
				case "P" :
					mp3Player.stopPlayer();
					index = (index - 1) > 0 ? index - 1 : 0;
					mp3Player = new MusicPlayerController(songsList, index);
					mp3Player.playPrevious();
					break;
				case "N" :
					mp3Player.stopPlayer();
					index = (index + 1) > songsList.getPlaylist().size() - 1 ? songsList.getPlaylist().size() - 1 : index + 1;
					mp3Player = new MusicPlayerController(songsList, index);
					mp3Player.playNext();
					break;
				default:
					System.out.println("Enter only P/N as values");
					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		SongList songsList = new SongList();
		String folderPath = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter the folder path containing the songs :");
		folderPath = sc.nextLine();
		songsList.initializePlayList(new File(folderPath));
		if (songsList.getPlaylist() != null && songsList.getPlaylist().size() > 0) {
			ShufflePlayList.shufflePlaylist(songsList.getPlaylist(), songsList.getPlaylist().size());
			playerController(sc, songsList);
		} else {
			System.out.println("Playlist empty");
		}
		sc.close();
	}
}
