package tictactoe.players;

import junit.framework.Assert;
import junit.framework.TestCase;
import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 07, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * RandomAIPlayerTest.
 * Tests the RandomAIPlayer for one game.
 */
public class RandomAIPlayerTest extends TestCase {
	private final Board board = new Board();
	private final RandomAIPlayer bad = new RandomAIPlayer('X', board, 100);

	/**
	 * Tests the chooseMove() method where this AI loses.
	 * @throws Exception
	 */
	public void testChooseMove() throws Exception {
		int[] choice = new int[2];

		choice = bad.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 1);
		board.setTile(bad.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 0);

		choice = bad.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 1);
		board.setTile(bad.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 1);

		choice = bad.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 2);
		board.setTile(bad.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 2);
	}
}
