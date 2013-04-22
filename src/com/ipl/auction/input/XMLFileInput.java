/**
 * 
 */
package com.ipl.auction.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ipl.auction.player.Player;

/**
 * @author santoshrangarajan
 *
 * Mar 13, 2013
 */
public class XMLFileInput implements Input {

	String xmlFile;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document dom;
	
	public XMLFileInput(String xmlFile) throws ParserConfigurationException{
		this.xmlFile = xmlFile;
		dbf = DocumentBuilderFactory.newInstance();
	    db = dbf.newDocumentBuilder();
	}
	
	
	@Override
	public List<Player> generateAuctionInput() throws FileNotFoundException, SAXException, IOException  {
		List<Player> playersList = new ArrayList<Player>();
		 Document dom = db.parse(new FileInputStream(xmlFile));
		 Element docEle = dom.getDocumentElement();
	     NodeList nl = docEle.getElementsByTagName("player");
	      if (nl != null && nl.getLength() > 0) {
	    	 
		        for (int i = 0 ; i < nl.getLength(); i++) {
		          try{
			          Element item = (Element)nl.item(i);
			          Element name = (Element)item.getElementsByTagName("name").item(0);
			          Element strengthBatting = (Element)item.getElementsByTagName("strength_batting").item(0);
			          Element strengthBowling = (Element)item.getElementsByTagName("strength_bowling").item(0);
			          Element strengthFielding = (Element)item.getElementsByTagName("strength_fielding").item(0);
			          Element strengthKeeping = (Element)item.getElementsByTagName("strength_keeping").item(0);
			          Element jerseyNumber = (Element)item.getElementsByTagName("jersey_number").item(0);
			          
			          playersList.add(new Player(name.getTextContent(),
			        		                     Integer.parseInt(strengthBatting.getTextContent()),
			        		                     Integer.parseInt(strengthBowling.getTextContent()),
			        		                     Integer.parseInt(strengthFielding.getTextContent()),
			        		                     Integer.parseInt(strengthKeeping.getTextContent()),
			        		                     Integer.parseInt(jerseyNumber.getTextContent())));
		          } catch(Exception ex){
		        	  System.out.println("Exception parsing item number = "+i+". Message ="+ex.getMessage());
		          }
		         
		        }
	      }
	      return playersList;
	}

}
