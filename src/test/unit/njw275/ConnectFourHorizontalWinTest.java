package test.unit.njw275;

import api.View;
import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Console;
import impl.game.ConnectFour;

import static org.junit.Assert.assertTrue;

public class ConnectFourHorizontalWinTest {
    public static void main(String[] args) {
		Game game = new ConnectFour();
		
		/*
		 * Code to make your game interact should go here. Feel free
		 * to alter this code depending on your implementation (what
		 * is here is just an example).
		 */
		View view = new Console(game);
		try{
			int order[] = {0,0,1,1,2,2,3};
			for (int i=0;i<order.length;i++){
				game.placeDisk(order[i]);
				
			}
			view.update(game);
			Chip winner = game.getWinningPlayer();
			System.out.println(winner + " wins!");
		    System.out.println();
			assertTrue(game.isGameOver());
		    
	    }
	    catch (GameStateException e) {
	    	System.out.println("Tie!");
	    }
		catch (IllegalMoveException e) {
	    	System.out.println("Cannot place a disk there!");
	    }
    }
}
