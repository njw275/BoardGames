package test.system;
import api.Chip;
import api.Game;
import api.View;
import exc.GameStateException;
import impl.game.ConnectFour;
import impl.game.Complica;
import impl.view.Console;
import javafx.application.Application;

public class ConnectFourTest {
	public static void main(String[] args) {
    	if (args[1].compareTo("console") == 0){
    		if (args[0].compareTo("connectfour") == 0){
	    		Game game = new ConnectFour();
	    		View view = new Console(game);
	    		try {
	    			view.update(game);
	    		    Chip winner = game.getWinningPlayer();
	    		    System.out.println(winner + " wins!");
	    		    System.out.println();
	    		}
	    		catch (GameStateException e) {
	    		    System.out.println("It was a tie!");
	    		}
    		}
    		else{
    			Game game = new Complica();
	    		View view = new Console(game);
	    		try {
	    			view.update(game);
	    		    Chip winner = game.getWinningPlayer();
	    		    System.out.println(winner + " wins!");
	    		    System.out.println();
	    		}
	    		catch (GameStateException e) {
	    		    System.out.println("It was a tie!");
	    		}
    		}
    	}
    	else{
    		Application.launch(impl.view.Graphical.class, args);
    	}
    }
}
