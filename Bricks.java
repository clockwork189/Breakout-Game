// The "PongGameX" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class Bricks extends Applet implements Runnable, MouseMotionListener
{
    // Place instance variables here
    final int numBricksX = 10;
    final int numBricksY = 10;
    int xInc = 2, yInc = 1;
    int xPos = 0, yPos = 0;
    Brick[] [] brickWall;
    int mouseX;
    Paddle pad;
    Ball ball;
    int appletsize_x = 500;
    int appletsize_y = 600;
    private Image dbImage;
    private Graphics dbg;
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
    int t = 1;
    boolean gamePlaying = true, mainMenu = false;
    final private int RIGHT = 10; //Identifies the RIGHT variable at 10
    final private int UP = 20; //Identifies the UP variable at 20
    final private int LEFT = 30; //Identifies the LEFT variable as 30
    final private int DOWN = 40; //Identifies the DOWN variable as 40
    final private int MIDDLE = 0; //Identifies the DOWN variable as 40

    final private int UPLEFT = 25; //Identifies UPLEFT between UP and LEFT at 25
    final private int UPRIGHT = 15; //Identifies UPRIGHT between UP and RIGHT at 15
    final private int DOWNLEFT = 35; // Identifies DOWNLEFT between DOWN and LEFT at 35
    final private int DOWNRIGHT = 45; //Identifies DOWNLEFT at 45
    public void init ()
    {
	setBackground (Color.black);
	mouseX = 300;
	pad = new Paddle ();
	ball = new Ball ();
	addMouseMotionListener (this);
	resize (appletsize_x, appletsize_y);
	brickWall = new Brick [numBricksX] [numBricksY];
	createBricks ();
    } // init method


    public void start ()
    {
	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();
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


    void createBricks ()
    {
	for (int x = 0 ; x < numBricksX ; x++)
	{
	    yPos = 50;
	    xPos += xInc + 40;

	    for (int y = 0 ; y < numBricksY ; y++)
	    {
		brickWall [x] [y] = new Brick (1, 1);
		yPos += yInc + brickWall [x] [y].brickHeight ();
		brickWall [x] [y].changeX (xPos);
		brickWall [x] [y].changeY (yPos);
	    }
	}
    }


    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
    }


    public void run ()
    {
	while (true)
	{
	    if (t < 5)
	    {
		t++;
	    }
	    else
	    {
		t = 0;
	    }
	    if (mainMenu)
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
	    }
	    else if (gamePlaying)
	    {
		ball.move ();
		if (ball.y_Pos () < 0)
		{
		    ball.reflectVertically ();


		}
		else if ((ball.x_Pos () < 0) || (ball.x_Pos () > appletsize_x))
		{
		    ball.reflectHorizontally ();


		}

		if (ball.y_Pos () > 590)
		{

		    if ((ball.x_Pos () > pad.x () - ball.radius ()) && ((ball.x_Pos () < pad.x () + (pad.width () / 2))))
		    {
			ball.reflectFromPaddle (LEFT);
		    }
		    else if ((ball.x_Pos () > pad.x () + ( pad.width () / 2)) && ((ball.x_Pos () < pad.x () + pad.width + ball.radius ())))
		    {
			ball.reflectFromPaddle (RIGHT);
		    }
		    else
		    {
			ball.startBall ();
		    }

		}

		for (int x = 0 ; x < numBricksX ; x++)
		{
		    for (int y = 0 ; y < numBricksY ; y++)
		    {
			if (brickWall [x] [y].notBroken ())
			{
			    if (brickWall [x] [y].ballContactVertical (ball.x_Pos (), ball.y_Pos ()))
			    {
				ball.reflectVertically ();
				brickWall [x] [y].reduceLife ();
				brickWall [x] [y].startDropping ();
			    }
			    else if (brickWall [x] [y].ballContactHorizontal (ball.x_Pos (), ball.y_Pos ()))
			    {
				ball.reflectHorizontally ();
				brickWall [x] [y].reduceLife ();
				brickWall [x] [y].startDropping ();
			    }

			}
		    }
		}

	    }
	    if (t == 5)
	    {
		for (int x = 0 ; x < numBricksX ; x++)
		{
		    for (int y = 0 ; y < numBricksY ; y++)
		    {
			brickWall [x] [y].dropPowerUp ();
			if (brickWall [x] [y].paddleContact (pad.xPos, 590))
			{
			    pad.widthIncrease ();
			}
		    }
		}
	    }

	    try
	    {
		// Stop thread for 20 milliseconds
		Thread.sleep (2);
	    }
	    catch (InterruptedException ex)
	    {
		// do nothing
	    }

	    repaint ();

	}
    }


    public void mouseMoved (MouseEvent e)
    { // called during motion when no buttons are down
	//  if (e.getY ==
	if ((e.getX () < appletsize_x - pad.width () / 2) && (e.getX () > (pad.width () / 2)))
	{
	    pad.changeX (e.getX () - (pad.width () / 2));
	    ball.moveWithPaddle (pad.xPos + (pad.width () / 2) - (ball.radius ()));


	}
	e.consume ();
	repaint ();
    }


    public void mouseDragged (MouseEvent e)
    { // called during motion with buttons down

	ball.LaunchBall ();

	e.consume ();
	repaint ();
    }




    public void drawTitleScreen (Graphics g)
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



    }


    void drawBricks (Graphics g)
    {
	for (int x = 0 ; x < numBricksX ; x++)
	{
	    for (int y = 0 ; y < numBricksY ; y++)
	    {
		if (brickWall [x] [y].notBroken ())
		{
		    g.setColor (brickWall [x] [y].colour ());
		    g.fillRect (brickWall [x] [y].brickX (), brickWall [x] [y].brickY (), brickWall [x] [y].brickWidth (), brickWall [x] [y].brickHeight ());
		}
	    }
	}
    }


    public void paint (Graphics g)
    {
	if (mainMenu)
	{
	    drawTitleScreen (g);
	}
	else if (gamePlaying)
	{
	    drawBricks (g);
	    g.setColor (Color.green);
	    g.fillRect (pad.x (), 590, pad.width (), 10);
	    g.fillOval (ball.x_Pos () - ball.radius (), ball.y_Pos () - ball.radius (), ball.radius () * 2, ball.radius () * 2);
	}
    } // paint method
} // PongGameX class


