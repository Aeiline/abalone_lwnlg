import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

class Menu extends GridPane {
	private TextFlow txtF = new TextFlow();
	private Text currentPlayerTxt;
	private Text[] piecesLeftTxt = new Text[6];
	private Text[] piPushedTxt = new Text[6];
	private int nbplayer;
	private int totalpieces;

	public Menu(int nbPlayers) {
		this.nbplayer = nbPlayers;
		this.totalpieces = 14 - (3 * (this.nbplayer - 2));
		
		String towin = "TO WIN:\n\t" +
		(this.nbplayer > 2 ? "Push 6 of your adverses' marbles" : "Push 6 of your adverse's marbles");
		
	    setPadding(new Insets(5));
	    setVgap(5);
	    this.setStyle("-fx-background-color: #C0C0C0;");

	    Text txt1 = new Text("Playing:");
	    txt1.setStyle("-fx-font-weight: bold");
	    currentPlayerTxt = new Text("\tPlayer" + 1 + "\n");
	    txtF.getChildren().addAll(txt1, currentPlayerTxt);
	    
		this.add(new Text("HOW TO PLAY:\n\tUse CTRL to select first and\nfollowing pieces. Then, just click to\nmove selected pieces.\n"
				+ towin), 1, 1);
		this.add(txtF, 1, 3);
		int row = 4;
		for (int i = 0; i < nbplayer; i++) {
			piecesLeftTxt[i] = new Text("Player" + (i + 1) + ":\t\t" + totalpieces + "/" + totalpieces + "\tmarbles");
			this.add(piecesLeftTxt[i], 1, row);
			piPushedTxt[i] = new Text("\tPushed:\t" + 0 + "/" + "6");
			this.add(piPushedTxt[i], 1, row + 1);
			row += 2;
		}
		for (int j = 6 - nbplayer; j >= 0; j--) {
			this.add(new Text("\n"), 1, row);
			this.add(new Text("\n"), 1, row + 1);
			row += 2;
		}
	}
	
	public void updateMenu(int currentPlayer, int[] piecesLeft, int[] piecesPushed){
		this.currentPlayerTxt.setText("\tPlayer" + currentPlayer);
		for (int i = 0; i < nbplayer; i++) {
			piecesLeftTxt[i].setText("Player" + (i + 1) + ":\t\t" + piecesLeft[i] + "/" + totalpieces + "\tmarbles");
			piPushedTxt[i].setText("\tPushed:\t" + piecesPushed[i] + "/" + "6");
		}
	}

	public void showMsgEnd(int winner) {
		this.getChildren().clear();
		Text endMsg = new Text("Winner is Player " + winner + "\t");
	    endMsg.setFill(Color.BLACK);
	    endMsg.setStyle("-fx-font: 24 arial;");
		for (int i = 0; i < 12; i++) {
			if (i == 5)
			    this.add(endMsg, 1, i);
			else
				this.add(new Text("\n"), 1, i);
		}
	}
	
	
}