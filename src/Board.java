public class Board 
{
	public static Piece[][] board; // Keeps track of the positions of every piece on the board. [0][0] is the top left. If there is no piece, the position is null.
	public static final int boardWidth = 8; //number of tiles on the board. height variable is not needed since the board is a square.
	
	public Board()
	{
		board = new Piece[8][8];
		init();
	}
	
	//Initialized the board with all 32 pieces in their legal starting positions.
	private void init()
	{
		Game.generatePieces();
		for(int i = 0; i < 16; i++)
		{
			if(Game.whitePieces[i] != null)
				Board.add(Game.whitePieces[i]);
			if(Game.blackPieces[i] != null)
				Board.add(Game.blackPieces[i]);
		}
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				if(Board.board[y][x] != null)
				{
					Board.board[y][x].updateArrays();
				}
			}
		}
	}
	
	//puts a piece on the board. 
	public static void add(Piece piece) 
	{
		board[piece.getY()][piece.getX()] = piece;
	}
	
	//returns the piece at the specified position.
	public static Piece getPiece(int xCord, int yCord)
	{
		return board[yCord][xCord];
	}
	
}
