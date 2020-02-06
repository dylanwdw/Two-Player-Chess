import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window extends Canvas implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Canvas canvas;
	private Mouse mouseListener = new Mouse();
	
	private double scalingFactor = Game.scalingFactor;
	private int screenSize = (int)(Game.resolution/scalingFactor);
	
	ImageIcon myImage = new ImageIcon(getClass().getResource("blackBishop.png"));
	Image myImage2 = myImage.getImage();
	
	public Window()
	{
		this.init();
	}
	
	private void init()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize, screenSize);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(myImage2);
		this.setCanvas();
		frame.setVisible(true);
	}

	private void setCanvas()
	{
		canvas = new Canvas();
		canvas.setSize(screenSize, screenSize);
		canvas.addMouseListener(mouseListener);
		frame.add(canvas);
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
