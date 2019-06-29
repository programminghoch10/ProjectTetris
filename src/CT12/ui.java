package CT12;

import javax.swing.*;
import java.awt.*;

public class ui {
	
	//=========================================================
	
	
	
	//=========================================================
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame frame = new JFrame("Tetris Game CT12"); 				//create new Frame
		frame.setSize(600, 1000);										//set start size 		
		frame.setVisible(true);										//Window becomes visible
		frame.setLayout(new GridLayout(15, 10));
	    for (int i=0; i<15*10; i++) {
	    	JPanel J = new JPanel();
	    	switch ((int)(Math.random()*6)) {
	    	case 0:
	    		J.setBackground(Color.BLACK);
	    		break;
	    	case 1:
	    		J.setBackground(Color.GREEN);
	    		break;
	    	case 2:
	    		J.setBackground(Color.BLUE);
	    		break;
	    	case 3:
	    		J.setBackground(Color.MAGENTA);
	    		break;
	    	case 4:
	    		J.setBackground(Color.RED);
	    		break;
	    	case 5:
	    		J.setBackground(Color.ORANGE);
	    		break;
	    	}
	    	frame.add(J);
	    }
	    frame.pack();
	    
		
		//==========================================================
		
		
		
		//==========================================================

	}
	
	
}