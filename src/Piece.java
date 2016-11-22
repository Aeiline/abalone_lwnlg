import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;

class Piece extends Group {
	private Line l1, l2;
	private Ellipse e;
	private int type;
	private Translate pos;
	
	public Piece(int type){
		// create a new translate object and take a copy of the type
		pos = new Translate();
		this.type = type;
		
		// choose which piece type we have
		if (type == 1) {
			// we have an X piece generate two lines and add them to
			// as render nodes add in the translate for our lines
			l1 = new Line(); l2 = new Line();
			getChildren().addAll(l1, l2);
			l1.getTransforms().add(pos);
			l2.getTransforms().add(pos);
			l1.setStroke(Color.RED);
			l2.setStroke(Color.RED);
			// as l1 starts top left startx, starty will always be zero
			// as l2 starts top right starty, endx will always be zero
			l1.setStartX(0); l1.setStartY(0);
			l2.setStartY(0); l2.setEndX(0);
		} else {
			// we have an O piece generate an oval and add it as a
			// render node
			e = new Ellipse();
			getChildren().addAll(e);
			e.getTransforms().add(pos);
			e.setStroke(Color.LIME);
		}
	}
	
	@Override
	public void resize(double width, double height){
		super.resize(width,  height);
		
		// update depending on the type
		if(type == 1) {
			// resize the lines
			l1.setEndX(width); l1.setEndY(height);
			l2.setStartX(width); l2.setEndY(height);
		} else {
			// recenter the ellipse// and update the radius
			e.setCenterX(width / 2); e.setCenterY(height / 2);
			e.setRadiusX(width / 2); e.setRadiusY(height / 2);
		}
	}
	
	@Override
	public void relocate(double x, double y){
		// call the superclass method and update the position
		super.relocate(x, y);
		pos.setX(x); pos.setY(y);
	}
}