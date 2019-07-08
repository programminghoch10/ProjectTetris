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
import java.util.HashMap;

public class ui {
	
	//=========================================================
	
	static JPanel[][] jpanelarray = new JPanel[backend.staticmatrix.length][backend.staticmatrix[0].length];
	static JPanel[][] previewarray = new JPanel[backend.previewmatrix.length][backend.previewmatrix[0].length];
	
	static JPanel[] menuarray = new JPanel[20];
	static JPanel[] optionsarray = new JPanel[5];
	
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
		
		JLabel labelstone = new JLabel("        Next Stone");
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
		
		paneloptions.setLayout(new BorderLayout());
		
		JLabel labeloptions = new JLabel("Options");
		labeloptions.setFont(labeloptions.getFont().deriveFont(18f));
		labeloptions.setForeground(Color.WHITE);
		paneloptions.add(labeloptions, BorderLayout.NORTH);
		
		JPanel panelabstand2 = new JPanel();
		panelabstand2.setBackground(Color.BLACK);
		panelabstand2.setPreferredSize(new Dimension(35, 0));
		paneloptions.add(panelabstand2, BorderLayout.EAST);
		
		JPanel paneloptionbuttons = new JPanel();
		paneloptionbuttons.setBackground(Color.BLACK);
		paneloptions.add(paneloptionbuttons, BorderLayout.CENTER);
		
		paneloptionbuttons.setLayout(new GridLayout(20,3));
		
		for (int x = 0; x < 0; x++) {
	    	optionsarray[x] = new JPanel();
	    	paneloptionbuttons.add(optionsarray[x]);
	    }
		JPanel panelabstand3 = new JPanel();
		panelabstand3.setBackground(Color.BLACK);
		panelabstand3.setPreferredSize(new Dimension(0,0));
		paneloptionbuttons.add(panelabstand3);
		
		for (int x = 1 ; x < 1; x++) {
	    	optionsarray[x] = new JPanel();
	    	paneloptionbuttons.add(optionsarray[x]);
	    }
		JButton pausebutton = new JButton("PAUSE");
		pausebutton.setFocusable(false);
		Font optionsfont = new Font("Serif",Font.BOLD,20);
		pausebutton.setFont(optionsfont);
		pausebutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		pausebutton.setBackground(new Color(15,15,15));
		pausebutton.setForeground(Color.WHITE);
		paneloptionbuttons.add(pausebutton);
		
		for (int x = 2; x < 2; x++) {
	    	optionsarray[x] = new JPanel();
	    	paneloptionbuttons.add(optionsarray[x]);
	    }
		JPanel panelabstand4 = new JPanel();
		panelabstand4.setBackground(Color.BLACK);
		panelabstand4.setPreferredSize(new Dimension(0,0));
		paneloptionbuttons.add(panelabstand4);
		
		for (int x = 4; x < 4; x++) {
	    	optionsarray[x] = new JPanel();
	    	paneloptionbuttons.add(optionsarray[x]);
	    }
		JButton resetbutton = new JButton("RESET");
		resetbutton.setFocusable(false);
		resetbutton.setFont(optionsfont);
		resetbutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		resetbutton.setBackground(new Color(15,15,15));
		resetbutton.setForeground(Color.WHITE);
		paneloptionbuttons.add(resetbutton);
		
		for (int x = 5; x < 5; x++) {
    		optionsarray[x] = new JPanel();
    		paneloptionbuttons.add(optionsarray[x]);
    	}
		JButton exitbutton = new JButton("EXIT");
		exitbutton.setFocusable(false);
		exitbutton.setFont(optionsfont);
		exitbutton.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		exitbutton.setBackground(new Color(15,15,15));
		exitbutton.setForeground(Color.WHITE);
		paneloptionbuttons.add(exitbutton);
		
		for (int x = 0; x < 5; x++) {
    		optionsarray[x] = new JPanel();
    		paneloptionbuttons.add(optionsarray[x]);
		}
		
		pausebutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backend.paused = !backend.paused;
				System.out.println("new pause state: " + backend.paused);
			}
		});
		
		if (pausebutton.getText() == "PAUSE") {
			pausebutton.setText("CONTINUE");
		} else {
			pausebutton.setText("PAUSE");
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
		
		for (int x = 0; x < 5; x++) {
	    	optionsarray[x].setBackground(Color.BLACK);
		   
	    }
		
		while(!backend.started && backend.active) {
			try{Thread.sleep(1000);} catch (InterruptedException ir) {}	
		}
		
		for (int x = 0; x < 5; x++) {
			//System.out.println(y);
			paneloptionbuttons.remove(optionsarray[x]);
    	}
		
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
        
        panelgame1.setBackground(Color.DARK_GRAY);
		
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
         
         HashMap<String, Color> colortable = new HashMap<String, Color>() {
             /**
			 * HashMap containing almost all needed colors of the game.
			 */
			private static final long serialVersionUID = 2192183556642580997L;

			{
            	 put("bk", Color.BLACK);
            	 put("wh", Color.WHITE);
                 put("bl", Color.BLUE);
                 put("gr", Color.GREEN);
                 put("ye", Color.YELLOW);
                 put("re", Color.RED);
                 put("ma", Color.MAGENTA);
                 put("or", Color.ORANGE);
                 put("cy", Color.CYAN);
                 put("empty", Color.BLACK);
                 
                 //G indicates the Grayscale Color
                 put("Gbk", Color.BLACK);
            	 put("Gwh", Color.WHITE);
                 put("Gbl", new Color(90, 90, 90));
                 put("Ggr", new Color(100, 100, 100));
                 put("Gye", new Color(110, 110, 110));
                 put("Gre", new Color(120, 120, 120));
                 put("Gma", new Color(130, 130, 130));
                 put("Gor", new Color(140, 140, 140));
                 put("Gcy", new Color(150, 150, 150));
                 put("G", Color.BLACK);
                 
                 put("gridcolor", Color.DARK_GRAY);
             }
         };
         final int ghostthickness = 4;
         
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
		    				
		    				if (pausebutton.getText() != "CONTINUE") pausebutton.setText("CONTINUE");
		    				
		    				if (backend.gamematrix[y][x] == "") {
		    					jpanelarray[y][x].setBackground(colortable.get("empty"));
		    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colortable.get("gridcolor")));						//displays a grid
		    				} else {
		    					boolean ghost = !(backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false");
		    					int currentthickness = 1;
		    					if (ghost) {
		    						jpanelarray[y][x].setBackground(colortable.get("G"));
		    						currentthickness = ghostthickness;
		    					}
	    						Color currentcolor = colortable.get("G" + backend.gamematrix[y][x].split("-")[0]);
    							if (!ghost & jpanelarray[y][x].getBackground() != currentcolor) jpanelarray[y][x].setBackground(currentcolor);
    	    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(currentcolor, currentthickness));
		    				}
		    			} else {
		    				
		    				if (pausebutton.getText() != "PAUSE") pausebutton.setText("PAUSE");
		    				
		    				if ((backend.gamematrix[y][x]+"-false").split("-")[1].contentEquals("false")) {
		    					if (backend.gamematrix[y][x].split("-")[0] == "") {
			    					if (jpanelarray[y][x].getBackground() != colortable.get("empty")) jpanelarray[y][x].setBackground(colortable.get("empty"));
		    						jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colortable.get("gridcolor")));
			    				} else {
			    					Color currentcolor = colortable.get(backend.gamematrix[y][x].split("-")[0]);
				    				if (jpanelarray[y][x].getBackground() != currentcolor) jpanelarray[y][x].setBackground(currentcolor);
		    						jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(currentcolor));
			    				}
		    				} else {
		    					
		    					if (backend.gamematrix[y][x].split("-")[0] == "") {
			    					if (jpanelarray[y][x].getBackground() != colortable.get("empty")) jpanelarray[y][x].setBackground(colortable.get("empty"));
		    						jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(colortable.get("gridcolor")));
			    				} else {
			    					Color currentcolor = colortable.get(backend.gamematrix[y][x].split("-")[0]);
			    					if (jpanelarray[y][x].getBackground() != colortable.get("G")) jpanelarray[y][x].setBackground(colortable.get("G"));
			    					jpanelarray[y][x].setBorder(BorderFactory.createLineBorder(currentcolor, ghostthickness));
			    				}
		    			}
		    		}
		    	}    	
		    }
		    
		    for (int y = 0; y < previewarray.length; y++) {
		    	for (int x = 0; x < previewarray[0].length; x++) {
	    			if (backend.paused) {
	    				Color gridcolor = colortable.get("gridcolor");
	    				if (backend.previewmatrix[y][x] == "") {
	    					previewarray[y][x].setBackground(colortable.get("empty"));
	    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(gridcolor));						//displays a grid
	    				} else {
	    					boolean ghost = !(backend.previewmatrix[y][x]+"-false").split("-")[1].contentEquals("false");
	    					int currentthickness = 1;
	    					if (ghost) {
	    						previewarray[y][x].setBackground(colortable.get("G"));
	    						currentthickness = ghostthickness;
	    					}
	    					Color currentcolor = colortable.get("G" + backend.previewmatrix[y][x].split("-")[0]);
	    					if (!ghost & previewarray[y][x].getBackground() != currentcolor) previewarray[y][x].setBackground(currentcolor);
	    					previewarray[y][x].setBorder(BorderFactory.createLineBorder(currentcolor, currentthickness));
	    					
	    				}		    				
	    				
	    			} else {
	    				
	    				if (backend.previewmatrix[y][x].split("-")[0] == "") {
	    					if (previewarray[y][x].getBackground() != colortable.get("empty")) previewarray[y][x].setBackground(colortable.get("empty"));
    						previewarray[y][x].setBorder(BorderFactory.createLineBorder(colortable.get("gridcolor")));
	    				} else {
	    					Color currentcolor = colortable.get(backend.previewmatrix[y][x].split("-")[0]);
		    				if (previewarray[y][x].getBackground() != currentcolor) previewarray[y][x].setBackground(currentcolor);
    						previewarray[y][x].setBorder(BorderFactory.createLineBorder(currentcolor));
	    				}
	    			}
		    	}
		    } 
		    
		    if (backend.gameover) {
	 			labelscore.setText("GAME OVER!   Score: " + backend.score);
	 			labelscore.setForeground(Color.RED);
	 			labelstone.setForeground(Color.DARK_GRAY);
	 			labeloptions.setForeground(Color.WHITE);
	 			resetbutton.setForeground(Color.WHITE);
	 			exitbutton.setForeground(Color.WHITE);
	 			pausebutton.setForeground(Color.WHITE);
	 		} else {
	 			labelscore.setText("Score: " + backend.score);
	 			
	 			if(backend.paused) {
			    	labelscore.setForeground(Color.WHITE);
			    	labelstone.setForeground(Color.WHITE);
			    	labeloptions.setForeground(Color.WHITE);
			    	resetbutton.setForeground(Color.WHITE);
			    	exitbutton.setForeground(Color.WHITE);
			    	pausebutton.setForeground(Color.WHITE);
			    } else {
			    	Color labelcolor = Color.GREEN;
			    	if (backend.currentstone != null) {
			    		labelcolor = colortable.get(backend.currentstone.color);
			    	}
			    	labelscore.setForeground(labelcolor);
			    	labelstone.setForeground(labelcolor);
			    	labeloptions.setForeground(labelcolor);
			    	resetbutton.setForeground(labelcolor);
			    	exitbutton.setForeground(labelcolor);
			    	pausebutton.setForeground(labelcolor);
			    }
	 		}   
 		}
 		
 	//=========================================================
 	//close
 		
 		backend.active = false;		//declare that app is closing
 		frame.dispose();			//close window
	}
	
	
}