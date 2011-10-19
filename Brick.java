import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
class Brick
{
    //Brick type.
    // 1 one hit
    // 2 two hit
    // 3 powerup
    // 4 Invisible



    private int xPos, yPos, height = 10, width = 40, life = 1;
    private boolean broken = false, isPowerUp = false, powerUpDrop;

    public void reduceLife ()
    {
	life--;
	if (life == 0)
	{
	    brickBreak ();
	}
    }


    public java.awt.Color colour ()
    {
	if (life == 1)
	{
	    return (Color.YELLOW);
	}

	else if (life == 2)
	{
	    return (Color.red);
	}
	else if (isPowerUp)
	{
	    return (Color.green);
	}
	else if (life == 3)
	{
	    return (Color.black);
	}
	else
	{
	    return (Color.black);
	}

    }


    public void typeChange (int type)
    {
	if (type == 1)
	{
	    life = 1;
	}
	else if (type == 2)
	{
	    life = 2;
	}
	if (type == 3)
	{
	    isPowerUp = true;
	}
	else if (type == 4)
	{
	    life = 3;

	}
    }




private void brickBreak ()
{
    broken = true;
}


public boolean notBroken ()
{
    return !(broken);
}


public int brickWidth ()
{
    return width;
}


public int brickX ()
{
    return xPos;
}


public int brickY ()
{
    return yPos;
}


public int brickHeight ()
{
    return height;
}


public Brick (int x, int y)
{
    xPos = x;
    yPos = y;

}


public void changeX (int x)
{
    xPos = x;
}


public void changeY (int y)
{
    yPos = y;
}


public boolean ballContactVertical (int xBall, int yBall)
{
    if (((xBall > xPos) && (xBall < xPos + width))
	    && ((yBall == yPos) || (yBall == yPos + height)))
    {
	return true;
    }


    else
    {

	return false;
    }
}




public boolean ballContactHorizontal (int xBall, int yBall)
{

    if ((((xBall == xPos) || (xBall == xPos + width))
		&& ((yBall + 10 < yPos) && (yBall - 10 > yPos))))
    {
	return true;
    }


    else
    {

	return false;
    }
}


public void startDropping ()
{
    if (isPowerUp)
    {
	powerUpDrop = true;

    }
}


public void dropPowerUp ()
{
    if ((powerUpDrop) && (yPos < 590))
    {
	yPos += 5;
    }


    else if (powerUpDrop)
    {
	brickBreak ();
    }
}


boolean paddleContact (int xPaddle, int yPaddle)
{

    if (((xPaddle > xPos) && (xPaddle < xPos + width))
	    && ((yPaddle < yPos)))
    {
	return true;
    }


    else
    {

	return false;
    }
}
}


