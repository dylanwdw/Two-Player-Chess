import java.awt.Image;

import javax.swing.ImageIcon;

public class Bishop extends Piece
{	
	public Bishop(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	public String getName()
	{
		return "Bishop";
	}
	
	protected Image getImage()
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
	
	protected int[][] calculatePossibleMoves()
	{
		boolean restUnaccesable = false;
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth];
		
		//Current Location to top Left
		int x = this.xCord-1;
		int y = this.yCord-1;
		while(x>=0 && y>=0)
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
		
		movesArray[this.yCord][this.xCord] = 0;
		return movesArray;
	}
}
