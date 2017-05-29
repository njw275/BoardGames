package test.unit.njw275;

import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

public class OutofBoundsRightTest {
	public static void main(String[] args) {
		Game game = new ConnectFour();
		
		/*
		 * Code to make your game interact should go here. Feel free
		 * to alter this code depending on your implementation (what
		 * is here is just an example).
		 */
		try{
			game.placeDisk(7);			
		}
		catch (GameStateException e) {
		    System.out.println("Tie!");
		   }
		catch (IllegalMoveException e) {
	    	System.out.println("Cannot place a disk there!");
	    }
    }
}