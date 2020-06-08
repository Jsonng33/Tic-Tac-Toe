import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

public class TicTacToeGUI extends Application {

	private static String[][] board = new String [3][3];
	private static int playerTurn = 1;
	private static boolean play = true;
	
	private Button[][] btn = new Button[3][3];
	
	public void start(Stage primaryStage) {
		
		VBox rightMenu = new VBox();
		rightMenu.setAlignment(Pos.TOP_CENTER);
		rightMenu.setPadding(new Insets(50, 50, 50, 50));
		rightMenu.setStyle("-fx-background-color: orange");
		
		Button menuButtonA = new Button("Restart");	
		menuButtonA.setPrefHeight(50);
		menuButtonA.setPrefWidth(100);
		
		rightMenu.getChildren().addAll(menuButtonA);
		
		BorderPane borderPane = new BorderPane();
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		gridPane.setStyle("-fx-background-color: green");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				btn[i][j] = new Button();
				btn[i][j].setPrefWidth(100);
				btn[i][j].setPrefHeight(100);
				gridPane.add(btn[i][j], j, i);
				
				btn[i][j].setOnAction(e -> {
						
					if(play == false) {
						return;
					}
					
					
					int col = GridPane.getColumnIndex((Node)(e.getSource())).intValue();
					int row = GridPane.getRowIndex((Node)(e.getSource())).intValue();
					
					
					try {
						
						if(playerTurn % 2 != 0) {
							if(board[row][col] == "O" || board[row][col] == "X") {
								throw new InvalidSpot();
							}
							else {
								board[row][col] = "O";
								((Button)(e.getSource())).setText("O");
								((Button)(e.getSource())).setFont(Font.font(45));
								playerTurn++;
							}
						}
						else {
							if(board[row][col] == "O" || board[row][col] == "X") {
								throw new InvalidSpot();
							}
							else {
								board[row][col] = "X";
								((Button)(e.getSource())).setText("X");
								((Button)(e.getSource())).setFont(Font.font(45));
								playerTurn++;
							}
						}
					}	
					catch(InvalidSpot ex){
						
						System.out.println(ex.getMessage());
						
					}
					System.out.println(playerTurn);
					// Checking for Winner
					if(winningCombo() == 1) {
						
						System.out.println("O Wins!");
						play = false;
					}
					else if(winningCombo() == 2) {
						System.out.println("X Wins!");
						play = false;
					}
					
					// Checking for a Tie
					else if(playerTurn == 10) {
						System.out.println("TIE");
						play = false;
					}	
					
						for(int a = 0; a < 3; a++) {
							for(int b = 0; b < 3; b++) {					
								
								System.out.print(board[a][b]);
								
							}
							System.out.println();
						}
						System.out.println();
					
					
					
				});
				
				// Restart Button
				menuButtonA.setOnAction(ex -> {
					
						System.out.println("Restarting Game!");
						play = true;
						playerTurn = 1;
			
						for (int c = 0; c < 3; c++) {
							for (int d = 0; d < 3; d++) {
								board[c][d] = " ";
								btn[c][d].setText(" ");
							}
						}
					
						
					});
			}
		}
		
		borderPane.setRight(rightMenu);
		borderPane.setCenter(gridPane);
		
		Scene scene = new Scene(borderPane, 700, 500);
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	
	public int winningCombo(){
		
		// Horizontal
		for(int x = 0; x < 3; x ++) {
			
			if((board[x][0] == "O") && (board[x][1] == "O") && (board[x][2] == "O"))
				return 1;
			if((board[x][0] == "X") && (board[x][1] == "X") && (board[x][2] == "X"))
				return 2;
		}
		
		// Vertical
		for(int y = 0; y < 3; y ++) {
			
			if((board[0][y] == "O") && (board[1][y] == "O") && (board[2][y] == "O"))
				return 1;
			if((board[0][y] == "X") && (board[1][y] == "X") && (board[2][y] == "X"))
				return 2;
		}
		
		// Diagonals
		if((board[0][0] == "O") && (board[1][1] == "O") && (board[2][2] == "O"))
			return 1;
		if((board[0][0] == "X") && (board[1][1] == "X") && (board[2][2] == "X"))
			return 2;
		
		if((board[0][2] == "O") && (board[1][1] == "O") && (board[2][0] == "O"))
			return 1;
		if((board[0][2] == "X") && (board[1][1] == "X") && (board[2][0] == "X"))
			return 2;
		
		
		return 0;
		
		
	}
	

	
	public class InvalidSpot extends Exception{
		
		public InvalidSpot(){
			
			super("This spot is already taken, please choose another one.");
			
		}
		
		public InvalidSpot(String message) {
			
			super(message);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j++) {
				
				board[i][j] = " ";
			}
		}
		
		launch(args);
		
	}


	
	
	
}
