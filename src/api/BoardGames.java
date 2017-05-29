package api;

import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.Complica;

public abstract class BoardGames extends Game {

	public Chip[][] board;
	public boolean player = true;
	public static int numrows;
	public static int numcols;
	public boolean isComplica;
	public int rows = 0;
	
	public void getGame(){
		if (this instanceof Complica){
			numrows = 6;
			numcols = 4;
			board = new Chip[this.getRows()][this.getColumns()];
			isComplica = true;
		}else{
			numrows = 6;
			numcols = 7;
			board = new Chip[this.getRows()][this.getColumns()];
			isComplica = false;
		}
		for (int i=0;i<this.getRows();i++){
    		for (int j=0;j<this.getColumns();j++){
    			board[i][j] = Chip.EMPTY;
    		}
    	}
	}
	
    public int getRows(){
    	return numrows;
    }
    public int getColumns(){
    	return numcols;
    }
    public Chip[][] getBoard(){
    	return board;
    }
    
    public void placeDisk(int row, int col) throws GameStateException, IllegalMoveException {
    	checkExceptions(row, col);
    	placing(row, col);
    	if (isComplica){
    		complicaPlace(row, col, rows);
    	}
    	if (isGameOver()){
    		getWinningPlayer();
    	}
    	changePlayer();
    }
    
    public  Chip getWinningPlayer() throws GameStateException{
    	Chip winner;
    	if (this.isGameOver()){
        	if (checkBoardFilled() != this.getRows()*this.getColumns()){
        		if (isComplica){
        			winner = complicaGetWinner();
        		}else{
        			winner = connectFourGetWinner();
        		}
        	}
        	else{
        		throw new GameStateException();
        	}
    	}
    	else{
    		throw new GameStateException();
    	}
    	return winner;
    }
    
    public Chip getCurrentPlayer(){
    	if (player){
    		return Chip.RED;
    	}
    	else{
    		return Chip.BLUE;
    	}
    }
    
    
    public boolean isGameOver(){
    	int finished = 0;
    	boolean draw = false;
    	int a = checkWinDiagonal(finished);
    	int b = checkWinDiagonalLeft(finished);
    	int c = checkWinHorizontal(finished);
    	int d = checkWinVertical(finished);
    	finished = a+b+c+d;
    	if (!isComplica){
    		draw = connectFourFullBoard();
    	}
//    			if (board[row][col] == Chip.EMPTY){
//    				finished = false;
//    				break;
//    			}
//    			if (){
//    				
//    			} 
    	if (draw){
    		return true;
    	}
    	System.out.println(finished);
    	return (finished%2 != 0) ? true : false;
    }
    
    
    void checkExceptions(int row, int col) throws GameStateException, 
    IllegalMoveException{
    	if (row != 0){
    		throw new IllegalMoveException();
    	}
    	if (col > this.getColumns()-1 || col < 0){
    		throw new IllegalMoveException();
    	}
    }
    
    void placing(int row, int col) throws IllegalMoveException{
    	rows = this.getRows() - 1;
		while (rows>-1){
			if (board[rows][col] != Chip.EMPTY){
				rows--;
			}
			else{
				break;
			}
		}
		if (rows>-1){
			if (player){
				board[rows][col] = Chip.RED;
			} 
			else{
				board[rows][col] = Chip.BLUE;
			}
		}
		else if (!isComplica && rows<1){
			throw new IllegalMoveException();
		}
		
    }
    
    void changePlayer(){
    	player = !player;
    }

    
    int checkBoardFilled(){
    	int counter = 0;
    	for(int row=0;row<this.getRows();row++){
    		for(int col=0;col<this.getColumns();col++){
    			if(board[row][col] != Chip.EMPTY){
    				counter++;
    			}
    		}
    	}
    	return counter;
    }
    
    int checkWinDiagonal(int finished){
    	for(int row=0;row<this.getRows();row++){
    		for(int col=0;col<this.getColumns();col++){
    			if ((row+3) < this.getRows() &&
    				(col+3) < this.getColumns()){
    					if (board[row][col] != Chip.EMPTY &&
    	    					board[row][col] == board[row+1][col+1] && 
    			    			board[row][col] == board[row+2][col+2] &&
    			    			board[row][col] == board[row+3][col+3]){
    			    					finished++;	
    	    			}
    			}
    		}
    	}
    	return finished;
    }
    
    int checkWinDiagonalLeft(int finished){
    	for(int row=0;row<this.getRows();row++){
    		for(int col=0;col<this.getColumns();col++){
		    	if ((row-3) > -1 &&
						(col+3) < this.getColumns()){
							if (board[row][col] != Chip.EMPTY &&
			    					board[row][col] == board[row-1][col+1] && 
					    			board[row][col] == board[row-2][col+2] &&
					    			board[row][col] == board[row-3][col+3]){
					    					finished++;	
			    			}
					}
    		}
    	}
    	return finished;
    }
    
    int checkWinHorizontal(int finished){
    	for(int row=0;row<this.getRows();row++){
    		for(int col=0;col<this.getColumns();col++){
		    	if (col<=this.getColumns()-4){
					if (board[row][col] != Chip.EMPTY &&
						board[row][col] == board[row][col+1] && 
		    			board[row][col] == board[row][col+2] &&
		    			board[row][col] == board[row][col+3]){
		    					finished++;
		    			
					}
				}
    		}
    	}
    	return finished;
    }
    
    int checkWinVertical(int finished){
    	for(int row=0;row<this.getRows();row++){
    		for(int col=0;col<this.getColumns();col++){
		    	if (row <=this.getRows()-4){
					if (board[row][col] != Chip.EMPTY &&
						board[row][col] == board[row+1][col] && 
						board[row][col] == board[row+2][col] &&
						board[row][col] == board[row+3][col]){
						System.out.println("r: "+row+" c:"+col);
						finished++;
					}
				}
    		}
    	}
    	return finished;
    }
    
   
    	
    
    public abstract void complicaPlace(int row, int col, int rows);
    
    public abstract Chip connectFourGetWinner();
    public abstract Chip complicaGetWinner();
    
    public abstract boolean connectFourFullBoard();
    	
}

