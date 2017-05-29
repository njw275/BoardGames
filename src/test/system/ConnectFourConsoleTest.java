package test.system;

import api.View;
import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Console;
import impl.game.ConnectFour;

public class ConnectFourConsoleTest {
    public static void main(String[] args) {
	Game game = new ConnectFour();
	
	/*
	 * Code to make your game interact should go here. Feel free
	 * to alter this code depending on your implementation (what
	 * is here is just an example).
	 */
	View view = new Console(game);
	view.update(game);
	try {
		view.update(game);
	    Chip winner = game.getWinningPlayer();
	    System.out.println(winner + " wins!");
	    System.out.println();
//		System.out.println("t");
//        game.placeDisk(game.getColumns() - 1);
	}
	catch (GameStateException e) {
	    System.out.println("It was a tie!");
	}
    }
}
