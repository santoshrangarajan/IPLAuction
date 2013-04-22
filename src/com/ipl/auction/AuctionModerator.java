/**
 * 
 */
package com.ipl.auction;

import com.ipl.auction.player.Player;
import com.ipl.auction.team.TeamManager;

/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 * Interface to handle steps in auction
 */
public interface AuctionModerator {
	
	public TeamManager selectTeamToBid();
	public Player selectPlayerToBeAuctioned();
	public void handlePlayerSelected(TeamManager teamManager, Player player);
	public void handlePlayerNotSelected(TeamManager teamManager, Player player);
	public String getStatus(boolean status);
	boolean shouldAuctionStop();
}
