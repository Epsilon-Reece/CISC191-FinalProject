package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640; // width of window
	public static final int HEIGHT = WIDTH / 12 * 9; // height of window as a ratio of the width
	public static final int SCALE = 2;
	public final String TITLE = "RPG Project"; // text at the top left of the window
	
	private boolean running = false;
	private Thread thread; // 
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	private synchronized void start() {
		if(running) {
			return;
			
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) {
			return;
			
		}
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(1);
		
	}
		
	public void run() {
		
		long lastTime = System.nanoTime();		// this area restricts ticks to 60 per second
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		int update = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			// game loop

	long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			if (delta >+ 1) {
				tick();
				update++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(update + " Ticks, FPS " + frames);
				update = 0;
				frames = 0;
			}

		}
		stop();
	}
	
	private void tick() {
		// updates game per frame
	}
	
	private void render() {
		// renders graphics
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(2); // loads images preemptively 
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); // draws graphics to canvas
		
		/////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		
		
		
		
		
		/////////////////////
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]) {  // main method
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // 
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); // 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows x to close out game
		frame.setResizable(false); // cannot resize window
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start(); // call the game loop
		
	}
}
