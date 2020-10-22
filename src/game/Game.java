package game;


/* For the Game timer thread I created the GameTimer class and under private synchronized void start() I started a new thread
 * Thread gameTimerThread = new Thread(new GameTimer()); and the started the thread gameTimerThread.start();
 * 
 * to run this you should be able to import the files into an IDE and just run it. You will see the time print to the console
 * along with the ticks per sec and the actual frame rate. You will see both threads running concurrently.

*/
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640; // width of window
	public static final int HEIGHT = WIDTH / 12 * 9; // height of window as a ratio of the width   1280px /960px
	public static final int SCALE = 2;
	public final String TITLE = "RPG Project"; // text at the top left of the window
	
	private boolean running = false;
	private Thread thread; // 
	private Menu menu; // start menu
	private Options options; //options menu from main menu
	private Main main; // main game menu
	
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static enum STATE {  // create an enum list of game states. this list should grow since our game will be focused on different menus
		MENU,
		GAME, 
		OPTIONS,
		MAIN
	};
	
	public static STATE State = STATE.MENU;  // can change the initial state of game
	

	private synchronized void start() {
		if(running) {
			return;	
		}
		
		running = true;
		thread = new Thread(this);	 // threading although only one thread
		//Thread gameTimerThread = new Thread(new GameTimer()); // GameTimer thread
		thread.start();
		//gameTimerThread.start(); // gameTimerThread started
		menu = new Menu(); // Initialize the menu
		options = new Options();
		main = new Main();
		
		this.addMouseListener(new MouseInput());
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
			delta += (now - lastTime) / ns;
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
		if (State == STATE.GAME) {
			
		}
		
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
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this); //keeps the black background regardless of game state
		
		if (State == STATE.MAIN) {
			
			//g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			main.render(g);
			
		} else if (State == STATE.MENU) {
			
			menu.render(g); // render anything created in the menu class
		
		} else if (State == STATE.OPTIONS) {
			options.render(g);
		}
		

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