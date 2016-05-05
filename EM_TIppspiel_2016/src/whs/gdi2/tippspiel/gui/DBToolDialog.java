package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class DBToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DBToolDialog(JFrame parent) {
		super(parent);
		setBackground(Color.PINK);
		setModal(true);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Config.getGuiColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnCreateTables = new JButton("Erstelle Tabellen");
			btnCreateTables.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					Log.info("Menue item 'Erstelle Tabellen' clicked.");
					DatabaseManagement.createTables(Main.mainConnection);
				}
			});
			btnCreateTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
			btnCreateTables.setBackground(Config.getGuiColor());
			btnCreateTables.setBounds(5, 5, 422, 25);
			contentPanel.add(btnCreateTables);
		}

		JButton button = new JButton("Testdaten Einfügen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Log.info("Menue item 'Testdaten Einfügen' clicked.");
				DatabaseManagement.addTestData(Main.mainConnection);
			}
		});
		button.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		button.setBackground(Color.WHITE);
		button.setBounds(5, 37, 422, 25);
		contentPanel.add(button);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Config.getGuiColor());
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DBToolDialog.this.dispose();
					}
				});
				okButton.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
				okButton.setBackground(Config.getGuiColor());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
