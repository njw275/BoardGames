package test.unit.njw275;

import api.Game;
import api.Chip;
import impl.game.ConnectFour;

import static org.junit.Assert.assertTrue;

public class PlayerStartTest {
	public static void main(String[] args) {
		Game game = new ConnectFour();
		
		/*
		 * Code to make your game interact should go here. Feel free
		 * to alter this code depending on your implementation (what
		 * is here is just an example).
		 */
		System.out.print(game.getCurrentPlayer());
		assertTrue(game.getCurrentPlayer() == Chip.RED);
		

    }
}
