package wssct20.projecttetris;

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
import java.io.IOException;

public class ui {
	
	//=========================================================
	
	static JPanel[][] jpanelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	static JPanel[][] previewarray = new JPanel[backend.previewmatrix.length][backend.previewmatrix[0].length];
	
	static JPanel[] menuarray = new JPanel[20];
	static JPanel[] optionsarray = new JPanel[20];
	
	//=========================================================
	
	public static void main(String[] args) {
		backend.main(args);
	}
	public static void UIstart(String[] args) {
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game WSSCT20"); 			//create new Frame
		ImageIcon img = null;
		try {
			img = new ImageIcon(ImageIO.read(backend.class.getResourceAsStream("/wssct20/projecttetris/icon.png")));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		frame.setIconImage(img.getImage());
		frame.setSize(1600, 900);									//set start size 		
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
 					if (backend.paused &&!backend.gameover) {
 						backend.paused = false;
 						System.out.println("new pause state: " + backend.paused);
 						break;
 					}
 					backend.currentstone.drop();
 					break;
 				case KeyEvent.VK_P:
 					if (!backend.gameover) {
 						backend.paused = !backend.paused;
 						System.out.println("new pause state: " + backend.paused);
 					}
 					break;
 				case KeyEvent.VK_ESCAPE:
 					backend.active = false;
 					break;
 				case KeyEvent.VK_R:
 					backend.reset();
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
			bufferedlogo = ImageIO.read(backend.class.getResourceAsStream("/wssct20/projecttetris/tetrislogotransparent.png"));
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
		credits.setText("Credits: WSSCT20");
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
		
		JPanel framepanel = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.add(framepanel);
		
		JPanel game = new JPanel() {
            private static final long serialVersionUID = 12L;
            @Override
            public Dimension getPreferredSize() {
                Dimension d = this.getParent().getSize();
                int sizex = 0;
                int sizey = 0;
                if (d.width > d.height*(float)((float)jpanelarray[0].length/(float)jpanelarray.length)*1.6) {
                //if (d.width > d.height) {
                    sizey = d.height;
                    sizex = (int)(d.height*(float)((float)jpanelarray[0].length/(float)jpanelarray.length)*1.6);
                    //sizex = d.width;
                } else {
                    sizex = d.width;
                    sizey = (int)(d.width*(float)((float)jpanelarray.length/(float)jpanelarray[0].length)*0.6);
                    //sizey = d.height;
                }
                return new Dimension(sizex, sizey);
            }
        };
		
        framepanel.add(game, BorderLayout.CENTER);
        framepanel.setBackground(Color.BLACK);
        
        game.setBackground(Color.MAGENTA);
        
		game.setLayout(new BorderLayout());
		
		JPanel southpadding = new JPanel();
		southpadding.setBackground(Color.BLACK);
		southpadding.setPreferredSize(new Dimension(0, 5));
		
		
		//Score
		JPanel panelscore = new JPanel();
		panelscore.setBackground(Color.BLACK);
		
		JLabel labelscore = new JLabel("Score: " + backend.score);
		labelscore.setFont(labelscore.getFont().deriveFont(18f));
		labelscore.setForeground(Color.WHITE);
		panelscore.add(labelscore);
		
		//Next Stone
		JPanel panelstone = new JPanel();
		panelstone.setBackground(Color.BLACK);
		panelstone.setPreferredSize(new Dimension(200, 0));
		
		panelstone.setLayout(new BorderLayout());
		
		JLabel labelstone = new JLabel("        Next Stone:");
		labelstone.setFont(labelscore.getFont().deriveFont(18f));
		labelstone.setForeground(Color.WHITE);
		panelstone.add(labelstone, BorderLayout.NORTH);
		
		JPanel panelabstand = new JPanel();
		panelabstand.setBackground(Color.BLACK);
		panelabstand.setPreferredSize(new Dimension(10, 0));
		panelstone.add(panelabstand, BorderLayout.WEST);
		
		
		JPanel panelpreview1 = new JPanel();
		JPanel panelpreview = new JPanel() {
            private static final long serialVersionUID = 112L;
            @Override
            public Dimension getPreferredSize() {
                Dimension d = this.getParent().getSize();
                int sizex = 0;
                int sizey = 0;
                double scale = 0.7;
                sizex = (int) (d.height*(float)((float)previewarray[0].length/(float)previewarray.length)*scale);
                sizey = (int) (d.height * scale);
                return new Dimension(sizex, sizey);
            }
        };
        panelpreview1.add(panelpreview);
		panelstone.add(panelpreview1, BorderLayout.CENTER);
		panelpreview1.setBackground(Color.BLACK);
		
		panelpreview.setBackground(Color.BLACK);
		panelpreview.setLayout(new GridLayout(previewarray.length,previewarray[0].length));					//Elements in the container "frame" are displayed in a GridLayout
	    
		for (int y = 0; y < previewarray.length; y++) {
	    	for (int x = 0; x < previewarray[0].length; x++) {
	    		previewarray[y][x] = new JPanel();
		    	panelpreview.add(previewarray[y][x]);
	    	}
	    }
		
		//options
		JPanel paneloptions = new JPanel();
		paneloptions.setBackground(Color.BLACK);
		paneloptions.setPreferredSize(new Dimension(200, 0));
		
		JLabel labeloptions = new JLabel("Options");
		labeloptions.setFont(labeloptions.getFont().deriveFont(18f));
		labeloptions.setForeground(Color.WHITE);
		paneloptions.add(labeloptions);
		
		paneloptions.setLayout(new GridLayout(6,3));
		
		for (int y = 0; y < 4; y++) {
	    	optionsarray[y] = new JPanel();
	    	paneloptions.add(optionsarray[y]);
	    }
		
		for (int y = 4; y < 6; y++) {
	    	optionsarray[y] = new JPanel();
	    	paneloptions.add(optionsarray[y]);
	    }
		JButton resetbutton = new JButton("RESET");
		Font optionsfont = new Font("Serif" , Font.BOLD , 20);
		resetbutton.setFont(optionsfont);
		resetbutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		resetbutton.setBackground(new Color(15,15,15));
		resetbutton.setForeground(Color.GREEN);
		
		paneloptions.add(resetbutton);
		
		for (int y = 6; y < 8; y++) {
    		optionsarray[y] = new JPanel();
    		paneloptions.add(optionsarray[y]);
    	}
	
		JButton exitbutton = new JButton("EXIT");
		exitbutton.setFont(optionsfont);
		exitbutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		exitbutton.setBackground(new Color(15,15,15));
		exitbutton.setForeground(Color.GREEN);
		paneloptions.add(exitbutton);
		
		for (int y = 8; y < 14; y++) {
    		optionsarray[y] = new JPanel();
    		paneloptions.add(optionsarray[y]);
		}
		
		
		resetbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("reset");
				backend.reset();
			}
		});
		
		exitbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("exit");
				backend.active = false;
			}
		});
		
		for (int y = 0; y < 14; y++) {
	    	optionsarray[y].setBackground(Color.black);
		   
	    }
		
		while(!backend.started && backend.active) {
			try{Thread.sleep(1000);} catch (InterruptedException ir) {}	
		}
		
		for (int y = 0; y < 14; y++) {
			//System.out.println(y);
			paneloptions.remove(optionsarray[y]);
    	}
		
