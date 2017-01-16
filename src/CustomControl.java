import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

class CustomControl extends Control {
	private AbaloneBoard xoboard;
	
	public CustomControl() {
		setSkin(new CustomControlSkin(this));
		xoboard = new AbaloneBoard();
		getChildren().add(xoboard);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			// overridden handle method
			@Override
			public void handle(KeyEvent event) {
				//if(event.getCode() == KeyCode.SPACE)
				//xoboard.resetGame();
			}
		});
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		xoboard.resize(width, height);
	}
}