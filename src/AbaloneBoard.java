import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

class AbaloneBoard extends Pane {
	/*private int[][] board;
	private Piece[][] renders;
	private Rectangle back;
	private Line h1, h2, v1, v2;
	private double cell_width, cell_height;
	
	private Translate ch_one, ch_two, cw_one, cw_two;
	private int current_player;
	
	private final int EMPTY = 0;
	private final int XPIECE = 1;
	private final int OPIECE = 2;*/
	private Cell[][] board = new Cell[9][9];
	private int current_player;
	private double cell_width, cell_height;
	private Polygon board_shape;
	private GameLogic game;
	
	public AbaloneBoard(){
		this.current_player = 0;
		game = new GameLogic();

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

		linkCells(5, 1);
	}
	
	private void linkCells(int size_line, int count) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				//System.out.println("i;j=" + i + ";" + j);
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
	
	private void placeAllPieces(int nbPlayer) {
		int player = 1;

		int size_line = 5;
		int count = 1;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				board[i][j] = new Cell(game, i, j);
				this.getChildren().add(board[i][j]);
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
		}
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width,  height);
		

		cell_width = width / 15.0;
		cell_height = height / 15.0;

		int size_line = 5;
		int count = 1;
		int save;
		int x, y;
		
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
	
	public void resetGame() {
		/*for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = 0;
				getChildren().remove(renders[i][j]);
				renders[i][j] = null;
			}
		}*/
	}
	
	public void placePiece(final double x, final double y) {
		/*// translate the x, y coordinates into cell indexes
		int indexx = (int) (x / cell_width);
		int indexy = (int) (y / cell_height);
		
		// if the position is empty then place a piece and swap the players
		if (board[indexx][indexy] == EMPTY && current_player == XPIECE) {
			board[indexx][indexy] = XPIECE;
			renders[indexx][indexy] = new Piece(XPIECE);
			renders[indexx][indexy].resize(cell_width, cell_height);
			renders[indexx][indexy].relocate(indexx * cell_width, indexy *
			cell_height);
			getChildren().add(renders[indexx][indexy]);
			current_player = OPIECE;
		}
		else if (board[indexx][indexy] == EMPTY && current_player == OPIECE) {
			board[indexx][indexy] = OPIECE;
			renders[indexx][indexy] = new Piece(OPIECE);
			renders[indexx][indexy].resize(cell_width, cell_height);
			renders[indexx][indexy].relocate(indexx * cell_width, indexy *
			cell_height);
			getChildren().add(renders[indexx][indexy]);
			current_player = XPIECE;
		}*/
	}
}