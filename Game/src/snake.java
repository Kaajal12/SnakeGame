import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;

public class snake extends JPanel implements Runnable, KeyListener,ActionListener {

	//instance variables
	static int x1,y1,x,y;
	int score,length;
	int height = 20;
	int width = 20;
	boolean up,left,right,down;
	Random r = new Random();
	boolean moving = false;
	int delay = 300;
	
	Timer timer = new Timer(delay,this);

	int size = 20;
	int boardSize = 300;
	
	//array for x coordinate of snake
	int[] posX = new int[boardSize];
	//array for y coordinate of snake
	int[] posY = new int[boardSize];

 	//start at right position
 	char direction = 'R';
 	
 	
 	//constructor 
	public snake()
	{
	//this.x = x;
	//this.y = y;
		
		x1 = r.nextInt(390); //random width
		y1 = r.nextInt(490); //random height
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		start();
	}
	
	//initializes new game
	public void start()
 	{	
 	posX = new int[boardSize];
 	posY = new int[boardSize];
 	
 	length = 1;
 	moving = true;
 	direction = 'R';
 	
 	//starts timer
 	timer.start();
 	}
	
 	public void paint(Graphics g) 
	{
 		//snake body
 		if(moving)
 		{
		 	 g.setColor(Color.black);
 			for(int i = 0; i < length; i++)
 			{
 				g.fillRect(posX[i], posY[i], size, size);
 			}
 		}
 		
 		
 		//collision detection between apple and snake
 		
 		if(posX[0] >= x1 + 20 == posX[0] <= x1 - 20 && y1 >= posY[0] + 20 == y1 <= posY[0] - 20) 
		{
				score++;
				length++;
	 			
				x1 = r.nextInt(300); 
				//random width for new apple
				
				y1 = r.nextInt(400); 
				//random height for new apple	
		}
	
 
 		//check to see if the snake is colliding with its own body
 		
 		for(int i = 1; i < length; i++)
 		{
 			//if the position at 1 is equal to its starting position for both x and y
 			
 			if(posX[i] == posX[0] && posY[i] == posY[0])
 			{
 			// make directions false
 			right = false;
 			left = false;
 			up = false;
 			down = false;
 			
	 		System.out.println("Game is over, collision with snake body was detected!");
	 		System.exit(0);
 			timer.stop(); //stops the game
 			}

 		}
 		
 		// checking wall collision width and height of JFrame
 		
 	/*	if(posX[0] < 0 || posX[0] > 380  || posY[0] < 0 || posY[0] > 480)
 		{	 
 	 		g.drawString("Game is over, you bumped into the wall!!", 100, 150); 
 	 		
 			timer.stop(); //stops the game
 		}
 		*/
 		
 		
 		//rebound off wall
 		if(posX[0] < 0)
 			posX[0] = 380;
 		
 		if(posX[0] > 390)
 			posX[0] = 0;
 		
 		if(posY[0] < 0)
 			posY[0] = 480;
 		
 		if(posY[0] > 490)
 			posY[0] = 0;
 		
 		//repaint();
 		
 		//different head and body colors for snake
 		for(int i = 0; i < length; i++)
 		{
 			if(i == 0)
 			{
 				//head
 				g.setColor(Color.blue);
 				g.fillRect(posX[i], posY[i], size, size);
 			}
 			
 			else { 				
 				g.setColor(Color.black);
 			}
 		}
 		
	/*	if(posY[0] >= height)
 		{
 			moving = false;
 		}
 		
 		//negative value
 		if(posY[0] < 0)
 		{
 			moving = false;
 		}
 		//past width
 		if(posX[0] >= width)
 		{
 			moving = false;
 		}
 		//negative
 		if(posX[0] < 0)
 		{
 			moving = false;
 		}
 		*/
 		
 			// apple
			g.setColor(Color.red);
			g.fillRect(x1,y1,20,20);
			
			//adjusts new positions of random new apple
			if(score >= 1 && up)  
	 		  {
	 	    	//g.drawRect(posX[0], posY[0],height,width);
	 		    g.fillRect(posX[0], posY[0],height,width);
	 		    	  
	 		  }
	 		      else if(score >= 1  && left) 
	 		      {
	 		          //g.drawRect(posX[0], posY[0], width,height);
	 		          g.fillRect(posX[0], posY[0], width,height);
	 		      }
			
	 		     else if(score >= 1  && right) 
	 		      {
	 	          //g.drawRect(posX[0], posY[0], width,height);
	 	          g.fillRect(posX[0], posY[0], width,height);
	 		      }
	 			 
	 		      else if(score >= 1  && down)
	 		      {
	 		    	  //g.drawRect(posX[0], posY[0],height,width);
	 		    	  g.fillRect(posX[0], posY[0],height,width);
	 		      }
			
 		
		//show score keeper
 		g.drawString("Your score: " + score, 15, 20);
 	
}
 	
 	//moves the snake when user presses arrow keys
	public void moveSnake()
	{
		for(int i = length; i > 0; i--)
		{
			posX[i] = posX[i-1];
			posY[i] = posY[i-1];
		}
		
		//left
		if(direction == 'L')
		{
			posX[0] -=size;
		}
		//right
		if(direction == 'R')
		{
			posX[0] +=size;
		}
		//down
		if(direction == 'D')
		{
			posY[0] +=size;
		}
		//up
		if(direction == 'U')
		{
			posY[0] -=size;
		}
		
	}
	
		//up, down, left, right for snake
		public void keyPressed(KeyEvent e)
		{
			
		int code = e.getKeyCode(); // store the key in code and check it
		if(code == KeyEvent.VK_UP && direction!='D')
			direction = 'U';

		if(code == KeyEvent.VK_DOWN && direction!='U')
			direction = 'D';
		
		if(code == KeyEvent.VK_RIGHT && direction!='L')
			direction = 'R';

		if(code == KeyEvent.VK_LEFT && direction!='R')
			direction = 'L';
		
		}
		
		
	public static void main(String args[])
	{
		//frame bounds
		snake s = new snake();
		JFrame frame = new JFrame("Snake Game");
		frame.setBounds(x,y,400,500);
		frame.add(s);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if snake is moving, call the method and repaint new coordinates
		if(moving)
		{
			moveSnake();
		}
		repaint();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
	