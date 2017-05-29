package impl.view;

import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import api.BoardGames;
import api.Chip;
import impl.game.ConnectFour;
import impl.game.Complica;
import javafx.scene.Node;

public class Graphical extends Application /*implements Observer*/ {
	
	
//	Observable observable;
//	Game game;
//	Scanner sc = new Scanner(System.in);
//	
//	public Graphical(Observable observable){
//		this.observable = observable;
//		observable.addObserver(this);
//		this.game = (Game)observable;	
//	}

	BoardGames game;
	GridPane gridPane = new GridPane();
	boolean continueRender = true;
	String gameType;

	public void init() throws Exception {

        Parameters parameters = getParameters();
        List<String> listParameters = parameters.getUnnamed();
        gameType = listParameters.get(0);
        //System.out.print(gameType);
        if (gameType.compareTo("complica") == 0){
        	this.game = new Complica();
        }
        else{
        	this.game = new ConnectFour();
        }
        game.getGame();
	}
	
	
//	public Graphical() {
		//System.out.println(gameType);
//		if (gameType.compareTo("connectfour") == 0){
//			this.game = new ConnectFour();
//		}
//		else{
			//this.game = new Complica();
//		}
//    }
	
	private Circle createChip(){
		Circle circle = new Circle(50,50,40,Color.WHITE);
		circle.setStroke(Color.BLACK);
		return circle;
	}
	
	private Circle redChip(){
		Circle circle = new Circle(50,50,40,Color.RED);
		circle.setStroke(Color.BLACK);
		return circle;
	}
	
	private Circle blueChip(){
		Circle circle = new Circle(50,50,40,Color.BLUE);
		circle.setStroke(Color.BLACK);
		return circle;
	}
	
	private void display(){
		for (int i=0;i<game.getRows();i++){
			for (int j=0;j<game.getColumns();j++){
				if (game.getBoard()[i][j] == Chip.RED){
					gridPane.add(redChip(),j,i);
				}
				else if (game.getBoard()[i][j] == Chip.BLUE){
					gridPane.add(blueChip(),j,i);
				}
				else{
					gridPane.add(createChip(),j,i);
				}
				
			}
		}
	}
	
	private Text toText(String message, int font){
		Text text = new Text(message);
		text.setFont(Font.font(font)); 
		return text;
	}
	
