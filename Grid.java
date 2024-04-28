/**
 * This class sets the board that the players will play later on.The class
 * {@link Ships} inherits from this class.
 *
 * The class has a 2-D variable that later on will have the ships and the
 * character 'h' if a ship was hit. So first of all in this object everything is
 * null, since no ship was placed here.
 *
 * @author Elena Eleftheriou
 * @since 07/03/2023
 * @see Ships
 * @see Player
 * @see BattleShipGame
 *
 */
public class Grid {

	/** The cells of the board, that is 10x10. */
	private char cell[][];

	/**
	 * The constructor of the object. It creates a blank board where the ships will
	 * be placed later on.
	 */
	public Grid() {
		this.cell = new char[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				cell[i][j] = ' ';
	}

	/**
	 * Prints the gird. Using the StringBuilder named grid, it builds the board that
	 * was requested.
	 * 
	 * @return The whole grid in the requested format.
	 */
	public String toString() {
		StringBuilder grid = new StringBuilder();
		grid.append("  |");
		for (int j = 0; j < 10; j++) {
			grid.append(" " + j + " |");
		}
		for (int i = 0; i < 10; i++) {
			grid.append("\n--|");
			for (int k = 0; k < 10; k++)
				grid.append("---+");
			grid.append("\n|" + i + "|");
			for (int j = 0; j < 10; j++) {
				grid.append(" " + cell[i][j] + " |");
			}
		}
		grid.append("\n--|");
		for (int i = 0; i < 10; i++)
			grid.append("---+");
		grid.append("\n");
		return grid.toString();
	}

	/**
	 * This method sets a cell by the given number.
	 * 
	 * @param i   the row of the grid
	 * @param j   the column of the grid
	 * @param num the number of the ship
	 */
	public void setCell(int i, int j, char num) {
		cell[i][j] = num;
	}

	/**
	 * This method returns the character that is in the cell in the given position.
	 * 
	 * @param i the row of the grid
	 * @param j the column of the grid
	 * @return the character in the given position
	 */
	public char getCell(int i, int j) {
		return cell[i][j];
	}

	/*
	 * public static void main(String[] args) { Grid g = new Grid();
	 * System.out.println(g.toString()); }
	 */

}
