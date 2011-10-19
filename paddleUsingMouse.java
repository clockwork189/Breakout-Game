import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class paddleUsingMouse extends Applet implements MouseMotionListener
{

    int mx;
    int x_posPaddle = 40;

    public void init ()
    {
	mx = 0;
	addMouseMotionListener (this);
    }



    public void mouseMoved (MouseEvent e)
    { // called during motion when no buttons are down
	mx = e.getX ();
	e.consume ();
    }


    public void mouseDragged (MouseEvent e)
    { // called during motion with buttons down
	mx = e.getX ();
	e.consume ();
    }

}


