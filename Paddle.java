class Paddle
{

    int xPos;
    int width = 60;

    int x ()
    {
	return xPos;
    }


    int width ()
    {
	return width;
    }


    void changeX (int Mousepos)
    {
	xPos = Mousepos;
    }


    void widthIncrease ()
    {
	if (width < 180)
	{
	    width += 10;
	}
    }
} // MovingBallApplet5 class
