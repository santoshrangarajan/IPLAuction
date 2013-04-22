/**
 * 
 */
package com.ipl.auction.player;

import java.util.HashSet;
import java.util.Set;



/**
 * @author santoshrangarajan
 *
 * Mar 11, 2013
 */
public class Player {
	private String name;
	private int strengthBatting;
	private int strengthBowling;
	private int strengthFielding;
	private int strengthKeeping;
	private int jerseyNumber;
	private Set<String> unsoldTeams;
	
	private int strength;
	private double price;
	private boolean isForeignNational;

	/**
	 * @param name
	 * @param strengthBatting
	 * @param strengthBowling
	 * @param strengthFielding
	 * @param strengthKeeping
	 * @param jerseyNumber
	 */
	public Player(String name, int strengthBatting, int strengthBowling,
			int strengthFielding, int strengthKeeping, int jerseyNumber) {
		
		this.name = name;
		this.strengthBatting = strengthBatting;
		this.strengthBowling = strengthBowling;
		this.strengthFielding = strengthFielding;
		this.strengthKeeping = strengthKeeping;
		this.jerseyNumber = jerseyNumber;
		unsoldTeams = new HashSet<String>();
		this.strength =  computeStrength();
		this.price = computePrice();
		this.isForeignNational = checkForeignNational();
	}
	
	public String getName() {
		return name;
	}

	public int getStrengthBatting() {
		return strengthBatting;
	}

	public int getStrengthBowling() {
		return strengthBowling;
	}

	public int getStrengthFielding() {
		return strengthFielding;
	}

	public int getStrengthKeeping() {
		return strengthKeeping;
	}

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	
	public void addUnsoldTeam(String teamName){
		unsoldTeams.add(teamName);
	}
	
	public int getStrength() {
		return strength;
	}

	public double getPrice() {
		return price;
	}
	
	public boolean isForeignNational() {
		return isForeignNational;
	}

/*	public void incrementUnsoldCount() {
		unsoldCount++;
	}*/

	
	public Set<String> getUnsoldTeams() {
		return unsoldTeams;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jerseyNumber;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (jerseyNumber != other.jerseyNumber)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private int computeStrength(){
		return strengthBatting + strengthBowling + strengthFielding +strengthKeeping;
	}
	
	private double computePrice(){
	   double priceParam1 = 1000000 * (0.45 * strengthBatting +0.35 * strengthBowling + 0.20 * strengthFielding) ;
       double priceParam2 = (strengthKeeping == 10 )? 500000 : 0;
       return priceParam1 + priceParam2;
	}

	private boolean checkForeignNational() {
		for(int i=2 ; 2*i<=jerseyNumber;i++) {
	        if( jerseyNumber % i==0)
	            return false;
	    }
	    return true;
	}
}
