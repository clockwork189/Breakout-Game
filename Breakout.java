import java.awt.*;
import java.applet.Applet;

public class Breakout extends Applet implements Runnable
{
    Dimension d;

    Graphics goff;
    Image screen;
    Thread thethread;

    boolean ingame = false;
    boolean controls = false;
    boolean difficulty = false;

    int player1score;
    int ballx, bally;
    int batpos;
    int batdpos = 0;
    int balldx = 0, balldy = 0;
    int dxval;
    int ballsleft;
    boolean showtitle = true;
    boolean[] showbrick;
    int bricksperline;

    final int borderwidth = 5;
    final int batlength = 50;
    final int ballsize = 20;
    final int batheight = 10;
    final int scoreheight = 20;
    final int brickwidth = 40;
    final int brickheight = 15;
    final int brickspace = 2;
    final int backcol = 0x102040;
    final int numlines = 9;
    final int startline = 40;


    public void init ()
    {
	resize (477, 600);
	Graphics g;
	d = size ();
	setBackground (new Color (backcol));
	bricksperline = (d.width - 2 * borderwidth) / (brickwidth + brickspace);
	d.width = bricksperline * (brickwidth + brickspace) + (2 * borderwidth + 5);
	g = getGraphics ();
	showbrick = new boolean [bricksperline * numlines];
	GameInit ();
    }


    public void GameInit ()
    {
	batpos = (d.width - batlength) / 2;
	ballx = (d.width - ballsize) / 2;
	bally = (d.height - ballsize - scoreheight - 2 * borderwidth);
	player1score = 0;
	ballsleft = 5;
	dxval = 5;
	if (Math.random () < 0.5)
	    balldx = dxval;
	else
	    balldx = -dxval;
	balldy = -dxval;
	batdpos = 0;
	InitBricks ();
    }



    public void InitBricks ()
    {
	int i;
	for (i = 0 ; i < numlines * bricksperline ; i++)
	    showbrick [i] = true;
    }


    public boolean keyDown (Event e, int key)
    {
	if (ingame)
	{
	    if (key == Event.LEFT)
		batdpos = -3;
	    if (key == Event.RIGHT)
		batdpos = 3;
	    if (key == Event.ESCAPE)
		ingame = false;

	}
	else
	{
	    if (key == 'n' || key == 'N')
	    {
		ingame = true;
		GameInit ();
	    }

	    if (key == 'c' || key == 'C')
	    {
		controls = true;

	    }

	    if (key == 'd' || key == 'D')
	    {
		difficulty = true;

	    }

	    if (key == 'e' || key == 'E')
	    {
		//make ball slower

	    }

	    if (key == 'm' || key == 'M')
	    {
		//keep ball speed same

	    }

	    if (key == 'h' || key == 'H')
	    {
		//make ball faster

	    }

	}
	return true;
    }


    public boolean keyUp (Event e, int key)
    {
	if (key == Event.LEFT || key == Event.RIGHT)
	    batdpos = 0;
	return true;
    }


    public void paint (Graphics g)
    {
	String s;
	Graphics gg;


	if (goff == null && d.width > 0 && d.height > 0)
	{
	    screen = createImage (d.width, d.height);
	    goff = screen.getGraphics ();
	}
	if (goff == null || screen == null)
	    return;

	goff.setColor (new Color (backcol));
	goff.fillRect (0, 0, d.width, d.height);
	if (ingame)
	    PlayGame ();
	else
	    ShowIntroScreen ();
	g.drawImage (screen, 0, 0, this);
    }


    public void PlayGame ()
    {
	MoveBall ();
	CheckBat ();
	CheckBricks ();
	DrawPlayField ();
	DrawBricks ();
	ShowScore ();
    }


