/**
 * 
 */
package com.ipl.auction.bidding;

import java.util.Iterator;
import java.util.List;


/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 */
public class AlternateBiddingStrategy implements  BiddingStrategy {

	private Iterator<String> iterator;
	public AlternateBiddingStrategy(final List<String> biddingOrderList){
		this.iterator = new Iterator<String>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				return true;
			}
			@Override
			public String next() {
				String res = biddingOrderList.get(index);
				index =  (index + 1) % biddingOrderList.size();
				return res;
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException(); 
			};
		};
	}
	

	
	@Override
	public String getNextTeam() {
		return iterator.next();
	}


}
