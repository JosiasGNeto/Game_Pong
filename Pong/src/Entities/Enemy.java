package Entities;

import java.awt.Color;
import java.awt.Graphics;
import Main.Game;

public class Enemy {
	
	public double x, y;
	public double difficulty = 0.2;
	public int width, height;
	
	//---------------------------------- Constructor Method ----------------------------------//
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 40;
	}
	
	//----------------------------------------- END ------------------------------------------//

	//------------------------------------- Enemy Logic --------------------------------------//
	public void tick() {
		//------------------------------| Enemy Movement |------------------------------//
		y += (Game.ball.y - y - 5) * difficulty;
		//------------------------------------------------------------------------------//	
		
		//---------------------------| Collision With Walls |---------------------------//
		if(y + height > Game.HEIGHT) { y = Game.HEIGHT - height; }
			
		else if(y < 0) { y = 0; }
			
		//------------------------------------------------------------------------------//
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Enemy Render -------------------------------------//
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	//----------------------------------------- END ------------------------------------------//
}