//		resetbutton.grabFocus();
// 		exitbutton.grabFocus();
		
		//==========================================================
		
		//place panels
		game.add(panelscore, BorderLayout.NORTH);
		game.add(panelstone, BorderLayout.EAST);
		game.add(paneloptions, BorderLayout.WEST);
		game.add(southpadding, BorderLayout.SOUTH);
		
	//=========================================================
	//display the panels

		JPanel panelgame1 = new JPanel();
		game.add(panelgame1, BorderLayout.CENTER);
		
		JPanel panelgame=panelgame1;
		
		/*JPanel panelgame = new JPanel() {
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
        };*/
        
        panelgame1.setBackground(Color.DARK_GRAY);
        
        //panelgame1.add(panelgame);
		
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

		    		if(backend.gameover) {
		    			
		    			
		    		}
		    			if (backend.paused) {
		    				Color gridcolor = Color.DARK_GRAY;
		    				if (backend.gamematrix[y][x] == "") {
		    					jpanelarray[y][x].setBackground(Color.BLACK);
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(gridcolor));						//displays a grid
		    				} else {
		    					boolean ghost = !(backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false");
		    					int thickness = 1;
		    					if (ghost) {
		    						jpanelarray[y][x].setBackground(Color.BLACK);
		    						//jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(gridcolor, 4));
		    						thickness = 4;
		    					}
		    						switch (backend.gamematrix[y][x].split("-")[0]) {
		    	    				case "bk":
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != Color.BLACK) jpanelarray[y][x].setBackground(Color.BLACK);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, thickness));
		    	    					break;
		    	    				case "wh":
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != Color.WHITE) jpanelarray[y][x].setBackground(Color.WHITE);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE, thickness));
		    	    					break;
		    	    				case "bl":
		    	    					Color colorbl = new Color(90,90,90);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorbl) jpanelarray[y][x].setBackground(colorbl);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorbl, thickness));
		    	    					break;
		    	    				case "gr":
		    	    					Color colorgr = new Color(100,100,100);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorgr) jpanelarray[y][x].setBackground(colorgr);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorgr, thickness));
		    	    					break;
		    	    				case "ye":
		    	    					Color colorye = new Color(110,110,110);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorye) jpanelarray[y][x].setBackground(colorye);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorye, thickness));
		    	    					break;
		    	    				case "re":
		    	    					Color colorre = new Color(120,120,120);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorre) jpanelarray[y][x].setBackground(colorre);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorre, thickness));
		    	    					break;
		    	    				case "ma":
		    	    					Color colorma = new Color(130,130,130);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorma) jpanelarray[y][x].setBackground(colorma);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorma, thickness));
		    	    					break;
		    	    				case "or":
		    	    					Color colorro = new Color(140,140,140);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorro) jpanelarray[y][x].setBackground(colorro);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorro, thickness));
		    	    					break;
		    	    				case "cy":
		    	    					Color colorcy = new Color(150,150,150);
		    	    					if (!ghost & jpanelarray[y][x].getBackground() != colorcy) jpanelarray[y][x].setBackground(colorcy);
		    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colorcy, thickness));
		    	    					break;
		    	    				}
		    				}
		    			} else {
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
		    				} else {
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
		    
		    for (int y = 0; y < previewarray.length; y++) {
		    	for (int x = 0; x < previewarray[0].length; x++) {
		    		try {
		    			if (backend.paused) {
		    				if (backend.previewmatrix[y][x].split("-")[0] == "") {
		    					if (previewarray[y][x].getBackground() != Color.BLACK) previewarray[y][x].setBackground(Color.BLACK);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		    				} else {
		    					if (previewarray[y][x].getBackground() != Color.DARK_GRAY) previewarray[y][x].setBackground(Color.DARK_GRAY);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		    				}
		    			} else {
		    				switch (backend.previewmatrix[y][x].split("-")[0]) {
		    				case "bk":
		    					if (previewarray[y][x].getBackground() != Color.BLACK) previewarray[y][x].setBackground(Color.BLACK);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    					break;
		    				case "wh":
		    					if (previewarray[y][x].getBackground() != Color.WHITE) previewarray[y][x].setBackground(Color.WHITE);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		    					break;
		    				case "bl":
		    					if (previewarray[y][x].getBackground() != Color.BLUE) previewarray[y][x].setBackground(Color.BLUE);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.BLUE));
		    					break;
		    				case "gr":
		    					if (previewarray[y][x].getBackground() != Color.GREEN) previewarray[y][x].setBackground(Color.GREEN);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.GREEN));
		    					break;
		    				case "ye":
		    					if (previewarray[y][x].getBackground() != Color.YELLOW) previewarray[y][x].setBackground(Color.YELLOW);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		    					break;
		    				case "re":
		    					if (previewarray[y][x].getBackground() != Color.RED) previewarray[y][x].setBackground(Color.RED);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.RED));
		    					break;
		    				case "ma":
		    					if (previewarray[y][x].getBackground() != Color.MAGENTA) previewarray[y][x].setBackground(Color.MAGENTA);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		    					break;
		    				case "or":
		    					if (previewarray[y][x].getBackground() != Color.ORANGE) previewarray[y][x].setBackground(Color.ORANGE);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		    					break;
		    				case "cy":
		    					if (previewarray[y][x].getBackground() != Color.CYAN) previewarray[y][x].setBackground(Color.CYAN);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.CYAN));
		    					break;
		    				case "":
		    					if (previewarray[y][x].getBackground() != Color.BLACK) previewarray[y][x].setBackground(Color.BLACK);
		    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));								//displays a grid -> maingrid
		    					break;
		    				}
		    			}
		    		} catch (NullPointerException nullerr) {}
		    	}
		    } 
		    
		    if (backend.gameover) {
	 			labelscore.setText("GAME OVER!   Score: " + backend.score);
	 			labelscore.setForeground(Color.RED);
	 			labelstone.setForeground(Color.DARK_GRAY);
	 			labeloptions.setForeground(Color.WHITE);
	 		}
		    else {
	 			labelscore.setText("Score: " + backend.score);
	 			
	 			if(backend.paused == true) {
			    	labelscore.setForeground(Color.WHITE);
			    	labelstone.setForeground(Color.WHITE);
			    	labeloptions.setForeground(Color.WHITE);
			    }
			    else {
			    	Color labelcolor = Color.GREEN;
			    	try {
				    	switch (backend.currentstone.color) {
	    				case "bk":
	    					labelcolor = Color.BLACK;
	    					break;
	    				case "wh":
	    					labelcolor = Color.WHITE;
	    					break;
	    				case "bl":
	    					labelcolor = Color.BLUE;
	    					break;
	    				case "gr":
	    					labelcolor = Color.GREEN;
	    					break;
	    				case "ye":
	    					labelcolor = Color.YELLOW;
	    					break;
	    				case "re":
	    					labelcolor = Color.RED;
	    					break;
	    				case "ma":
	    					labelcolor = Color.MAGENTA;
	    					break;
	    				case "or":
	    					labelcolor = Color.ORANGE;
	    					break;
	    				case "cy":
	    					labelcolor = Color.CYAN;
	    					break;
	    				case "":
	    					labelcolor = Color.GREEN;
	    					break;
	    				}
			    	} catch (NullPointerException nullerr) {}
			    	labelscore.setForeground(labelcolor);
			    	labelstone.setForeground(labelcolor);
			    	labeloptions.setForeground(labelcolor);
			    }
	 		}   
 		}
 		
 	//=========================================================
 	//close
 		
 		backend.active = false;		//declare that app is closing
 		frame.dispose();			//close window
	}
	
	
}