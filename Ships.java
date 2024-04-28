/**
 * This object creates the ships that are required for each player to be able to
 * play the game. It inherits from {@link Grid}.
 * 
 * The object has 5 different boolean variables that each one is a different
 * ship, and if they are false then it means that they were hit. Also it has a
 * variable that saves the position of the first ship with length 3, so that we
 * will be able to know which is which, the variable shipsSank that counts how
 * many ships are down and the variable flag that helps us know if a ship was
 * hit. It also has methods that help it so that the game battleship will be
 * able to run.
 * 
 * @author Elena Eleftheriou
 * @since 07/03/2023
 * @see Grid
 * @see Player
 * @see BattleShipGame
 *
 */
public class Ships extends Grid {

	/** The ship with length 5. */
	private boolean carrier[];
	/** The ship with length 4. */
	private boolean destroyer[];
	/** The first ship with length 3. */
	private boolean frigate[];
	/** The second ship with length 3. */
	private boolean corvette[];
	/** The ship with length 2. */
	private boolean submarine[];
	/**
	 * The 2-D array that saves the position of the first ship with length 3.
	 */
	private int posFrigate[][];
	/**
	 * The variable that counts how many ships are down.
	 */
	private int shipsSank;
	/**
	 * A variable that shows whether a ship was sank or not.
	 */
	private boolean flag;

	/**
	 * The constructor of the object. It uses the constructor of
	 * Grid,{@link Grid#Grid()}, to create a blank board. Then it its own variables
	 * and puts the ships into place, using {@link #position(int)}. Also it sets the
	 * first place of the 2-D array as -1 to know if it is the first ship or not.
	 */
	public Ships() {
		super();
		this.carrier = new boolean[5];
		this.destroyer = new boolean[4];
		this.frigate = new boolean[3];
		this.corvette = new boolean[3];
		this.submarine = new boolean[2];
		this.posFrigate = new int[3][3];
		shipsSank = 0;
		flag = false;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				posFrigate[i][j] = 0;
		posFrigate[0][0] = -1;

		position(carrier.length);
		position(destroyer.length);
		position(frigate.length);
		position(corvette.length);
		position(submarine.length);
	}

	/**
	 * Puts the ships at a random place. After making sure that the ships can fit in
	 * the board, horizontally or vertically depending on the variable side(1 is
	 * right and 0 is down), it uses the {@link Grid#setCell(int, int, char)} to
	 * change the cell as the number of the ship. Also it saves the position of the
	 * first ship with length 3 using {@link #setPosFrigate(int, int, int)}.
	 * 
	 * @param length the length of each ship/the number that will be imported in the
	 *               cell
	 */

	public void position(int length) {
		int i, j;
		int c = 0;
		int side;
		do {
			i = (int) (Math.random() * 10);
			j = (int) (Math.random() * 10);
			side = (int) (Math.random() * 2);
		} while (!FreeSpace(length, i, j, side));
		while (c < length) {
			if (side == 1) {
				super.setCell(i, j, (char) (length + 48));
				j++;
			} else {
				super.setCell(i, j, (char) (length + 48));
				i++;
			}
			c++;
		}
		if (length == 3 && posFrigate[0][0] == -1) {
			if (side == 1)
				setPosFrigate(i, j - 3, side);
			else
				setPosFrigate(i - 3, j, side);
		}
	}

	/**
	 * Checks if there is enough space for the ship to be placed in. This method
	 * depending on the side it checks if there is enough space inside the grid to
	 * fit the given ship, or if there is another ship in those cells using
	 * {@link Grid#getCell(int, int)}, which is not allowed.
	 * 
	 * @param length the length of the ship.
	 * @param i      the position of the row where the ship will start.
	 * @param j      the position of the column where the ship will start.
	 * @param side   the direction that the ship will go on.
	 * @return returns true if there is enough space for the ship to be placed and
	 *         false if not.
	 */
	private boolean FreeSpace(int length, int i, int j, int side) {
		if (side == 1) {
			if (j + (length - 1) >= 9)
				return false;
		} else {
			if (i + (length - 1) >= 9)
				return false;
		}
		if (side == 1) {
			for (int c = j; c <= j + (length - 1); c++)
				if (super.getCell(i, c) != ' ')
					return false;
		} else {
			for (int r = i; r <= i + (length - 1); r++)
				if (super.getCell(r, j) != ' ')
					return false;
		}
		return true;
	}

