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
import whs.gdi2.tippspiel.database.MySQLConnection;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

/**
 * Database management tool for the instance of testdatabase.
 * 
 * This tool can recreate the the database and fill it with testdata.
 * It can also show some informations over the testdatabase.
 * 
 * It is only usable with the testdatabase because testdatabase instances has always more rights
 * than instances which holds livedatabases.
 * 
 * @since 1.0
 * @version 1.2
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 *
 */
public class DBToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblHost;
	private JLabel lblUser;
	private JLabel lblDatabase;
	private JLabel lblDBExistiert;
	private JPanel panel;
	private JButton btnDeleteTables;
	private JButton btnCreateTables;
	private JButton btnDatenbankNeuAnlegen;
	private JButton button;
	private JPanel buttonPane;
	private JButton cancelButton;
	private DBToolDialog classContext;
	private JLabel lblHostPlaceholder;
	private JLabel lblUserPlaceholder;
	private JLabel lblDbPlaceholder;
	private JLabel lblTableplaceholder;
	private JLabel lblTabellenExistieren;
	private JLabel lblDatenbankExistiert;
	
	/**
	 * Create the dialog.
	 */
	public DBToolDialog(JFrame parent) {
		super(parent);

		classContext = this;
		
		setTitle("Tippspiel Admin - Tool | Verwaltung der Testdatenbank");
		setBackground(Config.getGuiColor());
		setModal(true);

		setBounds(100, 100, 450, 300);

		InitializeGui();
		InitializeEvents();
		
		reload();
		
	}

	public void InitializeGui() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Config.getGuiColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 168, 215);
		panel_1.setBorder(new LineBorder(UIManager.getColor("ComboBox.disabledForeground"), 2));
		panel_1.setBackground(UIManager.getColor("Button.light"));
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("Datenbankstatus");
		lblNewLabel.setBounds(7, 7, 101, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel);
		
		lblHost = new JLabel("Host:");
		lblHost.setBounds(7, 32, 36, 14);
		panel_1.add(lblHost);
		
		lblHostPlaceholder = new JLabel("host placeholder");
		lblHostPlaceholder.setBounds(46, 32, 151, 14);
		panel_1.add(lblHostPlaceholder);
		
		lblUser = new JLabel("User:");
		lblUser.setBounds(7, 57, 36, 14);
		panel_1.add(lblUser);
		
		lblUserPlaceholder = new JLabel("user placeholder");
		lblUserPlaceholder.setBounds(46, 57, 123, 14);
		panel_1.add(lblUserPlaceholder);
		
		lblDatabase = new JLabel("Datenbank:");
		lblDatabase.setBounds(7, 82, 86, 14);
		panel_1.add(lblDatabase);
		
		lblDbPlaceholder = new JLabel("db placeholder");
		lblDbPlaceholder.setBounds(82, 82, 76, 14);
		panel_1.add(lblDbPlaceholder);
		
		lblDBExistiert = new JLabel("Datenbank existiert: ");
		lblDBExistiert.setBounds(7, 107, 123, 14);
		panel_1.add(lblDBExistiert);
		
		lblDatenbankExistiert = new JLabel("ja");
		lblDatenbankExistiert.setBounds(131, 107, 48, 14);
		panel_1.add(lblDatenbankExistiert);
		
		lblTabellenExistieren = new JLabel("Tabellen existieren:");
		lblTabellenExistieren.setBounds(7, 132, 123, 14);
		panel_1.add(lblTabellenExistieren);
		
		lblTableplaceholder = new JLabel("tableplaceholder");
		lblTableplaceholder.setBounds(131, 132, 101, 14);
		panel_1.add(lblTableplaceholder);
		
		panel = new JPanel();
		panel.setBounds(173, 0, 256, 220);
		panel.setBackground(Config.getGuiColor());
		contentPanel.add(panel);
		panel.setLayout(null);
		
		btnDeleteTables = new JButton("L�sche Tabellen");
		btnDeleteTables.setBounds(10, 87, 236, 27);
		panel.add(btnDeleteTables);
		
		btnDeleteTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnDeleteTables.setBackground(Config.getGuiColor());
	
		btnCreateTables = new JButton("Erstelle Tabellen");
		btnCreateTables.setBounds(10, 125, 236, 27);
		panel.add(btnCreateTables);
		btnCreateTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnCreateTables.setBackground(Config.getGuiColor());
	

		button = new JButton("Testdaten Einf�gen");
		button.setBounds(10, 163, 236, 27);
		panel.add(button);

		button.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		button.setBackground(Color.WHITE);
		
		btnDatenbankNeuAnlegen = new JButton("Datenbank neu anlegen");
		btnDatenbankNeuAnlegen.setBounds(10, 49, 236, 27);

		btnDatenbankNeuAnlegen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnDatenbankNeuAnlegen.setBackground(Color.WHITE);
		panel.add(btnDatenbankNeuAnlegen);
		
		buttonPane = new JPanel();
		buttonPane.setBackground(Config.getGuiColor());
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		cancelButton = new JButton("Schlie\u00DFen");

		cancelButton.setBackground(Config.getGuiColor());
		cancelButton.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void InitializeEvents() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Log.info("Menue item 'Testdaten Einf�gen' clicked.");
				
				if(DatabaseManagement.addTestData(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext, "Testdaten wurden in Datenbank geschrieben.", "Testdaten importieren", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(classContext, "Testdaten konnten nicht importiert werden.", "Testdaten importieren", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnDatenbankNeuAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManagement.createDB(Main.mainConnection);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBToolDialog.this.dispose();
			}
		});
		
		btnDeleteTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Log.info("Menue item 'L�sche Tabellen' clicked.");
				if(DatabaseManagement.dropTables(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext,"Tabellen wurden gel�scht.", "Tabellen l�schen", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(classContext,"Tabellen konnten nicht gel�scht werden.", "Tabellen l�schen", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		
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
	}
	
	public void reload() {
		lblHostPlaceholder.setText(Config.getDBIp_offline());
		lblUserPlaceholder.setText(Config.getDBUser_offline());
		lblDbPlaceholder.setText(Config.getDB_offline());
		
		
		lblTableplaceholder.setText("nein");
		lblDatenbankExistiert.setText("nein");
		
		if(MySQLConnection.testConnection(Config.getDBIp_offline(), Config.getDBUser_offline(), Config.getDBPass_offline(), "")) {
			if(MySQLConnection.testConnection(Config.getDBIp_offline(), Config.getDBUser_offline(), Config.getDBPass_offline(), Config.getDB_offline())) {
				lblDatenbankExistiert.setText("ja");
				try {
					Statement stmt = Main.mainConnection.getConnection().createStatement();
					stmt.executeQuery("SELECT * FROM spiele LIMIT 1");
					lblDatenbankExistiert.setText("ja");
					
				}
				catch(Exception e) {
					// tables doesnt exists;
				}
			}
		}
	}
}
