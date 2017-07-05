package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
import it.polimi.ingsw.ps46.utils.MyJSONParser;

public class FactoryBoard {
	
private static FactoryBoard factoryBoard = null;
private String configFilesPath = "./src/main/java/it/polimi/ingsw/ps46/server/config/";
	
	
	private FactoryBoard() {
	}
	
	
	/**
	 * Creates a singleton of the FactoryBoard if it doesn't exist, 
	 * otherwise it returns the reference to the existing FactoryBoard.
	 */
	public static FactoryBoard getFactoryBoard() {
		if (factoryBoard == null) 
			factoryBoard = new FactoryBoard();
		return factoryBoard;
	}
	
	
	
	/**
	 * Reads the configuration file for Towers and Action Spaces and creates the board.
	 */
	public Board createBoard(String towersConfigFile, String actionSpacesConfigFile, int numberPlayers) {
		
		Board board = new Board(null, null);
		
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();

		//BEGIN OF TOWERS PARSER
		try {
			
			Object obj = parser.parse(new FileReader(configFilesPath + towersConfigFile));
        	
        	//BEGIN of parsing of tower field
        	JSONArray towersJSON = (JSONArray) obj;
        	ArrayList<Tower> towers = new ArrayList<Tower>();
        	Iterator<?> iterator = towersJSON.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject towerJSON = (JSONObject) iterator.next();
                Tower tower = myJSONParser.buildTower(towerJSON);
                towers.add(tower);
        		
        	}
        	//END of parsing of tower field
        	//END OF TOWERS PARSER
        	
        	
        	//BEGIN OF ACTION SPACES PARSER
        	obj = parser.parse(new FileReader(configFilesPath + actionSpacesConfigFile));
        	
        	JSONArray actionSpacesJSON = (JSONArray) obj;
        	ArrayList<ActionSpace> boardBoxes = new ArrayList<ActionSpace>();
        	iterator = actionSpacesJSON.iterator();
        	while(iterator.hasNext()) {
        		
        		JSONObject actionSpaceJSON = (JSONObject) iterator.next();
        		//takes only the action spaces where the minimum number of players required
        		//for the game is satisfied.
        		int minPlayers = ((Long) actionSpaceJSON.get("minPlayers")).intValue();
        		if(numberPlayers >= minPlayers) {
    				ActionSpace actionSpace = myJSONParser.buildActionSpace(actionSpaceJSON);
            		boardBoxes.add(actionSpace);
    			}
        		
        	}
    		//END OF ACTION SPACES PARSER
        	
        	board = new Board(towers, boardBoxes);
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return board;
	}
	
	
	public ArrayList<BonusTile> createBonusTiles(String configFile) {
		
		ArrayList<BonusTile> bonusTiles = new ArrayList<BonusTile>();
		
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(configFilesPath + configFile));
        	JSONArray bonusTilesJSON = (JSONArray) obj;
        	Iterator<?> iterator = bonusTilesJSON.iterator();
        	while(iterator.hasNext()) {
        		JSONObject bonusTileJSON = (JSONObject) iterator.next();
        		BonusTile bonusTile = myJSONParser.buildBonusTile(bonusTileJSON);
        		bonusTiles.add(bonusTile);
        	}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return bonusTiles;
		
	}
	
}
