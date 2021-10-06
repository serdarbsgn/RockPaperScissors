import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {

	//values to calculate places of images in display
	protected int rockX, paperX, scissorsX, squareX, computerRowY, playerRowY, backgroundY, backgroundY2,resolutionY;
	
	//images
	protected static BufferedImage rockImage;
	protected static BufferedImage paperImage;
	protected static BufferedImage scissorsImage;
	protected static BufferedImage squareImage;
	protected static BufferedImage backgroundImage;
	protected static BufferedImage xImage;
	
	
	//assigned xImage at first, changes to what computer selected after user confirms their selection. 
	protected static BufferedImage computerHand;
	
	//uses resolution values of screen from main function and adjusts values accordingly  
	public Images (int x, int y) {
		
		rockX=(int)(x/7.68);
		paperX=(int) (x/2.56);
		squareX=(int) (x/2.56);
		scissorsX=(int) (x/1.536);	
		
		computerRowY=(int)(y/2);
		playerRowY=(int)(y/10.8);		
		
		backgroundY=0;
		backgroundY2=y;
		resolutionY=y;
		
		try {                
	        backgroundImage = ImageIO.read(new File("images\\backgroundImage.jpg"));
	     } catch (IOException ex) { }
		
		try {                
	        rockImage = ImageIO.read(new File("images\\rockImage.png"));
	     } catch (IOException ex) { }
		
		try {                
	        paperImage = ImageIO.read(new File("images\\paperImage.png"));
	     } catch (IOException ex) { }
		
		try {                
	        scissorsImage = ImageIO.read(new File("images\\scissorsImage.png"));
	     } catch (IOException ex) { }
		
		try {                
	        xImage = ImageIO.read(new File("images\\x.png"));
	     } catch (IOException ex) { }
		try {                
	        squareImage = ImageIO.read(new File("images\\square.png"));
	     } catch (IOException ex) { }
		
		computerHand = xImage; 
	}
	
	//one seamless background image is used twice to create an illusion of constantly moving background
	public void moveBackground()
	{
		backgroundY--;
		backgroundY2--;
		if(backgroundY<=-resolutionY)
		{
			backgroundY=resolutionY;
		}
		if(backgroundY2<=-resolutionY)
		{
			backgroundY2=resolutionY;
		}
		
	}

}
