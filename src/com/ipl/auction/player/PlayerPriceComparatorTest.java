/**
 * 
 */
package com.ipl.auction.player;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 */
public class PlayerPriceComparatorTest {

	static PlayerComparatorAscOrder playerPriceComparator;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerPriceComparator = new PlayerComparatorAscOrder();
	}
	
	
	@Test
	public void testCompare(){
		Player player1 = new Player("Sachin",10, 5, 6, 0, 10);
		Player player2 = new Player("Veeru",8, 6, 4, 0, 90);
		
		int expectedResult = 1;
		
		int actualResult = playerPriceComparator.compare(player1, player2);
		
		assertEquals(expectedResult,actualResult);
	}

}
