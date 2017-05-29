package impl.view;
import api.View;
import exc.GameStateException;
import api.Game;
import api.BoardGames;
import api.Chip;
import impl.game.ConnectFour;
import impl.game.Complica;
import java.util.Scanner;
import java.util.Observable;
import exc.IllegalMoveException;

public class Console extends View {
    /*
     * This is a convenience method for the update implementation
     * required by the Observer interface.
     */
	Observable observable;
	BoardGames game;
	Scanner sc = new Scanner(System.in);
	
	public Console(Observable observable){
		this.observable = observable;
		observable.addObserver(this);
		this.game = (BoardGames)observable;	
		game.getGame();
	}
	
    public void render(Game game){
    	System.out.println(" ");

    	for (int i=0;i<game.getRows();i++){
    		for(int j=0; j<game.getColumns(); j++) {
    	        System.out.print("+---");
    	    }
    	    System.out.println("+");
    	    
    	    System.out.print("|");
    	    for(int j=0; j<game.getColumns(); j++) {
    	    	if (game.getBoard()[i][j] == Chip.RED){
	    	        System.out.print(" R ");
	    	        System.out.print("|");
    	    	}
    	    	else if (game.getBoard()[i][j] == Chip.BLUE){
    	    		System.out.print(" B ");
 	    	        System.out.print("|");
    	    	}
    	    	else{
    	    		System.out.print("   ");
 	    	        System.out.print("|");
    	    	}
    	    }
    	    System.out.println();
    	    if ((i+1) == game.getRows()){
    	    	for(int j=0; j<game.getColumns(); j++) {
        	        System.out.print("+---");
        	    }
        	    System.out.println("+");
    	    }
    	}
    	System.out.println(" ");
    }

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ConnectFour){
			ConnectFour connectFour = (ConnectFour)o;
			while (!connectFour.isGameOver()){
				try{
					this.render(connectFour);
					System.out.print(connectFour.getCurrentPlayer() + ", what column would you like to drop your "+
					"chip into?: ");
					int column = sc.nextInt();
					connectFour.placeDisk(column);
				}
				catch (IllegalMoveException e) {
				    System.out.println("That column is full or out of range");
				}
				catch (GameStateException e){
					System.out.println("The game ends in a draw!");
				}
			}	
			this.render(connectFour);
		}
		if (o instanceof Complica){
			Complica complica = (Complica)o;
			while (!complica.isGameOver()){
				try{
					
					this.render(complica);
					System.out.print(complica.getCurrentPlayer() + ", what column would you like to drop your "+
					"chip into?: ");
					int column = sc.nextInt();
					complica.placeDisk(column);
				}
				catch (IllegalMoveException e) {
				    System.out.println("That column is full or out of range");
				}
				catch (GameStateException e){
					System.out.println("The game ends in a draw!");
				}
			}	
			this.render(complica);
		}
	}
}

