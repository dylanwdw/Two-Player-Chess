import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window extends Canvas implements ActionListener //ActionListener is the interface needed for mouse click detection.
{
	private static final long serialVersionUID = 1L;
	private JFrame frame; //A container for the canvas
	private Canvas canvas; //A container for graphics
	private Mouse mouseListener = new Mouse();
	
	private double scalingFactor = Game.scalingFactor;
	private int screenSize = (int)(Game.resolution/scalingFactor);
	
	ImageIcon myImage = new ImageIcon(getClass().getResource("blackBishop.png"));
	Image finalImage = myImage.getImage(); //This is the icon for the application.
	
	public Window()
	{
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program will terminate when window is closed
		frame.setSize(screenSize, screenSize);
		frame.setResizable(false); //window can not be stretched
		frame.setLocationRelativeTo(null); //window will be positioned in the middle of the screen
		frame.setIconImage(finalImage);
		this.setCanvas();
		frame.setVisible(true); //makes the window visible
	}

	private void setCanvas()
	{
		canvas = new Canvas(); //this is where the graphics are drawn to
		canvas.setSize(screenSize, screenSize);
		canvas.addMouseListener(mouseListener); //allows the canvas to detect mouse clicks
		frame.add(canvas); //adds the canvas to the frame container
		frame.pack();
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public Mouse getMouseListener()
	{
		return mouseListener;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// Required by interface but never needed for this program.
	}
	
}
