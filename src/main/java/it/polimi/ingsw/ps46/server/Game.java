package it.polimi.ingsw.ps46.server;

import java.util.HashSet;


/**
 * Description of Game.
 * 
 * @author a.mondolo
 */
public class Game extends Observable {
	/**
	 * Description of the property idGame.
	 */
	private Integer idGame = Integer.valueOf(0);

	/**
	 * Description of the property nPhasesPerRound.
	 */
	public static Integer nPhasesPerRound = Integer.valueOf(0);

	/**
	 * Description of the property currentPeriod.
	 */
	private Integer currentPeriod = Integer.valueOf(0);

	/**
	 * Description of the property nPeriods.
	 */
	public static Integer nPeriods = Integer.valueOf(0);

	/**
	 * Description of the property nRoundsPerPeriod.
	 */
	public static Integer nRoundsPerPeriod = Integer.valueOf(0);

	/**
	 * Description of the property cards.
	 */
	public HashSet<Card> cards = new HashSet<Card>();

	/**
	 * Description of the property dices.
	 */
	public HashSet<Dice> dices = new HashSet<Dice>();

	/**
	 * Description of the property nrPlayer.
	 */
	private Integer nrPlayer = Integer.valueOf(0);

	/**
	 * Description of the property currentPhase.
	 */
	private Integer currentPhase = Integer.valueOf(0);

	/**
	 * Description of the property idPlayer.
	 */
	private Object idPlayer;

	/**
	 * Description of the property currentRound.
	 */
	private Integer currentRound = Integer.valueOf(0);

	/**
	 * Description of the property modeGameAdvanced.
	 */
	private Boolean modeGameAdvanced = Boolean.FALSE;

	/**
	 * Description of the property boards.
	 */
	public HashSet<Board> boards = new HashSet<Board>();

	/**
	 * Description of the property players.
	 */
	public HashSet<Player> players = new HashSet<Player>();


	/**
	 * The constructor.
	 */
	public Game() {
		super();
	}

	/**
	 * Description of the method startGame.
	 */
	public void startGame() {
	}

	/**
	 * Description of the method setupGame.
	 */
	public void setupGame() {
	}

	/**
	 * Description of the method endGame.
	 */
	public void endGame() {
	}

	/**
	 * Returns idGame.
	 * @return idGame 
	 */
	public Integer getIdGame() {
		return this.idGame;
	}

	/**
	 * Sets a value to attribute idGame. 
	 * @param newIdGame 
	 */
	public void setIdGame(Integer newIdGame) {
		this.idGame = newIdGame;
	}

	/**
	 * Returns nPhasesPerRound.
	 * @return nPhasesPerRound 
	 */
	public static Integer getNPhasesPerRound() {
		return nPhasesPerRound;
	}

	/**
	 * Sets a value to attribute nPhasesPerRound. 
	 * @param newNPhasesPerRound 
	 */
	public static void setNPhasesPerRound(Integer newNPhasesPerRound) {
		nPhasesPerRound = newNPhasesPerRound;
	}

	/**
	 * Returns currentPeriod.
	 * @return currentPeriod 
	 */
	public Integer getCurrentPeriod() {
		return this.currentPeriod;
	}

	/**
	 * Sets a value to attribute currentPeriod. 
	 * @param newCurrentPeriod 
	 */
	public void setCurrentPeriod(Integer newCurrentPeriod) {
		this.currentPeriod = newCurrentPeriod;
	}

	/**
	 * Returns nPeriods.
	 * @return nPeriods 
	 */
	public static Integer getNPeriods() {
		return nPeriods;
	}

	/**
	 * Sets a value to attribute nPeriods. 
	 * @param newNPeriods 
	 */
	public static void setNPeriods(Integer newNPeriods) {
		nPeriods = newNPeriods;
	}

	/**
	 * Returns nRoundsPerPeriod.
	 * @return nRoundsPerPeriod 
	 */
	public static Integer getNRoundsPerPeriod() {
		return nRoundsPerPeriod;
	}

	/**
	 * Sets a value to attribute nRoundsPerPeriod. 
	 * @param newNRoundsPerPeriod 
	 */
	public static void setNRoundsPerPeriod(Integer newNRoundsPerPeriod) {
		nRoundsPerPeriod = newNRoundsPerPeriod;
	}

	/**
	 * Returns cards.
	 * @return cards 
	 */
	public HashSet<Card> getCards() {
		return this.cards;
	}

	/**
	 * Returns dices.
	 * @return dices 
	 */
	public HashSet<Dice> getDices() {
		return this.dices;
	}

	/**
	 * Returns nrPlayer.
	 * @return nrPlayer 
	 */
	public Integer getNrPlayer() {
		return this.nrPlayer;
	}

	/**
	 * Sets a value to attribute nrPlayer. 
	 * @param newNrPlayer 
	 */
	public void setNrPlayer(Integer newNrPlayer) {
		this.nrPlayer = newNrPlayer;
	}

	/**
	 * Returns currentPhase.
	 * @return currentPhase 
	 */
	public Integer getCurrentPhase() {
		return this.currentPhase;
	}

	/**
	 * Sets a value to attribute currentPhase. 
	 * @param newCurrentPhase 
	 */
	public void setCurrentPhase(Integer newCurrentPhase) {
		this.currentPhase = newCurrentPhase;
	}

	/**
	 * Returns idPlayer.
	 * @return idPlayer 
	 */
	public Object getIdPlayer() {
		return this.idPlayer;
	}

	/**
	 * Sets a value to attribute idPlayer. 
	 * @param newIdPlayer 
	 */
	public void setIdPlayer(Object newIdPlayer) {
		this.idPlayer = newIdPlayer;
	}

	/**
	 * Returns currentRound.
	 * @return currentRound 
	 */
	public Integer getCurrentRound() {
		return this.currentRound;
	}

	/**
	 * Sets a value to attribute currentRound. 
	 * @param newCurrentRound 
	 */
	public void setCurrentRound(Integer newCurrentRound) {
		this.currentRound = newCurrentRound;
	}

	/**
	 * Returns modeGameAdvanced.
	 * @return modeGameAdvanced 
	 */
	public Boolean getModeGameAdvanced() {
		return this.modeGameAdvanced;
	}

	/**
	 * Sets a value to attribute modeGameAdvanced. 
	 * @param newModeGameAdvanced 
	 */
	public void setModeGameAdvanced(Boolean newModeGameAdvanced) {
		this.modeGameAdvanced = newModeGameAdvanced;
	}

	/**
	 * Returns boards.
	 * @return boards 
	 */
	public HashSet<Board> getBoards() {
		return this.boards;
	}

	/**
	 * Returns players.
	 * @return players 
	 */
	public HashSet<Player> getPlayers() {
		return this.players;
	}

}
