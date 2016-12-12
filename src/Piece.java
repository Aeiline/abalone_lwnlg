import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

class Piece extends Group {
	//private Ellipse e;
	private int player;
	private Translate pos;
	
	private Color[] colors = new Color[6];
	private Cell currentCell;
	
	public Piece(int p, Cell initCell){
		colors[0] = Color.DIMGREY;
		colors[1] = Color.LIGHTGOLDENRODYELLOW;
		colors[2] = Color.INDIANRED;
		colors[3] = Color.ROYALBLUE;
		colors[4] = Color.DARKORANGE;
		colors[5] = Color.DARKSEAGREEN;

		currentCell = initCell;
		player = p;
		//pos = new Translate();
		//this.player = player;
	
		currentCell.setColor(colors[player - 1]);
		/*e = new Ellipse();
		getChildren().addAll(e);
		e.getTransforms().add(pos);
		e.setStroke(Color.LIGHTGRAY);
		e.setFill(colors[player - 1]);*/

		/*this.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				currentCell.clicked();
			}
			
		});*/
	}

	public void changeCell(Cell cell) {
		this.currentCell = cell;
		currentCell.setColor(colors[player - 1]);
	}

	public Cell getCurrentCell() {
		return this.currentCell;
	}
	
	/*public void setSelected() {
		e.setStroke(Color.DARKGREY);
		System.out.println("Change stroke of piece");
	}
	*/
	@Override
	public void resize(double width, double height){
		super.resize(width,  height);
/*
		e.setCenterX(width / 2); e.setCenterY(height / 2);
		e.setRadiusX(width / 2); e.setRadiusY(height / 2);*/
		
		/*double val = 0.7;
		e.setCenterX(width * val); e.setCenterY(height * val);
		e.setRadiusX(width * val); e.setRadiusY(height * val);*/

	}
	
	@Override
	public void relocate(double x, double y){
		super.relocate(x, y);
		pos.setX(x); pos.setY(y);
	}
	

	
}