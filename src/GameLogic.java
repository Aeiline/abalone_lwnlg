class GameLogic {
	
	public GameLogic()
	{
		
	}
	
	public void click_occured(Cell clickedcell)
	{
		//last_pos_x = clickedcell.
		if (clickedcell.getPlayer() == -1)
		{
			case_number = 0;
			print_case_free();
			//affichage des cases disponibles
		}
		else if (clickedcell.getPlayer() == player_turn)
		{
			save_case();
			//tour joue
		}
	}
	
	private void save_case()
	{
		case_number += 1;
		if (case_to_move == null)
		{
			case_to_move = new int[2][3];
			for (int i = 0; i < 2; i +=1)
			{
				for(int j = 0; j < 3; j += 1)
				{
					case_to_move[i][j] = -1;
				}
			}
		}
		System.out.println("nombre " + case_number);
	}
	
	private void print_case_free()
	{
		
	}
	
	
	private int case_number;
	private int[][] case_to_move;
	private int player_turn;
	private int last_pos_x;
	private int last_pos_y;
	
	
	
}