import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

class Menu extends GridPane {
	private TextFlow txtF = new TextFlow();
	private Text currentPlayerTxt;
	private Text[] piecesLeftTxt = new Text[6];
	private int nbplayer;

	public Menu() {
		nbplayer = 2; // TODO link to real number (via parameters ?)
		
	    setPadding(new Insets(5));
	    setVgap(5);
	    this.setStyle("-fx-background-color: #C0C0C0;");

	    Text txt1 = new Text("Playing:");
	    txt1.setStyle("-fx-font-weight: bold");
	    currentPlayerTxt = new Text("\tPlayer" + 1 + "\n");
	    txtF.getChildren().addAll(txt1, currentPlayerTxt);
	    
		this.add(new Text("HOW TO PLAY:\nUse CTRL to select first and\nfollowing pieces. Then, just click to\nmove selected pieces.\n"), 1, 1);
		this.add(txtF, 1, 3);
		for (int i = 0; i < nbplayer; i++) {
			piecesLeftTxt[i] = new Text("Player" + (i + 1) + ":\t" + 6 + "/" + 6);
			this.add(piecesLeftTxt[i], 1, i + 4);
		}
		for (int j = 6 - nbplayer; j > 0; j--) {
			this.add(new Text("\n"), 1, j + 4);
		}
	}
	
	public void updateMenu(int currentPlayer, int[] piecesLeft){
		this.currentPlayerTxt.setText("\tPlayer" + currentPlayer);
		for (int i = 0; i < nbplayer; i++) {
			piecesLeftTxt[i].setText("Player" + (i + 1) + ":\t" + piecesLeft[i] + "/" + 6);
		}
	}
}