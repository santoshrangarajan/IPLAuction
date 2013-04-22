/**
 * 
 */
package com.ipl.auction.bidding;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 */
public class AlternateBiddingStrategyTest {

	
	@Test
	public void testBiddingTeamSelection() {
		List<String> biddingOrderList = new ArrayList<String>();
		biddingOrderList.add("B1");
		biddingOrderList.add("B2");
		
		BiddingStrategy biddingStrategy = new AlternateBiddingStrategy(biddingOrderList);
		
		String expectedValue1 = "B1";
		String actualValue1 = biddingStrategy.getNextTeam();
		
		assertEquals(expectedValue1,actualValue1);
		
		String expectedValue2 = "B2";
		String actualValue2 = biddingStrategy.getNextTeam();
		
		assertEquals(expectedValue2,actualValue2);
		
		String expectedValue3 = "B1";
		String actualValue3 = biddingStrategy.getNextTeam();
		
		assertEquals(expectedValue3,actualValue3);
		
		String expectedValue4 = "B2";
		String actualValue4 = biddingStrategy.getNextTeam();
		
		assertEquals(expectedValue4,actualValue4);
	}

}
