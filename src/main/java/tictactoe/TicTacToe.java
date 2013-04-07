package tictactoe;

import tictactoe.players.*;

import java.util.Scanner;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class TicTacToe {
	private static Scanner input;
	private Player PlayerX, PlayerY;
	private Board Game;

	public static void main() {
		input = new Scanner(System.in);
		String userInput1, userInput2;

		System.out.println("Welcome to Tic-Tac-Toe!");

		while(true) {
			System.out.println("Who would you like to play against?");
			System.out.println("[0] The AI.\n[1] A Human.");

			userInput1 = input.next();
			if(userInput1.equals("0") || userInput1.equals(("1"))) {
				if(userInput1.equals("0")) {
					while(true) {
						System.out.println(
								"What difficulty do you want the AI?");
						System.out.println(
								"[0] Easy.\n[1] Medium.\n[2] Difficult.");
						userInput2 = input.next();
						if(userInput2.equals("0") || userInput2.equals("1") ||
								userInput2.equals("2")) {
							new TicTacToe(userInput1, userInput2);
							break;
						} else
							System.err.println("Error: Invalid AI difficulty.");
					}
				}
				new TicTacToe(userInput1, null);
				break;
			} else
				System.err.println("Error: Invalid player option.");
		}
	}

	public TicTacToe(String otherPlayer, String difficulty) {
		this.Game = new Board();
		this.PlayerX = new HumanPlayer('X');

		if(otherPlayer.equals("0")) {
			if(difficulty.equals("0"))
				this.PlayerY = new BadAIPlayer('Y', this.Game);
			else if(difficulty.equals("1"))
				this.PlayerY = new OkayAIPlayer('Y', this.Game);
			else if(difficulty.equals("2"))
				this.PlayerY = new GoodAIPlayer('Y', this.Game);
			else
				System.err.println("Fatal Error: Invalid AI difficulty.");
		} else
			this.PlayerY = new HumanPlayer('Y');

		this.gameLoop();
	}

	private void gameLoop() {

	}
}
