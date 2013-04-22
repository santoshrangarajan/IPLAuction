/**
 * 
 */
package com.ipl.auction.queue;


import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import com.ipl.auction.player.Player;
import com.ipl.auction.player.PlayerComparatorAscOrder;

/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 */
public class UnsoldPlayersQueue {

	private Queue<Player> unsoldQueue;
	
	public UnsoldPlayersQueue(int poolSize){
		unsoldQueue = new PriorityBlockingQueue<Player>(poolSize,new PlayerComparatorAscOrder());
	}
	/**
	 * @param player
	 */
	public void add(Player player) {
		unsoldQueue.add(player);
	}
	
	public String getPlayers(){
		StringBuilder sb = new StringBuilder();
		sb.append("Unsold Players = ");
		sb.append("[");
		String delim = "";
		for(Player player:unsoldQueue){
			sb.append(delim).append(player.getName());
			delim = ",";
		}
		sb.append("]");
		return sb.toString();
	}
	
	public int getSize(){
		return unsoldQueue.size();
	}

}
