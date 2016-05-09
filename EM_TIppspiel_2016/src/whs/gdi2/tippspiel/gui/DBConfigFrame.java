package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.MySQLConnection;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;
import java.util.HashMap;


/**
 * Einstellungen für die D.atenbank
 * 
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 */
public class DBConfigFrame extends JDialog {

	private JPanel contentPane;
	private JTextField DBIP_offline_txtfield;
	private JTextField DB_offline_txtfield;
	private JTextField DBUser_offline_txtfield;
	private JTextField DBPass_offline_txtfield;
	private JTextField DBIP_online_txtfield;
	private JTextField DB_online_txtfield;
	private JTextField DBUser_online_txtfield;
	private JTextField DBPass_online_txtfield;
	private JTabbedPane tabbedPane;
	private JPanel offlineTab;
	private JLabel dBIP_offline;
	private JLabel dB_offline;
	private JLabel dBUser_offline;
	private JLabel dBPass_offline;
	private JButton btnVerbindungTestenOffline;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblAktiveDatenbank;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnLiveDatenbank;
	private ButtonGroup group;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JPanel onlineTab;
	private JLabel dBIP_online;
	private JLabel dB_online;
	private JLabel dBUser_online;
	private JLabel dBPass_online;
	private JButton btnVerbindungTestenOnline;
	private DBConfigFrame classContext;

