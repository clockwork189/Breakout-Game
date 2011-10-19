import java.util.Random;
class Ball
{
    private int radius = 5; //Identifies the radius of the ball as 25

    private int xPos = 340; //Identifies that the position of xPos is 340.
    private int yPos = 585 - (radius); //Identifies that the position of yPos is 150
    private int speedNorm = 1; //Identifies the normal speed of the ball would be 4
    private int speed = speedNorm; //Identifies that the speed of the ball would be the normal speed which is 4

    final private int RIGHT = 10; //Identifies the RIGHT variable at 10
    final private int UP = 20; //Identifies the UP variable at 20
    final private int LEFT = 30; //Identifies the LEFT variable as 30
    final private int DOWN = 40; //Identifies the DOWN variable as 40
    final private int MIDDLE = 0; //Identifies the DOWN variable as 40

    final private int UPLEFT = 25; //Identifies UPLEFT between UP and LEFT at 25
    final private int UPRIGHT = 15; //Identifies UPRIGHT between UP and RIGHT at 15
    final private int DOWNLEFT = 35; // Identifies DOWNLEFT between DOWN and LEFT at 35
    final private int DOWNRIGHT = 45; //Identifies DOWNLEFT at 45
    int direction; //Declares the variable direction
    private boolean launched = false;


    /*The following method determines the direction of the ball based on the score. If score1 (player 1's score),
     than the direction of the ball is to the LEFT. If score2 is greater than score 1, the direction of the ball is to
     the RIGHT. If the scores are level, the direction of the ball is randomnly generated.*/
 public void startBall ()
    {
	    launched = false;
	    direction = 0;
    }

     
    public void LaunchBall ()
    {
	if (!(launched))
	{
	    launched = true;
	    while (direction % 20 == 0)
	    {
		Random generator = new Random ();
		direction = ((generator.nextInt (1)*10 ) + 15);
	    }
	}
    }


    //The following method returns the direction

    public int stateDirection ()
    {
	return direction;
    }


    //Starts a new method for the movement of the ball
    public void move ()
    {
	if (launched)
	{
	  if (direction == UPLEFT) //An ELSE IF statement which works when the direction of the ball is UPLEFT
	    {
		moveUpLeft (); //Calls the moveUpLeft method
	    }
	    else if (direction == UPRIGHT) //An ELSE IF statement which works when the direction of the ball is UPRIGHT
	    {
		moveUpRight (); //Calls the moveUpRight method
	    }
	    else if (direction == DOWNLEFT) //An ELSE IF statement which works when the direction of the ball is DOWNLEFT
	    {
		moveDownLeft (); //Calls the moveDownLeft method
	    }
	    else if (direction == DOWNRIGHT) //An ELSE IF statement which works when the direction of the ball is DOWNRIGHT
	    {
		moveDownRight (); //Calls the moveDownRight method
	    }
	}
    }


    void moveWithPaddle (int Xpaddle)
    {
	if (!(launched))
	{
	    xPos = Xpaddle;
	    yPos = 585;
	}
    }



    /*The following method is called so that the ball reflects whenever it reaches the boundary.
    Iftheballreflectsin the UPLEFT corner, it changes its direction to DOWNLEFT. If the ball
    reflectsoftheUPRIGHTcorner, direction of the ball changes to DOWNRIGHT. If the ball reflects of the DOWNLEFT corner,
    thedirectionoftheball changes to UPLEFT. If the ball reflects of the DOWNRIGHT boundary, the direction changes to UPRIGHT.*/

