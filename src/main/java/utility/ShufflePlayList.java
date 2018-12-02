package utility;

import java.util.List;
import java.util.Random;

public class ShufflePlayList {
	/*
	 * Utility Function to a given list 
    */
	public static <T> void shufflePlaylist(List<T> listToShuffle, int size) 
    { 
        Random r = new Random(); 
        for (int i = size-1; i > 0; i--) { 
            int j = r.nextInt(i+1); 
            T temp = listToShuffle.get(i); 
            listToShuffle.set(i, listToShuffle.get(j)); 
            listToShuffle.set(j, temp); 
        } 
    } 
}
