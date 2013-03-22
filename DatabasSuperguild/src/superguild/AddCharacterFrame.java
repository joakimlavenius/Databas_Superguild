package superguild;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCharacterFrame extends JDialog{
	private JTextField name, level;
	private JRadioButton rdbtnMain, rdbtnAlt, rdbtnBank;
	private JComboBox<String> race, classes;
	private int memID;
	
	public AddCharacterFrame(int memID){
		this.memID = memID;
	}
	
	public AddCharacterFrame(){
		
	}
	
	public void createCharacter(){
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		setTitle("");
		ButtonGroup mainAltBank = new ButtonGroup();

		JPanel createCharacterPanel = new JPanel();
		getContentPane().add(createCharacterPanel);
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
				setVisible(false);
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 6;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		createCharacterPanel.add(createCharButton, gbc_btnNewButton);

//		setVisible(true);
		revalidate();
		pack();
	}


}
