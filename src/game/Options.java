package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Options {

	
	
	public Rectangle newGameButton = new Rectangle(Game.WIDTH - 100, 250, 200, 30);  // x pos, y pos, length, width
	public Rectangle continueGameButton = new Rectangle(Game.WIDTH - 100, 300, 200, 30);
	public Rectangle optionsButton = new Rectangle(Game.WIDTH - 100, 350, 200, 30);
	public Rectangle exitButton = new Rectangle(Game.WIDTH - 100, 400, 200, 30);

	
	
	
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Nightmare Diner", 440, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		
		g.drawString("New Game", newGameButton.x + 40, newGameButton.y + 25 );
		g2d.draw(newGameButton);
		
//		g.drawString("Continue", continueGameButton.x + 50, continueGameButton.y + 25 );
//		g2d.draw(continueGameButton);
		
		g.drawString("Options", optionsButton.x + 55, optionsButton.y + 25 );
		g2d.draw(optionsButton);
		g.drawString("Exit", exitButton.x + 75, exitButton.y + 25 );
		g2d.draw(exitButton);
		
	}

}
