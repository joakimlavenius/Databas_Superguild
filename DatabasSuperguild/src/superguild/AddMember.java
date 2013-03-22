package superguild;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class AddMember{
	private JTextField name, level, rankNumber, notes;
	private JDialog addMember, createCharacter;
	private int memID = 0;
	private JCheckBox guildtaxPaid, isOfficer;
	private JRadioButton rdbtnMain, rdbtnAlt, rdbtnBank;
	private JComboBox<String> rank, race, classes;


	public void addMember() {
		addMember = new JDialog();
		addMember.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		try {
			ResultSet res = MainFrame.getSQLHandler().selectQuery("SELECT memberId FROM member");
			ResultSetMetaData resInf = res.getMetaData();
			res.last();
			int noc = resInf.getColumnCount();
			for(int i = 1; i <= noc; i++){
				memID = Integer.parseInt(res.getString(i)) + 1;
			}
		} catch (SQLException e1) {
			System.err.println("Failed to get member id: " + e1.getMessage());
			memID = 1;
		}

		JPanel panel = new JPanel();
		addMember.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);



		JLabel lblMemberId = new JLabel("Member ID " + memID);
		GridBagConstraints gbc_lblMemberId = new GridBagConstraints();
		gbc_lblMemberId.gridwidth = 3;
		gbc_lblMemberId.insets = new Insets(0, 0, 5, 0);
		gbc_lblMemberId.gridx = 0;
		gbc_lblMemberId.gridy = 0;
		panel.add(lblMemberId, gbc_lblMemberId);

		JLabel lblRank = new JLabel("Rank");
		GridBagConstraints gbc_lblRank = new GridBagConstraints();
		gbc_lblRank.anchor = GridBagConstraints.EAST;
		gbc_lblRank.insets = new Insets(0, 0, 5, 5);
		gbc_lblRank.gridx = 0;
		gbc_lblRank.gridy = 1;
		panel.add(lblRank, gbc_lblRank);
		
		final String[] ranks = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
		rank = new JComboBox<String>(ranks);
		GridBagConstraints gbc_rank = new GridBagConstraints();
		gbc_rank.insets = new Insets(0, 0, 5, 5);
		gbc_rank.fill = GridBagConstraints.HORIZONTAL;
		gbc_rank.gridx = 1;
		gbc_rank.gridy = 1;
		rank.setBackground(Color.white);
		panel.add(rank, gbc_rank);

		isOfficer = new JCheckBox("Is Officer");
		GridBagConstraints gbc_isOfficer = new GridBagConstraints();
		gbc_isOfficer.anchor = GridBagConstraints.WEST;
		gbc_isOfficer.insets = new Insets(0, 0, 5, 0);
		gbc_isOfficer.gridx = 2;
		gbc_isOfficer.gridy = 1;
		panel.add(isOfficer, gbc_isOfficer);

		JLabel lblNotes = new JLabel("Notes");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 0;
		gbc_lblNotes.gridy = 2;
		panel.add(lblNotes, gbc_lblNotes);

		notes = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		panel.add(notes, gbc_textField_3);
		notes.setColumns(10);

		guildtaxPaid = new JCheckBox("Guildtax Paid");
		GridBagConstraints gbc_guildtaxPaid = new GridBagConstraints();
		gbc_guildtaxPaid.anchor = GridBagConstraints.WEST;
		gbc_guildtaxPaid.insets = new Insets(0, 0, 5, 0);
		gbc_guildtaxPaid.gridx = 2;
		gbc_guildtaxPaid.gridy = 2;
		panel.add(guildtaxPaid, gbc_guildtaxPaid);


		JButton createMemberButton = new JButton("Create Member");
		createMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int officer = 0;
				int guildTax = 0;
				
				if(isOfficer.isSelected()){
					officer = 1;
				}else{
					officer = 0;
				}
				if(guildtaxPaid.isSelected()){
					guildTax = 1;
				}else{
					guildTax = 0;
				}
				addMember.setVisible(false);
				int check = JOptionPane.showConfirmDialog(addMember, "Do you want to create a character for this member?",
						"Add Character?", JOptionPane.YES_NO_OPTION);
				switch (check){
				case JOptionPane.YES_OPTION:
					MainFrame.getSQLHandler().updateQuery("INSERT INTO member VALUES(?, ?, ?, ?, ?)",
						new String[] {""+memID, notes.getText(), ""+officer, ranks[rank.getSelectedIndex()], ""+guildTax});
					createCharacter();
					break;
				case JOptionPane.NO_OPTION:
					MainFrame.getSQLHandler().updateQuery("INSERT INTO member VALUES(?, ?, ?, ?, ?)",
						new String[] {""+memID, notes.getText(), ""+officer, ranks[rank.getSelectedIndex()], ""+guildTax});
					break;
				default:
					break;
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel.add(createMemberButton, gbc_btnNewButton);

		addMember.setVisible(true);
		addMember.pack();
	}

	public void createCharacter(){
		createCharacter = new JDialog();
		createCharacter.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		createCharacter.setTitle("");
		ButtonGroup mainAltBank = new ButtonGroup();

		JPanel createCharacterPanel = new JPanel();
		createCharacter.getContentPane().add(createCharacterPanel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		createCharacterPanel.setLayout(gbl_panel);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		createCharacterPanel.add(lblName, gbc_lblName);

		name = new JTextField();
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 5, 5);
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 1;
		gbc_name.gridy = 0;
		createCharacterPanel.add(name, gbc_name);
		name.setColumns(10);

		JLabel lblLevel = new JLabel("Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 1;
		createCharacterPanel.add(lblLevel, gbc_lblLevel);

		level = new JTextField();
		GridBagConstraints gbc_level = new GridBagConstraints();
		gbc_level.insets = new Insets(0, 0, 5, 5);
		gbc_level.fill = GridBagConstraints.HORIZONTAL;
		gbc_level.gridx = 1;
		gbc_level.gridy = 1;
		createCharacterPanel.add(level, gbc_level);
		level.setColumns(10);

		rdbtnMain = new JRadioButton("Main");
		GridBagConstraints gbc_rdbtnMain = new GridBagConstraints();
		gbc_rdbtnMain.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMain.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMain.gridx = 2;
		gbc_rdbtnMain.gridy = 0;
		createCharacterPanel.add(rdbtnMain, gbc_rdbtnMain);
		mainAltBank.add(rdbtnMain);

		JLabel lblRace = new JLabel("Race");
		GridBagConstraints gbc_lblRace = new GridBagConstraints();
		gbc_lblRace.anchor = GridBagConstraints.EAST;
		gbc_lblRace.insets = new Insets(0, 0, 5, 5);
		gbc_lblRace.gridx = 0;
		gbc_lblRace.gridy = 2;
		createCharacterPanel.add(lblRace, gbc_lblRace);

		final String[] races = {"Human", "Night Elf", "Worgen", "Gnome", "Dwarf", "Draenai", "Orc", "Troll",
				"Tauren", "Undead", "Goblin", "Blood Elf", "Pandaren"};
		race = new JComboBox<String>(races);
		GridBagConstraints gbc_race = new GridBagConstraints();
		gbc_race.insets = new Insets(0, 0, 5, 5);
		gbc_race.fill = GridBagConstraints.HORIZONTAL;
		gbc_race.gridx = 1;
		gbc_race.gridy = 2;
		race.setBackground(Color.white);
		createCharacterPanel.add(race, gbc_race);

		rdbtnAlt = new JRadioButton("Alt");
		GridBagConstraints gbc_rdbtnAlt = new GridBagConstraints();
		gbc_rdbtnAlt.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAlt.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAlt.gridx = 2;
		gbc_rdbtnAlt.gridy = 1;
		createCharacterPanel.add(rdbtnAlt, gbc_rdbtnAlt);
		mainAltBank.add(rdbtnAlt);

		JLabel lblClass = new JLabel("Class");
		GridBagConstraints gbc_lblClass = new GridBagConstraints();
		gbc_lblClass.anchor = GridBagConstraints.EAST;
		gbc_lblClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblClass.gridx = 0;
		gbc_lblClass.gridy = 3;
		createCharacterPanel.add(lblClass, gbc_lblClass);


		final String[] typeOfClass = {"Druid", "Death Knight", "Monk", "Shaman", "Rogue", "Mage", "Paladin",
				"Priest", "Warlock", "Warrior", "Hunter"};
		classes  = new JComboBox<String>(typeOfClass);
		GridBagConstraints gbc_classes = new GridBagConstraints();
		gbc_classes.insets = new Insets(0, 0, 5, 5);
		gbc_classes.fill = GridBagConstraints.HORIZONTAL;
		gbc_classes.gridx = 1;
		gbc_classes.gridy = 3;
		classes.setBackground(Color.white);
		createCharacterPanel.add(classes, gbc_classes);

		rdbtnBank = new JRadioButton("Bank");
		GridBagConstraints gbc_rdbtnBank = new GridBagConstraints();
		gbc_rdbtnBank.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBank.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBank.gridx = 2;
		gbc_rdbtnBank.gridy = 2;
		createCharacterPanel.add(rdbtnBank, gbc_rdbtnBank);
		mainAltBank.add(rdbtnBank);
		
		JButton createCharButton = new JButton("Create Character");
		createCharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mainAltOrBank = "";
				if(rdbtnMain.isSelected()){
					mainAltOrBank = "Main";
				}else if(rdbtnAlt.isSelected()){
					mainAltOrBank = "Alt";
				}else if(rdbtnBank.isSelected()){
					mainAltOrBank = "Bank";
				}
				MainFrame.getSQLHandler().updateQuery("INSERT INTO characters VALUES(?, ?, ?, ?, ?, ?)",
				new String[] {name.getText(), level.getText(), mainAltOrBank, races[race.getSelectedIndex()], ""+memID, typeOfClass[classes.getSelectedIndex()]});
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 6;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		createCharacterPanel.add(createCharButton, gbc_btnNewButton);

		createCharacter.setVisible(true);
		createCharacter.revalidate();
		createCharacter.pack();
	}

}