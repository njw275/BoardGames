package test.unit.njw275;

import api.Game;
import api.Chip;
import impl.game.ConnectFour;

import static org.junit.Assert.assertTrue;

public class EmptyBoardTest {
	public static void main(String[] args) {
		Game game = new ConnectFour();
		
		/*
		 * Code to make your game interact should go here. Feel free
		 * to alter this code depending on your implementation (what
		 * is here is just an example).
		 */
		for (int i=0;i<game.getRows();i++){
			for (int j=0;j<game.getColumns();j++){
				assertTrue(game.getBoard()[i][j] == Chip.EMPTY);
			}
		}
		System.out.print("Board is initialized as empty");
    }
}