package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Main {


	//g.drawRect(1200, 50, 50, 30);  // draws a rectangle starting at x = 1200, y = 50 50px wide by 30px height
	
	public Rectangle backButton = new Rectangle(1200, 50, 70, 30);  // x pos, y pos, length, width


	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		//GameTimer gt = new GameTimer(); // need to fix this. not sure where to go 
		//g.drawString(gt.elapsedTime, 100, 100);
		
	
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("You wake up dazed and confused", 100, 200);
		
		g.drawString("Where are you?", 100, 300);
		g.drawString("Are you dead?", 100, 400);
		
		
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		
		g.drawString("Pause", backButton.x + 5, backButton.y + 25);
		g2d.draw(backButton);
		
		
	}


}
