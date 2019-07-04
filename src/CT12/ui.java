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
	
	static JPanel[][] panelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	
	//=========================================================
	
	public static void main(String[] args) {
		backend.main(args);
	}
	public static void UIstart(String[] args) {
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game CT12"); 				//create new Frame
		frame.setSize(701, 1001);									//set start size 		
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
			bufferedlogo = ImageIO.read(new File("Logoneutransparent.png"));
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
		Font z = new Font("Serif" , Font.BOLD , 30);
		jbutton.setFont(z);
		jbutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		jbutton.setBackground(new Color(15,15,15));
		jbutton.setForeground(Color.GREEN);
		
		pane.add(jbutton);
		
		for (int y = 6; y < 8; y++) {
    		menuarray[y] = new JPanel();
	    	pane.add(menuarray[y]);
    	}
	
		JButton jknopf = new JButton("EXIT");
		jknopf.setFont(z);
		jknopf.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		jknopf.setBackground(new Color(15,15,15));
		jknopf.setForeground(Color.GREEN);
		pane.add(jknopf);
		
		for (int y = 8; y < 14; y++) {
    		menuarray[y] = new JPanel();
	    	pane.add(menuarray[y]);
		}
		
		Label credits = new Label();
		Font f = new Font("Monospaced" , Font.BOLD , 15);
		pane.add(credits);
		credits.setFocusable(false);
		credits.setText("Credits: CT_12_19");
		credits.setFont(f);
		credits.setBackground(Color.BLACK);
		credits.setForeground(Color.GREEN);
		credits.setAlignment(Label.CENTER);
		
		
		
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
		
		for (int y = 0; y < 14; y++) {
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
		
		for (int y = 0; y < 14; y++) {
			//System.out.println(y);
	    	pane.remove(menuarray[y]);
    	}
	    	
		pane.remove(icon);
		pane.remove(jbutton); 
		pane.remove(jknopf);
		pane.remove(credits);
		
		
	
	//=========================================================
	//score display
		
		frame.setLayout(new BorderLayout());
		
		//Score
		JPanel panelscore = new JPanel();
		panelscore.setBackground(Color.BLACK);
		
		JLabel labelscore = new JLabel("Score: " + backend.score);
		labelscore.setFont(labelscore.getFont().deriveFont(18f));
		labelscore.setForeground(Color.GREEN);
		panelscore.add(labelscore);
		
		//Next Stone
		JPanel panelstone = new JPanel();
		panelstone.setBackground(Color.BLACK);
		
		JLabel labelstone = new JLabel("Next Stone");
		panelstone.add(labelstone);
		
		//Save Stone
		JPanel panelsave = new JPanel();
		panelsave.setBackground(Color.BLACK);
		
		JLabel labelsave = new JLabel("Save Stone");
		panelsave.add(labelsave);
		
		//place panels
		frame.add(panelscore, BorderLayout.NORTH);
		frame.add(panelstone, BorderLayout.EAST);
		frame.add(panelsave, BorderLayout.WEST);
		
	//=========================================================
	//display the panels

		JPanel panelgame1 = new JPanel();
		frame.add(panelgame1, BorderLayout.CENTER);
		
		JPanel panelgame = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            public Dimension getPreferredSize() {
                Dimension d = this.getParent().getSize();
                int sizex = 0;
                int sizey = 0;
                if (d.width > d.height*(float)((float)jpanelarray[0].length/(float)jpanelarray.length)) {
                    sizey = d.height;
                    sizex = (int)(d.height*(float)((float)jpanelarray[0].length/(float)jpanelarray.length));
                } else {
                    sizex = d.width;
                    sizey = (int)(d.width*(float)((float)jpanelarray.length/(float)jpanelarray[0].length));
                }
                return new Dimension(sizex, sizey);
            }
        };
        
        panelgame1.setBackground(Color.BLACK);
        
        panelgame1.add(panelgame);
		
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
	 		
		    for (int y = 0; y < jpanelarray.length; y++) {
		    	for (int x = 0; x < jpanelarray[0].length; x++) {

		    		if(backend.gameover == true | backend.paused == true) {
		    			
		    			switch (backend.gamematrix[y][x].split("-")[0]) {
	    				
	    				case "bk":
	    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
	    					break;
	    				case "wh":
	    					if (jpanelarray[y][x].getBackground() != Color.WHITE) jpanelarray[y][x].setBackground(Color.WHITE);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
	    					break;
	    				case "bl":
	    					Color colorbl = new Color(20,20,20);
	    					if (jpanelarray[y][x].getBackground() != colorbl) jpanelarray[y][x].setBackground(colorbl);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorbl));
	    					break;
	    				case "gr":
	    					Color colorgr = new Color(40,40,40);
	    					if (jpanelarray[y][x].getBackground() != colorgr) jpanelarray[y][x].setBackground(colorgr);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorgr));
	    					break;
	    				case "ye":
	    					Color colorye = new Color(60,60,60);
	    					if (jpanelarray[y][x].getBackground() != colorye) jpanelarray[y][x].setBackground(colorye);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorye));
	    					break;
	    				case "re":
	    					Color colorre = new Color(80,80,80);
	    					if (jpanelarray[y][x].getBackground() != colorre) jpanelarray[y][x].setBackground(colorre);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorre));
	    					break;
	    				case "ma":
	    					Color colorma = new Color(100,100,100);
	    					if (jpanelarray[y][x].getBackground() != colorma) jpanelarray[y][x].setBackground(colorma);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorma));
	    					break;
	    				case "or":
	    					Color coloro = new Color(120,120,120);
	    					if (jpanelarray[y][x].getBackground() != coloro) jpanelarray[y][x].setBackground(coloro);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(coloro));
	    					break;
	    				case "cy":
	    					Color colorcy = new Color(140,140,140);
	    					if (jpanelarray[y][x].getBackground() != colorcy) jpanelarray[y][x].setBackground(colorcy);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorcy));
	    					break;
	    				case "":
	    					if (jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));								//displays a grid -> maingrid
	    					break;
	    				}
		    		}
		    		
		    		else {
		    		if(backend.paused == true) {
		    			
		    			if(backend.gamematrix[y][x] == "") {
		    				jpanelarray[y][x].setBackground(Color.BLACK);
		    				jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));			//displays a grid
		    			}
		    			
		    			else {
		    				
		    				
		    				if((backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false")) {
		    					jpanelarray[y][x].setBackground(new Color (50,50,50));
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(new Color(0,0,0 )));
		    				}
		    				
		    				else {
		    					jpanelarray[y][x].setBackground(new Color(1,1,1));
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(new Color(4,4,4)));
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
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));								//displays a grid -> maingrid
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
		    
		    if (backend.gameover) {
	 			labelscore.setText("GAME OVER!   Score: " + backend.score);
	 			labelscore.setForeground(Color.RED);
	 		}
		    else {
	 			labelscore.setText("Score: " + backend.score);
	 			
	 			if(backend.paused == true) {
			    	labelscore.setForeground(Color.WHITE);
			    }
			    else {
			    	labelscore.setForeground(Color.GREEN);
			    }
	 		}   
 		}
 		
 	//=========================================================
 	//close
 		
 		backend.active = false;		//declare that app is closing
 		frame.dispose();			//close window
	}
	
	
}