/**
 * 
 */
package com.ipl.auction.team;


import com.ipl.auction.player.Player;

/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 * 
 * Interface to handle operations related to team
 */
public interface TeamManager {
	
	public boolean isPlayerEligible(Player player);
	public boolean isSelectionComplete();
	public String getTeamName();
	public String getStatus(boolean format);
	public String actionToBePerformed(Player player);//// can be ADD/SHUFFLE
	public void addPlayer(Player player);
	public void removePlayer(Player player);
	public Player shuffleTeam(Player player);
	
}
