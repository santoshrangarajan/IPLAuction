/**
 * 
 */
package com.ipl.auction.team;
import java.util.PriorityQueue;
import java.util.Queue;
import com.ipl.auction.player.Player;
import com.ipl.auction.player.PlayerComparatorDescOrder;

/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 */
public class Team   {

	String name;
	Queue<Player> players;
	private int strength;
	private int desiredStrength;
	private int foreignPlayersCount;
	private int desiredForeignPlayersCount;
	private double cost;
	private int desiredSize;
	private int size;
	
	
	public Team(String name, int desiredStrength, int desiredSize, int desiredForeignPlayersCount)
	{	
		this.name = name;
		///players = new PriorityQueue<Player>(desiredSize,playerPriceComparator);
		this.desiredStrength = desiredStrength;
		this.desiredSize = desiredSize;
		this.desiredForeignPlayersCount = desiredForeignPlayersCount;
		this.size =0;
		players = new PriorityQueue<Player>(desiredSize, new PlayerComparatorDescOrder());
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDesiredStrength() {
		return desiredStrength;
	}

	
	public int getForeignPlayersCount() {
		return foreignPlayersCount;
	}

	public void setForeignPlayersCount(int foreignPlayersCount) {
		this.foreignPlayersCount = foreignPlayersCount;
	}

	public int getDesiredForeignPlayersCount() {
		return desiredForeignPlayersCount;
	}

	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getDesiredSize() {
		return desiredSize;
	}

    public int getSize(){
    	return size;
    }
    
    public void setSize(int size){
    	this.size = size;
    }
    

	public Queue<Player> getPlayers() {
		return players;
	}
	

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	@Override
	public String toString() {
		return "Team [name=" + name + ", players=" + players
				+ ", currentStrength=" + strength
				+ ", currentForeignPlayersCount=" + foreignPlayersCount
				+ ", cost=" + cost + "]";
	}

}
