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
 * OkayAIPlayerTest.
 * Tests the OkayAIPlayer for two games.
 */
public class OkayAIPlayerTest extends TestCase {
	private final Board board = new Board();
	private OkayAIPlayer okay;

	/**
	 * Tests the chooseMove() method where this AI wins.
	 * @throws Exception
	 */
	public void testChooseMoveWin() throws Exception {
		int[] choice = new int[2];
		okay = new OkayAIPlayer('X', board, 3, 3, 100);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 1);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 0);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 0);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 1);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 1);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 2);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 2);
		board.setTile(okay.getID(), choice[0], choice[1]);
	}

	/**
	 * Tests the chooseMove() method where this AI loses.
	 * @throws Exception
	 */
	public void testChooseMoveLose() throws Exception {
		int[] choice = new int[2];
		okay = new OkayAIPlayer('X', board, 3, 3, 50);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 1);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 0, 0);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 0 && choice[1] == 1);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 1, 0);

		choice = okay.chooseMove();
		Assert.assertTrue(choice[0] == 0 && choice[1] == 2);
		board.setTile(okay.getID(), choice[0], choice[1]);
		board.setTile('O', 2, 0);
	}
}