    public void reflectVertically ()
    {
	if ((direction == UP))  //An IF statement which works when the direction of the ball is RIGHT and the wall motion is 0
	{
	    direction = DOWN; // direction changes to LEFT
	    speed = speedNorm; //speed of the ball is now normal speed
	}


	else if ((direction == DOWN)) //An ELSE IF statement which works when the direction of the ball is LEFT and the wall motion is 0
	{
	    direction = UP; // direction changes to RIGHT
	    speed = speedNorm; //ball has a normal speed
	}


	else if (direction == UPLEFT)
	{
	    direction = DOWNLEFT;
	}


	else if (direction == UPRIGHT)
	{
	    direction = DOWNRIGHT;
	}


	else if (direction == DOWNLEFT)
	{
	    direction = UPLEFT;
	}


	else if (direction == DOWNRIGHT)
	{
	    direction = UPRIGHT;
	}
    }


    public void reflectHorizontally ()  //Calls for a method reflectFromWall when the ball reflects from the wall
    {



	if ((direction == UPLEFT)) //An ELSE IF statement which works when the direction of the ball is UPLEFT and wall motion is 0
	{
	    direction = UPRIGHT; //direction changes to UPRIGHT
	    speed = speedNorm; // ball has a normal speed
	}


	else if ((direction == UPRIGHT)) //An ELSE IF statement which works when the direction of the ball is UPRIGHT and wall motion is 0
	{
	    direction = UPLEFT; //direction changes to UPLEFT
	    speed = speedNorm; //ball has a normal speed
	}


	else if ((direction == DOWNLEFT)) //An ELSE IF statement which works when the direction of the ball is DOWNLEFT and the wall motion is 0
	{
	    direction = DOWNRIGHT; //direction changes to DOWNRIGHT
	    speed = speedNorm; //ball has a normal speed
	}


	else if ((direction == DOWNRIGHT)) //An ELSE IF statement which works when the direction of the ball is DOWNRIGHT and the wall motion is 0

	    {
		direction = DOWNLEFT; //direction changes to DOWNLEFT
		speed = speedNorm; //ball has a normal speed
	    }
    }
    
    
    public void reflectFromPaddle (int region)  //Calls for a method reflectFromWall when the ball reflects from the wall
    {

	if ((region == LEFT)) //An ELSE IF statement which works when the direction of the ball is UPLEFT and wall motion is 0
	{
	    direction = UPLEFT; //direction changes to UPLEFT
	}


	else if ((region == RIGHT)) //An ELSE IF statement which works when the direction of the ball is UPRIGHT and wall motion is 0
	{
	    direction = UPRIGHT; //direction changes to UPRIGHT
	}


	else if ((region == MIDDLE)) //An ELSE IF statement which works when the direction of the ball is DOWNLEFT and the wall motion is 0
	{
	    direction = UP; //direction changes to DOWNRIGHT

	}


    }


    //The following method decreases the y position of the ball by the speed



    //The following method increases the x position of the ball by the speed




    //The following method increases the x position and the y position of the ball by the speed

    public void moveDownRight ()
    {
	yPos += speed;
	xPos += speed;
    }


    //The following method decreases the x position and the y position of the ball by the speed

    void moveUpLeft ()
    {
	yPos -= speed;
	xPos -= speed;
    }


    /* The following method decreases the y-position of the ball by the speed and increases the
	x position of the ball by the speed. */

    void moveUpRight ()
    {
	yPos -= speed;
	xPos += speed;
    }


    /* The following method increases the y position by the speed while the x position of the ball decreases by speed*/

    void moveDownLeft ()
    {
	yPos += speed;
	xPos -= speed;
    }


    //The following method returns the y position of the ball
    public int y_Pos ()
    {
	return yPos + radius () / 2;
    }


    //The following method returns the x position of the ball
    public int x_Pos ()
    {
	return xPos + radius () / 2;
    }


    //method which takes in the current ball length and returns the current ball length increased by boostFactor
    void changeRadius (int boostFactor)
    {
	radius += boostFactor;
    }


    int radius ()
    {
	return radius;
    }


    //method which takes in the current ball speed and returns the current ball speed increased by boostFactor
    void changeBallSpeed (int boostFactor)
    {

	speed += boostFactor;

    }
}


