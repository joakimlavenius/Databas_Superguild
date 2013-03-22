package superguild;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;


public class AddProfessionFrame extends JDialog{
	private JTextField level, notes;
	private ArrayList<String> chars = null;
	private JComboBox<String> existingCharacters, profession;
	
	@SuppressWarnings("unchecked")
	public AddProfessionFrame() {
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCharacter = new JLabel("Character");
		GridBagConstraints gbc_lblCharacter = new GridBagConstraints();
		gbc_lblCharacter.insets = new Insets(0, 0, 5, 5);
		gbc_lblCharacter.anchor = GridBagConstraints.EAST;
		gbc_lblCharacter.gridx = 0;
		gbc_lblCharacter.gridy = 0;
		panel.add(lblCharacter, gbc_lblCharacter);

//		Get Characters
		try {
			chars = new ArrayList<>();
			ResultSet resSet = MainFrame.getSQLHandler().selectQuery("SELECT Name FROM characters");
			ResultSetMetaData resSetMeta = resSet.getMetaData();
			int noc = resSetMeta.getColumnCount();
			resSet.first();
			while(resSet.next()){
				chars.add(resSet.getString(noc));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		existingCharacters = new JComboBox(chars.toArray());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(existingCharacters, gbc_comboBox);
		
		
		JLabel lblProfession = new JLabel("Profession");
		GridBagConstraints gbc_lblProfession = new GridBagConstraints();
		gbc_lblProfession.anchor = GridBagConstraints.EAST;
		gbc_lblProfession.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfession.gridx = 0;
		gbc_lblProfession.gridy = 1;
		panel.add(lblProfession, gbc_lblProfession);
		
		final String[] professions = {"Blacksmithing", "Enchanting", "Tailoring", "Inscription", "Letherworking", "Jewelcrafting", "Mining",
				"Skinning", "Herbalism", "Alchemy"};
		profession = new JComboBox(professions);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		panel.add(profession, gbc_comboBox_1);
		
		JLabel lblLevel = new JLabel("Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 2;
		panel.add(lblLevel, gbc_lblLevel);
		
		level = new JTextField("1");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(level, gbc_textField);
		level.setColumns(10);
		
		JLabel lblNotes = new JLabel("Notes");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 0;
		gbc_lblNotes.gridy = 3;
		panel.add(lblNotes, gbc_lblNotes);
		
		notes = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		panel.add(notes, gbc_textField_1);
		notes.setColumns(10);
		
		JButton btnAddProfession = new JButton("Add Profession");
		GridBagConstraints gbc_btnAddProfession = new GridBagConstraints();
		gbc_btnAddProfession.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddProfession.gridwidth = 2;
		gbc_btnAddProfession.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddProfession.gridx = 0;
		gbc_btnAddProfession.gridy = 4;
		panel.add(btnAddProfession, gbc_btnAddProfession);
		btnAddProfession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(chars.get(existingCharacters.getSelectedIndex()));
				MainFrame.getSQLHandler().updateQuery("INSERT INTO professions VALUES(?, ?, ?, ?)",
						new String[] {chars.get(existingCharacters.getSelectedIndex()), professions[profession.getSelectedIndex()],
						level.getText(), notes.getText()});
				setVisible(false);
			}
		});
	}

	
	
}
