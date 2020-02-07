import java.awt.Image;

import javax.swing.ImageIcon;

public class Queen extends Piece
{
	
	public Queen(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	public String getName()
	{
		return "Queen";
	}
	
	protected Image getImage() //graphic representation of the Queen
	{
		Image icon;
		if(this.team == Team.WHITE)
		{
			icon = new ImageIcon(getClass().getResource("whiteQueen.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackQueen.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves() //returns all possible moves. 0 = current position, 1 = impossible move, 2 = possible move.
	{
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth];
		
		//from current location to left
		for(int x=this.xCord-1; x>=0; x--)
		{
			if(Board.getPiece(x, this.yCord) != null)
			{
				if(Board.board[this.yCord][x].getTeam() != this.team)
				{
					movesArray[this.yCord][x] = 2;
				}
				break;
			}
			movesArray[this.yCord][x] = 1;
		}
		
		//from current location to right
		for(int x=this.xCord+1; x<Board.boardWidth; x++)
		{
			if(Board.getPiece(x, this.yCord) != null)
			{
				if(Board.board[this.yCord][x].getTeam() != this.team)
				{
					movesArray[this.yCord][x] = 2;
				}
				break;
			}
			movesArray[this.yCord][x] = 1;
		}
		
		//from current location to bottom
		for(int y=this.yCord+1; y<Board.boardWidth; y++)
		{
			if(Board.getPiece(this.xCord, y) != null)
			{
				if(Board.board[y][this.xCord].getTeam() != this.team)
				{
					movesArray[y][this.xCord] = 2;
				}
				break;
			}
			movesArray[y][this.xCord] = 1;
		}
		
		//from current location to top
		for(int y=this.yCord-1; y>=0; y--)
		{
			if(Board.getPiece(this.xCord, y) != null)
			{
				if(Board.board[y][this.xCord].getTeam() != this.team)
				{
					movesArray[y][this.xCord] = 2;
				}
				break;
			}
			movesArray[y][this.xCord] = 1;
		}
		
		boolean restUnaccesable = false; //If the pathway is blocked by something, then the rest of the spots behind it do not need to be calculated.
	
		//Current Location to top Left
		int x = this.xCord-1;
		int y = this.yCord-1;
		while(x>=0 && y>=0)
		{
			if(Board.getPiece(x, y) != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].getTeam() != this.team)
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
			if(Board.getPiece(x, y) != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].getTeam() != this.team)
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
			if(Board.getPiece(x, y) != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].getTeam() != this.team)
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
			if(Board.getPiece(x, y) != null || restUnaccesable == true)
			{
				if(restUnaccesable == false && Board.board[y][x].getTeam() != this.team)
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
