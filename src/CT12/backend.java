package CT12;

public class backend {
	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//e.g. create a object Stone Stein1 = new Stone(1,1,1,re,0,0,0,);
		
		Stone stone1 = new Stone(Stone.arrayStoneType[6], 2, 1, "bl", false, false, false);
		//stone1.fall();
		gamematrix = staticmatrix;
		stone1.insertintogamematrix();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				if (gamematrix[y][x] != "")	{
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.println();
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
			// collision detected
		}
	}
	
	void movehorizontal(int direction) {
		if (	   backend.staticmatrix[this.yPosition][this.xPosition + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely2][this.xPosition + this.relx2 + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely3][this.xPosition + this.relx3 + direction] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely4][this.xPosition + this.relx4 + direction] == "" ) {
				xPosition = xPosition + direction;		
			} else { 
				// collision detected
			}
	}
	
	
	void fall() 
	{
		if (	   backend.staticmatrix[this.yPosition +1][this.xPosition] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely2 +1][this.xPosition + this.relx2] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely3 +1][this.xPosition + this.relx3] == "" 
				&& backend.staticmatrix[this.yPosition + this.rely4 +1][this.xPosition + this.relx4] == "" ) {
				yPosition = yPosition + 1;		
			} else {
				endPosition = true;
				// collision detected
			}
	}
	
	void drop() 
	{
		while (!this.endPosition) {
			this.fall();
		}
	}
	
}