import java.awt.Image;

import javax.swing.ImageIcon;

public class Rook extends Piece
{	
	public Rook(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	public String getName()
	{
		return "Rook";
	}
	
	protected Image getImage() 
	{
		Image icon;
		if(this.team == Team.WHITE) 
		{
			icon = new ImageIcon(getClass().getResource("whiteRook.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackRook.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves()
	{
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth];
		
		//from current location to left
		for(int x=this.xCord-1; x>=0; x--)
		{
			if(Board.board[this.yCord][x] != null)
			{
				if(Board.board[this.yCord][x].team != this.team)
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
			if(Board.board[this.yCord][x] != null)
			{
				if(Board.board[this.yCord][x].team != this.team)
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
				if(Board.board[y][this.xCord].team != this.team)
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
				if(Board.board[y][this.xCord].team != this.team)
				{
					movesArray[y][this.xCord] = 2;
				}
				break;
			}
			movesArray[y][this.xCord] = 1;
		}
		movesArray[this.yCord][this.xCord] = 0;
		return movesArray;
	}
}
