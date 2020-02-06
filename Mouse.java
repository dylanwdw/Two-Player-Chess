import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter
{
	
	private int clickCount = 0;
	private Piece primedPiece;
	private int tileSize = ((int)(Game.resolution/Game.scalingFactor)/8);
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		int x = (int)(e.getX()/tileSize);
		int y = (int)(e.getY()/tileSize);
		
		if(clickCount == 0)
		{
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && Board.board[y][x] != null && Board.board[y][x].getTeam() == Game.turn)
			{
				clickCount = 1;
				primedPiece = Board.board[y][x];
			}	
		}
		else if(clickCount == 1 && x >= 0 && x <= 7 && y >= 0 && y <= 7 && Board.board[y][x] != null && Board.board[y][x].getTeam().equals(Game.turn))
		{
			primedPiece = Board.board[y][x];
		}
		else if(clickCount == 1)
		{
			if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && primedPiece.getLegalMoves()[y][x] != 0)
			{
				clickCount = 0;
				Game.move(primedPiece, x, y);
			}
		}
	}
	
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
