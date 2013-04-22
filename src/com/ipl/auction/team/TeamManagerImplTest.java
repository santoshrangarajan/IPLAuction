/**
 * 
 */
package com.ipl.auction.team;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import com.ipl.auction.player.Player;

/**
 * @author santoshrangarajan
 *
 * Mar 14, 2013
 */
public class TeamManagerImplTest {

	
	private List<Player> generateCompleteList(){
		List<Player> playersList = new ArrayList<Player>();
		playersList.add(new Player("Pujara", 6, 0,9,0,93));
		playersList.add(new Player("Peeyush", 2, 5,9,0,62));
		playersList.add(new Player("Munaf", 1, 6,5,0,27));
		playersList.add(new Player("Sidhu", 3, 2,1,0,75));
		playersList.add(new Player("Zaheer", 2, 7,2,0,72));
		return playersList;
	}
	
	private List<Player> generateInCompleteList(){
		List<Player> playersList = new ArrayList<Player>();
		playersList.add(new Player("Pujara", 6, 0,9,0,93));
		playersList.add(new Player("Peeyush", 2, 5,9,0,62));
		return playersList;
	}
	
	private List<Player> generateListIncompleteStrength(){
		List<Player> playersList = new ArrayList<Player>();
		playersList.add(new Player("Vijay", 7, 2,3,0,60));
		playersList.add(new Player("Imran", 7, 2,1,0,7));
		playersList.add(new Player("Munaf", 1, 6,5,0,27));
		playersList.add(new Player("Sidhu", 3, 2,1,0,75));
		playersList.add(new Player("Zaheer", 2, 7,2,0,72));
		return playersList;
	}
	
	@Test
	public void testisSelectionComplete() {
		
		Team team1 = new Team("B1", 60, 5, 2);
		TeamManager manager = new TeamManagerImpl(team1);
		
		List<Player> completeList = generateCompleteList();
		for(Player player:completeList){
			manager.addPlayer(player);
		}
		assertTrue(manager.isSelectionComplete());
		
		for(Player player:completeList){
			manager.removePlayer(player);
		}
		List<Player> inCompleteList = generateInCompleteList();
		for(Player player:inCompleteList){
			manager.addPlayer(player);
		}
		assertFalse(manager.isSelectionComplete());
	}
	
	@Test
	public void testEligiblePlayerSelection(){
		
		Team team1 = new Team("B1", 60, 5, 2);
		TeamManagerImpl manager = new TeamManagerImpl(team1);
		List<Player> completeList = generateListIncompleteStrength();
		for(Player player:completeList){
			manager.addPlayer(player);
		}
		assertFalse(manager.isSelectionComplete());
		
		Player newPlayer = new Player("Peeyush", 2, 5,9,0,60);
		Player eligiblePlayer = manager.selectEligiblePlayer(newPlayer);
		
		Player expectedPlayer = new Player("Imran", 7, 2,1,0,7);
		assertEquals(expectedPlayer, eligiblePlayer);
	}

}
