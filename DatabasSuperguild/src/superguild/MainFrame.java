package superguild;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	public MainFrame(){
		getContentPane().setMaximumSize(new Dimension(100, 2147483647));
		getContentPane().setMinimumSize(new Dimension(100, 0));
		getContentPane().setSize(new Dimension(100, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setTitle("Superguild");
		setName("MainJFrame");
		setMinimumSize(new Dimension(500, 400));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMain = new JMenu("Main");
		menuBar.add(mnMain);
		
		JMenuItem mntmConnect = new JMenuItem("Connect");
		mnMain.add(mntmConnect);
		mntmConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectFrame connect = new ConnectFrame();
				//connect.setVisible(true);
			}
		});
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnMain.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnOptions.add(mntmPreferences);
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsFrame optionsWindow = new OptionsFrame();
				optionsWindow.setVisible(true);
			}
		});
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnOptions.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsFrame optionsWindow = new OptionsFrame();
				optionsWindow.setVisible(true);
			}
		});
		
		
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Das Super-Knapfe");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton2 = new JButton("Das Super-Knapfe2");
		GridBagConstraints gbc_btnNewButton2 = new GridBagConstraints();
		gbc_btnNewButton2.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton2.gridx = 0;
		gbc_btnNewButton2.gridy = 1;
		getContentPane().add(btnNewButton2, gbc_btnNewButton2);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 3;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 0;
		getContentPane().add(table, gbc_table);
		
		
		
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	private JTable table;

}