    public void ShowIntroScreen ()
    {


	if (showtitle)
	{

	    // Place the body of the drawing method here
	    goff.setColor (Color.white);
	    goff.setFont (new Font ("Vineta BT", Font.ITALIC, 50));
	    goff.drawString ("SUPER", 120, 80);
	    goff.drawString ("SMASH", 115, 130);
	    goff.drawString ("BRICKS", 110, 180);
	    goff.setFont (new Font ("Vineta BT", Font.PLAIN, 50));
	    goff.drawString ("SUPER", 120, 80);
	    goff.drawString ("SMASH", 115, 130);
	    goff.drawString ("BRICKS", 110, 180);

	    goff.setFont (new Font ("Arial", Font.PLAIN, 15));
	    goff.drawString ("Press [N] to start a new Game", 165, 250);
	    goff.setFont (new Font ("Arial", Font.PLAIN, 15));
	    goff.drawString ("Press [C] to see Controls", 180, 270);
	    goff.setFont (new Font ("Arial", Font.PLAIN, 15));
	    goff.drawString ("Press [D] to change Difficulty", 168, 290);
	    goff.setFont (new Font ("Arial", Font.PLAIN, 15));
	    goff.drawString ("Press [H] to see High Scores", 168, 310);

	    goff.setColor (Color.white);
	    goff.setFont (new Font ("Helvetica", Font.ITALIC, 14));
	    goff.drawString ("Game presented to you by:", 178, 350);
	    goff.setFont (new Font ("Tekton Pro", Font.ITALIC, 20));
	    goff.setColor (Color.red);
	    goff.drawString ("[SAMEER]", 80, 380);
	    goff.setFont (new Font ("Tekton Pro", Font.PLAIN, 20));
	    goff.drawString ("[SAMEER]", 80, 380);
	    goff.setColor (Color.white);
	    goff.setFont (new Font ("Tekton Pro", Font.ITALIC, 18));
	    goff.drawString ("&", 180, 380);
	    goff.setFont (new Font ("Tekton Pro", Font.PLAIN, 18));
	    goff.drawString ("&", 180, 380);
	    goff.setColor (Color.red);
	    goff.setFont (new Font ("Tekton Pro", Font.ITALIC, 20));
	    goff.drawString ("[AREEB]", 200, 380);
	    goff.setFont (new Font ("Tekton Pro", Font.PLAIN, 20));
	    goff.drawString ("[AREEB]", 200, 380);
	    goff.setColor (Color.white);
	    goff.setFont (new Font ("Tekton Pro", Font.ITALIC, 18));
	    goff.drawString ("&", 285, 380);
	    goff.setFont (new Font ("Tekton Pro", Font.PLAIN, 18));
	    goff.drawString ("&", 285, 380);
	    goff.setColor (Color.red);
	    goff.setFont (new Font ("Tekton Pro", Font.ITALIC, 20));
	    goff.drawString ("[CHARLES]", 305, 380);
	    goff.setFont (new Font ("Tekton Pro", Font.PLAIN, 20));
	    goff.drawString ("[CHARLES]", 305, 380);

	    if (controls == true)
	    {
		goff.setColor (new Color (backcol));
		goff.fillRect (120, 400, 260, 200);
		//difficulty = false;
		goff.setColor (Color.white);
		goff.setFont (new Font ("Helvetica", Font.PLAIN, 14));
		goff.drawString ("Controls are as follows:", 185, 420);
		goff.setFont (new Font ("Helvetica", Font.ITALIC, 14));
		goff.drawString ("Use the [Mouse] to control the Paddle", 135, 440);
		goff.drawString ("Click the [Mouse] to launch the Ball", 145, 460);


	    }

	    if (difficulty == true)
	    {

		goff.setColor (new Color (backcol));
		goff.fillRect (120, 400, 260, 200);
		//controls = false;
		goff.setColor (Color.white);
		goff.setFont (new Font ("Helvetica", Font.PLAIN, 14));
		goff.drawString ("To change the difficulty:", 185, 420);
		goff.setFont (new Font ("Helvetica", Font.ITALIC, 14));
		goff.drawString ("Press [E] for Easy", 200, 440);
		goff.drawString ("Press [M] for Medium", 192, 460);
		goff.drawString ("Press [H] for Hard", 200, 480);


	    }

	}
    }




    public void DrawBricks ()
    {
	int i, j;
	boolean nobricks = true;

	for (j = 0 ; j < numlines ; j++)
	{
	    for (i = 0 ; i < bricksperline ; i++)
	    {
		if (showbrick [j * bricksperline + i])
		{
		    nobricks = false;
		    goff.setColor (Color.red);
		    goff.fillRect ((borderwidth + i * (brickwidth + brickspace) + 3), startline + j * (brickheight + brickspace),
			    brickwidth, brickheight);
		}
	    }
	}


	if (nobricks)
	{
	    InitBricks ();
	    if (ingame)
		player1score += 100;
	}
    }


    public void DrawPlayField ()
    {
	goff.setColor (Color.white);
	goff.fillRect (0, 0, d.width, borderwidth);
	goff.fillRect (0, 0, borderwidth, d.height);
	goff.fillRect (d.width - borderwidth, 0, borderwidth, d.height);
	goff.fillRect (0, 595, d.width, borderwidth);
	goff.fillRect (batpos, d.height - 2 * borderwidth - scoreheight, batlength, batheight); // bat
	goff.fillOval (ballx, bally, ballsize, ballsize); // ball
    }


