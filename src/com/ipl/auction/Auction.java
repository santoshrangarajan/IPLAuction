/**
 * 
 */
package com.ipl.auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ipl.auction.bidding.AlternateBiddingStrategy;
import com.ipl.auction.bidding.BiddingStrategy;
import com.ipl.auction.input.Input;
import com.ipl.auction.input.XMLFileInput;
import com.ipl.auction.player.Player;
import com.ipl.auction.queue.PlayersQueue;
import com.ipl.auction.queue.UnsoldPlayersQueue;
import com.ipl.auction.team.Team;
import com.ipl.auction.team.TeamManager;
import com.ipl.auction.team.TeamManagerImpl;

/**
 * @author santoshrangarajan
 * 
 *         Mar 11, 2013
 */
public class Auction {

	private Map<String, TeamManager> participatingTeams;
	private boolean stopAuction;
	private AuctionModerator auctionModerator;

	private int round;

	public Auction(List<Player> playersList, List<Team> teamsList, List<String> biddingCallerList){
		PlayersQueue playersQueue = initPlayersQueue(playersList);
		UnsoldPlayersQueue unsoldPlayersQueue = initUnsoldPlayersQueue(playersList.size());
		initTeams(teamsList);
		BiddingStrategy biddingStrategy = new AlternateBiddingStrategy(biddingCallerList);
		auctionModerator = new AuctionModeratorImpl(playersQueue, participatingTeams, unsoldPlayersQueue,biddingStrategy);
		round=1;
	}
	
	private UnsoldPlayersQueue initUnsoldPlayersQueue(int size){
		return new UnsoldPlayersQueue(size);
	}
	
	private PlayersQueue initPlayersQueue(List<Player> playersList) {
		PlayersQueue playersQueue = new PlayersQueue(playersList.size());
		for (Player player : playersList) {
			playersQueue.addPlayer(player);
		}
		return playersQueue;
	}
	
	private void initTeams(List<Team> teamsList){
		participatingTeams = new ConcurrentHashMap<String, TeamManager>();
		for(Team team: teamsList){
			TeamManager teamManager = new TeamManagerImpl(team);
			participatingTeams.put(team.getName(), teamManager);
		};	
	}
	
	public void start() {
		System.out.println("Starting Auction....");
		while (!stopAuction) {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("Round = " + round);
			Player player = auctionModerator.selectPlayerToBeAuctioned();
			System.out.println("Player to be auctioned =" + player.getName() + ", " 
			                                              + "Strength ="+ player.getStrength()
			                                              + ", " + "Price ="+ player.getPrice());
			TeamManager teamManager = auctionModerator.selectTeamToBid();
			System.out.println("Team to bid =" + teamManager.getTeamName());
			boolean isPlayerEligible = teamManager.isPlayerEligible(player);
			System.out.println("Is Player Eligible for selection ="+ isPlayerEligible);
			if (isPlayerEligible) {
				auctionModerator.handlePlayerSelected(teamManager, player);
			} else {
				auctionModerator.handlePlayerNotSelected(teamManager, player);
			}
			stopAuction = auctionModerator.shouldAuctionStop();
			System.out.println("Auction status after round = " + round);
			System.out.println(auctionModerator.getStatus(false));
			round++;
		}
	}

	public void stop() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Stopping Auction. Final Team status below..");
		for (TeamManager manager : participatingTeams.values()) {
			System.out.println(manager.getStatus(true));
		}
	}

	 
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		
		if(args.length<=0 ){
			System.out.println("Please pass complete fileName as an argument to main");
			System.out.println("Sample input file is provided as part of zip, in src folder");
			System.exit(-1);
		}
		
		Input input = new XMLFileInput(args[0]);
		List<Player> playersList = new ArrayList<Player>();
		try {
			playersList = input.generateAuctionInput();
		} catch (Exception e) {
			System.out.println("Exception parsing input. Message="+e.getMessage());
			System.exit(-1);
		}
		
		List<Team> teamsList = new ArrayList<Team>();
		teamsList.add(new Team("B1", 60, 5, 2));
		//teamsList.add(new Team("B2", 70, 5, 2));
		
		List<String> biddingOrderList = new ArrayList<String>();
		biddingOrderList.add("B1");
		//biddingOrderList.add("B2");
		
		Auction auction = new Auction(playersList, teamsList, biddingOrderList);
		
		///// start auction
		auction.start();
		auction.stop();
	}

}
