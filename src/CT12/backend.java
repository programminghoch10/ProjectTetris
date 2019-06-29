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
	
	public static void main(String[] args) {
		Thread UI = new Thread() { 			//start UI Thread
			@Override 
			public void run() {
				ui.UIstart(args);
			}
		};
		UI.start(); //comment this line out to disable UI
		
		Thread Dropper = new Thread() { 			//parallel processing
			@Override 
			public void run() {
				while (backend.active) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (backend.paused) {
						while (backend.paused); //wait if game paused
						continue;	// skip moving directly after resume
					}
					while (backend.currentstone == null); //wait for creation of object
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
					if (!backend.currentstone.endPosition) {
						//System.out.println("not yet at end");
						for (int y = 0; y < staticmatrix.length; y++) {
							for (int x = 0; x < staticmatrix[0].length; x++) {
								gamematrix[y][x] = staticmatrix[y][x];
							}
						}
						//backend.gamematrix = backend.staticmatrix;
						backend.currentstone.insertintogamematrix();
					}
				}
			}
		};
		Dropper.start(); //Comment this line out to disable gameplay

		//System.out.println("enabled loop");
		while (active) {
			while (!paused) {
				//loop for managing game
				
				if (currentstone == null) {
					int nextStonetype = (int)(Math.random()*6);
					currentstone = new Stone(Stone.arrayStoneType[nextStonetype], (int)(backend.staticmatrix[0].length / 2), 1, Stone.arrayStoneTypeColor[nextStonetype], true, false, false);
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
					currentstone = null;
					
					//debug: show matrix in console
					for (int y = 0; y < gamematrix.length; y++) {
						for (int x = 0; x < gamematrix[0].length; x++) {
							if (gamematrix[y][x] != "")	{
								System.out.print("#");
							} else {
								System.out.print(".");
							}
						}
						System.out.println();
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i<10; i++) {
					System.out.println("");
				}
				for (int y = 0; y < gamematrix.length; y++) {
					for (int x = 0; x < gamematrix[0].length; x++) {
						if (gamematrix[y][x] != "")	{
							System.out.print("#");
						} else {
							System.out.print("-");
						}
					}
					System.out.println();
				}
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
	}
	
	void move(int direction) {
		if (   backend.staticmatrix[this.yPosition][this.xPosition + direction] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely2][this.xPosition + this.relx2 + direction] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely3][this.xPosition + this.relx3 + direction] == "" 
			&& backend.staticmatrix[this.yPosition + this.rely4][this.xPosition + this.relx4 + direction] == "" ) {
				xPosition = xPosition + direction;		
			} else { 
				// collision detected, move not possible
			}
	}
	
	
	void fall() {
		if (!(	this.xPosition < backend.staticmatrix[0].length
			 && this.yPosition + 1 < backend.staticmatrix.length
			 && this.xPosition + this.relx2 < backend.staticmatrix[0].length
			 && this.yPosition + this.rely2 + 1 < backend.staticmatrix.length
			 && this.xPosition + this.relx3 < backend.staticmatrix[0].length
			 && this.yPosition + this.rely3 + 1 < backend.staticmatrix.length
			 && this.xPosition + this.relx4 < backend.staticmatrix[0].length
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