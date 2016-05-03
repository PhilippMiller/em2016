package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Window.Type;

public class DBConfigFrame extends JFrame {

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
					DBConfigFrame frame = new DBConfigFrame();
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
	public DBConfigFrame() {
		setType(Type.UTILITY);
		setTitle("DB Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel oflineTab = new JPanel();
		tabbedPane.addTab("Offline", null, oflineTab, null);
		oflineTab.setLayout(null);
		
		JLabel DBIP_offline = new JLabel("DB offline Host");
		DBIP_offline.setBounds(70, 11, 130, 14);
		oflineTab.add(DBIP_offline);
		
		DBIP_offline_txtfield = new JTextField();
		DBIP_offline_txtfield.setText("DBIP_textfield");
		DBIP_offline_txtfield.setColumns(10);
		DBIP_offline_txtfield.setBounds(70, 31, 130, 20);
		oflineTab.add(DBIP_offline_txtfield);
		
		JLabel DB_offline = new JLabel("DB offline Name");
		DB_offline.setBounds(70, 77, 130, 14);
		oflineTab.add(DB_offline);
		
		DB_offline_txtfield = new JTextField();
		DB_offline_txtfield.setText("DBIP_textfield");
		DB_offline_txtfield.setColumns(10);
		DB_offline_txtfield.setBounds(70, 97, 130, 20);
		oflineTab.add(DB_offline_txtfield);
		
		JLabel DBUser_offline = new JLabel("DB offline User");
		DBUser_offline.setBounds(240, 11, 130, 14);
		oflineTab.add(DBUser_offline);
		
		DBUser_offline_txtfield = new JTextField();
		DBUser_offline_txtfield.setText("DBIP_textfield");
		DBUser_offline_txtfield.setColumns(10);
		DBUser_offline_txtfield.setBounds(240, 31, 130, 20);
		oflineTab.add(DBUser_offline_txtfield);
		
		JLabel DBPass_offline = new JLabel("DB offline Passwort");
		DBPass_offline.setBounds(240, 77, 130, 14);
		oflineTab.add(DBPass_offline);
		
		DBPass_offline_txtfield = new JTextField();
		DBPass_offline_txtfield.setText("DBIP_textfield");
		DBPass_offline_txtfield.setColumns(10);
		DBPass_offline_txtfield.setBounds(240, 97, 130, 20);
		oflineTab.add(DBPass_offline_txtfield);
		
		JPanel onlineTab = new JPanel();
		tabbedPane.addTab("Online", null, onlineTab, null);
		onlineTab.setLayout(null);
		
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
		
		DBPass_online_txtfield = new JTextField();
		DBPass_online_txtfield.setText("DBIP_textfield");
		DBPass_online_txtfield.setColumns(10);
		DBPass_online_txtfield.setBounds(240, 97, 130, 20);
		onlineTab.add(DBPass_online_txtfield);
		
		JButton btnNewButton = new JButton("Einstellungen Speichern");
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
}
