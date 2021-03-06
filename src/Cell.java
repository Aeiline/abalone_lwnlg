
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

class Cell extends Ellipse{

	private int player;
	private GameLogic game;
	private Translate pos;
	public Cell[] others = new Cell[6];
	private int[] boardPos = new int[2];
	private Cell that;
	//private Piece piece;
	
	private boolean highlighted = false;
	private boolean selected = false;
	private int width, height;
	
	private static Color[] colors = new Color[6];
	
	public void initColors() {
		colors[0] = Color.DIMGREY;
		colors[1] = Color.LIGHTGOLDENRODYELLOW;
		colors[2] = Color.INDIANRED;
		colors[3] = Color.ROYALBLUE;
		colors[4] = Color.DARKORANGE;
		colors[5] = Color.DARKSEAGREEN;
	}
	
	public Cell(GameLogic gameL, int x, int y) {
		initColors();
		game = gameL;
		player = -1;
		boardPos[0] = x;
		boardPos[1] = y;
		pos = new Translate(x + 10, y + 15);
		
		that = this;

		width = 26;
		height = 26;
		
		this.getTransforms().add(pos);
		this.setStroke(Color.DARKRED);
		this.setFill(Color.LIGHTGRAY); 
		
		double val = 0.7;
		this.setCenterX(width * val); this.setCenterY(height * val);
		this.setRadiusX(width * val); this.setRadiusY(height * val);

		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.isControlDown())
					game.click_control(that);
				else
					game.click_occured(that);
			}
			
		});
	
		
	}
	
	public void setColor(Color c) {
		this.setFill(c);
	}
	
	public void setSelected(boolean activ) {
		if (activ)
			this.setStroke(Color.BLACK);
		else
			this.setStroke(Color.DARKRED);
		this.selected = activ;
		System.out.println("Change stroke of cell");
	}
	
	public void setHighlight(boolean activ) {
		if (activ)
			this.setStroke(Color.GREENYELLOW);
		else
			this.setStroke(Color.DARKRED);
		this.highlighted = activ;
	}
	
	public void clicked() {
		game.click_occured(that);
	}
	
	public void addCellsAround(Cell o1, Cell o2,Cell o3, Cell o4,Cell o5, Cell o6) {
		others[0] = o1;
		others[1] = o2;
		others[2] = o3;
		others[3] = o4;
		others[4] = o5;
		others[5] = o6;
	}

	@Override
	public void resize(double width, double height){
		super.resize(width,  height);
		
		//System.out.println("w:" + width + "; h:" + height);
		/*double val = 0.7;
		this.setCenterX(width * val); this.setCenterY(height * val); //shape.
		this.setRadiusX(width * val); this.setRadiusY(height * val); //shape.*/

	}
	
	@Override
	public void relocate(double x, double y){
		super.relocate(x, y);
		pos.setX(x); pos.setY(y);
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void setPlayer(int p) {
		player = p;
		if (player > 0)
			this.setFill(Cell.colors[this.player - 1]);
		else
			this.setFill(Color.LIGHTGRAY);
	}
	
	public boolean getHighlighted() {
		return this.highlighted;
	}
	
	public boolean getSelected() {
		return this.selected;
	}

	public int[] getBoardPos(){
		return boardPos;
	}
	
	public void setBoardPos(int x, int y) {
		boardPos[0] = x;
		boardPos[1] = y;
	}

}