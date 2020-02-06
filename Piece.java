import java.awt.Image;

public abstract class Piece
{
	protected Team team;
	protected int xCord;
	protected int yCord;
	protected boolean firstMove = true;
	
	protected int[][] possibleMoves;
	protected int[][] legalMoves;
	
	private static int blackSide = 0;
	private static int whiteSide = Board.boardWidth-1;
	
	public Piece(int xCord, int yCord, Team team)
	{
		this.xCord = xCord;
		this.yCord = yCord;
		this.team = team;
	}
	
	public abstract String getName();
	
	protected abstract int[][] calculatePossibleMoves();

	protected abstract Image getImage();
	
	public int getStartingSide()
	{
		if(this.team == Team.BLACK)
		{
			return blackSide;
		}
		return whiteSide;
	}
	
	protected int[][] calculateLegalMoves()
	{
		Piece king = Game.getKing();
		int[][] movesArray = this.possibleMoves;
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				if(movesArray[y][x] != 0)
				{
					Piece tempPiece = Board.board[y][x];
					Board.board[y][x] = this;
					Board.board[this.yCord][this.xCord] = null;
					int oldX = this.xCord;
					int oldY = this.yCord;
					this.xCord = x;
					this.yCord = y;
					
					for(int x2=0; x2<8; x2++)
					{
						for(int y2=0; y2<8; y2++)
						{
							if(Board.board[y2][x2] != null && Board.board[y2][x2].team != Game.turn)
							{
								if(Board.board[y2][x2].calculatePossibleMoves()[king.yCord][king.xCord] == 2)
								{
									movesArray[y][x] = 0;
								}
							}
						}
					}
					
					this.xCord = oldX;
					this.yCord = oldY;
					Board.board[y][x] = tempPiece;
					Board.board[this.yCord][this.xCord] = this;
				}
			}
		}
		return movesArray;
	}
	
	public int getX() 
	{
		return xCord;
	}
	
	public int getY()
	{
		return yCord;
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	public void updateArrays()
	{
		this.possibleMoves = this.calculatePossibleMoves();
		this.legalMoves = this.calculateLegalMoves();
	}
	
	public int[][] getLegalMoves()
	{
		return this.legalMoves;
	}
	
	public int[][]getPossibleMoves()
	{
		return this.possibleMoves;
	}
}

