
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;


@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener,KeyListener {

	int resolutionx,resolutiony; 			// get the resolution from main function to adjust interface.
	private Player user, computer; 			//class for holding info about players. score and hand symbol
	private int userSelection = 1; 			//Program starts with user hovering over paper.  
	private char decision = 'f'; 			//if decision is win, draw or lose. initializing value is f  
	private String roundEndString = "" ;	//container for the string to declare rounds' result 
		
	private Timer timer;
	private int delay =	70;
	
	boolean quitgame=false;
	
	//image container class
	protected Images i;
		
	//gameOver checks for if round is over. noRepeat is used as a flag to not trigger accidental button presses.
	protected boolean gameOver=false;
	protected boolean noRepeat = true;
	
	//controls
	protected boolean restart= false;
	protected boolean right=	false;
	protected boolean left	=	false;
	protected boolean spacebar = false;
	
	//adjusting values too cumbersome to write beforehand
	protected int scorePunto ,imageWidth,imageHeight;
	
	
	public Game(int x,int y)
	{		
			resolutionx=x;
			resolutiony=y;
			adjustResolutionValues();
			
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			
			i = new Images(resolutionx,resolutiony);
			user = new Player();
			computer = new Player();
			
			timer = new Timer(delay,this);
			timer.start();
			
	}
	
	public void adjustResolutionValues()
	{
		scorePunto = (int)(resolutionx/35.2);
		imageWidth = resolutionx/5;
		imageHeight = resolutiony/3;
	}

	public void paint(Graphics g)
	{
		requestFocus(true);
		
		//background loop
		g.drawImage(Images.backgroundImage,0,i.backgroundY,resolutionx,resolutiony,null);
		g.drawImage(Images.backgroundImage,0,i.backgroundY2,resolutionx,resolutiony,null);
		
		//score cards
		Font f = new Font("Comic Sans MS",Font.BOLD,scorePunto);
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString("You:"+ user.score, 10, 30);
		g.drawString("Cpu:"+ computer.score, 10, (int)(resolutiony/1.14) );
		
		//user selections
		g.drawImage(Images.rockImage,i.rockX,i.playerRowY, imageWidth, imageHeight, null);
		g.drawImage(Images.paperImage,i.paperX,i.playerRowY , imageWidth, imageHeight, null);
		g.drawImage(Images.scissorsImage,i.scissorsX,i.playerRowY, imageWidth, imageHeight, null);
		
		//square to indicate which symbol is selected by user
		g.drawImage(Images.squareImage,i.squareX,i.playerRowY , imageWidth, imageHeight, null);
		
		//x s to indicate computer's selections, revealed after user locks a symbol 
		g.drawImage(Images.xImage,(int)(resolutionx/7.68),i.computerRowY, imageWidth, imageHeight,null);
		g.drawImage(Images.xImage,(int)(resolutionx/1.536),i.computerRowY, imageWidth, imageHeight,null);	
		g.drawImage(Images.computerHand,(int)(resolutionx/2.56),i.computerRowY, imageWidth, imageHeight,null);
		
		//if statement to draw the result of round to the screen only when the round ends.
		if(decision!='f')
		{		
		f = new Font("Comic Sans MS",Font.BOLD,(int)(resolutionx/19.2));
		g.setFont(f);
		g.drawString(roundEndString, (int)(resolutionx/4), (int)(resolutiony/1.93) );
		}	
		 
	}

	private void adjustScoreandText(char decision)
	{
		if(decision == 'w')
		{
			roundEndString = "You won this round!!";
			user.score++;
		}
		if(decision == 'l')
		{
			roundEndString = "You lost this round!!";
			computer.score++;
		}
		if(decision == 'd')
		{					
			roundEndString = "This round is a draw!!";
		}
	}
	
	//looks for win or lose situations first and if all fails, returns draw because its the only option left.       
	private char decideWinner(char uSymbol, char cSymbol)
	{
		if(cSymbol=='r')
		{
			Images.computerHand=Images.rockImage;
			
			if(uSymbol=='p')
			{
				return 'w';
			}
			
			if(uSymbol=='s')
			{
				return 'l';
			}
		}	
		
		if(cSymbol=='p')
		{
			Images.computerHand=Images.paperImage;
			
			if(uSymbol=='r')
			{
				return 'l';
			}
			
			if(uSymbol=='s')
			{
				return 'w';
			}
		}	
		
		if(cSymbol=='s')
		{
			Images.computerHand=Images.scissorsImage;
			
			if(uSymbol=='r')
			{
				return 'w';
			}
			
			if(uSymbol=='p')
			{
				return 'l';
			}
		}	
		
		return 'd';
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
		
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT||e.getKeyCode()== KeyEvent.VK_D) 
		{
			right=true;			
		}
	
		if(e.getKeyCode()== KeyEvent.VK_LEFT||e.getKeyCode()== KeyEvent.VK_A) 
		{
			left=true;			
		}
		if(e.getKeyCode()== KeyEvent.VK_SPACE) 
		{
			spacebar =true;
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			quitgame=true;		
		}	
		
		if(e.getKeyCode()== KeyEvent.VK_R) 
		{
			restart =true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT||e.getKeyCode()== KeyEvent.VK_D) 
		{
			right=false;			
			noRepeat=true;			
		}
	
		if(e.getKeyCode()== KeyEvent.VK_LEFT||e.getKeyCode()== KeyEvent.VK_A) 
		{
			left=false;			
			noRepeat=true;
		}
		if(e.getKeyCode()== KeyEvent.VK_SPACE) 
		{
			spacebar=false;			
			noRepeat=true;
		}
		
		if(e.getKeyCode()== KeyEvent.VK_R) 
		{
			restart=false;			
			noRepeat=true;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		timer.start();
		
		if(quitgame)
		{
			System.exit(0);
		}
		
		if(gameOver)
		{
			if(restart)
			{
			Images.computerHand= Images.xImage;
			gameOver = false;
			noRepeat = false;
			decision = 'f';
			}
	
		}	
		else {
	
			if(right&&noRepeat)
			{
				//adjusting images according to the input. moving right in this case
				int temp = i.scissorsX ;
				i.scissorsX = i.paperX ;
				i.paperX = i.rockX;
				i.rockX = temp ;
			
				//to remember which symbol user selects when pressing spacebar. 0 is rock, 1 is paper, 2 is scissors 
				userSelection = (userSelection+1)%3;
			
				noRepeat=false;
		
			}
			if(left&&noRepeat)
			{
				//reverse of the function above. moving left.
				int temp = i.rockX ;
				i.rockX = i.paperX ;
				i.paperX = i.scissorsX;
				i.scissorsX = temp ;
				//thought about using Math.abs() to only use positive values but couldn't find a way. 
				userSelection = (userSelection-1)%3;
						
				noRepeat=false;
			}
		
			if(spacebar&&noRepeat)
			{
				//computer selects immediately after user presses spacebar
				Random rngValue = new Random();
				computer.Selection(rngValue.nextInt(3)); 
			
				//user's selection is compared and winner is decided
				user.Selection(userSelection);
				char uSymbol = user.selectedSymbol;
				char cSymbol = computer.selectedSymbol;
				
				decision = decideWinner(uSymbol,cSymbol);
				
				//based on the winner, roundEndString is adjusted and score is given; draw gives no points 
				adjustScoreandText(decision);
				
				
				gameOver=true;
				noRepeat=false;
			}
		}
		i.moveBackground();
		
		repaint();
		
	}
}
