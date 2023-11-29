package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Main.Game;

public class Ball {
	
	public double x, y;
	public double dx, dy;
	public double speed = 3;
	
	public static int playerPoints = 0;
	public static int enemyPoints = 0;
	
	public int width, height;
	
	//---------------------------------- Constructor Method ----------------------------------//
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 5;
		
		int angle = new Random().nextInt(30 + 30);
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	//----------------------------------------- END ------------------------------------------//

	//-------------------------------------- Ball Logic --------------------------------------//
	public void tick() {
		//-------------------------------| Ball Movement |------------------------------//
		x += dx * speed;
		y += dy * speed;
		//------------------------------------------------------------------------------//	
		
		//----------------------------| Collision With Wall |---------------------------//
		if(y + (dy * speed) + height >= Game.HEIGHT) { dy *= -1; }
			
		else if(y + (dy * speed) < 0){ dy *= -1; }
			
		//------------------------------------------------------------------------------//
		
		//---------------------------| Collision With Entity |--------------------------//
		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(30 + 30);		
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx < 0) { dx *=-1; }
				
		}
		else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(30 + 30);			
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx > 0) { dx *=-1; }			
		}	
		//------------------------------------------------------------------------------//
			
		//--------------------------------| Score System |------------------------------//
		if(x >= Game.WIDTH) {		
			playerPoints++;
			new Game();
		return;
			}
		else if (x < 0) {
			enemyPoints ++; 
			new Game();			
		return;
		}	
		//------------------------------------------------------------------------------//

	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//-------------------------------------- Ball Render -------------------------------------//
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	//----------------------------------------- END ------------------------------------------//
}
