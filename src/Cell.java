
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

class Cell extends Ellipse{

	private int player;
	private GameLogic game;
	private Translate pos;
	private Cell[] others = new Cell[6];
	private int[] boardPos = new int[2];
	private Cell that;
	
	public Cell(GameLogic gameL, int x, int y) {
		game = gameL;
		player = -1;
		boardPos[0] = x;
		boardPos[1] = y;
		pos = new Translate(x + 10, y + 15);
		
		that = this;

		this.getTransforms().add(pos);
		this.setStroke(Color.DARKRED);
		this.setFill(Color.LIGHTGRAY); 
		
		double val = 0.7;
		this.setCenterX(26 * val); this.setCenterY(26 * val);
		this.setRadiusX(26 * val); this.setRadiusY(26 * val);
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				game.click_occured(that);
				/*for(int i = 0; i < 6; i +=1)
				{
					if(others[i] != null)
						others[i].setFill(Color.ALICEBLUE);
				}*/
			}
			
		});
	
		
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
	}
	
	public int[] getBoardPos(){
		return boardPos;
	}
	
	public void setBoardPos(int x, int y) {
		boardPos[0] = x;
		boardPos[1] = y;
	}
}