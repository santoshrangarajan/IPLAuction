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
public class PlayersQueue {

	private Queue<Player> playersQueue;
	
	public PlayersQueue(int poolSize){
		playersQueue = new PriorityBlockingQueue<Player>(poolSize,new PlayerComparatorAscOrder());
	}
	
	public void  addPlayer(Player player){
		playersQueue.add(player);
	}
	/**
	 * @return
	 */
	public Player getNext() {
		return playersQueue.remove();
	}

	
	public int getSize() {
		return playersQueue.size();
	}
	
	public String getPlayers(){
		StringBuilder sb = new StringBuilder();
		sb.append("Auctioners queue = ");
		sb.append(" [");
		String delim = "";
		for(Player player:playersQueue){
			sb.append(delim).append(player.getName());
			delim = ",";
		}
		sb.append("]");
		return sb.toString();
	}

}
