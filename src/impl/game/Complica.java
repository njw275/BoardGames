package impl.game;
import exc.GameStateException;
import exc.IllegalMoveException;
import api.Game;
import api.BoardGames;
import api.Chip;

public class Complica extends BoardGames {
	Chip test = Chip.RED;
	public boolean player = true;
	Chip winnerDL;
	Chip winnerDR;
	Chip winnerH;
	Chip winnerV;
	
	
	public Complica() {
		getGame();
	}

    /*
     * @throws GameStateException if the game is a tie
     * @throws IllegalMoveException if the move is out-of-bounds or is
     *         to a column that is not legal according to the rules of
     *         the concrete game
     */
     public void complicaPlace(int row, int col, int rows){
    	 if (rows == -1){
 			for (int r = this.getRows()-1;r>-1;r--){
 				if (r==0){
 					board[r][col] = this.getCurrentPlayer();
 				}
 				else{
	    				if (board[r-1][col] == Chip.RED){
	    					board[r][col] = Chip.RED;
	    				}
	    				else{
	    					board[r][col] = Chip.BLUE;
	    				}
 				}
 			}
 		}
     }
    
    
     public  Chip complicaGetWinner(){
    	 int blueCount = 0;
 		int redCount = 0;
 		if (winnerDL == Chip.RED){
 			redCount++;
 		}
 		else if (winnerDL == Chip.BLUE){
 			blueCount++;
 		}
 		if (winnerDR == Chip.RED){
 			redCount++;
 		}
 		else if (winnerDR == Chip.BLUE){
 			blueCount++;
 		}
 		if (winnerH == Chip.RED){
 			redCount++;
 		}
 		else if (winnerH == Chip.BLUE){
 			blueCount++;
 		}
 		if (winnerV == Chip.RED){
 			redCount++;
 		}
 		else if (winnerV == Chip.BLUE){
 			blueCount++;
 		}
 		return (blueCount > redCount) ? Chip.BLUE : Chip.RED;
     }

	@Override
	public Chip connectFourGetWinner() {
		return null;
	}

	@Override
	public boolean connectFourFullBoard() {
		return false;
	}
}


