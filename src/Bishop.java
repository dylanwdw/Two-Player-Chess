import java.awt.Image;

import javax.swing.ImageIcon;

public class Bishop extends Piece
{	
	public Bishop(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	//returns the type of piece as a string
	public String getName()
	{
		return "Bishop";
	}
	
	protected Image getImage() //graphic representation of the Bishop
	{
		Image icon;
		if(this.team == Team.WHITE)
		{
			icon = new ImageIcon(getClass().getResource("whiteBishop.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackBishop.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves() //returns all possible moves. 0 = current position, 1 = impossible move, 2 = possible move.
	{
		boolean restUnaccesable = false; //when a piece is detected in the path, this is set to true.
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth]; 
		
		//Tests the diagonal path from the bishop's current position to the top left of the board. (0,0) = top left
		int x = this.xCord-1;
		int y = this.yCord-1;
		
		while(x>=0 && y>=0)
		{
			if(Board.board[y][x] != null || restUnaccesable == true) //tests if there is another piece in the way
			{
				if(restUnaccesable == false && Board.board[y][x].team != this.team)
				{
					movesArray[y][x] = 2;
				}
				restUnaccesable = true;
			}
			else
			{
				movesArray[y][x] = 1;
			}
			x--; 
			y--;
		}
		restUnaccesable = false;
		
		//current location to top right
		x = this.xCord+1;
		y = this.yCord-1;
		while(x<Board.boardWidth && y>=0)
		{
			if(Board.board[y][x] != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].team != this.team)
				{
					movesArray[y][x] = 2;
				}
				restUnaccesable = true;
			}
			else
			{
				movesArray[y][x] = 1;
			}
			x++;
			y--;
		}
		restUnaccesable = false;
		
		//current location to bottom left
		x = this.xCord-1;
		y = this.yCord+1;
		while(x>=0 && y<Board.boardWidth)
		{
			if(Board.board[y][x] != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].team != this.team)
				{
					movesArray[y][x] = 2;
				}
				restUnaccesable = true;
			}
			else
			{
				movesArray[y][x] = 1;
			}
			x--;
			y++;
		}
		restUnaccesable = false;
		
		//current location to bottom right
		x = this.xCord+1;
		y = this.yCord+1;
		while(x<Board.boardWidth && y<Board.boardWidth)
		{
			if(Board.board[y][x] != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].team != this.team)
				{
					movesArray[y][x] = 2;
				}
				restUnaccesable = true;
			}
			else
			{
				movesArray[y][x] = 1;
			}
			x++;
			y++;
		}
		restUnaccesable = false;
		
		movesArray[this.yCord][this.xCord] = 0; // sets current position to 0
		return movesArray;
	}
}
