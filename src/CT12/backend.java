package CT12;

public class backend {
	
	private static String gmi = "bl-n-n"; //gamematrix initial value for every cell for testing
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
		
		//e. g. create a object Stone Stein1 = new Stone(1,1,1,re,0,0,0,);
		
	}
}

class Stone				
{
	String 		type;
	int 		xPosition;
	int 		yPosition;
	String 		color;
	boolean 	drop;
	boolean 	ghost;
	boolean 	endPosition;
	
	static String[] arrayStoneType = {"I","Ll","Lr","Sq","S","T","Z"}; //Array with the types of stones
	
	int relx2 = 0;
	int rely2 = 0;
	int relx3 = 0;
	int rely3 = 0;
	int relx4 = 0;
	int rely4 = 0;
	
	Stone(String type, int xPosition, int yPosition, String color, boolean drop, boolean ghost, boolean endPosition) // constructor
	{
		this.type = type;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
		this.drop = drop;
		this.ghost = ghost;
		this.endPosition = endPosition;
		switch (type) {
		case "I":
			this.relx2 = -1;
			this.rely2 = 0;
			this.relx3 = 0;
			this.rely3 = -1;
			this.relx4 = 1;
			this.rely4 = 0;
		case "Ll":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = 1;
			this.relx4 = -1;
			this.rely4 = 1;
		case "Lr":
			this.relx2 = 0;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = 1;
			this.relx4 = 1;
			this.rely4 = -1;
		case "Sq":
			this.relx2 = 1;
			this.rely2 = 0;
			this.relx3 = 1;
			this.rely3 = 1;
			this.relx4 = 0;
			this.rely4 = 1;
		case "S":
			this.relx2 = 0;
			this.rely2 = 1;
			this.relx3 = 1;
			this.rely3 = 1;
			this.relx4 = -1;
			this.rely4 = 0;
		case "T":
			this.relx2 = -1;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = -1;
			this.relx4 = 1;
			this.rely4 = -1;
		case "Z":
			this.relx2 = -1;
			this.rely2 = -1;
			this.relx3 = 0;
			this.rely3 = -1;
			this.relx4 = 1;
			this.rely4 = 0;
		}
		for (int i = 0; i<((int)(Math.random()*3)); i++) {
			this.rotate();
		}
	}
	
	void rotate() {
		int newrelx2 = this.rely2 *(-1);
		int newrely2 = this.relx2;
		int newrelx3 = this.rely3 *(-1);
		int newrely3 = this.relx3;
		int newrelx4 = this.rely4 *(-1);
		int newrely4 = this.relx4;
		if (   backend.staticmatrix[this.yPosition + newrely2][this.xPosition + newrelx2] == "0"
			&& backend.staticmatrix[this.yPosition + newrely3][this.xPosition + newrelx3] == "0"
			&& backend.staticmatrix[this.yPosition + newrely4][this.xPosition + newrelx4] == "0") {
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
	
	void moveright()
	{
		
		
		if(		backend.staticmatrix[this.yPosition][this.xPosition +1] == "" && 								
				backend.staticmatrix[this.yPosition + this.rely2][this.xPosition + this.relx2 +1] == "" &&
				backend.staticmatrix[this.yPosition + this.rely3][this.xPosition + this.relx3 +1] == "" &&
				backend.staticmatrix[this.yPosition + this.rely4][this.xPosition + this.relx4 +1] == "" )
		{
				xPosition = xPosition +1;		
				this.relx2 = this.relx2 +1;
				this.relx3 = this.relx3 +1;
				this.relx4 = this.relx4 +1;
		}
		
		else 
		
		{}
	}
	
	void moveleft()
	{
		
	}
	void fall() 
	{
		
	}
	void drop() 
	{
		
	}
	
}