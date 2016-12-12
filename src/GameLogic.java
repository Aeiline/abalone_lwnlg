class GameLogic {
	
	public GameLogic(AbaloneBoard board)
	{
		this.board = board
	}
	
	public void click_occured(Cell clickedcell)
	{
		case_number = 1;
		System.out.println("click_occured");
		last_pos = clickedcell.getBoardPos(); 
		System.out.println(clickedcell.getBoardPos()[0] + ";" + clickedcell.getBoardPos()[1]);
		if (clickedcell.getPlayer() == -1)
		{
			print_case_free();
			System.out.println("free");
			//affichage des cases disponibles
		}
		else
		{
			System.out.println(clickedcell.getPlayer());
			System.out.println("not free");
//			save_case();
		clickedcell.setSelected();
			//tour joue
		}
	}
	
	private void save_case()
	{
		case_number += 1;
		if (case_to_move == null)
		{
			case_to_move = new int[3][2];
			for (int i = 0; i < 3; i +=1)
			{
				for(int j = 0; j < 2; j += 1)
				{
					case_to_move[i][j] = -1;
				}
			}
		}
		System.out.println("nombre " + case_number);
		case_to_move[case_number][0] = last_pos[0];
		case_to_move[case_number][1] = last_pos[1];
	}
	
	private void print_case_free()
	{
		
	}
	
	private void setPlayer(int play)
	{
		play = player_turn;
	}
	
	private AbaloneBoard board;
	
	private int case_number;
	private int[][] case_to_move;
	private int player_turn;
	private int[] last_pos;

	
	
	
}