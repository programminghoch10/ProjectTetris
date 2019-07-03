package CT12;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ui {
	
	//=========================================================
	
	static JPanel[][] jpanelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	
	static JPanel[] menuarray = new JPanel[20];
	
	//=========================================================
	
	public static void main(String[] args) {
		backend.main(args);
	}
	public static void UIstart(String[] args) {
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game CT12"); 				//create new Frame
		frame.setSize(601, 1001);									//set start size 		
		frame.setVisible(true);										//Window becomes visible
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//set window to do nothing on close, we got our own way to close
		Container pane = frame.getContentPane();
		
		
	//=========================================================
	// Keyboard controls
		
 		frame.addKeyListener(new KeyListener() {
 			public void keyTyped(KeyEvent key) {
 			}
 			
 			public void keyPressed(KeyEvent key) {
 				//System.out.println("pressed key \""+key.getKeyChar()+"\" with KeyCode " + key.getKeyCode());
 				try {
 				switch (key.getKeyCode()) {
 				case KeyEvent.VK_W:
 				case KeyEvent.VK_UP:
 					backend.currentstone.rotate();
 					break;
 				case KeyEvent.VK_S:
 				case KeyEvent.VK_DOWN:
 					if (!backend.fastdropping) {
	 					backend.fastdropping = true;
	 					backend.Dropper.interrupt();
 					}
 					break;
 				case KeyEvent.VK_A:
 				case KeyEvent.VK_LEFT:
 					backend.currentstone.move(-1);
 					break;
 				case KeyEvent.VK_D:
 				case KeyEvent.VK_RIGHT:
 					backend.currentstone.move(+1);
 					break;
 				case KeyEvent.VK_SPACE:
 					if (backend.paused) {
 						backend.paused = false;
 						break;
 					}
 					backend.currentstone.drop();
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
 				case KeyEvent.VK_S:
 				case KeyEvent.VK_DOWN:
 					backend.fastdropping = false;
 					break;
 				}
 			}
 		});
 		
 	 		
 		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				backend.active = false;
			}
		});
 		
 		
 	//=========================================================
 	//menu
 		
 		frame.setLayout(new GridLayout(6,3));
		
		for (int y = 0; y < 4; y++) {
	    	menuarray[y] = new JPanel();
		    pane.add(menuarray[y]);
	    }
		BufferedImage bufferedlogo = null;
		try {
			bufferedlogo = ImageIO.read(new File("Logo.png"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		ImageIcon imageIcon = null;
		JLabel icon = new JLabel(imageIcon, JLabel.CENTER);
		pane.add(icon);
		for (int y = 4; y < 6; y++) {
	    	menuarray[y] = new JPanel();
		    pane.add(menuarray[y]);
	    }
		JButton jbutton = new JButton("START");
		Font f = new Font("Monosoaced" , Font.BOLD , 30);
		jbutton.setFont(f);
		jbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jbutton.setBackground(Color.BLACK);
		jbutton.setForeground(Color.GREEN);
		
		pane.add(jbutton);
		
		for (int y = 6; y < 8; y++) {
    		menuarray[y] = new JPanel();
	    	pane.add(menuarray[y]);
    	}
	
		JButton jknopf = new JButton("EXIT");
		jknopf.setFont(f);
		jknopf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jknopf.setBackground(Color.BLACK);
		jknopf.setForeground(Color.GREEN);
		pane.add(jknopf);
		
		for (int y = 8; y < 15; y++) {
    		menuarray[y] = new JPanel();
	    	pane.add(menuarray[y]);
		}
		
	
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("start");
				backend.started = true;
			}
		});
		
		jknopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("exit");
				backend.active = false;
			}
		});
		
		for (int y = 0; y < 15; y++) {
	    	menuarray[y].setBackground(Color.black);
		   
	    }
		
		frame.validate();
		Image logo = bufferedlogo.getScaledInstance(icon.getWidth(), icon.getHeight(),
		        Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(logo);
		icon.setIcon(imageIcon);
		icon.setOpaque(true);
		icon.setBackground(Color.BLACK);
		
		jbutton.grabFocus();
		
		while(!backend.started && backend.active) {
			try{Thread.sleep(1000);} catch (InterruptedException ir) {}	
		}
		
		for (int y = 0; y < 15; y++) {
			//System.out.println(y);
	    	pane.remove(menuarray[y]);
    	}
	    	
		pane.remove(icon);
		pane.remove(jbutton); 
		pane.remove(jknopf);
		
		
	
	//=========================================================
	//score display
		
		frame.setLayout(new BorderLayout());
		
		
		JPanel panelscore = new JPanel();
		
		JLabel label1 = new JLabel("Score: " + backend.score);
		panelscore.add(label1);
		
		
		
		
		JPanel panelstone = new JPanel();
		
		JLabel label2 = new JLabel("Jannik");
		panelstone.add(label2);
		
		frame.add(panelscore, BorderLayout.NORTH);
	//	frame.add(panelstone, BorderLayout.WEST);
		
	//=========================================================
	//display the panels

		JPanel panelgame = new JPanel();
		frame.add(panelgame, BorderLayout.CENTER);
		
		panelgame.setLayout(new GridLayout(jpanelarray.length,jpanelarray[0].length));					//Elements in the container "frame" are displayed in a GridLayout
	    
		for (int y = 0; y < jpanelarray.length; y++) {
	    	for (int x = 0; x < jpanelarray[0].length; x++) {
	    		jpanelarray[y][x] = new JPanel();
		    	panelgame.add(jpanelarray[y][x]);
	    	}
	    }
		
		 frame.invalidate();
         frame.validate();

         frame.requestFocusInWindow();
         
         
 		while(backend.active) {
	 		try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	 		
	 		if (backend.gameover) {
	 			label1.setText("GAME OVER!   Score: " + backend.score);
	 		} else {
	 			label1.setText("Score: " + backend.score);
	 		}
	 		
		    for (int y = 0; y < jpanelarray.length; y++) {
		    	for (int x = 0; x < jpanelarray[0].length; x++) {

		    		
		    		if(backend.paused == true) {
		    			
		    			if(backend.gamematrix[y][x] == "") {
		    				jpanelarray[y][x].setBackground(Color.BLACK);
		    				jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));			//displays a grid
		    			}
		    			
		    			else {
		    				
		    				if((backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false")) {
		    					jpanelarray[y][x].setBackground(Color.GRAY);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.GRAY));
		    				}
		    				
		    				else {
		    					jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
		    				}
		    			}
		    		}
		    		
		    		else {
		    			if ((backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false")) {
		    			
		    				switch (backend.gamematrix[y][x].split("-")[0]) {
		    				
		    				case "bk":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    					break;
		    				case "wh":
		    					if (jpanelarray[y][x].getBackground() != Color.WHITE) jpanelarray[y][x].setBackground(Color.WHITE);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		    					break;
		    				case "bl":
		    					if (jpanelarray[y][x].getBackground() != Color.BLUE) jpanelarray[y][x].setBackground(Color.BLUE);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLUE));
		    					break;
		    				case "gr":
		    					if (jpanelarray[y][x].getBackground() != Color.GREEN) jpanelarray[y][x].setBackground(Color.GREEN);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.GREEN));
		    					break;
		    				case "ye":
		    					if (jpanelarray[y][x].getBackground() != Color.YELLOW) jpanelarray[y][x].setBackground(Color.YELLOW);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		    					break;
		    				case "re":
		    					if (jpanelarray[y][x].getBackground() != Color.RED) jpanelarray[y][x].setBackground(Color.RED);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.RED));
		    					break;
		    				case "ma":
		    					if (jpanelarray[y][x].getBackground() != Color.MAGENTA) jpanelarray[y][x].setBackground(Color.MAGENTA);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		    					break;
		    				case "or":
		    					if (jpanelarray[y][x].getBackground() != Color.ORANGE) jpanelarray[y][x].setBackground(Color.ORANGE);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		    					break;
		    				case "cy":
		    					if (jpanelarray[y][x].getBackground() != Color.CYAN) jpanelarray[y][x].setBackground(Color.CYAN);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.CYAN));
		    					break;
		    				case "":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));		//displays a grid -> maingrid
		    					break;
		    				}	
		    			}
		    			
		    			else {
		    				
		    				switch (backend.gamematrix[y][x].split("-")[0]) {
		    				
		    				case "bk":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		    					break;
		    				case "wh":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
		    					break;
		    				case "bl":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
		    					break;
		    				case "gr":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
		    					break;
		    				case "ye":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
		    					break;
		    				case "re":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.RED, 4));
		    					break;
		    				case "ma":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 4));
		    					break;
		    				case "or":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
		    					break;
		    				case "cy":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.CYAN, 4));
		    					break;
		    				case "":
		    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));							//displays a grid -> maingrid
		    					break;
		    				}
		    			}
			    	}
		    	}
		    }
 		}
 		
 	//=========================================================
 	//close
 		
 		backend.active = false;		//declare that app is closing
 		frame.dispose();			//close window
	}
	
	
}