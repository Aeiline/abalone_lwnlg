
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

class Cell extends Ellipse{

	private Ellipse e;
	private int player;
	private Translate pos;
	private Cell[] others = new Cell[6];
	//private Ellipse shape;
	
	public Cell(int x, int y) {
		player = -1;
		pos = new Translate(x, y);
	
		e = this;

		this.getTransforms().add(pos); //shape.
		this.setStroke(Color.BLACK); //shape.
		this.setFill(Color.LIGHTGRAY); //shape.
		//getChildren().addAll(shape);
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				for(int i = 0; i < 6; i +=1)
				{
					others[i].setFill(Color.ALICEBLUE);
				}
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

		double val = 0.7;
		this.setCenterX(width * val); this.setCenterY(height * val); //shape.
		this.setRadiusX(width * val); this.setRadiusY(height * val); //shape.

	}
	
	@Override
	public void relocate(double x, double y){
		super.relocate(x, y);
		pos.setX(x); pos.setY(y);
	}
	
}