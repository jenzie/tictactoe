package tictactoe;

import tictactoe.players.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * TicTacToe.
 * Implementation of the tic-tac-toe game with a human player and multiple AIs.
 */
public class TicTacToe {
	private static Scanner input;
	private static Player PlayerX, PlayerO;
	private static Board game;

	/**
	 * Runs/starts the program.
	 * @param args optional arguments when running the program.
	 */
	public static void main(String[] args) {
		input = new Scanner(System.in);
		String userInput1, userInput2;

		System.out.println("Welcome to Tic-Tac-Toe!");

		while (true) {
			System.out.println("Who would you like to play against?");
			System.out.println("[0] The AI.\n[1] A Human.");

			// get type of the other player
			userInput1 = input.next();
			if (userInput1.equals("0") || userInput1.equals(("1"))) {

				// if other player is an AI, choose difficulty of the AI
				if (userInput1.equals("0")) {
					while (true) {
						System.out.println(
								"What difficulty do you want the AI?");
						System.out.println(
								"[0] Easy.\n[1] Medium.\n[2] Difficult.");

						userInput2 = input.next();
						if (userInput2.equals("0") || userInput2.equals("1") ||
								userInput2.equals("2")) {
							TicTacToe tictactoe =
									new TicTacToe(userInput1, userInput2);

							// play the game using those two players
							tictactoe.gameLoop();

							return;
						} else
							System.err.println("Error: Invalid AI difficulty.");
					}
				} // otherwise, make two human players
				else {
					TicTacToe tictactoe = new TicTacToe(userInput1, null);

					// play the game using those two players
					tictactoe.gameLoop();

					return;
				}
			} else
				System.err.println("Error: Invalid player option.");
		}
	}

	/**
	 * Constructor; creates the players.
	 * @param otherPlayer type of the other player
	 * @param difficulty difficulty of the AI, if otherPlayer is an AI
	 */
	public TicTacToe(String otherPlayer, String difficulty) {
		this.game = new Board();
		
		// create the first player as a human
		this.PlayerX = new HumanPlayer('X', this.game);

		// create the second player based on user input
		if (otherPlayer.equals("0")) {
			if (difficulty.equals("0"))
				this.PlayerO = new RandomAIPlayer('O', this.game);
			else if (difficulty.equals("1"))
				this.PlayerO = new OkayAIPlayer('O', this.game,
						this.game.getLength(), this.game.getWidth());
			else if (difficulty.equals("2"))
				this.PlayerO = new GoodAIPlayer('O', this.game,
						this.game.getLength(), this.game.getWidth());
			else
				System.err.println("Fatal Error: Invalid AI difficulty.");
		}
		// create second player to be a human, as selected by user input
		else
			this.PlayerO = new HumanPlayer('O', this.game);
	}

	/**
	 * Simulates the actual game after players have been chosen.
	 */
	private void gameLoop() {
		int[] choice = new int[2];
		boolean madeMove = false;
		Queue<Player> players = new LinkedList<Player>();
		players.add(PlayerX);
		players.add(PlayerO);
		Player curPlayer;

		// alternate between players using a queue until a win/tie
		while(true) {
			// dequeue first player in the line
			curPlayer = players.poll();
			System.out.println(
					"Player " + curPlayer.getID() + " chooses a move.");
			choice = curPlayer.chooseMove();
			madeMove = game.setTile(curPlayer.getID(), choice[0], choice[1]);
			System.out.println(game.toString());

			// if move was successful, check if win, tie, or nothing
			if(madeMove) {
				String gameStatus =
						isOver(choice[0], choice[1], curPlayer.getID(),
								game.getBoard(), game.getValidMoves().length);
				if(gameStatus == null) {
					players.add(curPlayer);
				} else if(gameStatus.equals("tie")) {
					System.out.println(
							this.gameOver(null, false, null, choice));
					break;
				} else {
					System.out.println(
							this.gameOver(curPlayer, true, gameStatus, choice));
					break;
				}
			}
			// if move was unsuccessful, have player make move again
			else {
				// get this player back in the front of the queue
				players.add(curPlayer);
				curPlayer = players.poll();
				System.out.print("curr:" + curPlayer.getID());
				players.add(curPlayer);
				System.err.println("Error: Invalid move.");
			}
		}
	}

	/**
	 * Gets the current status of the game: win, tie, or nothing.
	 * @param lastX row/x-coordinate of the last move made.
	 * @param lastY column/y-coordinate of the last move made.
	 * @param lastPlayer identification of the last player to move.
	 * @param board the game.
	 * @param validMoves list of empty tiles.
	 * @return if player has won, return type of win (row, column, diagonal);
	 * 			if tie, return tie; if nothing, return null.
	 */
	public static String isOver(int lastX, int lastY, Character lastPlayer,
								Character[][] board, int validMoves) {
		int winLength = Math.min(board.length, board[0].length);
		int currX = lastX;
		int currY = lastY;
		int total = 0;

		// check adjacent tiles
		for(int moveX = -1; moveX <= 1; moveX++) {
			for(int moveY = -1; moveY <= 0; moveY++) {
				currX = lastX;
				currY = lastY;

				if((moveX == 0 && moveY == 0) || (moveX == 1 && moveY == 0))
					continue;

				// back up in the check
				while(game.validRange(currX, currY) //check if match
						&& board[currX][currY] == lastPlayer) {
					currX += moveX;
					currY += moveY;
				}
				currX -= moveX;
				currY -= moveY;
				total = 0; //reset total

				// move forwards in the check
				while(total < winLength && game.validRange(currX, currY)
						&& board[currX][currY] == lastPlayer) {
					total += 1;
					currX -= moveX;
					currY -= moveY;
				}

				//check if won and with what direction
				if(total >= winLength) {
					if(moveX == 0)
						return "row";
					if(moveY == 0)
						return "column";
					if(moveX != 0 && moveY != 0)
						return "diagonal";
				}
			}
		}

		// tie if no more moves left and not a win
		if(validMoves == 0)
			return "tie";
		return null;
	}

	/**
	 * @param player identification of the last player.
	 * @param win true if it was a win, false if a tie.
	 * @param winType type of win; row, column, diagonal.
	 * @param lastMove row/column of last move.
	 * @return message indicating the game is over.
	 */
	private String gameOver(
			Player player, boolean win, String winType, int[] lastMove) {
		if(win && winType.equals("row"))
			return "Game over!\nPlayer " + player.getID() + " has won on " +
					winType + " " + lastMove[0] + "!\n";
		else if(win && winType.equals("column"))
			return "Game over!\nPlayer " + player.getID() + " has won on " +
					winType + " " + lastMove[0] + "!\n";
		else if(win && winType.equals("diagonal"))
			return "Game over!\nPlayer " + player.getID() + " has won on a " +
					winType + "!\n";
		return "Game over!\nThere is a tie!\n";
	}

	/**
	 * @param knownID identification of the player who knows its own id.
	 * @return identification of the other player.
	 */
	public static Character getOtherPlayer(Character knownID) {
		// return 'O' for testing purposes
		if(!(PlayerX instanceof Player) && !(PlayerO instanceof Player))
			return 'O';

		// return the identification of the other player
		if(PlayerX.getID() == knownID){
			return PlayerO.getID();
		} return PlayerX.getID();
	}

	/**
	 * For testing purposes only.
	 * @return the other player for GoodAIPlayer's tests.
	 */
	public Player getPlayerO() {
		return PlayerO;
	}
}