	/**
	 * Create the frame.
	 */
	public DBConfigFrame(JFrame parent) {
		super(parent);
		
		classContext = this;
		
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DBConfigFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Color.WHITE);
		setTitle("Tippspiel Admin - Tool | DB Einstellungen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 274);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Config.getGuiColor());
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		offlineTab = new JPanel();
		offlineTab.setBackground(Config.getGuiColor());
		tabbedPane.addTab("Offline", null, offlineTab, null);
		offlineTab.setLayout(null);
		
		dBIP_offline = new JLabel("Datenbank Host");
		dBIP_offline.setBounds(14, 8, 130, 20);
		offlineTab.add(dBIP_offline);
		
		DBIP_offline_txtfield = new JTextField();
		DBIP_offline_txtfield.setText("DBIP_textfield");
		DBIP_offline_txtfield.setColumns(10);
		DBIP_offline_txtfield.setBounds(154, 8, 265, 20);
		offlineTab.add(DBIP_offline_txtfield);
		
		dB_offline = new JLabel("Datenbank");
		dB_offline.setBounds(14, 84, 130, 20);
		offlineTab.add(dB_offline);
		
		DB_offline_txtfield = new JTextField();
		DB_offline_txtfield.setText("DBIP_textfield");
		DB_offline_txtfield.setColumns(10);
		DB_offline_txtfield.setBounds(154, 84, 265, 20);
		offlineTab.add(DB_offline_txtfield);
		
		dBUser_offline = new JLabel("Datenbank Benutzer");
		dBUser_offline.setBounds(14, 33, 130, 20);
		offlineTab.add(dBUser_offline);
		
		DBUser_offline_txtfield = new JTextField();
		DBUser_offline_txtfield.setText("DBIP_textfield");
		DBUser_offline_txtfield.setColumns(10);
		DBUser_offline_txtfield.setBounds(154, 33, 265, 20);
		offlineTab.add(DBUser_offline_txtfield);
		
		dBPass_offline = new JLabel("Datenbank Passwort");
		dBPass_offline.setBounds(14, 59, 130, 20);
		offlineTab.add(dBPass_offline);
		
		btnVerbindungTestenOffline = new JButton("Verbindung testen");
		btnVerbindungTestenOffline.setBackground(Config.getGuiColor());
		
		btnVerbindungTestenOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(MySQLConnection.testConnection(DBIP_offline_txtfield.getText(), DBUser_offline_txtfield.getText(), DBPass_offline_txtfield.getText(), "")) {
					if(!MySQLConnection.testConnection(DBIP_offline_txtfield.getText(), DBUser_offline_txtfield.getText(), DBPass_offline_txtfield.getText(), DB_offline_txtfield.getText())) {
						JOptionPane.showMessageDialog(classContext, "Verbindung erfolgreich, aber Datenbank existiert nicht.", "Verbindungstest",  JOptionPane.WARNING_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(classContext, "Verbindung erfolgreich!", "Verbindungstest",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(classContext, "Verbindung konnte nicht hergestellt werden.", "Verbindungstest", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnVerbindungTestenOffline.setBounds(268, 107, 151, 23);
		offlineTab.add(btnVerbindungTestenOffline);
		
		DBPass_offline_txtfield = new JPasswordField();
		DBPass_offline_txtfield.setText("DBIP_textfield");
		DBPass_offline_txtfield.setColumns(10);
		DBPass_offline_txtfield.setBounds(154, 59, 265, 20);
		offlineTab.add(DBPass_offline_txtfield);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(Config.getGuiColor());
		
		lblAktiveDatenbank = new JLabel("Standard Datenbank:");
		panel_1.add(lblAktiveDatenbank);
		lblAktiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		
		rdbtnNewRadioButton = new JRadioButton("Test Datenbank");
		panel_1.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		rdbtnNewRadioButton.setBackground(Config.getGuiColor());
		
		rdbtnLiveDatenbank = new JRadioButton("Live Datenbank");
		panel_1.add(rdbtnLiveDatenbank);
		rdbtnLiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		rdbtnLiveDatenbank.setBackground(Config.getGuiColor());
		
		group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnLiveDatenbank);
		
		if (Config.isDBType()) {
			rdbtnLiveDatenbank.setSelected(true);
		} else {
			rdbtnNewRadioButton.setSelected(true);
		}
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel_2.setBackground(Config.getGuiColor());
		
		btnNewButton = new JButton("Einstellungen Speichern");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, String> map = new HashMap<String, String>();
				if(rdbtnLiveDatenbank.isSelected()) {
					map.put("DBType", "true");
				}
				else {
					map.put("DBType", "false");
				}
				map.put("DBIp_offline", DBIP_offline_txtfield.getText());
				map.put("DBUser_offline", DBUser_offline_txtfield.getText());
				map.put("DBPass_offline", DBPass_offline_txtfield.getText());
				map.put("DB_offline", DB_offline_txtfield.getText());
				

				map.put("DBIp_online", DBIP_online_txtfield.getText());
				map.put("DBUser_online", DBUser_online_txtfield.getText());
				map.put("DBPass_online", DBPass_online_txtfield.getText());
				map.put("DB_online", DB_online_txtfield.getText());
				
				if(Config.write(map) && Main.switchDatabaseConnection(Config.isDBType())) {
					((MainFrame)parent).reload();
					
					JOptionPane.showMessageDialog(classContext, "Daten erfolgreich gespreichert und zur Standarddatenbank gewechselt");
					classContext.dispose();
				}
			}
		});
		btnNewButton.setBackground(Config.getGuiColor());
		
		DBIP_offline_txtfield.setText(Config.getDBIp_offline());
		DB_offline_txtfield.setText(Config.getDB_offline());
		DBUser_offline_txtfield.setText(Config.getDBUser_offline());
		DBPass_offline_txtfield.setText(Config.getDBPass_offline());
		
		onlineTab = new JPanel();
		tabbedPane.addTab("Online", null, onlineTab, null);
		onlineTab.setLayout(null);
		onlineTab.setBackground(Config.getGuiColor());
		
		dBIP_online = new JLabel("Datenbank Host");
		dBIP_online.setBounds(14, 8, 130, 20);
		onlineTab.add(dBIP_online);
		
		DBIP_online_txtfield = new JTextField();
		DBIP_online_txtfield.setText("DBIP_textfield");
		DBIP_online_txtfield.setColumns(10);
		DBIP_online_txtfield.setBounds(154, 8, 265, 20);
		onlineTab.add(DBIP_online_txtfield);
		
		dB_online = new JLabel("Datenbank ");
		dB_online.setBounds(14, 84, 130, 20);
		onlineTab.add(dB_online);
		
		DB_online_txtfield = new JTextField();
		DB_online_txtfield.setText("DBIP_textfield");
		DB_online_txtfield.setColumns(10);
		DB_online_txtfield.setBounds(154, 84, 265, 20);
		onlineTab.add(DB_online_txtfield);
		
		dBUser_online = new JLabel("Datenbank Benutzer");
		dBUser_online.setBounds(14, 33, 130, 20);
		onlineTab.add(dBUser_online);
		
		DBUser_online_txtfield = new JTextField();
		DBUser_online_txtfield.setText("DBIP_textfield");
		DBUser_online_txtfield.setColumns(10);
		DBUser_online_txtfield.setBounds(154, 33, 265, 20);
		onlineTab.add(DBUser_online_txtfield);
		
		dBPass_online = new JLabel("Datenbank Passwort");
		dBPass_online.setBounds(14, 59, 130, 20);
		onlineTab.add(dBPass_online);
		
		DBPass_online_txtfield = new JPasswordField();
		DBPass_online_txtfield.setText("DBIP_textfield");
		DBPass_online_txtfield.setColumns(10);
		DBPass_online_txtfield.setBounds(154, 59, 265, 20);
		onlineTab.add(DBPass_online_txtfield);
		
		DBIP_online_txtfield.setText(Config.getDBIp_online());
		DB_online_txtfield.setText(Config.getDB_online());
		DBUser_online_txtfield.setText(Config.getDBUser_online());
		DBPass_online_txtfield.setText(Config.getDBPass_online());
		
		DBIP_online_txtfield.setText(Config.getDBIp_online());
		
		btnVerbindungTestenOnline = new JButton("Verbindung testen");
		btnVerbindungTestenOnline.setBackground(Config.getGuiColor());
		btnVerbindungTestenOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(MySQLConnection.testConnection(DBIP_online_txtfield.getText(), DBUser_online_txtfield.getText(), DBPass_online_txtfield.getText(), DB_online_txtfield.getText())) {
					JOptionPane.showMessageDialog(classContext, "Verbindung erfolgreich!", "Verbindungstest",JOptionPane.PLAIN_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(classContext, "Verbindung konnte nicht hergestellt werden.", "Verbindungstest", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnVerbindungTestenOnline.setBounds(268, 107, 151, 23);
		onlineTab.add(btnVerbindungTestenOnline);
		
	}

	protected boolean checkTest() {
		return false;
	}
	
	protected boolean checkLive() {
		return true;
	}
}
