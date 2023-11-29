package Entities;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;

public class Player {
	
	public boolean up, down;
	
	public int speed = 2;
	public int x, y;
	public int width, height;
	
	//---------------------------------- Constructor Method ----------------------------------//
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 40;
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Player Logic -------------------------------------//
	public void tick() {
		//------------------------------| Player Movement |-----------------------------//
		if(up) { y -= speed; }
			
		else if(down) { y += speed; }
		//------------------------------------------------------------------------------//		
		
		//---------------------------| Collision With Walls |---------------------------//
		if(y + height > Game.HEIGHT) { y = Game.HEIGHT - height; }

		else if(y < 0) { y = 0; }
		//------------------------------------------------------------------------------//
	}
	
	//----------------------------------------- END ------------------------------------------//
	
	//------------------------------------- Player Render ------------------------------------//
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	//----------------------------------------- END ------------------------------------------//
}
