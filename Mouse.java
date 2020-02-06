import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter
{
	
	private int clickCount = 0; //Keeps track of how many times the mouse has been clicked, reset when new piece is clicked on
	private Piece primedPiece; //Keeps track of the piece that is currently "highlighted" by the player, or the piece that was last clicked on.
	private int tileSize = ((int)(Game.resolution/Game.scalingFactor)/8);
	
	@Override
	//called when a mouse button is released
	public void mouseReleased(MouseEvent e) 
	{
		// (0,0) = top left
		int x = (int)(e.getX()/tileSize);
		int y = (int)(e.getY()/tileSize);
		
		if(clickCount == 0) //re-assigns the primedPiece
		{
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && Board.board[y][x] != null && Board.board[y][x].getTeam() == Game.turn)
			{
				clickCount = 1;
				primedPiece = Board.board[y][x];
			}	
		}
		else if(clickCount == 1 && x >= 0 && x <= 7 && y >= 0 && y <= 7 && Board.board[y][x] != null && Board.board[y][x].getTeam().equals(Game.turn)) //if a new piece is clicked on, then that is the new primed piece
		{
			primedPiece = Board.board[y][x];
		}
		else if(clickCount == 1) //If the primedPiece has been assigned and the player clicks on a legal new position for that piece, then the piece is moved to that position.
		{
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && primedPiece.getLegalMoves()[y][x] != 0)
			{
				clickCount = 0; //clickCount must be reset for next player
				Game.move(primedPiece, x, y);
			}
		}
	}
	
	//needed for Visuals class so it can highlight the available moves for the piece the player has clicked on.
	public Piece getPrimedPiece()
	{
		if(primedPiece != null)
		{
			return primedPiece;
		}
		return null;
	}
	
	public int getClickCount()
	{
		return clickCount;
	}
	
}
