import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

	public static void main(String[] args) {
		JFrame m = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth()/2;
		int height = (int)screenSize.getHeight()/2;
		Game newGame = new Game(width,height);
		m.setBounds(0, 0, width, height);
		m.setTitle("Rock Paper Scissors");
		m.setVisible(true);
		m.setResizable(false);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.add(newGame);
	}

}
