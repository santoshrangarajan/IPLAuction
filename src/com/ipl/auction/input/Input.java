/**
 * 
 */
package com.ipl.auction.input;

import java.util.List;

import com.ipl.auction.player.Player;

/**
 * @author santoshrangarajan
 *
 * Mar 13, 2013
 * 
 * Interface to generate input for auction
 */
public interface Input {
	
	public List<Player> generateAuctionInput() throws Exception;

}
