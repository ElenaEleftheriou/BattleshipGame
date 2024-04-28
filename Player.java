/**
 * This object creates a player that has 5 different ships, using {@link Ships},
 * in order to be able to play the game.
 * 
 * It has only one variable that is {@link Ships}, and then some methods to be
 * able to play the battleship game afterwards.
 * 
 * @author Elena Eleftheriou
 * @since 07/03/2023
 * @see Ships
 * @see Grid
 * @see BattleShipGame
 *
 */
public class Player {

	/** The ships that the player will have, using {@link Ships} */
	Ships s;

	/**
	 * The constructor of the object that creates the ships using
	 * {@link Ships#Ships()}
	 */
	public Player() {
		s = new Ships();
	}

	/**
	 * This method returns the board with the player's ships using
	 * {@link Ships#toString()}.
	 */
	public String toString() {
		return s.toString();
	}

	/**
	 * This method prints "HIT" if a ship was hit, with {@link Ships#hit(int, int)},
	 * and if a ship was sank the appropriate message by seeing the flag's value,
	 * {@link Ships#getFlag()}(true- a ship is down). Else it prints "MISS".
	 * 
	 * @param i the row of the grid
	 * @param j the column of the grid
	 * @return the appropriate message
	 */
	public String shipHit(int i, int j) {
		s.setFlag();
		if (s.hit(i, j) && !s.getFlag())
			return "HIT!\n";
		else if (s.getFlag())
			return "You just sank a ship!\n";
		return "MISS!\n";
	}

	/**
	 * This method returns the value of {@link Ships#shipsSank()}, true-if every
	 * ship of the player is down.
	 * 
	 * @return true/false
	 */
	public boolean shipSank() {
		return s.shipsSank();
	}

	/**
	 * This method returns true if a cell was hit, using
	 * {@link Ships#getCell(int, int)}, but first it checks if the given input is
	 * exit or not.
	 * 
	 * @param i the row of the grid
	 * @param j the column of the grid
	 * @param a if the input was exit or not
	 * @return true-there is a ship that was hit there/false
	 */
	public boolean getCell(int i, int j, boolean a) {
		if (a == false)
			if (s.getCell(i, j) == 'H')
				return true;
		return false;
	}

}
