class GameLogic {
	
	//setselected true : highlight,  false, black
	
	public GameLogic(Cell[][] board)
	{
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
		player_push = new int[nb_player][2];
		for (int i = 0; i < nb_player ; i += 1)
		{
			player_push[i][0] = 0;
			player_push[i][1] = 16;
		}
	}
	
	public void click_control(Cell clickedcell)
	{

		if (clickedcell.getPlayer() == player_turn)
		{		//	System.out.println("begin");
			last_pos = clickedcell; 
			if(case_number < 3)
			{
				//System.out.println("first step");
				if (verify_direction(clickedcell))
				{
					//System.out.println("second step");
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
	{
		for (int i = 0; i <= case_number; i++)
		{
			System.out.println(case_to_move[i].getBoardPos()[0] +";" + case_to_move[i].getBoardPos()[1]);
		}
	}
	
	public void reset_case()
	{
		case_number = 0;
		case_to_move = null;
	}
	
	public void click_occured(Cell clickedcell)
	{	


		if (clickedcell.getPlayer() == -1)
		{

			last_pos = clickedcell; 
			System.out.println("cell : " + last_pos.getBoardPos()[0] + ";" + last_pos.getBoardPos()[1]);

			move_case();
			//affichage des cases disponibles
			if (player_turn == nb_player)
				player_turn = 1;
			else
				player_turn += 1;
			reset_case();
		}
		//verify_victory();
		case_number = 0;
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
	
	private int find_direction()
	{
		int inc;
		Cell tmp_cell;
		int hist;
		System.out.println("entryboucle "+ case_number);
		int i = 0;
	/*	for (int i = 0; i < case_number; i+=1)
		{*/
			for (inc = 0; inc < 6; inc += 1)
			{
				hist = inc;
				tmp_cell = case_to_move[i];
				for (int tmp = 0; tmp < case_number; tmp += 1)
				{
					
			
				if (tmp_cell != null && tmp_cell.others[inc] != null && last_pos.getBoardPos()[0] == tmp_cell.others[inc].getBoardPos()[0])
				{
					if (hist == inc)
						System.out.println("good "+ hist);
					else
						System.out.println(" not good");
					if (last_pos.getBoardPos()[1] == tmp_cell.others[inc].getBoardPos()[1])
						return inc;
				}
				tmp_cell = tmp_cell.others[inc];
				}
			}
		//}
		return -10;
	}
	

	public void nextTurn() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 5; j++) {
				if (this.board[i][j].getHighlighted())
					this.board[i][j].setHighlight(false);
				if (this.board[i][j].getSelected())
					this.board[i][j].setSelected(false);
			}
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
	
	private void move_case()
	{
		nextTurn();
		Cell tmp = null;
		boolean finish = false;
		int direction = find_direction();
		int coordx;
		int coordy;
		System.out.println("new direction " + direction);
		System.out.println("last direction " + last_direction);
			if (direction == last_direction || (last_direction == direction - 3) || (last_direction == direction + 3) 
					|| last_direction == 4 || last_direction == 1)
				{
				System.out.println("good direction");
				coordx = case_to_move[0].getBoardPos()[0];
				coordy = case_to_move[0].getBoardPos()[1];
			while (!finish)
			{
				for (int i = 0; i < case_number; i += 1)
				{

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
					System.out.println("youpi");
					finish = true;
				}
			}
				}
	}
	
	private void print_case_free()
	{
		Cell tmp;
		System.out.println("length case"+ case_number);
		//case vide
		for (int i = 0; i < 6; i += 1)
		{
			tmp = case_to_move[0].others[i];
			for (int inc = 0; inc < case_number; inc += 1)
			{
			if (tmp != null)
				tmp.setHighlight(true);
			if (tmp != null && tmp.others[i] != null)
				tmp = tmp.others[i];
			}
		}
		
	}
	
	/*private void verify_victory()
	{
		for (int i = 0; i < nb_player; i += 1)
		{
			if (player_push[i][0] >= 6)
				end_of_the_game();
			
		}
	}*/
	
	private void setPlayer(int play)
	{
		play = player_turn;
	}
	
	
	private int[][] player_push;
	private Cell[][] board;
	private Cell[] highlited;
	
	private int last_direction;
	private int case_number;
	private Cell[] case_to_move;
	private int player_turn;
	private Cell last_pos;
	private int nb_player;
	
}