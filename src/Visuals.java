import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public class Visuals extends JComponent implements Runnable
{
	
	private static final long serialVersionUID = 1L;
	private Window window; //container for these graphics
	public static Graphics2D g2; 
	private BufferStrategy bs;
	public Graphics g;
	private static int fps = 10; //rate at which the thread runs
	private Thread thread;
	private boolean isRunning = false; //keeps track of the state if the thread
	private Mouse mouseListener; //detects mouse clicks
	private Color myBrown = new Color(208, 140, 71);
	private Color myBeige = new Color(254, 206, 158);
	
	private double scalingFactor = Game.scalingFactor; 
	private int screenSize = (int)(Game.resolution/scalingFactor);
	private int tileSize = screenSize/8;
	private int imageSize = (int)(60/scalingFactor); //size of the chess piece icons
	private int imageOffset = (tileSize-imageSize)/2; //the position of the chess piece icons on the board may need to be altered slightly
	
	//Constructor: initialized the window and mouse listener, adds mouse listener to the window
	public Visuals()
	{
		window = new Window();
		mouseListener = window.getMouseListener();
		this.render();
		window.getFrame().addMouseListener(new Mouse());
	}

	//updates the graphics to their proper state/position. Repeatedly called by run() function
	public void render()
	{

		bs = window.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			window.getCanvas().createBufferStrategy(3);
			return;
		}
	
		g = bs.getDrawGraphics();
		
		g.clearRect(0,  0, screenSize, screenSize); //temporarily clears the graphics so they can be redrawn.
		
		//Main Loop
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				//draws checkerboard
				if((y%2 == 0 && x%2 != 0) || y%2 != 0 && x%2 == 0)
				{
					g.setColor(myBrown);
					g.fillRect((x*tileSize), (y*tileSize), tileSize, tileSize);
				}
				else
				{
					g.setColor(myBeige);
					g.fillRect((x*tileSize), (y*tileSize), tileSize, tileSize);
				}
				
				//displays the images of the pieces
				if(Board.board[y][x] != null)
				{
					g.drawImage(Board.board[y][x].getImage(), x*tileSize+imageOffset, y*tileSize+imageOffset, imageSize, imageSize, null);
				}
			}
		}
		
		//highlights the tiles for legal moves, if the user clicks on a piece.
		Piece piece = mouseListener.getPrimedPiece();
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				if(piece != null && mouseListener.getClickCount() == 1)
				{
					if(piece.getLegalMoves()[y][x] == 1)
					{
						g.setColor(Color.GRAY);
						g.fillOval((x*tileSize)+(tileSize*3/8), (y*tileSize)+(tileSize*3/8), tileSize/4, tileSize/4);
					}
					else if(piece.getLegalMoves()[y][x] == 2)
					{
						g.setColor(Color.GRAY);
						g.fillOval((x*tileSize)+(tileSize*3/8), (y*tileSize)+(tileSize*3/8), tileSize/4, tileSize/4);
					}
				}
			}
		}	
		
		bs.show();
		g.dispose();
		
		//changes the text at the top of the window to the proper state of the game (White's turn, Black's turn, checkmate, etc.)
		if(Game.checkMate == true)
		{
			if(Game.getTurnAsString().equals("White"))
				window.getFrame().setTitle("Checkmate - black wins.");
			else
				window.getFrame().setTitle("Checkmate - white wins.");
		}
		else if(Game.checkKing == true)
		{
			window.getFrame().setTitle(Game.getTurnAsString() + "'s King is in check.");
		}
		else if(Game.checkDraw == true)
		{
			window.getFrame().setTitle("Draw.");
		}
		else
		{
			window.getFrame().setTitle(Game.getTurnAsString() + "'s turn.");
		}
	}
	
	//repeatedly called by the thread
	public void run()
	{
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		while(isRunning)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			lastTime = now;
			if(delta >= 1)
			{
				render();
				delta=0; 
			}
		}
		stop();
	}
	
	//starts the thread
	public synchronized void start()
	{
		if(isRunning)
		{
			return;
		}
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//ends the thread
	public synchronized void stop()
	{
		if(!isRunning)
		{
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
