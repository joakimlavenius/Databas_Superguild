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
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
				connect.setVisible(true);
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

		JButton listMembersButton = new JButton("List All Members");
		GridBagConstraints gbc_listMembersButton = new GridBagConstraints();
		gbc_listMembersButton.fill = GridBagConstraints.BOTH;
		gbc_listMembersButton.insets = new Insets(0, 0, 0, 0);
		gbc_listMembersButton.gridx = 0;
		gbc_listMembersButton.gridy = 0;
		getContentPane().add(listMembersButton, gbc_listMembersButton);
		listMembersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Add doing a query here
				ResultSet debugresults = debugQuery();
				//Populates table, send resultset
				try {
					lister(debugresults);
				} catch (SQLException e1) {
					System.out.println("Error populating table!" + e1.getMessage());
					e1.printStackTrace();
				}

			}
		});

		JButton selectAMemberButton = new JButton("Select A Member");
		GridBagConstraints gbc_selectAMemberButton = new GridBagConstraints();
		gbc_selectAMemberButton.fill = GridBagConstraints.BOTH;
		gbc_selectAMemberButton.insets = new Insets(0, 0, 0, 0);
		gbc_selectAMemberButton.gridx = 0;
		gbc_selectAMemberButton.gridy = 1;
		getContentPane().add(selectAMemberButton, gbc_selectAMemberButton);
		selectAMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Gör query

			}
		});

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

	//This populates the JTable
	public void lister (ResultSet result) throws SQLException{
		//DefaultTableModel

		DefaultTableModel model = null;

		ResultSetMetaData meta = result.getMetaData();
		if (model==null){
			model= new DefaultTableModel();
		}
		String columns[] = new String[meta.getColumnCount()];

		for(int i=0;i<columns.length;++i)
		{
			columns[i]= meta.getColumnLabel(i+1);
		}

		model.setColumnIdentifiers(columns);

		while(result.next())
		{
			Object data[]= new Object[columns.length];
			for(int i=0;i<data.length;++i)
			{
				data[i]=result.getObject(i+1);
			}
			model.addRow(data);
		}
		table.setModel(model);
		//				    used to: return model;
	}

	//DEBUG QUERYER
	public ResultSet debugQuery(){
		
		//Gör statement som skickar queerberries
		Statement queryCaller = null;
		try {
			queryCaller = ConnectFrame.con.createStatement(ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_SCROLL_SENSITIVE);
		} catch (SQLException e1) {
			System.out.println("Statement creation fail");
			e1.printStackTrace();
		}
		//Måste skapa ResultSet objekt
		ResultSet results = null;
		
			try {
				results = queryCaller.executeQuery("SELECT * FROM Member");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return results;
	}

}


