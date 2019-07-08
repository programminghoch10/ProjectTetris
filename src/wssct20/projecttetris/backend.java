package wssct20.projecttetris;

public class backend {
	
	public static int dimensionx = 10;		//game field dimensions
	public static int dimensiony = 15;
	
	public static boolean started = false;		//defining if game has started
	public static boolean paused = false;		//defining if game is paused
	public static boolean active = true;		//defining if game is active/running, end processes if not
	public static boolean gameover = false;		//defining if game is over
	
	public static String mi = ""; //initial value for gamematrix and staticmatrix
	public static String[][] gamematrix = new String[dimensiony][dimensionx];		//gamematrix contains everything that needs to be visible
	public static String[][] staticmatrix = new String[dimensiony][dimensionx];		//staticmatrix only contains static (fallen) stones, to test collision
	public static String[][] previewmatrix = new String[3*4][3];
	
	public static int[] nextStoneTypes = new int[Stone.arrayStoneType.length * 2];
	public static Stone currentstone = null;
	public static Stone ghoststone = null;
	public static int score = 0;
	
	public static boolean fastdropping = false;		//defines if currentstone is fastdropping (when arrow down key is pressed)
	
	public static Thread Dropper = new Thread() { 			//parallel processing
		@Override 
		public void run() {
			System.out.println("Dropper started");
			while (backend.active) {
				// second Thread for managing autodrop and fast dropping
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					if (!backend.fastdropping) e.printStackTrace();		//if interrupt comes from ui thread with intention to fast drop, do not show error
				}
				if (backend.paused) {
					System.out.println("Dropper paused");
					while (backend.paused && backend.active) {
						//wait if game paused
						try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
					}
					System.out.println("Dropper resumed");
					continue;	// skip moving directly after resume
				}
				while (backend.currentstone == null) { //wait for creation of object
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				}
				
				if (backend.fastdropping) {
					//System.out.println("started fast dropping");
					while (backend.fastdropping && !backend.currentstone.endPosition) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						backend.currentstone.fall();
					}
					//System.out.println("stopped fast dropping");
					continue;
				}
				backend.currentstone.fall();
				backend.ghoststone.fall();
			}
			System.out.println("Dropper stopped.");
		}
	};
	
	static Thread UI = new Thread() { 			//start UI Thread
		@Override 
		public void run() {
			//Thread for UI
			System.out.println("UI started");
			ui.UIstart(null);
			System.out.println("UI stopped");
		}
	};
	
	public static void main(String[] args) {
		
		for (int y = 0; y < staticmatrix.length; y++) {				//setup initial values of matrices
			for (int x = 0; x < staticmatrix[0].length; x++) {
				staticmatrix[y][x] = mi;
				gamematrix[y][x] = mi;
			}
		}
		
		UI.start();			//comment this line out to disable UI
		while (!started && active) {try{Thread.sleep(1000);} catch (InterruptedException ir) {}}   //wait for UI to start game
		Dropper.start();	//Comment this line out to disable gameplay/dropping
		
		int nextLength = 0;		//nextStone initial setup
		for (int i = 0; i < nextStoneTypes.length; i++) {
			nextStoneTypes[i] = -1;
		}
		
		System.out.println("Main started");
		while (active) {
			try {
				while (!paused && active) {
					//general game managing loop
					
					if (currentstone == null) {
						if (nextLength <= Stone.arrayStoneType.length) {
							int prearray[] = new int[Stone.arrayStoneType.length];
							for (int i = 0; i < Stone.arrayStoneType.length; i++) {		//generate every type if stone
								prearray[i] = i;
								nextStoneTypes[nextLength + i] = -1;
							}
							for (int i = 0; i < prearray.length; i++) {		//shuffle those stones
								int nextplace = 0;
								do {
									nextplace = nextLength + (int)(Math.random()*prearray.length);
								} while (nextStoneTypes[nextplace] != -1);
								nextStoneTypes[nextplace] = prearray[i];
							}
							nextLength += prearray.length;
						}
						
						currentstone = new Stone(Stone.arrayStoneType[nextStoneTypes[0]], (int)(backend.staticmatrix[0].length / 2), 1, Stone.arrayStoneTypeColor[nextStoneTypes[0]], true, false, false);
						ghoststone = new Stone(Stone.arrayStoneType[nextStoneTypes[0]], (int)(backend.staticmatrix[0].length / 2), 1, Stone.arrayStoneTypeColor[nextStoneTypes[0]], true, true, false);
						//System.out.println("new stone created: " + currentstone);
						//System.out.println("X: " + currentstone.xPosition);
						//System.out.println("Y: " + currentstone.yPosition);
						//System.out.println("Type: " + currentstone.type);
						//System.out.println("Color: " + currentstone.color);
						
						for (int i = 0; i < nextLength - 1; i++) {
							nextStoneTypes[i] = nextStoneTypes[i + 1];		//move stone up one cell
						}
						nextStoneTypes[nextLength-1] = -1;		//set last cell to "no type"
						nextLength--;
						
						for (int y = 0; y < previewmatrix.length; y++) {
							for (int x = 0; x < previewmatrix[0].length; x++) {
								previewmatrix[y][x] = "";
							}
						}
							for (int i = 0; i < previewmatrix.length / 4; i++) {
								Stone currentpreviewstone = new Stone(Stone.arrayStoneType[nextStoneTypes[i]], (i * 4) + 1, Stone.arrayStoneTypeColor[nextStoneTypes[i]], false);
								try {
									currentpreviewstone.insertintopreviewmatrix(currentpreviewstone.yPosition);
								} catch (ArrayIndexOutOfBoundsException arrerr) {}
							}
					}
					
					if (backend.currentstone.endPosition) {
						//System.out.println("received end position");
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
						//System.out.println("end position: removed current stone");
						currentstone = null;
						ghoststone = null;
						
						for(int h = 0; h < backend.staticmatrix.length; h++) {	//check every line of fullness
							int linecount = 0; 
								for(int i=0; i <  backend.staticmatrix[0].length; i++) { //check for full last line
									if (backend.staticmatrix[h][i] != "") {	// yes or no
									linecount++;
									}
								}
								if( backend.staticmatrix[0].length == linecount ) {  //delete last line
									for(int k=0; k < backend.staticmatrix[0].length; k++) { // clean the line
										backend.staticmatrix[h][k] = ""; 
									}
									for(int y = h - 1; y > 0; y--) { //move the elements down
										for(int x = 0; x < backend.staticmatrix[0].length; x++) {
											backend.staticmatrix[y + 1][x] = backend.staticmatrix[y][x];
											backend.gamematrix[y + 1][x] = backend.staticmatrix[y][x];
										}
									}
									score = score + 1; // increase the score
									//System.out.println("Score: " + score);
								}
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (backend.currentstone != null) { //save to gamematrix
						for (int y = 0; y < staticmatrix.length; y++) {
							for (int x = 0; x < staticmatrix[0].length; x++) {
								gamematrix[y][x] = staticmatrix[y][x];
							}
						}
						backend.ghoststone.drop();
						backend.ghoststone.insertintogamematrix();
						backend.currentstone.insertintogamematrix();
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (NullPointerException errnull) {}
		}
		System.out.println("Score: " + score);
		System.out.println("Main stopped");
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
	
	static String[] arrayStoneType = {"I","Ll","Lr","Sq","S","T","Z"}; 				//Array with the types of stones
	static String[] arrayStoneTypeColor = {"cy","bl","or","ye","re","ma","gr"}; 	//Array with the colors of stones
	
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
		for (int i = 0; i<((int)(Math.random()*3)); i++) {
			this.rotate();
		}
		if (!(	backend.staticmatrix[this.yPosition][this.xPosition] == ""
			 && backend.staticmatrix[this.yPosition + this.rely2][this.xPosition + this.rely2] == ""
			 && backend.staticmatrix[this.yPosition + this.rely3][this.xPosition + this.rely3] == ""
			 && backend.staticmatrix[this.yPosition + this.rely4][this.xPosition + this.rely4] == "")) {
				backend.gameover = true;
				backend.paused = true;
				backend.currentstone = null;
			}
	}
	
	Stone (String type, int yPosition, String color, boolean ghost) {
		this.type = type;
		this.xPosition = 1;
		this.yPosition = yPosition;
		this.color = color;
		this.dropping = false;
		this.ghost = ghost;
		this.endPosition = false;
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
		if (this.type != "I") {
			for (int i = 0; i<((int)(Math.random()*3)); i++) {
				this.rotate();
			}
		}
	}
	
	void insertintogamematrix() {
		backend.gamematrix[this.yPosition][this.xPosition] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely2][this.xPosition + this.relx2] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely3][this.xPosition + this.relx3] = this.color + "-" + this.ghost + "-" + this.dropping;
		backend.gamematrix[this.yPosition + this.rely4][this.xPosition + this.relx4] = this.color + "-" + this.ghost + "-" + this.dropping;
	}
	void insertintopreviewmatrix(int yPos) {
		backend.previewmatrix[yPos][this.xPosition] = this.color + "-" + this.ghost;
		backend.previewmatrix[yPos + this.rely2][this.xPosition + this.relx2] = this.color + "-" + this.ghost;
		backend.previewmatrix[yPos + this.rely3][this.xPosition + this.relx3] = this.color + "-" + this.ghost;
		backend.previewmatrix[yPos + this.rely4][this.xPosition + this.relx4] = this.color + "-" + this.ghost;
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
		if (this.ghost) {
			this.xPosition = backend.currentstone.xPosition;
			this.yPosition = backend.currentstone.yPosition;
			this.relx2 = backend.currentstone.relx2;
			this.rely2 = backend.currentstone.rely2;
			this.relx3 = backend.currentstone.relx3;
			this.rely3 = backend.currentstone.rely3;
			this.relx4 = backend.currentstone.relx4;
			this.rely4 = backend.currentstone.rely4;
		}
		while (!this.endPosition) { //dropping until at end
			this.fall();
		}
		if (this.ghost) this.endPosition = false;
	}
}