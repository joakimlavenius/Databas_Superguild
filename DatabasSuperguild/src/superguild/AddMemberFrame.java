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

public class AddMemberFrame extends JDialog{
	private JTextField notes;
	private JDialog addMember;
	private int memID = 0;
	private JCheckBox guildtaxPaid, isOfficer;
	private JComboBox<String> rank;


	public void addMember() {
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
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
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
				setVisible(false);
				int check = JOptionPane.showConfirmDialog(addMember, "Do you want to create a character for this member?",
						"Add Character?", JOptionPane.YES_NO_OPTION);
				switch (check){
				case JOptionPane.YES_OPTION:
					MainFrame.getSQLHandler().updateQuery("INSERT INTO member VALUES(?, ?, ?, ?, ?)",
						new String[] {""+memID, notes.getText(), ""+officer, ranks[rank.getSelectedIndex()], ""+guildTax}); 
					AddCharacterFrame character = new AddCharacterFrame(memID);
					character.setVisible(true);
					character.createCharacter();
					
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

		setVisible(true);
		pack();
	}

	
}