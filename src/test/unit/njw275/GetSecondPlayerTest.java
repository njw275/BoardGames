package test.unit.njw275;

import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import static org.junit.Assert.assertTrue;

public class GetSecondPlayerTest {
	public static void main(String[] args) {
		Game game = new ConnectFour();
		
		/*
		 * Code to make your game interact should go here. Feel free
		 * to alter this code depending on your implementation (what
		 * is here is just an example).
		 */
		try{
			game.placeDisk(0);
			System.out.print(game.getCurrentPlayer());
			assertTrue(game.getCurrentPlayer() == Chip.BLUE);
		}
		catch (GameStateException e){
			System.out.println("Game is tied!");
		}
		catch(IllegalMoveException e) {
			System.out.println("You cant place a disk there!");
		}
		
		

    }
}