public class Main 
{
	public static void main(String [] args)
	{
		Board myBoard = new Board(); //initialized a new Board object
		myBoard.getClass(); //removes warning that variable "myBoard" is not used
		Visuals v = new Visuals(); //initializes a new Visuals object
		v.start(); //starts the thread in the visuals class.
	}
}
