
public class Player {

	protected int score;
	protected char selectedSymbol;
	
	public Player()
	{
		score=0;
		selectedSymbol='n';	// n to initialize , r for rock , p for paper , s for scissors
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
