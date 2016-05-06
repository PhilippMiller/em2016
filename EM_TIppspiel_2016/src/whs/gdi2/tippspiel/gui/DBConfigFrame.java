package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.MySQLConnection;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConfigFrame frame = new DBConfigFrame(new JFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DBConfigFrame(JFrame parent) {
		super(parent);
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DBConfigFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Color.WHITE);
		setTitle("DB Einstellungen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 243);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Config.getGuiColor());
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel onlineTab = new JPanel();
		tabbedPane.addTab("Online", null, onlineTab, null);
		onlineTab.setLayout(null);
		onlineTab.setBackground(Config.getGuiColor());
		
		JLabel DBIP_online = new JLabel("DB online Host");
		DBIP_online.setBounds(70, 11, 130, 14);
		onlineTab.add(DBIP_online);
		
		DBIP_online_txtfield = new JTextField();
		DBIP_online_txtfield.setText("DBIP_textfield");
		DBIP_online_txtfield.setColumns(10);
		DBIP_online_txtfield.setBounds(70, 31, 130, 20);
		onlineTab.add(DBIP_online_txtfield);
		
		JLabel DB_online = new JLabel("DB online Name");
		DB_online.setBounds(70, 77, 130, 14);
		onlineTab.add(DB_online);
		
		DB_online_txtfield = new JTextField();
		DB_online_txtfield.setText("DBIP_textfield");
		DB_online_txtfield.setColumns(10);
		DB_online_txtfield.setBounds(70, 97, 130, 20);
		onlineTab.add(DB_online_txtfield);
		
		JLabel DBUser_online = new JLabel("DB online User");
		DBUser_online.setBounds(240, 11, 130, 14);
		onlineTab.add(DBUser_online);
		
		DBUser_online_txtfield = new JTextField();
		DBUser_online_txtfield.setText("DBIP_textfield");
		DBUser_online_txtfield.setColumns(10);
		DBUser_online_txtfield.setBounds(240, 31, 130, 20);
		onlineTab.add(DBUser_online_txtfield);
		
		JLabel DBPass_online = new JLabel("DB online Passwort");
		DBPass_online.setBounds(240, 77, 130, 14);
		onlineTab.add(DBPass_online);
		
		DBPass_online_txtfield = new JPasswordField();
		DBPass_online_txtfield.setText("DBIP_textfield");
		DBPass_online_txtfield.setColumns(10);
		DBPass_online_txtfield.setBounds(240, 97, 130, 20);
		onlineTab.add(DBPass_online_txtfield);
		
		JPanel offlineTab = new JPanel();
		offlineTab.setBackground(Config.getGuiColor());
		tabbedPane.addTab("Offline", null, offlineTab, null);
		offlineTab.setLayout(null);
		
		JLabel DBIP_offline = new JLabel("DB offline Host");
		DBIP_offline.setBounds(70, 11, 130, 14);
		offlineTab.add(DBIP_offline);
		
		DBIP_offline_txtfield = new JTextField();
		DBIP_offline_txtfield.setText("DBIP_textfield");
		DBIP_offline_txtfield.setColumns(10);
		DBIP_offline_txtfield.setBounds(70, 31, 130, 20);
		offlineTab.add(DBIP_offline_txtfield);
		
		JLabel DB_offline = new JLabel("DB offline Name");
		DB_offline.setBounds(70, 77, 130, 14);
		offlineTab.add(DB_offline);
		
		DB_offline_txtfield = new JTextField();
		DB_offline_txtfield.setText("DBIP_textfield");
		DB_offline_txtfield.setColumns(10);
		DB_offline_txtfield.setBounds(70, 97, 130, 20);
		offlineTab.add(DB_offline_txtfield);
		
		JLabel DBUser_offline = new JLabel("DB offline User");
		DBUser_offline.setBounds(240, 11, 130, 14);
		offlineTab.add(DBUser_offline);
		
		DBUser_offline_txtfield = new JTextField();
		DBUser_offline_txtfield.setText("DBIP_textfield");
		DBUser_offline_txtfield.setColumns(10);
		DBUser_offline_txtfield.setBounds(240, 31, 130, 20);
		offlineTab.add(DBUser_offline_txtfield);
		
		JLabel DBPass_offline = new JLabel("DB offline Passwort");
		DBPass_offline.setBounds(240, 77, 130, 14);
		offlineTab.add(DBPass_offline);
		
		DBPass_offline_txtfield = new JPasswordField();
		DBPass_offline_txtfield.setText("DBIP_textfield");
		DBPass_offline_txtfield.setColumns(10);
		DBPass_offline_txtfield.setBounds(240, 97, 130, 20);
		offlineTab.add(DBPass_offline_txtfield);
		
		JButton btnNewButton = new JButton("Einstellungen Speichern");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MySQLConnection.testConnection(DBIP_offline_txtfield.getText(), DBUser_offline_txtfield.getText(), DBPass_offline_txtfield.getText(), "");
				MySQLConnection.testConnection(DBIP_online_txtfield.getText(), DBUser_online_txtfield.getText(), DBPass_online_txtfield.getText(), DB_online_txtfield.getText());
			}
		});
		btnNewButton.setBackground(Config.getGuiColor());
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		DBIP_offline_txtfield.setText(Config.getDBIp_offline());
		DB_offline_txtfield.setText(Config.getDB_offline());
		DBUser_offline_txtfield.setText(Config.getDBUser_offline());
		DBPass_offline_txtfield.setText(Config.getDBPass_offline());
		
		DBIP_online_txtfield.setText(Config.getDBIp_online());
		DB_online_txtfield.setText(Config.getDB_online());
		DBUser_online_txtfield.setText(Config.getDBUser_online());
		DBPass_online_txtfield.setText(Config.getDBPass_online());
		
		DBIP_online_txtfield.setText(Config.getDBIp_online());
		
	}
}
