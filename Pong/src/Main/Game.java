package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Entities.Ball;
import Entities.Enemy;
import Entities.Player;

public class Game extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//------------------------------- Instantiation Variables --------------------------------//
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	//----------------------------------------- END ------------------------------------------//
	
	//---------------------------------- Constructor Method ----------------------------------//	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		
		player = new Player(0, 40);
		enemy = new Enemy(230, 40);
		ball = new Ball(120, (HEIGHT / 2) - 1);
	}
	
	//----------------------------------------- END ------------------------------------------//

	//------------------------------------- Main Method --------------------------------------//	
	public static void main(String[]  args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Game Logic ---------------------------------------//
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Game Render --------------------------------------//
	public void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) { 
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white);
		g.fillRect(119, 0, 2, HEIGHT);
		g.drawString(""+Ball.playerPoints, 100, 15);
		g.drawString(""+Ball.enemyPoints, 130, 15);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Game Runner --------------------------------------//
	@Override
	public void run() {
	    //-------------------------------| Game Looping |-------------------------------//  
		requestFocus();
		while(true) {
			tick();
			render();
			try {Thread.sleep(1000/60);}			
			catch (InterruptedException e) {e.printStackTrace();}				
		}		
		//------------------------------------------------------------------------------//
	}

	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------ Key Listener --------------------------------------//
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) { player.up = true; }			
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) { player.down = true; }		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) { player.up = false; }		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) { player.down = false; }
	}
	
	//----------------------------------------- END ------------------------------------------//
		
}
