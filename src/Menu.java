import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

class Menu extends GridPane {
	private Rectangle r = new Rectangle();
	
	public Menu() {
		int nbplayer = 2; // TODO link to real number
		r.setX(10);
		r.setY(50);

	    setPadding(new Insets(5));
	    setHgap(450);
	    setVgap(15);
	    
		r.setWidth(180);
		r.setHeight(50); // total height = +350
		r.setArcWidth(20);
		r.setArcHeight(20);
		
		this.add(r, 1, 1);
		this.add( new Text("Playing:\tPlayer" + 1), 1, 2); // TODO replace with current player
		for (int i = 0; i < nbplayer; i++) {
			this.add(new Text("Player" + (i + 1) + ":\t" + 6 + "/" + 6), 1, i + 3); // TODO replace with nb pieces left
		}
	}
}