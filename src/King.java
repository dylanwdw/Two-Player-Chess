import java.awt.Image;

import javax.swing.ImageIcon;

public class King extends Piece
{
	public King(int xCord, int yCord, Team team)
	{
		super(xCord, yCord, team);
	}
	
	public String getName()
	{
		return "King";
	}
	
	protected Image getImage() //graphic representation of the king
	{
		Image icon;
		if(this.team == Team.WHITE)
		{
			icon = new ImageIcon(getClass().getResource("whiteKing.png")).getImage();
		}
		else
		{
			icon = new ImageIcon(getClass().getResource("blackKing.png")).getImage();
		}
		return icon;
	}
	
	protected int[][] calculatePossibleMoves() //returns all possible moves. 0 = current position, 1 = impossible move, 2 = possible move.
	{
		int[][] movesArray = new int[Board.boardWidth][Board.boardWidth];
		
		for(int x=0; x<Board.boardWidth; x++)
		{
			for(int y=0; y<Board.boardWidth; y++)
			{
				if(Math.abs(this.xCord-x) <= 1 && Math.abs(this.yCord-y) <= 1)
				{
					if(Board.board[y][x] == null)
					{
						movesArray[y][x] = 1;
					}
					else if(Board.board[y][x].team != this.team)
					{
						movesArray[y][x] = 2;
					}
				}
			}
		}
		movesArray[this.yCord][this.yCord] = 0;
		return movesArray;
	}
}