/**
 * 
 */
package com.ipl.auction.team;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Queue;


import com.ipl.auction.player.Player;

/**
 * @author santoshrangarajan
 *
 * Mar 12, 2013
 */
public class TeamManagerImpl implements TeamManager {

	private Team team;
	//private AuctionAssistOrig auctionAssist;
	NumberFormat fmt;
	
	public TeamManagerImpl(Team team /*AuctionAssistOrig auctionAssist*/){
		this.team = team;
		//this.auctionAssist = auctionAssist;
		fmt = NumberFormat.getCurrencyInstance();
		fmt.setCurrency(Currency.getInstance("INR"));
	};
	
	
	@Override
	public boolean isPlayerEligible(Player player) {
		
		if(player.getUnsoldTeams().contains(team.name)){
			return false;
		} else if(isSelectionComplete()){
			return false;
		} else if(!isForeignPlayersConstraintMet(player)){
			return false;
		} else {
			return true;
		}
	}
	
	public Player shuffleTeam(Player player){
		Player eligiblePlayer = selectEligiblePlayer(player);
		if(eligiblePlayer !=null){
			removePlayer(eligiblePlayer);
			addPlayer(player);
			return eligiblePlayer;
		} else {
			return player;
		}
	}
	
	/*
	 * Iterate through existing players
	 * for particular player, find strength of team if that player is replaced with new player
	 * if newStrength is > existingStrength then replace the player with new player
	 */
	 protected Player selectEligiblePlayer(Player player){
		Queue<Player> players = team.getPlayers();
		int initStength = team.getStrength();
		int currStrength = team.getStrength();
		int newStrength = 0;
		Player eligiblePlayer = null;
		for(Player qplayer:players){
			newStrength = initStength - qplayer.getStrength() + player.getStrength();
			if(newStrength > currStrength &&  newStrength <= team.getDesiredStrength()) {
					if(newStrength == team.getDesiredStrength() && ( team.getSize() == (team.getDesiredSize()-1))) {
						System.out.println("Not updating as strength will increase but size will be less than desired");
					} else {
					  eligiblePlayer = qplayer;
					  currStrength = newStrength;
					}
			}
		}
		return eligiblePlayer;
	}
	
	private boolean isForeignPlayersConstraintMet(Player player) {
		if(!player.isForeignNational()){
			return true;
		}
		
		return (team.getForeignPlayersCount() < team.getDesiredForeignPlayersCount());
	}

	private boolean isStrengthLessThanDesired(Player player) {
		return (team.getStrength() + player.getStrength() < team.getDesiredStrength());
	}
	
	@Override
	public void addPlayer(Player player) {
		team.addPlayer(player);
		team.setCost(team.getCost()+ player.getPrice());
		team.setStrength(team.getStrength()+ player.getStrength());
		//int newSize = team.getSize()+1;
		team.setSize(team.getSize()+1);
		if(player.isForeignNational()){
			team.setForeignPlayersCount(team.getForeignPlayersCount()+1);
		}
	}
	
	public void removePlayer(Player player){
	    team.getPlayers().remove(player);
		team.setCost(team.getCost() - player.getPrice());
		team.setStrength(team.getStrength() - player.getStrength());
		team.setSize(team.getSize()-1);
		if(player.isForeignNational()){
			team.setForeignPlayersCount(team.getForeignPlayersCount() - 1 );
		}
	}
		
	@Override
	public boolean isSelectionComplete() {
		return (team.getStrength() == team.getDesiredStrength() 
				&& team.getSize() == team.getDesiredSize());
	}
	
	@Override
	public String getTeamName() {
		return team.getName();
	}
	
	@Override
	public String getStatus(boolean format) {
		StringBuilder status = new StringBuilder();
		status.append("Team="+team.getName());
		if(format) {
			status.append(", Cost="+fmt.format(team.getCost()));
		} else {
			 status.append(", Cost="+(team.getCost())); 
		}
		status.append(", Strength="+team.getStrength());
		status.append(", Players= [");
		String delim = "";
		for(Player player:team.getPlayers()){
			status.append(delim).append(player.getName());
			delim = ",";
		}
		status.append("]");
		status.append("\n");
		return status.toString();
	}

	@Override
	public String actionToBePerformed(Player player) {
		if(isStrengthLessThanDesired(player) && (team.getSize() < team.getDesiredSize()) ) {
			 return "ADD";
		} else {
			 return "SHUFFLE";
		}
	}

}
