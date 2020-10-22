package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX(); // get x value of mouse location
		int my = e.getY(); // get y value of mouse location
		
		if (Game.State == Game.STATE.MENU) {  // below mouse inputs only work when game state is set to MENU
		
			if (mx >= Game.WIDTH - 100 && mx <= Game.WIDTH + 100 ) {
				
				if (my >= 250 && my <= 280) {
					//pressed new game button
					Game.State = Game.STATE.MAIN; //TODO:need to create a new game class that starts a new game (Main)
					
					Thread gameTimerThread = new Thread(new GameTimer()); // GameTimer thread
					gameTimerThread.start(); // gameTimerThread started
					

				}
			}
			
//			if (mx >= Game.WIDTH - 100 && mx <= Game.WIDTH + 100 ) {
//				
//				if (my >= 300 && my <= 330) {
//					//pressed continue game button
//					Game.State = Game.STATE.GAME; //TODO:need to create a way to save game then continue from saved point
//				}
//			}
			
//			if (mx >= Game.WIDTH - 100 && mx <= Game.WIDTH + 100 ) {
//				
//				if (my >= 350 && my <= 380) {
//					//pressed options button
//					Game.State = Game.STATE.OPTIONS;  //TODO:need to create an options class  (Done)
//				}
//			}
			
			if (mx >= Game.WIDTH - 100 && mx <= Game.WIDTH + 100 ) {
				
				if (my >= 400 && my <= 430) {
					//pressed Exit button
					System.exit(1); // exits game
				}
			}
		//} else if (Game.State == Game.STATE.OPTIONS) {
			//if (mx >= Game.WIDTH - 100 && mx <= Game.WIDTH + 100 ) {
				
				//if (my >= 250 && my <= 280) {
					//pressed new game button
					//Game.State = Game.STATE.MENU; //TODO:need to create a new game class that starts a new game
				//}
			//}
		} else if (Game.State == Game.STATE.MAIN) { // (1200, 50, 70, 30)
			if (mx >= 1200 && mx <= 1270 ) {
				
				if (my >= 50 && my <= 80) {
					//pressed new game button
					Game.State = Game.STATE.MENU; //TODO:need to create a new game class that starts a new game
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
