import java.awt.Image;

import javax.swing.ImageIcon;

public final class Pawn extends Piece
{
	public Pawn(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	public String getName()
	{
		return "Pawn";
	}
	
	protected Image getImage() //graphic representation of thr Pawn
	{
		Image icon;
		if(this.team == Team.WHITE)
		{
			icon = new ImageIcon(getClass().getResource("whitePawn.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackPawn.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves() //returns all possible moves. 0 = current position, 1 = impossible move, 2 = possible move.
	{
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth];
		
		//The pawn has a special move where it can move two spots if it is it's first move.
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				if(this.team == Team.WHITE)
				{
					if(firstMove == true && y == 4 && x == this.xCord && Board.board[y][x] == null && Board.board[5][x] == null)
					{
						movesArray[y][x] = 1;
					}
					if(y == this.yCord-1 && x == this.xCord && Board.board[y][x] == null)
					{
						movesArray[y][x] = 1;
					}
				}
				else
				{
					if(firstMove == true && y == 3 && x == this.xCord && Board.board[y][x] == null && Board.board[2][x] == null)
					{
						movesArray[y][x] = 1;
					}
					if(y == this.yCord+1 && x == this.xCord && Board.board[y][x] == null)
					{
						movesArray[y][x] = 1;
					}
				}
				if(Math.abs(this.getY() - this.getStartingSide()) < Math.abs(y - this.getStartingSide()))
				{
					if(Math.abs(y - this.yCord) == 1 && Math.abs(x - this.xCord) == 1)
					{
						if(Board.board[y][x] != null && Board.board[y][x].team != this.team)
						{
							movesArray[y][x] = 2;
						}
					}
				}
			}
		}
		movesArray[this.yCord][this.xCord]= 0; 
		return movesArray;
	}
	
}