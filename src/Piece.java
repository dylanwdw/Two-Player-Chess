import java.awt.Image;

// This class defines the behavior of every piece. 
public abstract class Piece
{
	protected Team team;
	protected int xCord;
	protected int yCord;
	protected boolean firstMove = true;
	
	protected int[][] possibleMoves; //an array of all the possible moves the piece can make, ignoring whether or not the piece's team's king is in check.
	protected int[][] legalMoves; // an array of all legal moves the piece can make. if this team's king is in check, a move is only legal if it removes the king from danger.
	
	private static int blackSide = 0;
	private static int whiteSide = Board.boardWidth-1;
	
	//This constructor is only called by subclasses.
	public Piece(int xCord, int yCord, Team team)
	{
		this.xCord = xCord;
		this.yCord = yCord;
		this.team = team;
	}
	
	public abstract String getName(); //returns the type of piece as a String.
	
	/*
	 * calculates all possible moves, ignoring whether or not the king is in check.
	 * 0 = not a possible move, 1 = possible move, 2 = possible capture.
	 */
	protected abstract int[][] calculatePossibleMoves();

	protected abstract Image getImage(); //returns the graphic representation of the piece to be displayed on the chess board.
	
	//returns the side (top or bottom) on which the pieces line up at the beginning of the game
	public int getStartingSide()
	{
		if(this.team == Team.BLACK)
		{
			return blackSide; //represent the top of the board
		}
		return whiteSide; //represents the bottom of the board
	}
	
	//If this piece's team's king is in check, only moves that remove the king from danger are legal. Otherwise, this array is identical to the possibleMoves array.
	protected int[][] calculateLegalMoves()
	{
		/*
		 * this function temporarily moves this piece to every possible move.
		 * then, it is determined if any of the opposing teams pieces could take the king after this move is made
		 * if so, then this is not a legal move. Otherwise, it is.
		 * a 0 represents a non-legal move, a 1 represents a legal move, and a 2 represents a legal capture.
		 */
		
		Piece king = Game.getKing(); //returns the king of the team who is currently taking a turn
		int[][] movesArray = this.possibleMoves;
		
		for(int x=0; x<8; x++) //loops through all possible moves
		{
			for(int y=0; y<8; y++)
			{
				if(movesArray[y][x] != 0) 
				{
					Piece tempPiece = Board.board[y][x]; //if there is a piece at a potential position, it is stored here
					Board.board[y][x] = this; //this piece is placed in all possible positions
					Board.board[this.yCord][this.xCord] = null; 
					int oldX = this.xCord;
					int oldY = this.yCord;
					this.xCord = x;
					this.yCord = y;
										
					for(int x2=0; x2<8; x2++) //loops through every spot on the board
					{
						for(int y2=0; y2<8; y2++)
						{
							if(Board.board[y2][x2] != null && Board.board[y2][x2].team != Game.turn) //if there is a piece on the opposing team at this spot
							{
								if(Board.board[y2][x2].calculatePossibleMoves()[king.yCord][king.xCord] == 2) //if it could take the king
								{
									movesArray[y][x] = 0; //this is no longer a legal move
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
	
	//recalculates the possible and legal moves.
	public void updateArrays()
	{
		this.possibleMoves = this.calculatePossibleMoves();
		this.legalMoves = this.calculateLegalMoves();
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
	
	public int[][] getLegalMoves()
	{
		return this.legalMoves;
	}
	
	public int[][]getPossibleMoves()
	{
		return this.possibleMoves;
	}
}

