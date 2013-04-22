/**
 * 
 */
package com.ipl.auction;

import java.util.Iterator;
import java.util.Map;

import com.ipl.auction.bidding.BiddingStrategy;
import com.ipl.auction.player.Player;
import com.ipl.auction.queue.PlayersQueue;
import com.ipl.auction.queue.UnsoldPlayersQueue;
import com.ipl.auction.team.TeamManager;

/**
 * @author santoshrangarajan
 * 
 *         Mar 11, 2013
 */
public class AuctionModeratorImpl implements AuctionModerator {

	private PlayersQueue playersQueue;
	private Map<String, TeamManager> participatingTeams;
	private UnsoldPlayersQueue unsoldPlayersQueue;
	private BiddingStrategy biddingStrategy;

	/**
	 * @param playersQueue
	 * @param participatingTeams
	 * @param unsoldPlayersQueue
	 * @param count
	 */
	public AuctionModeratorImpl(PlayersQueue playersQueue,Map<String, TeamManager> participatingTeams,
			                     UnsoldPlayersQueue unsoldPlayersQueue,BiddingStrategy biddingStrategy) {
		this.playersQueue = playersQueue;
		this.participatingTeams = participatingTeams;
		this.unsoldPlayersQueue = unsoldPlayersQueue;
		this.biddingStrategy = biddingStrategy;
	}

	@Override
	public TeamManager selectTeamToBid() {
		return participatingTeams.get(biddingStrategy.getNextTeam());
	}

	@Override
	public Player selectPlayerToBeAuctioned() {
		return playersQueue.getNext();
	}

	@Override
	public void handlePlayerSelected(TeamManager teamManager, Player player) {
		String action = teamManager.actionToBePerformed(player);
		if (action.equals("ADD")) {
			teamManager.addPlayer(player);
		} else if (action.equals("SHUFFLE")){
			Player splayer = teamManager.shuffleTeam(player);
			if (splayer != null) {
				splayer.addUnsoldTeam(teamManager.getTeamName());
				updateQueues(splayer);
			}
		}

	}

	private void updateQueues(Player player) {
		if (player.getUnsoldTeams().size() == participatingTeams.size()) {
			unsoldPlayersQueue.add(player);
		} else {
			// // add player back to queue
			playersQueue.addPlayer(player);
		}
	}

	@Override
	public void handlePlayerNotSelected(TeamManager teamManager, Player player) {
		player.addUnsoldTeam(teamManager.getTeamName());
		updateQueues(player);
	}

	private boolean selectionForAllTeamsComplete(){
		Iterator<String> itr = participatingTeams.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			TeamManager teamManager = participatingTeams.get(key);
			if (!teamManager.isSelectionComplete()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean shouldAuctionStop() {
		if (playersQueue.getSize() == 0) {
			return true;
		}
		return selectionForAllTeamsComplete();
	}

	public String getStatus(boolean format) {
		StringBuilder sb = new StringBuilder();
		for (TeamManager manager : participatingTeams.values()) {
			sb.append(manager.getStatus(format));
		}
		sb.append(playersQueue.getPlayers());
		sb.append("\n");
		sb.append(unsoldPlayersQueue.getPlayers());
		sb.append("\n");
		return sb.toString();
	}

}
