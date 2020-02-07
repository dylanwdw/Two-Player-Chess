/*
 * This class's main purpose is to initialize all of the chess pieces and keep track of the state of the game.
 * This class also detects whether a king is in check or checkmate, or if the game has ended in a draw.
 */
public class Game 
{
	
	public static final int resolution = 640;
	public static final double scalingFactor = 1.25; //This value can be changed to your monitor's current scaling factor.
	
	public static Team turn = Team.WHITE;
	public static Piece[] whitePieces = new Piece[16]; //contains all the white pieces
	public static Piece[] blackPieces = new Piece[16]; //contains all the black pieces
	public static Team winner; //initalized when there is a winner
	public static boolean checkKing = false; //set to true if the current turn's team is in check
	public static boolean checkMate = false; //set to true if the current turn's team is in check mate
	public static boolean checkDraw = false; //set to true in the case of a draw
	
	//update the current turn after every move
	public static void updateTurn() 
	{
		if(turn == Team.WHITE)
		{
			turn = Team.BLACK;
			return;
		}
		turn = Team.WHITE;
	}
	
	//returns the king of the current turn's team.
	public static Piece getKing()
	{
		if(turn == Team.WHITE)
			return whitePieces[12];
		return blackPieces[12];
	}
	
	//initializes every piece with their legal starting positions
	public static void generatePieces()
	{
		//Initializes all the pawns
		for(int x=0; x<Board.boardWidth; x++)
		{
			whitePieces[x] = new Pawn(x, Board.boardWidth-2, Team.WHITE);
			blackPieces[x] = new Pawn(x, 1, Team.BLACK);
		}
		
		//initializes all of the non-pawn pieces
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
	
	/*
	 * Moves a specified piece to a specified new position
	 * Sets the old position to null
	 * calls the updateTurn() function
	 * updates the game states
	 */
	public static void move(Piece piece, int newX, int newY)
	{
		Board.board[piece.yCord][piece.xCord] = null; //old position
		Board.board[newY][newX] = piece; //new position
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
		//re-checks the three possible game states since a new move was made
		checkKing(); 
		checkMate(); 
		checkDraw();
	}
	
	//returns true if current player's king is in check
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
	
	//returns true if current player's king is in check mate
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
	
	//returns true in the case of a draw (no legal moves, but king is not in check)
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
	
	//returns the color of the current turn's team as a string
	public static String getTurnAsString()
	{
		if (turn == Team.BLACK)
		{
			return "Black";
		}
		return "White";
	}
	
	
}
