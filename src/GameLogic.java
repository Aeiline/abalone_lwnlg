class GameLogic {
	
	//setselected true : highlight,  false, black
	
	public GameLogic(Cell[][] board)
	{
		this.board = board;
		case_number = 0;
	}
	
	public void click_control(Cell clickedcell)
	{
		System.out.println("control");
		if (clickedcell.getPlayer() == player_turn)
		{
			last_pos = clickedcell.getBoardPos(); 
			if(case_number == 0 || clickedcell.getHighlighted())
			{
				save_case();
				calc_highlited();
				clickedcell.setSelected(false);
				verify_case();
			}
		}
	}
	
	public void verify_case()
	{
		for (int i = 0; i <= case_number; i++)
		{
			System.out.println(case_to_move[i][0] +";" + case_to_move[i][1]);
		}
	}
	
	public void click_occured(Cell clickedcell)
	{		System.out.println("click");
		case_number = 0;

		if (clickedcell.getPlayer() == -1)
		{
			last_pos = clickedcell.getBoardPos(); 
			print_case_free();
			//affichage des cases disponibles
		}
	}
	
	private void calc_highlited()
	{
		if (case_number == 0)
		{
			if (board[last_pos[0] - 1][last_pos[1]].getPlayer() == player_turn)
			{
				board[last_pos[0] - 1][last_pos[1]].setSelected(true);
			}
			if (board[last_pos[0] + 1][last_pos[1]].getPlayer() == player_turn)
			{
				board[last_pos[0] + 1][last_pos[1]].setSelected(true);
			}

			if (board[last_pos[0]][last_pos[1] - 1].getPlayer() == player_turn)
			{
				board[last_pos[0]][last_pos[1] - 1].setSelected(true);
			}
			if (board[last_pos[0]][last_pos[1] + 1].getPlayer() == player_turn)
			{
				board[last_pos[0]][last_pos[1] + 1].setSelected(true);
			}
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
	
	private Cell[][] board;
	
	private int case_number;
	private int[][] case_to_move;
	private int player_turn;
	private int[] last_pos;
	
	
}