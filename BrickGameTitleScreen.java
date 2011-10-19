// The "MovingBallApplet5" class.
import java.applet.*;
import java.awt.*;
import java.awt.Font;

public class BrickGameTitleScreen extends Applet implements Runnable
{
    // Place instance variables here
    // Initialization of variables

    int appletsize_x = 500;
    int appletsize_y = 600;
    int posx_new = 180;
    int posy_new = 250;
    int speedy = 0;
    int posx_diffi = 180;
    int posy_diffi = 300;
    int posx_controls = 180;
    int posy_controls = 350;
    int posx_credits = 180;
    int posy_credits = 400;
    int posx_highscores = 180;
    int posy_highscores = 450;
    int posx_cursor = 180;
    int posy_cursor = 250;
    boolean gamePlaying = false;


    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    public void init ()
    {
	resize (500, 600);
	// Place the body of the initialization method here
	setBackground (Color.black);


    } // init method


    public void start ()
    {
	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();
    }


    public void run ()
    {
	// lower ThreadPriority

	while (true)
	{

	    // Ball is bounced if its x - position reaches the right border of the applet
	    if (posy_cursor < posy_new)
	    {

		// Change direction of ball movement
		posy_cursor += 50;

	    }
	    // Ball is bounced if its x - position reaches the left border of the applet
	    else if (posy_cursor > posy_highscores)
	    {

		// Change direction of ball movement
		posy_cursor -= 50;

	    }

	    posy_cursor += speedy;

	    // repaint the applet
	    repaint ();



	    try
	    {
		// Stop thread for 20 milliseconds
		Thread.sleep (20);
	    }
	    catch (InterruptedException ex)
	    {
		// do nothing
	    }

	    // set ThreadPriority to maximum value
	    Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	}

    }






    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}

	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    // method to handle key - down events
    public boolean keyDown (Event e, int key)
    {
	if (key == Event.UP)
	{
	    // the right paddle moves 20 pixels up
	    posy_cursor -= 50;


	}
	// user presses down cursor key
	else if (key == Event.DOWN)
	{
	    // the right paddle moves 20 pixels down
	    posy_cursor += 50;
	}

	else if (key == Event.ENTER)
	{
	    // the right paddle moves 20 pixels down
	    if (posy_cursor < 250)
	    {
		gamePlaying = true;
	    }

	}
	System.out.print (posy_cursor);



	return true;

    }



    public void paint (Graphics g)
    {
	//color the screen
	g.setColor (Color.red);
	g.fillRect (posx_cursor - 5, posy_cursor - 5, 150, 50);
	g.fillRect (posx_cursor + 5, posy_cursor - 5, 150, 50);


	// Place the body of the drawing method here
	g.setColor (Color.white);
	g.setFont (new Font ("Vineta BT", Font.ITALIC, 50));
	g.drawString ("SUPER", 125, 80);
	g.drawString ("SMASH", 120, 130);
	g.drawString ("BRICKS", 115, 180);
	g.setFont (new Font ("Vineta BT", Font.PLAIN, 50));
	g.drawString ("SUPER", 125, 80);
	g.drawString ("SMASH", 120, 130);
	g.drawString ("BRICKS", 115, 180);


	g.setFont (new Font ("Arial", Font.PLAIN, 20));
	g.setColor (Color.white);
	g.fillRect (posx_new, posy_new, 150, 40);
	g.setColor (Color.black);
	g.fillRect (185, 255, 140, 30);
	g.setColor (Color.white);
	g.setFont (new Font ("Imprint MT Shadow", Font.PLAIN, 20));
	g.drawString ("New Game", 205, 275);


	g.setColor (Color.white);
	g.fillRect (posx_diffi, posy_diffi, 150, 40);
	g.setColor (Color.black);
	g.fillRect (185, 305, 140, 30);
	g.setColor (Color.white);
	g.drawString ("Difficulty", 215, 325);

	g.setColor (Color.white);
	g.fillRect (posx_controls, posy_controls, 150, 40);
	g.setColor (Color.black);
	g.fillRect (185, 355, 140, 30);
	g.setColor (Color.white);
	g.drawString ("Controls", 217, 375);

	g.setColor (Color.white);
	g.fillRect (posx_credits, posy_credits, 150, 40);
	g.setColor (Color.black);
	g.fillRect (185, 405, 140, 30);
	g.setColor (Color.white);
	g.drawString ("Credits", 222, 425);

	g.setColor (Color.white);
	g.fillRect (posx_highscores, posy_highscores, 150, 40);
	g.setColor (Color.black);
	g.fillRect (185, 455, 140, 30);
	g.setColor (Color.white);
	g.drawString ("High Scores", 205, 475);

	g.setFont (new Font ("Arial", Font.BOLD, 15));
	g.setColor (Color.white);
	g.drawString ("Instructions:", 218, 520);
	g.setFont (new Font ("Arial", Font.PLAIN, 15));
	g.drawString ("Scroll to the appropriate button and hit [Enter] to continue.", 70, 535);
	g.drawString ("Press [Esc] to return back to Main Menu.", 130, 550);



    } // paint method
} // MovingBallApplet5 class
