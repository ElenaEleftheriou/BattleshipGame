package eeleft01.hw2;

import java.util.Scanner;

/**
 * This class is where the Game will be held.
 * 
 * The code with the 2 instances of type {@link Player}, creates 2 different
 * players with each one 5 ships {@link Ships} in one grid{@link Grid}.The
 * Players enter numbers from 0 to 9 inclusive, if not gives the appropriate
 * message. The program checks if the player had hit a part of the ship and then
 * checks if the ship has sank or not. Furthermore the player that sinks all 5
 * ships of the component wins. The players also have to choice to end the game
 * by typing as an input the word "exit", the game finishes and there is no
 * winner.
 * 
 * @author Elena Eleftheriou
 * @since 07/03/2023
 * @see Grid
 * @see Player
 * @see Ships
 *
 */
public class BattleShipGame {
	/**
	 * A static variable that shows whether there is a winner yet or not.
	 */
	static boolean win = false;
	/**
	 * A static variable to check whether the user has enter the input "exit" to
	 * exit the game without finishing or not.
	 */
	static boolean flag = false;

	/**
	 * This method is where the game runs. After getting the input from the user and
	 * checking it, it gives the appropriate output if its between 0 and 9 included,
	 * or "exit" to end the game, or a number that was given before and hit a
	 * ship({@link Player#getCell(int, int, boolean)}.It uses the
	 * {@link Player#shipHit(int, int)} to check if the player hit the opponent's
	 * ship or not. After that it checks if there is a winner with
	 * {@link Player#shipSank()} and prints it.
	 * 
	 * @param pl1 the current player
	 * @param pl2 the opponent
	 * @param num the number of the player(0-for the player 1, and 1- for the player
	 *            2)
	 */
	public static void game(Player pl1, Player pl2, int num) {
		Scanner scan = new Scanner(System.in);
		String a;
		System.out.println("Player: " + num + " your ships:");
		System.out.println(pl1);
		System.out.println("Player: " + num
				+ " Please select 2 coodinates(0-9) to attack Enemy or type \"exit\" to end the game:");
		a = scan.nextLine();
		if (a.equals("exit")) {
			System.out.println("Exit game.");
			flag = true;
		} else {
			while (a.length() < 3) {
				System.out.println("Wrong input, give it again");
				a = scan.nextLine();
			}
			while (((a.charAt(0) > '9' || a.charAt(1) != ' ' || a.charAt(2) > '9' || a.charAt(0) < '0'
					|| a.charAt(2) < '0' || a.length() > 3) && !flag)
					|| (pl2.getCell(a.charAt(0) - '0', a.charAt(2) - '0', a.equals("exit")) && !flag)) {
				if ((a.charAt(0) > '9' || a.charAt(1) != ' ' || a.charAt(2) > '9' || a.charAt(0) < '0'
						|| a.charAt(2) < '0' || a.length() > 3) && !flag && !a.equals("exit"))
					System.out.println(
							"Wrong input.Please select 2 coodinates(0-9) to attack Enemy or type \"exit\" to end the game:");
				else if (pl2.getCell(a.charAt(0) - '0', a.charAt(2) - '0', a.equals("exit")) && !flag
						&& !a.equals("exit"))
					System.out.println(
							"Input given again.Please select 2 coodinates(0-9) to attack Enemy or type \\\"exit\\\" to end the game:");

				a = scan.nextLine();
				if (a.equals("exit")) {
					flag = true;
					System.out.println("Exit game.");
					scan.close();
				}
			}
		}
		if (!flag) {
			System.out.println(pl2.shipHit(a.charAt(0) - '0', a.charAt(2) - '0'));
		}
		if (pl2.shipSank()) {
			win = true;
			scan.close();
			System.out.println("WINNER IS THE:" + num);
		}

	}

	/**
	 * The main method that creates two players, {@link Player#Player()} and with a
	 * do-while runs the game. The do-while works until there is a winner or the
	 * word "exit" was given by the user.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Player pl1 = new Player();
		Player pl2 = new Player();
		System.out.println("Welcome to the BattleShip game!");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		do {
			game(pl1, pl2, 0);
			if (!flag)
				game(pl2, pl1, 1);
		} while (!flag && !win);
	}

}
