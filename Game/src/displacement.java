import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class displacement  {
int x, y, size,dx,dy;

	displacement(int size) // constructor
	{
	this.size = size;	
	}
	
	public int getXValue() //getter
	{
		return x;
	}
	
	public int getYValue()
	{
		return y;
	}
	
	public void setXValue() //setter
	{
		this.x = x;
	}
	public void setYValue() //setter
	{
		this.y = y;
	}
	
	//position setter
	public void adjustPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	
	}
	
	public void moveObjects(int dx, int dy)
	{
		x+=dx;
		y+=dy;
	}
	public Rectangle getBound()
	{
		return new Rectangle(x,y,size,size);
	}
	public void render(Graphics2D g2)
	{
		g2.fillRect(x + 1, y+1, size - 2, size -2);
	}
	
	
	
	
}
