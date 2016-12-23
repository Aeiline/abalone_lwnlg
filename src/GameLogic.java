class GameLogic {
	
	//setselected true : highlight,  false, black
	
	public GameLogic(Cell[][] board)
	{
		this.board = board;
		case_number = 0;
		player_turn = 1;
	}
	
	public void click_control(Cell clickedcell)
	{
		System.out.println("control" + player_turn);
		if (clickedcell.getPlayer() == player_turn)
		{			System.out.println("begin");
			last_pos = clickedcell.getBoardPos(); 
			if(case_number < 3/* || clickedcell.getHighlighted()*/)
			{
				System.out.println("first step");
				if (verify_direction(clickedcell))
				{
					System.out.println("second step");
					save_case();
					calc_highlited(clickedcell);
					clickedcell.setSelected(true);
					verify_case();
					case_number += 1;
				}
			}
		}
	}
	
	public boolean verify_direction(Cell clickedcell)
	{
		int new_direction = -20;
		if (case_number > 0)
		{
			for (int i = 0; i < 6; i += 1)
			{
				if (clickedcell.others[i] != null && case_to_move[case_number - 1][0] == clickedcell.others[i].getBoardPos()[0])
				{
					if (case_to_move[case_number - 1][1] == clickedcell.others[i].getBoardPos()[1])
					{
						new_direction = i;
					}
				}
			}
		}
		System.out.println("last : " + last_direction + "new : " + new_direction);
		
		if (new_direction != -20)
		{
			if (new_direction == last_direction)
				return true;
			else if(new_direction ==  last_direction - 3 )
				return true;
			else if (new_direction == last_direction + 3)
				return true;
		}
		if (new_direction == -20 && case_to_move == null)
			return true;
		else if (last_direction < 0)
			return true;
		return false;
			
}
	
	public void verify_case()
	{
		for (int i = 0; i <= case_number; i++)
		{
			System.out.println(case_to_move[i][0] +";" + case_to_move[i][1]);
		}
	}
	
	public void click_occured(Cell clickedcell)
	{	
		case_number = 0;

		if (clickedcell.getPlayer() == -1)
		{
			last_pos = clickedcell.getBoardPos(); 
			print_case_free();
			//affichage des cases disponibles
		}
	}
	
	private void calc_highlited(Cell clickedcell)
	{
		if (case_number < 3)
		{
			if (case_number > 0)
			{
				for (int i = 0; i < 6; i += 1)
				{
					
					if (clickedcell.others[i] != null && case_to_move[case_number - 1][0] == clickedcell.others[i].getBoardPos()[0])
					{
						if (case_to_move[case_number - 1][1] == clickedcell.others[i].getBoardPos()[1])
						{
							last_direction = i;
						}
					}
				}
			}
			System.out.println("last_direction :" + last_direction);
			
			for (int i = 0; i < 6; i += 1)
			{
				if (clickedcell.others[i] != null)
				{
					if (clickedcell.others[i].getPlayer() == player_turn)
					{
						if (clickedcell.others[i].getSelected() == false)
							clickedcell.others[i].setHighlight(true);
					}
				}
			}
		}
	}
	
	private void save_case()
	{

		if (case_to_move == null)
		{
			last_direction = -10;
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
	
	private int last_direction;
	private int case_number;
	private int[][] case_to_move;
	private int player_turn;
	private int[] last_pos;
	
	
}