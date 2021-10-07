
public class Player {

	private int score;
	private char selectedSymbol;
	
	public Player()
	{
		score=0;
		selectedSymbol='n';	// n to initialize , r for rock , p for paper , s for scissors
	}
	
	void addScore()
	{
		score +=1;
	}
	
	int getScore()
	{
		return score;
	}
	
	void setScore(int i)
	{
		score=i;
	}
	
	char getSymbol()
	{
		return selectedSymbol;
	}
	
	void Selection(int x)
	{
		if(x==0)
		{
			selectedSymbol = 'r' ;
		}
		
		if(x==1||x==-2)
		{
			selectedSymbol = 'p' ;
		}
		
		if(x==2||x==-1)
		{
			selectedSymbol = 's' ;
		}		
	}
	
}