	/**
	 * This method depending on the side, saves the position of the first ship with
	 * length 3, so that we will use it later on to know which ship is which.
	 * 
	 * @param i    the starting position of the ship in row
	 * @param j    the starting position of the ship in column
	 * @param side which side the ship will be at
	 */
	public void setPosFrigate(int i, int j, int side) {
		posFrigate[0][2] = side;
		if (side == 1) {
			for (int x = 0; x < 3; x++) {
				posFrigate[x][0] = i;
				posFrigate[x][1] = j + x;
			}
		} else {
			for (int x = 0; x < 3; x++) {
				posFrigate[x][1] = j;
				posFrigate[x][0] = x + i;

			}
		}
	}

	/**
	 * The method checks if a ship was hit or not, using
	 * {@link Grid#getCell(int, int)}. It also uses {@link #shipHit(int, int)} to
	 * make changes in the board.
	 * 
	 * @param i the row of the grid that was chosen
	 * @param j the column of the grid that was chosen
	 * @return true- if the ship was hit/false- if there was not a ship in that
	 *         position
	 */
	public boolean hit(int i, int j) {
		if (super.getCell(i, j) == ' ')
			return false;
		shipHit(i, j);
		return true;
	}

	/**
	 * This method uses {@link #shipHit(int, int, int)} to change the variable of
	 * the ship that was hit, and sets the cell as 'H', using
	 * {@link Grid#setCell(int, int, char)}.
	 * 
	 * @param i the row of the grid
	 * @param j the column of the grid
	 */
	public void shipHit(int i, int j) {
		shipHit(i, j, super.getCell(i, j) - '0');
		super.setCell(i, j, 'H');
	}

	/**
	 * This method after checking which ship was hit, it uses
	 * {@link #shipDown(boolean[])} to change it's value. Also, if the length was 3
	 * it fist checks which one of the ship was, using {@link #firstShip(int, int)}.
	 * 
	 * @param i      the row of the grid
	 * @param j      the column of the grid
	 * @param length the length of the ship
	 */
	public void shipHit(int i, int j, int length) {
		if (length == 5)
			shipDown(carrier);
		else if (length == 4)
			shipDown(destroyer);
		else if (length == 3) {
			if (firstShip(i, j))
				shipDown(frigate);
			else
				shipDown(corvette);
		} else if (length == 2)
			shipDown(submarine);
	}

	/**
	 * This method makes the next true value of the array into false, until
	 * everything is false, which means the ship is down and the variable shipsSank
	 * adds one. Also, if a ship was sank then the flag becomes true.
	 * 
	 * @param ship the ship that was hit.
	 */
	public void shipDown(boolean ship[]) {
		flag = false;
		int e = 0;
		while (e < ship.length - 1 && ship[e] == true)
			e++;
		if (e < ship.length)
			ship[e] = true;
		if (e == ship.length - 1) {
			shipsSank++;
			flag = true;
		}
	}

	/**
	 * This method checks if the ship in the given position was the first one with
	 * length 3 or not. In the position x-0 and y-2 in the 2-D array it is saved the
	 * side of the ship.
	 * 
	 * @param i the row of the grid
	 * @param j the column of the grid
	 * @return true-if is the first ship/false- if is the second ship
	 */
	public boolean firstShip(int i, int j) {
		if (posFrigate[0][2] == 1) {
			for (int x = 0; x < 3; x++)
				if (posFrigate[x][0] == i && posFrigate[x][1] == j)
					return true;
		}

		else
			for (int x = 0; x < 3; x++)
				if (posFrigate[x][0] == i && posFrigate[x][1] == j)
					return true;
		return false;

	}

	/**
	 * This method checks if all the ships are sank.
	 * 
	 * @return true-if all the ships are sank/false
	 */
	public boolean shipsSank() {
		if (shipsSank == 5)
			return true;
		return false;
	}

	/**
	 * This method returns the value of flag.
	 * 
	 * @return true-if a ship was sank/false
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * this method sets the value of flag as false.
	 */
	public void setFlag() {
		flag = false;
	}

	/**
	 * This method uses {@link Grid#toString()} to return the board with ships.
	 */
	public String toString() {
		return super.toString();
	}
	/*
	 * public static void main(String[] args) { Ships s = new Ships();
	 * System.out.println(s.toString()); }
	 */
}
