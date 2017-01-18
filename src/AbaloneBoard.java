
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class AbaloneBoard extends Pane {
	private Menu menu;
	private Cell[][] board = new Cell[9][9];
	//private int current_player;
	private double cell_width, cell_height;
	//private Polygon board_shape;
	private GameLogic game;
	private int nbPlayers;
	
	final ToggleGroup group = new ToggleGroup();

	public AbaloneBoard(){
		playersSelection();
	}
	
	private void playersSelection() {
		int setX = 300;
		int setYInit = 100;
		int offset = 30;
		
		Label q = new Label("How many players ?:");
		q.setTranslateX(setX - q.getText().length());
		q.setTranslateY(setYInit - offset);
		RadioButton rb1 = new RadioButton("2 players");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
	    rb1.setUserData(2);
		rb1.setTranslateX(setX);
		rb1.setTranslateY(setYInit);
		RadioButton rb2 = new RadioButton("3 players");
		rb2.setToggleGroup(group);
	    rb2.setUserData(3);
		rb2.setTranslateX(setX);
		rb2.setTranslateY(setYInit + offset);
		RadioButton rb3 = new RadioButton("4 players");
		rb3.setToggleGroup(group);
	    rb3.setUserData(4);
		rb3.setTranslateX(setX);
		rb3.setTranslateY(setYInit + 2*offset);
		RadioButton rb4 = new RadioButton("5 players");
		rb4.setToggleGroup(group);
	    rb4.setUserData(5);
		rb4.setTranslateX(setX);
		rb4.setTranslateY(setYInit + 3*offset);
		RadioButton rb5 = new RadioButton("6 players");
		rb5.setToggleGroup(group);
	    rb5.setUserData(6);
		rb5.setTranslateX(setX);
		rb5.setTranslateY(setYInit + 4*offset);
		
		Button btn = new Button("Play!");
		btn.setTranslateX(setX + offset);
		btn.setTranslateY(setYInit + 6*offset);	
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame(Integer.parseInt(group.getSelectedToggle().getUserData().toString()));
            }
        });
		
		this.getChildren().addAll(q, rb1, rb2, rb3, rb4, rb5, btn);
	}
	
	private void startGame(int nbPlayers) {
		this.getChildren().clear();
		this.nbPlayers = nbPlayers;
		
		this.menu = new Menu(nbPlayers);
		menu.setTranslateX(450);
		this.getChildren().add(menu);
		
		Button resetBtn = new Button("Restart");
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	resetGame();
            }
        });
		resetBtn.setTranslateX(400);
		this.getChildren().add(resetBtn);
		
		// init board
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++)
				board[i][j] = null;
		}
		
		//current_player = 1;
		game = new GameLogic(this.board, this.menu);
		game.setNbPlayer(nbPlayers);

		//game.setPlayer(current_player);
		Polygon hexagon = new Polygon();
		Double a, b, c;
		a = 100.0;
		b = 180.0;
		c = 200.0;
		
		// generate hexagon
		hexagon.setFill(Color.DARKRED);
        hexagon.setStroke(Color.DARKRED);
		
		hexagon.getPoints().addAll(new Double[]{
		    5.0, b,
		    a, 10.0,
		    a + c, 10.0,
		    2 * c, b,
		    a + c, 2 * b,
		    a, 2 * b});
        this.getChildren().add(hexagon);
        
		int size_line = 5;
		int count = 1;
		
		// add cells to board
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				board[i][j] = new Cell(game, i, j);
				this.getChildren().add(board[i][j]);
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
		}

		this.resize(600, 400);
		this.relocate(0, 0);
		linkCells(5, 1);
		placeAllPieces();
		this.setSize();
	}
	
	private void linkCells(int size_line, int count) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				 // cells around clockwise
				if (i == 0) { // first line
					if (j == 0)
						board[i][j].addCellsAround(null, board[i][j+1], board[i+1][j+1], board[i+1][j], null, null);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(null, null, board[i+1][j+1], board[i+1][j], board[i][j-1], null);
					else
						board[i][j].addCellsAround(null, board[i][j+1], board[i+1][j+1], board[i+1][j], board[i][j-1], null);
				}
				else if (i < 4) {
					if (j == 0)
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j+1], board[i+1][j], null, null);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(null, null, board[i+1][j+1], board[i+1][j], board[i][j-1], board[i-1][j-1]);
					else
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j+1], board[i+1][j], board[i][j-1], board[i-1][j-1]);
				}
				else if (i == 4) { // middle line
					if (j == 0)
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j], null, null, null);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(null, null, null, board[i+1][j-1], board[i][j-1], board[i-1][j-1]);
					else
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j], board[i+1][j-1], board[i][j-1], board[i-1][j-1]);
				}
				else if (i == 8) { // last line
					if (j == 0)
						board[i][j].addCellsAround(board[i-1][j+1], board[i][j+1], null, null, null, board[i-1][j]);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(board[i-1][j+1], null, null, null, board[i][j-1], board[i-1][j]);
					else
						board[i][j].addCellsAround(board[i-1][j+1], board[i][j+1], null, null, board[i][j-1], board[i-1][j]);
				}
				else { // i > 5 but not last line
					if (j == 0)
						board[i][j].addCellsAround(board[i-1][j+1], board[i][j+1], board[i+1][j], null, null, board[i-1][j]);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(board[i-1][j+1], null, null, board[i+1][j-1], board[i][j-1], board[i-1][j]);
					else
						board[i][j].addCellsAround(board[i-1][j+1], board[i][j+1], board[i+1][j], board[i+1][j-1], board[i][j-1], board[i-1][j]);
				}
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
		}
	}
	
	private void case2Players(int player, int i, int j) {
		if (player == 1 && (i == 6 || i == 7 || i == 8)) {
			if (i == 6 && (j == 0 || j == 1 || j == 5 || j == 6))
				board[i][j].setPlayer(-1);
			else {
				board[i][j].setPlayer(player);
			}
		}
		else if (player == 2 && (i == 0 || i == 1 || i == 2)) {
			if (i == 2 && (j == 0 || j == 1 || j == 5 || j == 6))
				board[i][j].setPlayer(-1);
			else {
				board[i][j].setPlayer(player);
			}
		}
	}
	
	private void case3Players(int player, int i, int j) {
		if (player == 1 && (i == 0 || i == 1))
			board[i][j].setPlayer(player);
		else if (player == 2 && i >= 3) {
			if (i == 3 && j == 7 ||
				i != 3 && (j == getLineLength(board[i]) || j == getLineLength(board[i]) - 1))
				board[i][j].setPlayer(player);
		}
		else if (player == 3 && i >= 3) {
			if (i == 3 && j == 0 || i != 3 && (j == 0 || j == 1))
				board[i][j].setPlayer(player);
		}
	}
	
	private void case4Players(int player, int i, int j) {
		if (player == 1 && (i == 0 || i == 1)) {
			if (j < 4)
				board[i][j].setPlayer(player);
		}
		else if (player == 2 && i >= 1 && i <= 5) {
			if ((i == 1 || i == 5) && j == getLineLength(board[i]) ||
				i != 1 && i != 5 && (j == getLineLength(board[i]) || j == getLineLength(board[i]) - 1))
				board[i][j].setPlayer(player);
		}
		else if (player == 3 && (i == 7 || i == 8)) {
			if (i == 7 && j > 1 || i == 8 && j > 0)
				board[i][j].setPlayer(player);
		}
		else if (player == 4 && i >= 3 && i <= 7) {
			if ((i == 3 || i == 7) && j == 0 || i != 3 && i != 7 && (j == 0 || j == 1))
				board[i][j].setPlayer(player);
		}
	}
	
	private void case5Players(int player, int i, int j) {
		if (player == 1 && (i == 0 || i == 1)) {
			if (j < 4)
				board[i][j].setPlayer(player);
		}
		else if (player == 2 && i >= 1 && i <= 5) {
			if ((i == 1 || i == 5) && j == getLineLength(board[i]) ||
				i != 1 && i != 5 && (j == getLineLength(board[i]) || j == getLineLength(board[i]) - 1))
				board[i][j].setPlayer(player);
		}
		else if (player == 3 && (i == 7 || i == 8)) {
			if (i == 7 && j > 1 || i == 8 && j > 0)
				board[i][j].setPlayer(player);
		}
		else if (player == 4 && i >= 3 && i <= 7) {
			if ((i == 3 || i == 7) && j == 0 ||
				i != 3 && i != 7 && (j == 0 || j == 1))
				board[i][j].setPlayer(player);
		}
		else if (player == 5 && i >= 3 && i <= 5) {
			if ((i == 3 || i == 5) && j > 2 && j < 5 ||
				i != 3 && i != 5 && j > 2 && j < 6)
				board[i][j].setPlayer(player);
		}
	}
	
	private void case6Players(int player, int i, int j) {
		if (player == 1 && i >= 0 && i <= 2) {
			if (i == 0 && j > 0 && j < getLineLength(board[i]) ||
				i == 1 && j > 1 && j < getLineLength(board[i]) - 1 ||
				i == 2 && j == 3)
				board[i][j].setPlayer(player);
		}
		else if (player == 2 && i >= 1 && i <= 3) {
			if (j == getLineLength(board[i]) ||
				i != 1 && j == getLineLength(board[i]) - 1 ||
				i == 3 && j == getLineLength(board[i]) - 2)
				board[i][j].setPlayer(player);
		}
		else if (player == 3 && i >= 5 && i <= 7) {
			if (j == getLineLength(board[i]) ||
					i != 7 && j == getLineLength(board[i]) - 1 ||
					i == 5 && j == getLineLength(board[i]) - 2)
					board[i][j].setPlayer(player);
		}
		else if (player == 4 && i >= 6 && i <= 8) {
			if (i == 8 && j > 0 && j < getLineLength(board[i]) ||
				i == 7 && j > 1 && j < getLineLength(board[i]) - 1 ||
				i == 6 && j == 3)
				board[i][j].setPlayer(player);
		}
		else if (player == 5 && i >= 5 && i <= 7) {
			if (j == 0 || i != 7 && j == 1 || i == 5 && j == 2)
				board[i][j].setPlayer(player);
		}
		else if (player == 6 && i >= 1 && i <= 3) {
			if (j == 0 || i != 1 && j == 1 || i == 3 && j == 2)
				board[i][j].setPlayer(player);
		}
	}
	
	private void placeAllPieces() {
		int size_line;
		int count;
		
		for (int player = 1; player <= this.nbPlayers; player++) {
			size_line = 5;
			count = 1;
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < size_line; j++) {
					if (this.nbPlayers == 2)
						case2Players(player, i, j);
					else if (this.nbPlayers == 3)
						case3Players(player, i, j);
					else if (this.nbPlayers == 4)
						case4Players(player, i, j);
					else if (this.nbPlayers == 5)
						case5Players(player, i, j);
					else if (this.nbPlayers == 6)
						case6Players(player, i, j);
				}
				if (size_line == 9)
					count *= -1;
				size_line += count;
			}
		}
	}
	
	private int getLineLength(Cell[] line) {
		for (int i = line.length - 1; i >= 0; i--) {
			if (line[i] != null)
				return i;
		}
		return 0;
	}
	
	private void setSize() {
		int size_line = 5;
		int count = 1;
		int save;
		int x, y;
		
		cell_width = 650 / 15.0;
		cell_height = 400 / 15.0;
		
		x = 51; y = 7;
		for(int i = 0; i < 9; i++) {
			save = x;
			for(int j = 0; j < size_line; j++) {
				board[i][j].relocate(x, y);
				board[i][j].resize(cell_width, cell_height);
				x += 20; // space between cells of the same row
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
			y += 19; // space between rows
			x = save - count * 10; // reinit the x pos with a tabulation
		}
	}
	
	private void resetGame() {
		this.getChildren().clear();
		this.playersSelection();
	}
	
	/*public void endGame(int winner) {
		menu.showMsgEnd(winner);
	}*/
	
}