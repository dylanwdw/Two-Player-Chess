import java.awt.Image;

import javax.swing.ImageIcon;

public class Knight extends Piece
{	
	public Knight(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	//returns the piece type as a string
	public String getName()
	{
		return "Knight";
	}
	
	protected Image getImage() //graphic representation of the knight
	{
		Image icon;
		if(this.team == Team.WHITE)
		{
			icon = new ImageIcon(getClass().getResource("whiteKnight.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackKnight.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves() //returns all possible moves. 0 = current position, 1 = impossible move, 2 = possible move.
	{
	
		int movesArray[][] = new int[Board.boardWidth][Board.boardWidth];
		for(int x=0; x<Board.boardWidth; x++)
		{
			for(int y=0; y<Board.boardWidth; y++)
			{
				if(((Math.abs(x-this.xCord)==2) && (Math.abs(y-this.yCord)==1)) || ((Math.abs(y-this.yCord) == 2) && (Math.abs(x-this.xCord) == 1)))
				{
					if(Board.board[y][x] == null)
					{
						movesArray[y][x] = 1;	
					}
					else
					{
						if(!(Board.board[y][x].team.equals(this.team)))
						{
							movesArray[y][x] = 2;
						}
					}
				}
			}
		}
		movesArray[this.yCord][this.xCord] = 0;
		return movesArray;	
	}
}
