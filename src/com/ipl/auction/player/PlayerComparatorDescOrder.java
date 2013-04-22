/**
 * 
 */
package com.ipl.auction.player;

import java.util.Comparator;



/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 * 
 * To Sort Players in Descending order of Prices
 * Used by Team
 */
public class PlayerComparatorDescOrder implements Comparator<Player> {

	
	@Override
	public int compare(Player p1, Player p2) {
		
		if(p2.getPrice() < p1.getPrice()) { 
			return -1;
		} else if( p2.getPrice() > p1.getPrice()){
			return 1;
		} else  {
			return 0;
		}
	}

}
