package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class DBToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DBToolDialog(JFrame parent) {
		super(parent);

		DBToolDialog classContext = this;
		
		setTitle("Verwaltung der Testdatenbank");
		setBackground(Color.PINK);
		setModal(true);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Config.getGuiColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.ORANGE, 3));
		panel_1.setBackground(Config.getGuiColor());
		panel_1.setBounds(0, 0, 147, 226);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datenbankstatus:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 106, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblHost = new JLabel("Host: 127.0.0.1");
		lblHost.setBounds(10, 36, 106, 14);
		panel_1.add(lblHost);
		
		JLabel lblUser = new JLabel("User: testuser");
		lblUser.setBounds(10, 61, 84, 14);
		panel_1.add(lblUser);
		
		JLabel lblDatabase = new JLabel("Database: em2016");
		lblDatabase.setBounds(10, 86, 106, 14);
		panel_1.add(lblDatabase);
		
		JLabel lblTabellenExistieren = new JLabel("Tabellen existieren: ja");
		lblTabellenExistieren.setBounds(10, 111, 127, 14);
		panel_1.add(lblTabellenExistieren);
		
		JPanel panel = new JPanel();
		panel.setBounds(147, 0, 284, 226);
		panel.setBackground(Config.getGuiColor());
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnDeleteTables = new JButton("Lösche Tabellen");
		btnDeleteTables.setBounds(10, 11, 264, 27);
		panel.add(btnDeleteTables);
		btnDeleteTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Log.info("Menue item 'Lösche Tabellen' clicked.");
				if(DatabaseManagement.dropTables(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext,"Tabellen wurden gelöscht.", "Tabellen löschen", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(classContext,"Tabellen konnten nicht gelöscht werden.", "Tabellen löschen", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		btnDeleteTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnDeleteTables.setBackground(Config.getGuiColor());
	
		JButton btnCreateTables = new JButton("Erstelle Tabellen");
		btnCreateTables.setBounds(10, 41, 264, 27);
		panel.add(btnCreateTables);
		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Log.info("Menue item 'Erstelle Tabellen' clicked.");
				if(!DatabaseManagement.createTables(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext, "Tabellen konnten nicht erstellt werden.", "Tabellen erstellen", JOptionPane.WARNING_MESSAGE);
				}
				else {

					JOptionPane.showMessageDialog(classContext, "Tabellen konnten erstellt werden.", "Tabellen erstellen", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnCreateTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnCreateTables.setBackground(Config.getGuiColor());
	

		JButton button = new JButton("Testdaten Einfügen");
		button.setBounds(10, 73, 264, 27);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Log.info("Menue item 'Testdaten Einfügen' clicked.");
				if(DatabaseManagement.addTestData(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext, "Testdaten wurden in Datenbank geschrieben.", "Testdaten importieren", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(classContext, "Testdaten konnten nicht importiert werden.", "Testdaten importieren", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		button.setBackground(Color.WHITE);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Config.getGuiColor());
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Schlie\u00DFen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DBToolDialog.this.dispose();
					}
				});
				cancelButton.setBackground(Config.getGuiColor());
				cancelButton.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
