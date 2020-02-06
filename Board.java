public class Board 
{
	public static Piece[][] board;
	public static final int boardWidth = 8;
	
	public Board()
	{
		board = new Piece[8][8];
		init();
	}
	
	public static void add(Piece piece)
	{
		board[piece.getY()][piece.getX()] = piece;
	}
	
	public static Piece getPiece(int xCord, int yCord)
	{
		return board[yCord][xCord];
	}
	
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
	
}
