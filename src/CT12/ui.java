package CT12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ui {
	
	//=========================================================
	
	static JPanel[][] jpanelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	
	//=========================================================
	
	public static void main(String[] args) {
		backend.main(args);
	}
	public static void UIstart(String[] args) {
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game CT12"); 				//create new Frame
		frame.setSize(600, 1000);									//set start size 		
		frame.setVisible(true);										//Window becomes visible
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//set window to do nothing on close, we got our own way to close
		Container pane = frame.getContentPane();
		frame.setLayout(new GridLayout(jpanelarray.length,jpanelarray[0].length));					//Elements in the container "frame" are displayed in a GridLayout
	    
		for (int y = 0; y < jpanelarray.length; y++) {
	    	for (int x = 0; x < jpanelarray[0].length; x++) {
	    		jpanelarray[y][x] = new JPanel();
	    		jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.gray));			//displays a grid
		    	pane.add(jpanelarray[y][x]);
	    	}
	    }
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				backend.active = false;
			}
		});
		
		//====================================================
		
	    // Keyboard controls
 		frame.addKeyListener(new KeyListener() {
 			public void keyTyped(KeyEvent key) {
 			}
 			
 			public void keyPressed(KeyEvent key) {
 				//System.out.println("pressed key \""+key.getKeyChar()+"\" with KeyCode " + key.getKeyCode());
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
 				case KeyEvent.VK_ENTER:
 					backend.started = true;
 					break;
 				case KeyEvent.VK_P:
 					backend.paused = !backend.paused;
 					System.out.println("new pause state: " + backend.paused);
 					break;
 				case KeyEvent.VK_ESCAPE:
 					backend.active = false;
 					break;
 				}
 				} catch (NullPointerException e) {} //catch error if currentstone is null
 			}
 			
 			public void keyReleased(KeyEvent key) {
 				//System.out.println("released key \""+key.getKeyChar()+"\" with KeyCode " + key.getKeyCode());
 				switch (key.getKeyCode()) {
 				case KeyEvent.VK_DOWN:
 					backend.fastdropping = false;
 					break;
 				}
 			}
 		});
	    
		
		//==========================================================
		
 		while(backend.active) {
	 		try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	 		
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
 		
 		backend.active = false;		//declare that app is closing
 		frame.dispose();			//close window
	}
	
	
}