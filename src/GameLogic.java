class GameLogic {
	
	public GameLogic()
	{
		
	}
	
	public void click_occured(Cell clickedcell)
	{
		case_number = 1;
		System.out.println("click_occured");
		last_pos = clickedcell.getBoardPos(); 

		if (clickedcell.getPlayer() == -1)
		{
			print_case_free();
			//affichage des cases disponibles
		}
		else if (clickedcell.getPlayer() == player_turn)
		{
			save_case();
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
	
	
	private int case_number;
	private int[][] case_to_move;
	private int player_turn;
	private int[] last_pos;

	
	
	
}