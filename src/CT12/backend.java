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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stone Stein1 = new Stone();
		
	}
}

class Stone				
{
	String 		type;
	int 		xPosition;
	int 		yPosition;
	String 		coler;
	boolean 	drop;
	boolean 	ghost;
	boolean 	endPosition;
	
	Stone(String type, int xPosition, int yPosition, String coler, boolean drop, boolean ghost, boolean endPosition) // Konstruktor
	{
		this.type = type;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.coler = coler;
		this.drop = drop;
		this.ghost = ghost;
		this.endPosition = endPosition;
	}
	
	void rotate() 
	{
		
	} 
	
	void moveright()
	{
		
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