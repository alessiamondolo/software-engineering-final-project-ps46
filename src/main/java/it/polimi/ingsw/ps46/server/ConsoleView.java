package it.polimi.ingsw.ps46.server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.utils.ReadInput;


public class ConsoleView extends View {
	
	private ReadInput input;
	private PrintStream output;
	private List<String> colors = new ArrayList<String>();
	
	private Game game;
	
	public ConsoleView(Game game, OutputStream output) {
		this.game = game;
		this.output = new PrintStream(output);
		input = new ReadInput();
		
		colors.add("Red");
		colors.add("Yellow");
		colors.add("Blue");
		colors.add("Green");
	}
	
	
	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
	}
	
	
	public void visit(EventMessage eventMessage) {
		//output.println("Game state: " + game.getGameState().toString());
		//output.println("Message: " + eventMessage.getMessage().toString());
		switch(eventMessage.getMessage()) {
		case START_GAME :
			welcomeMessage();
			getGameMode();
			break;
		case CHANGED_CURRENT_PLAYER :
			GameState gameState = game.getGameState();
			switch(gameState) {
			case SETUP_PLAYERS_USERNAME :
				getPlayerUsername(game.getCurrentPlayer().getIdPlayer());
				break;
			case SETUP_PLAYERS_COLOR :
				getPlayerColor(game.getCurrentPlayer().getUsername());
				break;
			case GET_PLAYER_ACTION : 
				printPlayerStatus();
				Integer action = getPlayerAction();
				setChanged();
				notifyObservers(new EventStringInput(action.toString(), InputType.PLAYER_ACTION));
				getFamilyMember();
				getServants();
				break;
			default:
				break;
			}
			break;
		case SET_INITIAL_ORDER :
			showInitialOrder();
			break;
		case UPDATE_ROUND_INFO : 
			updateRoundInfo();
			break;
		case THROWN_DICE :
			printBoard();
			break;
		case SET_NEXT_TURN_ORDER :
			showNextTurnOrder();
			break;
			/*
		case UPDATE_CURRENT_PLAYER_STATE :
			printPlayerStatus();*/
		default:
			break;
		}
	}

	
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	public void welcomeMessage() {
		output.println("==========================================================================");
		output.println("==========================================================================");
		output.println("Welcome to the game Lorenzo Il Magnifico!");
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.GAME_STARTED));
	}
	
	
	
	public void getGameMode() {
		
		output.println("==========================================================================");
		output.println("In which game mode do you want to play?");
		output.println("1. Basic");
		output.println("2. Advanced");
		int gameMode = input.IntegerFromConsole(1, 2);
		if (gameMode == 2) {
			setChanged();
			notifyObservers(new EventMessage(NewStateMessage.ADVANCED_GAME_MODE));
		}
		
	}
	
	
	
	public void getPlayerUsername(int id) {
		
		output.println("==========================================================================");
		output.println("Player " + id + ": what is your username?");
		String username = input.stringFromConsole();
		
		setChanged();
		notifyObservers(new EventStringInput(username, InputType.PLAYER_USERNAME));
		
	}
	
	
	
	public void showInitialOrder() {
		
		output.println("==========================================================================");
		output.println("The initial game order will be:");
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.SET_INITIAL_ORDER));
	}
	
	
	
	public String getPlayerColor(String username) {
		output.println("==========================================================================");
		output.println(username + ": which color do you want?");
		int index = 1;
		for(ListIterator<String> iterator=colors.listIterator(); iterator.hasNext();){
			String color =iterator.next();
			output.println(index + ". " + color);
			index++;
		}
		int choice = input.IntegerFromConsole(1, colors.size()) - 1;
		String color = colors.get(choice);
		colors.remove(color);
		output.println("Your color will be " + color);
		return color;
	}
	
	
	
	public void printBoard() {
		int quantity;
		int cost;
		String name;
		String color;

		int[][] towersBonusQuantity = new int[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		String[][] towersBonusName = new String[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		int[][] towersCost = new int[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		String[][] towersPlayers = new String[game.getBoard().getNumberOfTowers()][game.getBoard().getTower(0).getNumberOfFloors()];
		
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				
				if(!game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().getResourcesMap().isEmpty()) {
					quantity = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().toArray().get(0).getQuantity();
				}
				else 
					quantity = 0;
				towersBonusQuantity[tower][floor] = quantity;
				
				
				if(!game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().getResourcesMap().isEmpty()) {
					name = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getEffectOfActionSpace().getAdditionalResources().toArray().get(0).getId();
					switch(name) {
					case "Wood" :
						towersBonusName[tower][floor] = "|     wood    |";
						break;
					case "Stones" :
						towersBonusName[tower][floor] = "|   stones    |";
						break;
					case "Money" :
						towersBonusName[tower][floor] = "|    money    |";
						break;
					case "Servants" :
						towersBonusName[tower][floor] = "|  servants   |";
						break;
					case "MilitaryPoints" :
						towersBonusName[tower][floor] = "| military pt.|";
						break;
					case "VictoryPoints" :
						towersBonusName[tower][floor] = "| victory pt. |";
						break;
					case "FaithPoints" :
						towersBonusName[tower][floor] = "|  faith pt.  |";
						break;
					};
				}
				else 
					towersBonusName[tower][floor] = "|             |";
				 
				
				cost = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getRequiredFamilyMemberValue().getValue();
				towersCost[tower][floor] = cost;
				
				
				color = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().getPlayerColor();
				switch(color) {
				case "Yellow" :
					towersPlayers[tower][floor] = "|   yellow    |";
					break;
				case "Red" :
					towersPlayers[tower][floor] = "|     red     |";
					break;
				case "Blue" :
					towersPlayers[tower][floor] = "|    blue     |";
					break;
				case "Green" :
					towersPlayers[tower][floor] = "|   green     |";
					break;
				case "" :
					towersPlayers[tower][floor] = "|      -      |";
					break;
				};
				
			}
		}
		
		output.println("THIS IS THE BOARD OF LORENZO IL MAGNIFICO:");
		output.println(" ________________________________________________________________________ ");
		output.println("|                                                                        |");
		output.println("|     GREEN TOWER      BLUE TOWER      YELLOW TOWER     PURPLE TOWER     |");
		output.println("|    _____________    _____________    _____________    _____________    |");
		output.println("|   |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |  |   FLOOR 4   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][3], towersBonusQuantity[1][3], towersBonusQuantity[2][3], towersBonusQuantity[3][3]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][3], towersBonusName[1][3], towersBonusName[2][3], towersBonusName[3][3]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Cost: %d   |  |   Cost: %d   |  |   Cost: %d   |  |   Cost: %d   |   |\n",
				towersCost[0][3], towersCost[1][3], towersCost[2][3], towersCost[3][3]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      4      |  |      8      |  |     12      |  |     16      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |  |   FLOOR 3   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][2], towersBonusQuantity[1][2], towersBonusQuantity[2][2], towersBonusQuantity[3][2]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][2], towersBonusName[1][2], towersBonusName[2][2], towersBonusName[3][2]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |  |   Cost: 5   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      3      |  |      7      |  |     11      |  |     15      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |  |   FLOOR 2   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][1], towersBonusQuantity[1][1], towersBonusQuantity[2][1], towersBonusQuantity[3][1]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][1], towersBonusName[1][1], towersBonusName[2][1], towersBonusName[3][1]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |  |   Cost: 3   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      2      |  |      6      |  |     10      |  |     14      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |=============|  |=============|  |=============|  |=============|   |");
		output.println("|   |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |  |   FLOOR 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.printf("|   |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |  |   Bonus: %d  |   |\n",
				towersBonusQuantity[0][0], towersBonusQuantity[1][0], towersBonusQuantity[2][0], towersBonusQuantity[3][0]);
		output.printf("|   %s  %s  %s  %s   |\n", towersBonusName[0][0], towersBonusName[1][0], towersBonusName[2][0], towersBonusName[3][0]);
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |    Card:    |  |    Card:    |  |    Card:    |  |    Card:    |   |");
		output.println("|   |      1      |  |      5      |  |      9      |  |     13      |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |   Player:   |  |   Player:   |  |   Player:   |  |   Player:   |   |");
		output.printf("|   %s  %s  %s  %s   |\n", towersPlayers[0][3], towersPlayers[1][3], towersPlayers[2][3], towersPlayers[3][3]);
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|                                                                        |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|           PRODUCTION SPACES                 HARVEST SPACES             |");
		output.println("|    _____________    ____(3+)_____    _____________    ____(3+)_____    |");
		output.println("|   |             |  |             |  |             |  |             |   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   | Penality: - |  | Penality: 3 |  | Penality: - |  | Penality: 3 |   |");
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|          1                2                1                2          |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|                             MARKET SPACES                              |");
		output.println("|    _____________    _____________    _____(4)_____    _____(4)_____    |");
		output.println("|   |             |  |             |  |             |  |             |   |");
		output.println("|   |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |  |   Cost: 1   |   |");
		output.println("|   |-------------|  |-------------|  |-------------|  |-------------|   |");
		output.println("|   |  Bonus: 5   |  |  Bonus: 5   |  |  Bonus: 2   |  |  Bonus: 2   |   |");
		output.println("|   |    money    |  |  servants   |  | military pt,|  |  different  |   |");
		output.println("|   |             |  |             |  |   2 money   |  |counsil priv.|   |");
		output.println("|   |_____________|  |_____________|  |_____________|  |_____________|   |");
		output.println("|          1                2                3                4          |");
		output.println("|------------------------------------------------------------------------|");
		output.println("|           COUNSIL SPACE                            DICE                |");
		output.println("|    ______________________________         _____    _____    _____      |");
		output.println("|   |         |                    |       |     |  |     |  |     |     |");
		output.println("|   |  Cost:  | Bonus: 1 counsil   |       |  " + game.getDice().get("Black").getValue() +
				"  |  |  " + game.getDice().get("White").getValue() + "  |  |  " + game.getDice().get("Orange").getValue() + "  |     |");
		output.println("|   |    1    | privilege, 1 money |       |_____|  |_____|  |_____|     |");
		output.println("|   |_________|____________________|        black    white   orange      |");
		output.println("|                                                                        |");
		output.println("|________________________________________________________________________|");
		output.println("\n");
		
		output.println("These are the cards available on the board:");
		int cardNumber = 1;
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				output.println(cardNumber + ". Tower " + (tower+1) + ", floor " + (floor+1));
				Card card = game.getBoard().getTower(tower).getTowerFloor(floor).getCard();
				if(card != null)
					output.println(card);
				else
					output.println("This card has already been taken!");
				cardNumber++;
			}
		}		
	}
	
	public int getPlayerAction() {
		output.println("Where do you want to move? (Insert the ID of the action space)");
		int move = input.IntegerFromConsole(1, 25);
		return move;
		
	}
	
	
	public void getFamilyMember() {
		output.println("Which family member do you want to use?");
		int index = 1;
		for(String key : game.getCurrentPlayer().getFamilyMembers().keySet()) {
			if(!game.getCurrentPlayer().getFamilyMembers().get(key).isUsed()) {
				output.println(index + ". " + key);
				index++;
			}
		}
		
		int choice = input.IntegerFromConsole(1, game.getCurrentPlayer().getFamilyMembers().size());
		index = 1;
		String color = null;
		for(String key : game.getCurrentPlayer().getFamilyMembers().keySet()) {
			if(index == choice) {
				color = key;
				break;
			}
			else
				index++;
		}
		
		output.println("You chose the " + color + " family member.");
		setChanged();
		notifyObservers(new EventStringInput(color, InputType.FAMILY_MEMBER_CHOICE));
	}
	
	public void getServants() {
		output.println("How many servants do you want to add to your family member?");
		int servants = input.IntegerFromConsole(0, 50);
		Integer aux = new Integer(servants);
		
		setChanged();
		notifyObservers(new EventStringInput(aux.toString(), InputType.SERVANTS_USED));
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.ACTION_SENT));
		
	}
	
	public void printPlayerStatus() {
		Player player = game.getCurrentPlayer();
		output.println("==========================================================================");
		output.println(player.getUsername() + ": it's now your turn.");
		output.println("This is what you have:");
		output.println("1. Resources:");
		output.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
		output.println("2. Territory Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().isEmpty())
			output.println("You don't have territory cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck());
		output.println("3. Character Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck().isEmpty())
			output.println("You don't have character cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getCharacterDeck());
		output.println("4. Building Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().isEmpty())
			output.println("You don't have building cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getBuildingDeck());
		output.println("5. Venture Cards:");
		if(game.getCurrentPlayer().getPersonalBoard().getVentureDeck().isEmpty())
			output.println("You don't have venture cards for now.");
		else
			output.println(game.getCurrentPlayer().getPersonalBoard().getVentureDeck());
		output.println("\n");
		//TODO print cards
	}
	
	public void updateRoundInfo() {
		output.println("==========================================================================");
		output.println("We are now playing round " + game.getCurrentRound() + " of period " + game.getCurrentPeriod() + ".");
		output.println("\n");
	}
	
	
	private void showNextTurnOrder() {
		output.println("==========================================================================");
		output.println("The game order for the next round will be:");
		int position = 1;
		
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			output.println(position + ". " + player.getUsername());
			position++;
		}
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.SET_NEXT_TURN_ORDER));
		
	}
	
}
