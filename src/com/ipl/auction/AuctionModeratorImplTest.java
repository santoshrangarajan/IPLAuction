/**
 * 
 */
package com.ipl.auction;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;

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
 * Mar 14, 2013
 */
public class AuctionModeratorImplTest {

	
	private PlayersQueue playersQueue;
	private Map<String, TeamManager> participatingTeams;
	private UnsoldPlayersQueue unsoldPlayersQueue;
	private BiddingStrategy biddingStrategy;
	private AuctionModerator auctionModerator;
	
	
	@Before
	public void setUp() throws Exception {
		
		//auctionAssist = new AuctionAssist();
		initQueues();
		initTeams();
		initBiddingStrategy();
		
		auctionModerator = new AuctionModeratorImpl(playersQueue, participatingTeams, unsoldPlayersQueue,biddingStrategy);
	}
	
	private void initQueues() throws ParserConfigurationException{
		Input input = new XMLFileInput("/Users/santoshrangarajan/Portfolio/Blog/SnapDeal/sample.xml");
		List<Player> playersList = new ArrayList<Player>();
		try {
			playersList = input.generateAuctionInput();
		} catch (Exception e) {
			System.out.println("Exception parsing input. Message="+e.getMessage());
			System.exit(0);
		}

		playersQueue = new PlayersQueue(playersList.size());
		for (Player player : playersList) {
			playersQueue.addPlayer(player);
		}
		 unsoldPlayersQueue = new UnsoldPlayersQueue(playersList.size());
	}
	
	private void initTeams(){
		
		Team team1 = new Team("B1", 60, 5, 2);
		TeamManager teamManager1 = new TeamManagerImpl(team1);
		Team team2 = new Team("B2", 60, 5, 2);
		TeamManager teamManager2 = new TeamManagerImpl(team2);
		participatingTeams = new ConcurrentHashMap<String, TeamManager>();
		participatingTeams.put(team1.getName(), teamManager1);
		participatingTeams.put(team2.getName(), teamManager2);
	}
	
	private void initBiddingStrategy(){
		List<String> biddingOrderList = new ArrayList<String>();
		biddingOrderList.add("B1");
		biddingOrderList.add("B2");
		biddingStrategy = new AlternateBiddingStrategy(biddingOrderList);
	}
	
	@Test
	public void testTeamToBeSelectedForBidding() {
		
		TeamManager expectedTeamManager = auctionModerator.selectTeamToBid();
		String actualValue = expectedTeamManager.getTeamName();
		
		String expectedValue = "B1";
		assertEquals(expectedValue,actualValue);
	}
	
	@Test 
	public void testPlayersQueuesPopulatedCorrectly(){
		int origQueueSize = playersQueue.getSize();
		TeamManager mgr1 = auctionModerator.selectTeamToBid();
		Player player1 = auctionModerator.selectPlayerToBeAuctioned();
		auctionModerator.handlePlayerSelected(mgr1, player1);
		assertEquals(origQueueSize-1,playersQueue.getSize());	
	}
	
	@Test
	public void testUnsoldQueuePopulatedCorrectly(){
		TeamManager mgr1 = auctionModerator.selectTeamToBid();
		Player player1 = auctionModerator.selectPlayerToBeAuctioned();
		auctionModerator.handlePlayerNotSelected(mgr1, player1);
		
		TeamManager mgr2 = auctionModerator.selectTeamToBid();
		Player player2 = auctionModerator.selectPlayerToBeAuctioned();
		auctionModerator.handlePlayerNotSelected(mgr2, player2);
		assertEquals(1,unsoldPlayersQueue.getSize());
	}

}
