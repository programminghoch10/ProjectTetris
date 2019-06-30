package CT12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ui {
	
	//=========================================================
	
	static JPanel[][] jpanelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	
	//=========================================================
	
	public static void main(String[] args) {
		backend.main(args);
	}
	public static void UIstart(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game CT12"); 				//create new Frame
		frame.setSize(600, 1000);									//set start size 		
		frame.setVisible(true);										//Window becomes visible
		Container pane = frame.getContentPane();
		frame.setLayout(new GridLayout(jpanelarray.length,jpanelarray[0].length));					//Elements in the container "frame" are displayed in a GridLayout
	    
		for (int y = 0; y < jpanelarray.length; y++) {
	    	for (int x = 0; x < jpanelarray[0].length; x++) {
	    		jpanelarray[y][x] = new JPanel();
		    	pane.add(jpanelarray[y][x]);
	    	}
	    }
		
	    // Keyboard controls
 		frame.addKeyListener(new KeyListener() {
 			public void keyTyped(KeyEvent key) {
 			}
 			
 			public void keyPressed(KeyEvent key) {
 				System.out.println("pressed key \""+key.getKeyChar()+"\" with KeyCode " + key.getKeyCode());
 				try {
 				switch (key.getKeyCode()) {
 				case KeyEvent.VK_UP:
 					backend.currentstone.rotate();
 					break;
 				case KeyEvent.VK_DOWN:
 					if (!backend.fastdropping) {
	 					backend.fastdropping = true;
	 					backend.Dropper.interrupt();
 					}
 					break;
 				case KeyEvent.VK_LEFT:
 					backend.currentstone.move(-1);
 					break;
 				case KeyEvent.VK_RIGHT:
 					backend.currentstone.move(+1);
 					break;
 				case KeyEvent.VK_SPACE:
 					backend.currentstone.drop();
 					break;
 				}
 				} catch (NullPointerException e) {} //catch error if currentstone is null
 			}
 			
 			public void keyReleased(KeyEvent key) {
 				System.out.println("released key \""+key.getKeyChar()+"\" with KeyCode " + key.getKeyCode());
 				switch (key.getKeyCode()) {
 				case KeyEvent.VK_DOWN:
 					backend.fastdropping = false;
 					break;
 				}
 			}
 		});
	    
		
		//==========================================================
		
 		while(backend.active) {
	 		/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
	 		
		    for (int y = 0; y < jpanelarray.length; y++) {
		    	for (int x = 0; x < jpanelarray[0].length; x++) {
			    	switch (backend.gamematrix[y][x].split("-")[0]) {
			    	
			    	case "bk":
			    		jpanelarray[y][x].setBackground(Color.BLACK);
			    		break;
			    	case "wh":
			    		jpanelarray[y][x].setBackground(Color.WHITE);
			    		break;
			    	case "bl":
			    		jpanelarray[y][x].setBackground(Color.BLUE);
			    		break;
			    	case "gr":
			    		jpanelarray[y][x].setBackground(Color.GREEN);
			    		break;
			    	case "ye":
			    		jpanelarray[y][x].setBackground(Color.YELLOW);
			    		break;
			    	case "re":
			    		jpanelarray[y][x].setBackground(Color.RED);
			    		break;
			    	case "ma":
			    		jpanelarray[y][x].setBackground(Color.MAGENTA);
			    		break;
			    	case "or":
			    		jpanelarray[y][x].setBackground(Color.ORANGE);
			    		break;
			    	case "cy":
			    		jpanelarray[y][x].setBackground(Color.CYAN);
			    		break;
			    	case "":
			    		jpanelarray[y][x].setBackground(Color.BLACK);
			    		break;
			    	}
		    	}
		    }
 		}
		
		//==========================================================
 		
	}
	
	
}