package superguild;

import javax.swing.JFrame;
import java.awt.Dimension;

public class MainFrame extends JFrame {
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500, 400));
		setTitle("Superguild");
		setName("MainJFrame");
		setMinimumSize(new Dimension(500, 400));
	}

	private static final long serialVersionUID = 1L;

}
