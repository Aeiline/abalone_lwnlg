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
	
	public AbaloneBoard(){
		/*// init the boards
		board = new int[3][3];
		renders = new Piece[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++){
				board[i][j] = EMPTY;
				renders[i][j] = null;
			}
		current_player = XPIECE;
		
		// init the rectangle and lines
		back = new Rectangle();
		back.setFill(Color.BLACK);
		h1 = new Line(); h2 = new Line();
		v1 = new Line(); v2 = new Line();
		h1.setStroke(Color.WHITE); h2.setStroke(Color.WHITE);
		v1.setStroke(Color.WHITE); v2.setStroke(Color.WHITE);
		

		h1.setStartX(0); h1.setStartY(0); h1.setEndY(0);
		h2.setStartX(0); h2.setStartY(0); h2.setEndY(0);

		v1.setStartX(0); v1.setStartY(0); v1.setEndX(0);
		v2.setStartX(0); v2.setStartY(0); v2.setEndX(0);
		
		// setup the translation of one cell height and two cell heights
		ch_one = new Translate(0, 0);
		ch_two = new Translate(0, 0);
		h1.getTransforms().add(ch_one);
		h2.getTransforms().add(ch_two);
		// setup the translation of one cell width and two cell widths
		cw_one = new Translate(0, 0);
		cw_two = new Translate(0, 0);
		v1.getTransforms().add(cw_one);
		v2.getTransforms().add(cw_two);
		
		getChildren().addAll(back, h1, h2, v1, v2);*/
		this.current_player = 0;

		int size_line = 5;
		int count = 1;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				board[i][j] = new Cell(i+10, j+50);
				this.getChildren().add(board[i][j]);
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
		}
		
		// reinit
		size_line = 5;
		count = 1;
		// ** link cells between each other
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				System.out.println("i;j=" + i + ";" + j);
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
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j+1], null, null, null);
					else if (j == size_line - 1)
						board[i][j].addCellsAround(null, null, null, board[i+1][j-1], board[i][j-1], board[i-1][j-1]);
					else
						board[i][j].addCellsAround(board[i-1][j], board[i][j+1], board[i+1][j+1], board[i+1][j], board[i][j-1], board[i-1][j-1]);
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
		
		//this.getChildren().add(board);
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
		
		x = 45; y = 15;
		for(int i = 0; i < 9; i++) {
			save = x;
			for(int j = 0; j < size_line; j++) {
				board[i][j].relocate(x, y);
				board[i][j].resize(cell_width, cell_height);
				x += 14;
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
			y += 10;
			x = save - count * 6;
		}
		/*// get size of a cell
		cell_width = width / 3.0;
		cell_height = height / 3.0;
		
		// resize the rectangle to take the full size of the widget
		back.setWidth(width); back.setHeight(height);
		// set a new y on the horizontal lines and translate them into place
		ch_one.setY(cell_height); ch_two.setY(2 * cell_height);
		h1.setEndX(width); h2.setEndX(width);
		// set a new x on the vertical lines and translate them into place
		cw_one.setX(cell_width); cw_two.setX(2 * cell_width);
		v1.setEndY(height); v2.setEndY(height);
		
		// we need to reset the sizes and positions of all XOPieces that were placed
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != 0) {
					renders[i][j].relocate(i * cell_width, j * cell_height);
					renders[i][j].resize(cell_width, cell_height);
				}
			}
		}*/
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