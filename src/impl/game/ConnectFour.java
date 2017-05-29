package impl.game;
import exc.GameStateException;
import exc.IllegalMoveException;
import api.Game;
import api.BoardGames;
import api.Chip;

public class ConnectFour extends BoardGames {

	Chip test = Chip.RED;
	public boolean player = true;
	
	public ConnectFour() {
		getGame();
	}

	public Chip connectFourGetWinner(){
		return (this.getCurrentPlayer() == Chip.RED) ? Chip.BLUE : Chip.RED;
	}
    
    public boolean connectFourFullBoard(){
    	boolean finished = false;
	    int counter = 0;
		for(int row=0;row<this.getRows();row++){
			for(int col=0;col<this.getColumns();col++){
				if(board[row][col] != Chip.EMPTY){
					counter++;
				}
			}
		}
		if (counter == this.getRows()*this.getColumns()){
			finished = true;
		}
		return finished;
    }


	@Override
	public void complicaPlace(int row, int col, int rows) {}


	@Override
	public Chip complicaGetWinner() {
		return null;
	}
    
    
}