	public void start(Stage stage){
		
		BorderPane border = new BorderPane();
		HBox hbox = new HBox();

		Circle winningPlayer = createChip();
		
		
		
		Image img = new Image("http://sofaloca.com/themes/ypanel/ionicons/png/512/chevron-down.png");
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(100);
		imgView.setFitWidth(82);
		imgView.setPreserveRatio(true);
		hbox.getChildren().add(imgView);
		
		for (int i=0;i<game.getColumns()-1;i++){
			img = new Image("http://sofaloca.com/themes/ypanel/ionicons/png/512/chevron-down.png");
			imgView = new ImageView(img);
			imgView.setFitHeight(100);
			imgView.setFitWidth(82);
			imgView.setPreserveRatio(true);
			hbox.getChildren().add(imgView);
		}
		
		VBox vbox2 = new VBox();
		Rectangle background2 = new Rectangle(80*(game.getColumns()+1)+20,100,Color.WHITE);
		
		StackPane layout2 = new StackPane();
		vbox2.getChildren().add(layout2);
        layout2.getChildren().addAll(
                background2,
                hbox
        );
        layout2.setMaxWidth(game.getColumns()*80);
		
		border.setTop(layout2);
		
		Rectangle background = new Rectangle(100,486,Color.WHITE);

		border.setRight(vbox2);
		
		StackPane layout3 = new StackPane();
		
		
		vbox2.getChildren().add(layout3);
        layout3.getChildren().addAll(
                background,
                winningPlayer,
                toText("Winner is...",12)
        );
        
//        vbox2.setMinWidth(game.getColumns()*80);
        
		border.setCenter(gridPane);

		
		gridPane.setGridLinesVisible(true);
		Scene scene = new Scene(border);

		scene.setFill(Color.TAN);
		
		for (int i=0;i<game.getRows();i++){
			for (int j=0;j<game.getColumns();j++){
				Node chip = createChip();
				gridPane.add(chip,j,i);
			}
		}
		
		
		hbox.setOnMouseExited(e -> {
			display();
		});
		
			hbox.setOnMouseMoved(e -> {
				if (continueRender){
					if (0 < e.getX() && e.getX() < 80){ //100 - 180
						display();
						if (game.getBoard()[0][0] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 0, 0);
						}
						else{
							gridPane.add(blueChip(), 0, 0);
						}
					}
					else if (80 < e.getX() && e.getX() < 160){ //180-260
						display();
						if (game.getBoard()[0][1] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 1, 0);
						}
						else{
							gridPane.add(blueChip(), 1, 0);
						}
					}
					else if (160 < e.getX() && e.getX() < 240){ //260-340
						display();
						if (game.getBoard()[0][2] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 2, 0);
						}
						else{
							gridPane.add(blueChip(), 2, 0);
						}
					}
					else if (240 < e.getX() && e.getX() < 320){ //340-420
						display();
						if (game.getBoard()[0][3] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 3, 0);
						}
						else{
							gridPane.add(blueChip(), 3, 0);
						}
					}
					else if (320 < e.getX() && e.getX() < 400){ //420-500
						display();
						if (game.getBoard()[0][4] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 4, 0);
						}
						else{
							gridPane.add(blueChip(), 4, 0);
						}
					}
					else if (400 < e.getX() && e.getX() < 480){ //5-580
						display();
						if (game.getBoard()[0][5] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 5, 0);
						}
						else{
							gridPane.add(blueChip(), 5, 0);
						}
					}
					else if (480 < e.getX() && e.getX() < 560){ //580-660
						display();
						if (game.getBoard()[0][6] != Chip.EMPTY){
							display();
						}
						else if (game.getCurrentPlayer() == Chip.RED){
							gridPane.add(redChip(), 6, 0);
						}
						else{
							gridPane.add(blueChip(), 6, 0);
						}
					}
					else{
						display();
					}
				}
			});
		
			hbox.setOnMouseClicked(e -> { //gridPane.seton... //border.setONMOuse
				try{
					if (continueRender){
						if (0 < e.getX() && e.getX() < 80){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(0);
								display();
								
							}
							else{
								game.placeDisk(0);
								display();
							}
						}
						else if (80 < e.getX() && e.getX() < 160){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(1);
								display();
								
							}
							else{
								game.placeDisk(1);
								display();
							}
						}
						else if (160 < e.getX() && e.getX() < 240){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(2);
								display();
								
							}
							else{
								game.placeDisk(2);
								display();
							}
						}
						else if (240 < e.getX() && e.getX() < 320){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(3);
								display();
								
							}
							else{
								game.placeDisk(3);
								display();
							}
						}
						else if (320 < e.getX() && e.getX() < 400){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(4);
								display();
								
							}
							else{
								game.placeDisk(4);
								display();
							}
						}
						else if (400 < e.getX() && e.getX() < 480){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(5);
								display();
								
							}
							else{
								game.placeDisk(5);
								display();
							}
						}
						else if (480 < e.getX() && e.getX() < 560){
							if (game.getCurrentPlayer() == Chip.RED){
								game.placeDisk(6);
								display();
								
							}
							else{
								game.placeDisk(6);
								display();
							}
						}
				}
					if (game.isGameOver()){
						continueRender = false;
						display();
						Circle winner;
						if (game.getWinningPlayer() == Chip.RED){
							vbox2.getChildren().clear();
							winner = redChip();
							Text text = toText("WINS",25);
							StackPane layout = new StackPane();
							vbox2.getChildren().add(layout);
					        layout.getChildren().addAll(
					        		background,
					                winner,
					                text
					        );
						}
						else{
							vbox2.getChildren().clear();
							winner = blueChip();
							Text text = toText("WINS",25);
							StackPane layout = new StackPane();
							vbox2.getChildren().add(layout);
					        layout.getChildren().addAll(
					        		background,
					                winner,
					                text
					        );
						}
					}
				}
				catch (IllegalMoveException a) {
				    System.out.println("That column is full or out of range");
				}
				catch (GameStateException a){
					System.out.println("The game ends in a draw!");
					vbox2.getChildren().clear();
					Circle winner = createChip();
					Text text = toText("DRAW",25);
					StackPane layout = new StackPane();
					vbox2.getChildren().add(layout);
			        layout.getChildren().addAll(
			        		background,
			                winner,
			                text
			        );
				}
					});
		
		
        stage.setTitle("Connect Four");
        stage.setScene(scene);
        System.out.println(vbox2.getBoundsInParent().getWidth());
        int width = (game.getColumns()*80) + (int)vbox2.getBoundsInParent().getWidth();
        stage.setWidth(width);
        //^width = all the cols of the particular game plus the other hbox on the right side^
        stage.show();
	}



}