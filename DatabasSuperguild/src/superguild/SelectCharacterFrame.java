package superguild;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import javax.swing.JButton;

public class SelectCharacterFrame extends JFrame{
	
	private JComboBox charBox;
	private int i;
	
	public SelectCharacterFrame() {
		setPreferredSize(new Dimension(300, 200));
		setSize(new Dimension(300, 200));
		setResizable(false);
		setTitle("Choose a member");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0,0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		charBox = new JComboBox();
		charBox.setToolTipText("Select a character:");
		GridBagConstraints gbc_charBox = new GridBagConstraints();
		gbc_charBox.insets = new Insets(0, 0, 5, 5);
		gbc_charBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_charBox.gridx = 1;
		gbc_charBox.gridy = 1;
		getContentPane().add(charBox, gbc_charBox);
		
		JButton btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 2;
		getContentPane().add(btnOk, gbc_btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
					
			}
		});
		
		
		
		charBox.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void focusGained(FocusEvent arg0) {
				
				ResultSet charResults = MainFrame.sqlHandler.selectQuery("SELECT memberId, notes FROM member");
				
				try {
					charBox.removeAllItems();
					int j = 1;

					while(charResults.next())
					{
						charBox.addItem(charResults.getInt(1) + " - " + charResults.getString(2));
					}
					
				} catch (SQLException | NullPointerException e) {
					System.out.println("Failed adding entries");
					e.printStackTrace();
					setVisible(false);
				}

					}
				
			});
		//Updates fields with data from the bookmark object.
		charBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (charBox.getSelectedIndex() != -1){
					int sel = charBox.getSelectedIndex();
					
				}	
			}
		});
		
		
	
	
}
}
