/**
 * 
 */
package com.ipl.auction.player;

import java.util.Comparator;

/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 * 
 * To Sort Players in Ascending order of Prices
 * If price of 2 players is same, they will be sorted by ascending order of strengths
 * Used by PlayersQueue
 */
public class PlayerComparatorAscOrder  implements Comparator<Player> {
	
	@Override
	public int compare(Player p1, Player p2) {
		if(p1.getPrice() < p2.getPrice()) { 
			return -1;
		} else if( p1.getPrice() > p2.getPrice()){
			return 1;
		} else  {
			///// if prices are same, then sort by asc strengths
			if(p1.getStrength() < p2.getStrength()) {
				return -1;		
			} else if( p1.getStrength() > p2.getStrength()){
				return 1;
			} else {
				return 0;
			}
		}
	}
}
