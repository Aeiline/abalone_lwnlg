import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

class Piece extends Group {
	private Ellipse e;
	//private int player;
	private Translate pos;
	
	private Color[] colors = new Color[6];
	
	public Piece(int player){
		colors[0] = Color.PAPAYAWHIP;
		colors[1] = Color.DIMGREY;
		colors[2] = Color.INDIANRED;
		colors[3] = Color.ROYALBLUE;
		colors[4] = Color.DARKORANGE;
		colors[5] = Color.DARKSEAGREEN;

		
		pos = new Translate();
		//this.player = player;
	
		e = new Ellipse();
		getChildren().addAll(e);
		e.getTransforms().add(pos);
		e.setStroke(colors[player]);

	}
	
	@Override
	public void resize(double width, double height){
		super.resize(width,  height);

		e.setCenterX(width / 2); e.setCenterY(height / 2);
		e.setRadiusX(width / 2); e.setRadiusY(height / 2);

	}
	
	@Override
	public void relocate(double x, double y){
		super.relocate(x, y);
		pos.setX(x); pos.setY(y);
	}
}