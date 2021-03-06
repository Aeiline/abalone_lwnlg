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

	private static Color[] colors = new Color[6];

	public Menu(int nbPlayers) {

		colors[0] = Color.DIMGREY;
		colors[1] = Color.LIGHTGOLDENRODYELLOW;
		colors[2] = Color.INDIANRED;
		colors[3] = Color.ROYALBLUE;
		colors[4] = Color.DARKORANGE;
		colors[5] = Color.DARKSEAGREEN;
		
		this.nbplayer = nbPlayers;
		// 2 => 14; 3 => 11; 4 => 8; 5 => 8; 6 => 6
		this.totalpieces = this.nbplayer <= 4 ? 14 - (3 * (this.nbplayer - 2)) :
													(this.nbplayer == 5 ? 8 : 6);
		
		String towin = "TO WIN:\n\t" +
		(this.nbplayer > 2 ? "Push 6 of your adverses' marbles" : "Push 6 of your adverse's marbles");
		
	    setPadding(new Insets(5));
	    setVgap(5);
	    this.setStyle("-fx-background-color: #C0C0C0;");

	    Text txt1 = new Text("Playing:");
	    currentPlayerTxt = new Text("\tPlayer" + 1 + "\n");
	    currentPlayerTxt.setFill(colors[0]);
	    txtF.setStyle("-fx-font-weight: bold");
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
		this.currentPlayerTxt.setText("\tPlayer" + currentPlayer + "\n");
	    this.currentPlayerTxt.setFill(colors[currentPlayer-1]);
		for (int i = 0; i < nbplayer; i++) {
			piecesLeftTxt[i].setText("Player" + (i + 1) + ":\t\t" + piecesLeft[i] + "/" + totalpieces + "\tmarbles");
			piPushedTxt[i].setText("\tPushed:\t" + piecesPushed[i] + "/" + "6");
		}
	}

	public void nextPlayer(int currentPlayer){
		System.out.println("\tUPDATE PLAYER !:" + currentPlayer);
		this.currentPlayerTxt.setText("\tPlayer" + currentPlayer);
	    this.currentPlayerTxt.setFill(colors[currentPlayer-1]);
	}
	
	public void showMsgEnd(int winner) {
		this.getChildren().clear();
		Text endMsg = new Text("Winner is Player " + winner + "\t");
	    endMsg.setStyle("-fx-font: 24 arial;");
	    endMsg.setFill(colors[winner - 1]);
		for (int i = 0; i < 12; i++) {
			if (i == 5)
			    this.add(endMsg, 1, i);
			else
				this.add(new Text("\n"), 1, i);
		}
	}
	
	
}