package CT12;

public class backend {
	
	public static boolean paused = false;
	public static boolean active = true;
	
	private static String gmi = ""; //gamematrix initial value for every cell for testing
	public static String[][] gamematrix = {
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
	};
	// only predefined like this until there is a proper initialization within the backend main function
	// also comes in handy for testing connection to ui class
	
	public static String[][] staticmatrix = {
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
			{gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi,gmi},
	};
	//matrix only contains static (fallen) stones, to test collision
	
	public static Stone currentstone = null;
	public static int score = 0;
	
	public static boolean fastdropping = false;
	
	public static Thread Dropper = new Thread() { 			//parallel processing
		@Override 
		public void run() {
			while (backend.active) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					if (!backend.fastdropping == true) e.printStackTrace();
					//if interrupt comes from ui thread with intention to fast drop, do not show error
				}
				if (backend.paused) {
					System.out.println("dropper paused");
					while (backend.paused) {
						//wait if game paused
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("dropper resumed");
					continue;	// skip moving directly after resume
				}
				while (backend.currentstone == null); //wait for creation of object
				
				if (backend.fastdropping == true) {
					//System.out.println("now dropping");
					while (backend.fastdropping == true && backend.currentstone.endPosition == false) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						backend.currentstone.fall();
					}
					//System.out.println("stopped dropping");
					continue;
				}
				
				backend.currentstone.fall();
				
				/*for (int y = 0; y < gamematrix.length; y++) {
					for (int x = 0; x < gamematrix[0].length; x++) {
						if (gamematrix[y][x] != "")	{
							System.out.print("1");
						} else {
							System.out.print("0");
						}
					}
					System.out.println();
				}*/
			}
		}
	};
	
	static Thread UI = new Thread() { 			//start UI Thread
		@Override 
		public void run() {
			ui.UIstart(null);
		}
	};
	
	public static void main(String[] args) {
		
		UI.start(); //comment this line out to disable UI
		Dropper.start(); //Comment this line out to disable gameplay
		
		//System.out.println("enabled loop");
		while (active) {
			while (!paused && active) {
				//loop for managing game
				
				if (currentstone == null) {
					System.out.println(currentstone);
					int nextStonetype = (int)(Math.random()*6);
					//int nextStonetype = 3;
					currentstone = new Stone(Stone.arrayStoneType[nextStonetype], (int)(backend.staticmatrix[0].length / 2), 1, Stone.arrayStoneTypeColor[nextStonetype], true, false, false);
					System.out.println("new stone created: " + currentstone);
					System.out.println("X: " + currentstone.xPosition);
					System.out.println("Y: " + currentstone.yPosition);
					System.out.println("Type: " + currentstone.type);
					System.out.println("Color: " + currentstone.color);
				}
				
				if (backend.currentstone.endPosition) {
					System.out.println("received end position");
					for (int y = 0; y < staticmatrix.length; y++) {
						for (int x = 0; x < staticmatrix[0].length; x++) {
							gamematrix[y][x] = staticmatrix[y][x];
						}
					}
					currentstone.insertintogamematrix();
					for (int y = 0; y < staticmatrix.length; y++) {
						for (int x = 0; x < staticmatrix[0].length; x++) {
							staticmatrix[y][x] = gamematrix[y][x];
						}
					}
					System.out.println("end position: removed current stone");
					currentstone = null;
					
					int linecount = 0;			//check for full last line
					for(int i=0; i <  backend.staticmatrix[0].length; i++){
						if (backend.staticmatrix[backend.staticmatrix.length - 1][i] != "") {
							linecount++;
						}
					}
					
					if( backend.staticmatrix[0].length == linecount ) {  //delete last line
						for(int k=0; k < backend.staticmatrix[0].length; k++) {
							backend.staticmatrix[backend.staticmatrix.length - 1][k] = ""; 
						}
						
						for(int y = backend.staticmatrix.length - 2; y > 0; y--) {
							for(int x = 0; x < backend.staticmatrix[0].length; x++) {
								backend.staticmatrix[y + 1][x] = backend.staticmatrix[y][x];
								backend.gamematrix[y + 1][x] = backend.staticmatrix[y][x];
							}
						}
						score = score +1; // increase the score
						System.out.println("Score: " + score);
					}
					
					
					
					//debug: show matrix in console
					for (int y = 0; y < staticmatrix.length; y++) {
						for (int x = 0; x < staticmatrix[0].length; x++) {
							if (staticmatrix[y][x] != "")	{
								System.out.print("#");
							} else {
								System.out.print(".");
							}
						}
						System.out.println();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (backend.currentstone != null) { //save to gamematrix
					//System.out.println("not yet at end");
					for (int y = 0; y < staticmatrix.length; y++) {
						for (int x = 0; x < staticmatrix[0].length; x++) {
							gamematrix[y][x] = staticmatrix[y][x];
						}
					}
					//backend.gamematrix = backend.staticmatrix;
					backend.currentstone.insertintogamematrix();
				}
				
				/*
				for (int i = 0; i<10; i++) {
					System.out.println("");
				}
				for (int y = 0; y < gamematrix.length; y++) { //console gameplay
					for (int x = 0; x < gamematrix[0].length; x++) {
						if (gamematrix[y][x] != "")	{
							System.out.print("#");
						} else {
							System.out.print("-");
						}
					}
					System.out.println();
				}*/
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Stone    
{
	String 		type;
	int 		xPosition;
	int 		yPosition;
	String 		color;
	boolean 	dropping;
	boolean 	ghost;
	boolean 	endPosition;
	
	static String[] arrayStoneType = {"I","Ll","Lr","Sq","S","T","Z"}; //Array with the types of stones
	static String[] arrayStoneTypeColor = {"bl","ye","re","gr","or","cy","ma"}; //Array with the colors of stones
	
	int relx2 = 0;
	int rely2 = 0;
	int relx3 = 0;
	int rely3 = 0;
	int relx4 = 0;
	int rely4 = 0;
	
	Stone(String type, int xPosition, int yPosition, String color, boolean dropping, boolean ghost, boolean endPosition) // constructor
	{
		this.type = type;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
		this.dropping = dropping;
		this.ghost = ghost;
		this.endPosition = endPosition;
		switch (type) {
		case "I":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = 1;
			this.relx4 = 0;
			this.rely4 = 2;
			break;
		case "Ll":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = 1;
			this.relx4 = -1;
			this.rely4 = 1;
			break;
		case "Lr":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = 1;
			this.relx4 = 1;
			this.rely4 = 1;
			break;
		case "Sq":
			this.relx2 = 1;
			this.rely2 = 0;
			this.relx3 = 1;
			this.rely3 = 1;
			this.relx4 = 0;
			this.rely4 = 1;
			break;
		case "S":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = -1;
			this.rely3 = 0;
			this.relx4 = 1;
			this.rely4 = -1;
			break;
		case "T":
			this.relx2 = -1;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = -1;
			this.relx4 = 1;
			this.rely4 = -1;
			break;
		case "Z":
			this.relx2 = -1;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = -1;
			this.relx4 = 1;
			this.rely4 = 0;
			break;
		default:
			this.relx2 = 0;
			this.rely2 = 0;
			this.relx3 = 0;
			this.rely3 = 0;
			this.relx4 = 0;
			this.rely4 = 0;
			break;
		}
		/*for (int i = 0; i<((int)(Math.random()*3)); i++) {
			this.rotate();
		}*/
	}
	
	void insertintogamematrix() {
		backend.gamematrix[this.yPosition][this.xPosition] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely2][this.xPosition + this.relx2] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely3][this.xPosition + this.relx3] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely4][this.xPosition + this.relx4] = this.color + "-" + this.ghost + "-" + this.dropping;
	}
	
	void rotate() {
		if (this.type == "Sq") return;
		try {
		int newrelx2 = this.rely2 *(-1);
		int newrely2 = this.relx2;
		int newrelx3 = this.rely3 *(-1);
		int newrely3 = this.relx3;
		int newrelx4 = this.rely4 *(-1);
		int newrely4 = this.relx4;
		if (   backend.staticmatrix[this.yPosition + newrely2][this.xPosition + newrelx2] == ""
			&& backend.staticmatrix[this.yPosition + newrely3][this.xPosition + newrelx3] == ""
			&& backend.staticmatrix[this.yPosition + newrely4][this.xPosition + newrelx4] == "") {
			this.relx2 = newrelx2;
			this.rely2 = newrely2;
			this.relx3 = newrelx3;
			this.rely3 = newrely3;
			this.relx4 = newrelx4;
			this.rely4 = newrely4;
		} else {
			// collision detected, no rotation possible
		}
		} catch (ArrayIndexOutOfBoundsException aerr) {
			//error expected, no action needed
		}
	}
	
	void move(int direction) {
			
		if (!(		this.xPosition  + direction < backend.staticmatrix[0].length
				 && this.xPosition + this.relx2 + direction < backend.staticmatrix[0].length
				 && this.xPosition + this.relx3 + direction < backend.staticmatrix[0].length
				 && this.xPosition + this.relx4 + direction < backend.staticmatrix[0].length
				 && this.xPosition  + direction >= 0
				 && this.xPosition + this.relx2 + direction >= 0
				 && this.xPosition + this.relx3 + direction >= 0
				 && this.xPosition + this.relx4 + direction >= 0)) {
				//reached side, can't move
				return;
		}
		
		if (   	   backend.staticmatrix[this.yPosition][this.xPosition + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely2][this.xPosition + this.relx2 + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely3][this.xPosition + this.relx3 + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely4][this.xPosition + this.relx4 + direction] == "" ) {
				xPosition = xPosition + direction;		
		} else { 
			// collision detected, move not possible
		}
		
	}
	
	void fall() {
		if (!(	this.yPosition + 1 < backend.staticmatrix.length
			 && this.yPosition + this.rely2 + 1 < backend.staticmatrix.length
			 && this.yPosition + this.rely3 + 1 < backend.staticmatrix.length
			 && this.yPosition + this.rely4 + 1 < backend.staticmatrix.length)) {
			//bottom reached, declaring final position
			this.endPosition = true;
			return;
		}
		if (   backend.staticmatrix[this.yPosition + 1][this.xPosition] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely2 + 1][this.xPosition + this.relx2] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely3 + 1][this.xPosition + this.relx3] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely4 + 1][this.xPosition + this.relx4] == "" ) {
				yPosition = yPosition + 1;		
			} else {
				// collision detected, declaring final position
				this.endPosition = true;
			}
	}
	
	void drop() 
	{
		while (!this.endPosition) { //dropping until at end
			this.fall();
		}
	}
}