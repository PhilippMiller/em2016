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
import javax.swing.UIManager;

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
		panel_1.setBounds(5, 5, 168, 215);
		panel_1.setBorder(new LineBorder(UIManager.getColor("ComboBox.disabledForeground"), 2));
		panel_1.setBackground(UIManager.getColor("Button.light"));
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datenbankstatus:");
		lblNewLabel.setBounds(7, 7, 101, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel);
		
		JLabel lblHost = new JLabel("Host: 127.0.0.1");
		lblHost.setBounds(7, 32, 151, 14);
		panel_1.add(lblHost);
		
		JLabel lblUser = new JLabel("User: testuser");
		lblUser.setBounds(7, 57, 151, 14);
		panel_1.add(lblUser);
		
		JLabel lblDatabase = new JLabel("Database: em2016");
		lblDatabase.setBounds(7, 82, 151, 14);
		panel_1.add(lblDatabase);
		
		JLabel lblTabellenExistieren = new JLabel("Tabellen existieren: ja");
		lblTabellenExistieren.setBounds(7, 107, 151, 14);
		panel_1.add(lblTabellenExistieren);
		
		JPanel panel = new JPanel();
		panel.setBounds(173, 0, 256, 220);
		panel.setBackground(Config.getGuiColor());
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnDeleteTables = new JButton("Lösche Tabellen");
		btnDeleteTables.setBounds(10, 87, 236, 27);
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
			btnCreateTables.setBounds(10, 125, 236, 27);
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
				button.setBounds(10, 163, 236, 27);
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
				
				JButton btnDatenbankNeuAnlegen = new JButton("Datenbank neu anlegen");
				btnDatenbankNeuAnlegen.setBounds(10, 49, 236, 27);
				btnDatenbankNeuAnlegen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnDatenbankNeuAnlegen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
				btnDatenbankNeuAnlegen.setBackground(Color.WHITE);
				panel.add(btnDatenbankNeuAnlegen);
				
				JButton btnDatenbankLschen = new JButton("Datenbank l\u00F6schen");
				btnDatenbankLschen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnDatenbankLschen.setBounds(10, 11, 236, 27);
				btnDatenbankLschen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
				btnDatenbankLschen.setBackground(Color.WHITE);
				panel.add(btnDatenbankLschen);
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
