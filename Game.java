
public class Game 
{
	
	public static int resolution = 640;
	public static double scalingFactor = 1.25;
	
	public static Team turn = Team.WHITE;
	public static Piece[] whitePieces = new Piece[16];
	public static Piece[] blackPieces = new Piece[16];
	public static Team winner;
	public static boolean checkKing = false;
	public static boolean checkMate = false;
	public static boolean checkDraw = false;
	
	public static void updateTurn()
	{
		if(turn == Team.WHITE)
		{
			turn = Team.BLACK;
			return;
		}
		turn = Team.WHITE;
	}
	
	public static Piece getKing()
	{
		if(turn == Team.WHITE)
			return whitePieces[12];
		return blackPieces[12];
	}
	
	public static void generatePieces()
	{
		
		for(int x=0; x<Board.boardWidth; x++)
		{
			whitePieces[x] = new Pawn(x, Board.boardWidth-2, Team.WHITE);
			blackPieces[x] = new Pawn(x, 1, Team.BLACK);
		}
		
		whitePieces[8] = new Rook(0, Board.boardWidth-1, Team.WHITE);
		whitePieces[15] = new Rook(7, Board.boardWidth-1, Team.WHITE);
		whitePieces[9] = new Knight(1, Board.boardWidth-1, Team.WHITE);
		whitePieces[14] = new Knight(6, Board.boardWidth-1, Team.WHITE);
		whitePieces[10] = new Bishop(2, Board.boardWidth-1, Team.WHITE);
		whitePieces[13] = new Bishop(5, Board.boardWidth-1, Team.WHITE);
		whitePieces[11] = new Queen(3, Board.boardWidth-1, Team.WHITE);
		whitePieces[12] = new King(4, Board.boardWidth-1, Team.WHITE);
		
		blackPieces[8] = new Rook(0, 0, Team.BLACK);
		blackPieces[15] = new Rook(7, 0, Team.BLACK);
		blackPieces[9] = new Knight(1, 0, Team.BLACK);
		blackPieces[14] = new Knight(6, 0, Team.BLACK);
		blackPieces[10] = new Bishop(2, 0, Team.BLACK);
		blackPieces[13] = new Bishop(5, 0, Team.BLACK);	
		blackPieces[11] = new Queen(3, 0, Team.BLACK);
		blackPieces[12] = new King(4, 0, Team.BLACK);
	}	
	
	public static void move(Piece piece, int newX, int newY)
	{
		
		Board.board[piece.yCord][piece.xCord] = null; 
		Board.board[newY][newX] = piece;
		piece.xCord = newX;
		piece.yCord = newY;
		piece.firstMove = false;
		updateTurn();
		
		//updates the arrays for each piece
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
		checkKing();
		checkMate();
		checkDraw();
	}
	
	//return true if current player's king is in check
	public static void checkKing()
	{
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				if(Board.board[y][x] != null && Board.board[y][x].getTeam() != turn)
				{
					if(Board.board[y][x].getLegalMoves()[getKing().getY()][getKing().getX()] == 2)
					{
						checkKing = true;
						return;
					}
				}
			}
		}
		checkKing = false;
	}
	
	//return true if current player's king is in check mate
	public static void checkMate()
	{
		if(checkKing == true)
		{
			for(int x=0; x<8; x++)
			{
				for(int y=0; y<8; y++)
				{
					if(Board.board[y][x] != null && Board.board[y][x].getTeam() == turn)
					{
						for(int x2=0; x2<8; x2++)
						{
							for(int y2=0; y2<8; y2++)
							{
								if(Board.board[y][x].getLegalMoves()[y2][x2] != 0)
								{
									return;
								}
							}
						}
					}
				}
			}
			checkMate = true;
		}
	}
	
	public static void checkDraw()
	{
		if(checkKing == false)
		{
			for(int x=0; x<8; x++)
			{	
				for(int y=0; y<8; y++)
				{
					if(Board.board[y][x] != null && Board.board[y][x].getTeam() == turn)
					{
						for(int x2=0; x2<8; x2++)
						{
							for(int y2=0; y2<8; y2++)
							{
								if(Board.board[y][x].getLegalMoves()[y2][x2] != 0)
								{
									checkDraw = false;
									return;
								}
							}
						}
					}
				}
			}
		}
		checkDraw = true;
	}
	
	public static String getTurnAsString()
	{
		if (turn == Team.BLACK)
		{
			return "Black";
		}
		return "White";
	}
	
	
}
