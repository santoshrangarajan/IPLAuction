/**
 * 
 */
package com.ipl.auction.queue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import com.ipl.auction.player.Player;
import com.ipl.auction.player.PlayerComparatorAscOrder;

/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 */
public class PlayersQueueTest {

	static PlayersQueue playersQueue;
	static PlayerComparatorAscOrder playerPriceComparator;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerPriceComparator = new PlayerComparatorAscOrder();
		playersQueue = new PlayersQueue(5);
	}
	
	
	@Test
	public void testPlayersAreSortedAfterAddition() {
		
		playersQueue.addPlayer(new Player("Sachin",10, 5, 6, 0, 10));
		playersQueue.addPlayer(new Player("Yuvi",7, 6, 8, 0, 21));
		playersQueue.addPlayer(new Player("Veeru",8, 6, 4, 0, 34));
		playersQueue.addPlayer(new Player("Rahane",7, 4, 7, 0, 78));
		playersQueue.addPlayer(new Player("Virat",7, 4, 8, 0, 90));
		
		List<String> expectedPlayers = new ArrayList<String>();
		expectedPlayers.add("Rahane");
		expectedPlayers.add("Virat");
		expectedPlayers.add("Veeru");
		expectedPlayers.add("Yuvi");
		expectedPlayers.add("Sachin");
		
	    for(int i=0;i<5;i++){
		   Player actualPlayer = playersQueue.getNext();
		    assertEquals(expectedPlayers.get(i),actualPlayer.getName());
	    }
		
	}

}
