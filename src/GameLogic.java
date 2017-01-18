class GameLogic {
	
	//setselected true : highlight,  false, black
	
	public GameLogic(Cell[][] board, Menu menu)
	{
		this.menu = menu;
		nb_player = 2;
		this.board = board;
		case_number = 0;
		player_turn = 1;
		highlited = null;
		
	}
	
	public void setNbPlayer(int play)
	{
		nb_player = play;
		player_push = null;
		player_push = new int[nb_player];
		for (int i = 0; i < nb_player ; i += 1)
		{
			player_push[i] = 0;
		}
		player_left = null;
		player_left = new int[nb_player];
		for (int i = 0; i < nb_player ; i += 1)
		{
			player_left[i] = 16;
		}

	}
	
	public void click_control(Cell clickedcell)
	{

		if (clickedcell.getPlayer() == player_turn)
		{	
			last_pos = clickedcell; 
			if(case_number < 3)
			{
				if (verify_direction(clickedcell))
				{
					save_case();
					calc_highlited(clickedcell);
					clickedcell.setSelected(true);
					verify_case();
					case_number += 1;
					print_case_free();
				}
			}
		}
	}
	
	public boolean verify_direction(Cell clickedcell)
	{
		int new_direction = -20;
		if (case_number > 0)
		{                        
			for (int inc = 0; inc < case_number; inc += 1 )
			{
			for (int i = 0; i < 6; i += 1)
			{
				if (clickedcell.others[i] != null && case_to_move[inc].getBoardPos()[0] == clickedcell.others[i].getBoardPos()[0])
				{
					if (case_to_move[inc].getBoardPos()[1] == clickedcell.others[i].getBoardPos()[1])
					{
						new_direction = i;
					}
				}
			}
			}
		}
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
	{	int size_line = 5;
	int count = 1;
	for(int i = 0; i < 9; i++) {
		for(int j = 0; j < size_line; j++) {
			if (this.board[i][j].getSelected())
				System.out.println("case selected : " + this.board[i][j].getBoardPos()[0] + " ; " + this.board[i][j].getBoardPos()[1] );
		}
		if (size_line == 9)
			count *= -1;
		size_line += count;
	}
	}
	
	public void reset_case()
	{
		case_number = 0;
		case_to_move = null;
	}
	
	public void click_occured(Cell clickedcell)
	{	

		if (clickedcell.getPlayer() == -1 && case_number > 0)
		{

			last_pos = clickedcell; 
		
			if (move_case())
			{
			//affichage des cases disponibles
			if (player_turn == nb_player)
				player_turn = 1;
			else
				player_turn += 1;
			reset_case();
			case_number = 0;
			verify_victory();
			}
		
			
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
					
					if (case_to_move[case_number - 1].others[i] != null && case_to_move[case_number - 1].others[i].getBoardPos()[0] == clickedcell.getBoardPos()[0])
					{
						if (case_to_move[case_number - 1].others[i].getBoardPos()[1] == clickedcell.getBoardPos()[1])
						{
							last_direction = i;
						}
					}
				}
			}
			
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
	
	private int find_direction()
	{
		int inc;
		Cell tmp_cell;
		int hist;
		int i = 0;
			for (inc = 0; inc < 6; inc += 1)
			{
				hist = inc;
				tmp_cell = case_to_move[i];
				for (int tmp = 0; tmp < case_number; tmp += 1)
				{
					
			
				if (tmp_cell != null && tmp_cell.others[inc] != null && last_pos.getBoardPos()[0] == tmp_cell.others[inc].getBoardPos()[0])
				{
					if (last_pos.getBoardPos()[1] == tmp_cell.others[inc].getBoardPos()[1])
						return inc;
				}
				tmp_cell = tmp_cell.others[inc];
				}
			}
		return -10;
	}
	

	public void nextTurn() {
		int size_line = 5;
		int count = 1;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < size_line; j++) {
				if (this.board[i][j].getHighlighted())
					this.board[i][j].setHighlight(false);
				if (this.board[i][j].getSelected())
					this.board[i][j].setSelected(false);
			}
			if (size_line == 9)
				count *= -1;
			size_line += count;
		}
	}
		
	private void save_case()
	{

		if (case_to_move == null)
		{
			last_direction = -10;
			case_to_move = new Cell[3];
			for (int i = 0; i < 3; i +=1)
			{
				for(int j = 0; j < 2; j += 1)
				{
					case_to_move[i] = null;
				}
			}
		}
		case_to_move[case_number] = last_pos;

			

	}
	
	/*private boolean check_if_good()
	{
	/*	for (int i = 0; i < 6; i += 1)
		{
			if (case_to_move[0].others[i].getBoardPos()[0] == )
		}
	}*/
	
	private boolean move_case()
	{
	
		Cell tmp = null;
		boolean finish = false;
		int direction = find_direction();
		player_save = null;
		player_save = new int[3][3];
		int inc;
		int tmp_int;
		int coordx;
		int coordy;
		int old = -1;

		for (inc = 0; inc < 3; inc += 1)
		{
			player_save[inc][0] = -2;
			player_save[inc][1] = -1;
			player_save[inc][2] = -1;
		}
			if (last_pos.getHighlighted())
				{
				coordx = case_to_move[0].getBoardPos()[0];
				coordy = case_to_move[0].getBoardPos()[1];
			while (!finish)
			{
				
				for (int i = 0; i < case_number; i += 1)
				{
					boolean check = false;
					if (case_to_move[i].getPlayer() != -1 && !case_to_move[i].getSelected())
					{
						System.out.println(" entry " + case_to_move[i].getBoardPos()[0] + " ; " + case_to_move[i].getBoardPos()[1]);
						for (inc = 0; inc < 3 && !check; inc += 1)
						{
							if (player_save[inc][0] == -2)
							{
								player_save[inc][0] = case_to_move[i].getPlayer();
								player_save[inc][1] = case_to_move[i].others[direction].getBoardPos()[0];
								player_save[inc][2] = case_to_move[i].others[direction].getBoardPos()[1];
								check = true;
							}
						}
					
					}
					check = false;
					for(inc = 0; inc < 3 && !check; inc += 1)
					{
						if (player_save[inc][1] == case_to_move[i].getBoardPos()[0] && player_save[inc][2] == case_to_move[i].getBoardPos()[1])
						{
							check = true;
							case_to_move[i].setPlayer(player_save[inc][0]);
							player_save[inc][0] = -2;
							player_save[inc][1] =-1;
							player_save[inc][2] = -1;

						}
					}
					if (check == false)
						case_to_move[i].setPlayer(-1);
					
					case_to_move[i].others[direction].setPlayer(player_turn);
					case_to_move[i] = case_to_move[i].others[direction];
					if (i == 0)
					{
						coordx = case_to_move[0].getBoardPos()[0];
						coordy = case_to_move[0].getBoardPos()[1];
					}
				}

				if (coordx == last_pos.getBoardPos()[0] && coordy == last_pos.getBoardPos()[1])
				{
					last_pos.setPlayer(player_turn);
					finish = true;
				}
			}
			for (inc = 0; inc < 3 ; inc += 1)
			{
				if (player_save[inc][0] != -2)
				{
					if (this.board[player_save[inc][1]][player_save[inc][2]] != null)
						this.board[player_save[inc][1]][player_save[inc][2]].setPlayer(player_save[inc][0]);
					else
					{
						player_push[player_turn-1] += 1;
						player_push[player_save[inc][0] - 1] -= 1;
						menu.updateMenu(player_turn, player_left, player_push);
						
						
					}
				}


			}
			nextTurn();
			return true;
			}
			return false;

	}
	
	private void print_case_free()
	{
		Cell tmp;
		//case vide
		for (int i = 0; i < 6; i += 1)
		{
			tmp = case_to_move[0].others[i];
			for (int inc = 0; inc < case_number; inc += 1)
			{
			if (tmp != null && !tmp.getSelected())
				tmp.setHighlight(true);
			if (tmp != null && tmp.others[i] != null)
				tmp = tmp.others[i];
			}
		}
	}
	
	private void verify_victory()
	{
		for (int i = 0; i < nb_player; i += 1)
		{
			if (player_push[i] >= 6)
				menu.showMsgEnd(i + 1);
			
		}
	}
	
	private void setPlayer(int play)
	{
		play = player_turn;
	}
	private Menu menu;
	
	private int[][] player_save;
	private int[] player_push;
	private int[] player_left;
	private Cell[][] board;
	private Cell[] highlited;
	
	private int last_direction;
	private int case_number;
	private Cell[] case_to_move;
	private int player_turn;
	private Cell last_pos;
	private int nb_player;
	
}