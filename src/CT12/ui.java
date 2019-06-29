package CT12;

import javax.swing.JFrame;

public class ui {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(backend.gamematrix[1][1].split("-")[0]);
		
		JFrame fenster = new JFrame("Tetris Game CT12"); 	//Neues Frame (Fenster)
		fenster.setSize(600, 400);		//Startgroesse festlegen
		fenster.setVisible(true);		//Fenster wird sichtbar gemacht
	}

}