    public void ShowScore ()
    {
	String s;
	goff.setFont (new Font ("Helvetica", Font.BOLD, 14));
	goff.setColor (Color.white);

	s = "Score: " + player1score;
	goff.drawString (s, 40, d.height - 5);
	s = "Balls left: " + ballsleft;
	goff.drawString (s, d.width - 120, d.height - 5);
    }


    public void MoveBall ()
    {
	ballx += balldx;
	bally += balldy;
	if (bally <= borderwidth)
	{
	    balldy = -balldy;
	    bally = borderwidth;
	}


	if (bally >= (d.height - ballsize - scoreheight))
	{
	    if (ingame)
	    {
		ballsleft--;
		if (ballsleft <= 0)
		    ingame = false;
	    }
	    ballx = batpos + (batlength - ballsize) / 2;
	    bally = startline + numlines * (brickheight + brickspace);
	    balldy = dxval;
	    balldx = 0;
	}


	if (ballx >= (d.width - borderwidth - ballsize))
	{
	    balldx = -balldx;
	    ballx = d.width - borderwidth - ballsize;
	}


	if (ballx <= borderwidth)
	{
	    balldx = -balldx;
	    ballx = borderwidth;
	}
    }


    public void BatDummyMove ()
    {
	if (ballx < (batpos + 2))
	    batpos -= 3;
	else if (ballx > (batpos + batlength - 3))
	    batpos += 3;
    }


    public void CheckBat ()
    {
	batpos += batdpos;

	if (batpos < borderwidth)
	    batpos = borderwidth;
	else if (batpos > (d.width - borderwidth - batlength))
	    batpos = (d.width - borderwidth - batlength);

	if (bally >= (d.height - scoreheight - 2 * borderwidth - ballsize) &&
		bally < (d.height - scoreheight - 2 * borderwidth) &&
		(ballx + ballsize) >= batpos && ballx <= (batpos + batlength))
	{
	    bally = d.height - scoreheight - ballsize - borderwidth * 2;
	    balldy = -dxval;
	    balldx = CheckBatBounce (balldx, ballx - batpos);
	}
    }


    public int CheckBatBounce (int dy, int delta)
    {
	int sign;
	int stepsize, i = -ballsize, j = 0;
	stepsize = (ballsize + batlength) / 8;

	if (dy > 0)
	    sign = 1;
	else
	    sign = -1;

	while (i < batlength && delta > i)
	{
	    i += stepsize;
	    j++;
	}


	switch (j)
	{
	    case 0:
	    case 1:
		return -4;
	    case 2:
		return -3;
	    case 7:
		return 3;
	    case 3:
	    case 6:
		return sign * 2;
	    case 4:
	    case 5:
		return sign * 1;
	    default:
		return 4;
	}
    }




    public void CheckBricks ()
    {
	int i, j, x, y;
	int xspeed = balldx;
	if (xspeed < 0)
	    xspeed = -xspeed;
	int ydir = balldy;

	if (bally < (startline - ballsize) || bally > (startline + numlines * (brickspace + brickheight)))
	    return;
	for (j = 0 ; j < numlines ; j++)
	{
	    for (i = 0 ; i < bricksperline ; i++)
	    {
		if (showbrick [j * bricksperline + i])
		{
		    y = startline + j * (brickspace + brickheight);
		    x = borderwidth + i * (brickspace + brickwidth);
		    if (bally >= (y - ballsize) && bally < (y + brickheight) &&
			    ballx >= (x - ballsize) && ballx < (x + brickwidth))
		    {
			showbrick [j * bricksperline + i] = false;
			if (ingame)
			    player1score += (numlines - j);
			// Where did we hit the brick
			if (ballx >= (x - ballsize) && ballx <= (x - ballsize + 3))
			{ // leftside
			    balldx = -xspeed;
			}
			else if (ballx <= (x + brickwidth - 1) && ballx >= (x + brickwidth - 4))
			{ // rightside
			    balldx = xspeed;
			}
			balldy = -ydir;
		    }
		}
	    }
	}
    }



    public void run ()
    {
	long starttime;
	Graphics g;

	Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	g = getGraphics ();

	while (true)
	{
	    starttime = System.currentTimeMillis ();
	    try
	    {
		paint (g);
		starttime += 20;
		Thread.sleep (Math.max (0, starttime - System.currentTimeMillis ()));
	    }
	    catch (InterruptedException e)
	    {
		break;
	    }
	}
    }


    public void start ()
    {
	if (thethread == null)
	{
	    thethread = new Thread (this);
	    thethread.start ();
	}
    }


    public void stop ()
    {
	if (thethread != null)
	{
	    thethread.stop ();
	    thethread = null;
	}
    }
}


