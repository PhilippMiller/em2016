package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

/**
 * 
 * @version 1.0
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 * @author Philipp Miller <philipp.miller@studmail.w-hs.de>
 *
 */

public class MainFrame extends JFrame {
	static String sqlOutput;
	protected DBConfigFrame dbConfigFrame;
	protected MainFrame classContext;
	
	// Gui Elemente. Bitte die Hauptsachen immer als Attribut
	private JPanel content;
	private JMenuBar menuBar;
	private JMenu mnSpielplan;
	private JMenuItem mntmEm;
	private JMenu mnErgebnisse;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenu mnSpielerranking;
	private JMenu mnEm;
	private JMenuItem mntmSpielerRanking;
	private JMenuItem mntmGruppenRanking;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JMenu mnBundesliga;
	private JMenuItem mntmSchlieen;
	private JMenu mnEinstellungen;
	private JMenuItem mntmDbEinstellungen;
	private JMenuItem mntmTestdatenEinpflegen;
	private JMenu mnDbSwitcher;
	private JRadioButtonMenuItem rdbtnmntmLiveDatenbank;
	private JRadioButtonMenuItem rdbtnmntmTestDatenbank;
	private JMenu mnHilfe;
	private JMenuItem mntmEinstellungen;
	private JLabel lblTop;
	private JLabel lblDieNchsten;
	private JButton btnAktualisieren;
	private JLabel lblMysqlKonsoleneingabe;
	private JButton btnSenden;
	private JLabel lblMysqlKonsolenausgabe;
	private JTextArea txtrFasfsafsaf;
	private JButton btnMiroslav;

	
	public String getSqlOutput() {
		return sqlOutput;
	}

	public void setSqlOutput(String text) {
		MainFrame.sqlOutput = text + ",";
	}

	public MainFrame(boolean showDBSettings) {

		classContext = this;
		
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setTitle("Tippspiel Admin - Tool");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 860);

		this.menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMen = new JMenu("Men\u00FC");
		mnMen.setBackground(Config.getGuiColor());
		mnMen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnMen);

		mnSpielplan = new JMenu("Spielplan");
		mnSpielplan.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnMen.add(mnSpielplan);

		mntmEm = new JMenuItem("EM 2016");
		mntmEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielplanEM2016Frame spielplanFrame = new SpielplanEM2016Frame(classContext);
				spielplanFrame.setVisible(true);
				spielplanFrame.setModal(true);
				Log.info("Menue item 'EM 2016' clicked.");
			}
		});
		mntmEm.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnSpielplan.add(mntmEm);

		JMenuItem mntmBundesliga = new JMenuItem("Bundesliga 16/17");
		mntmBundesliga.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmBundesliga.setEnabled(false);
		mnSpielplan.add(mntmBundesliga);

		JMenuItem mntmErgebnisseEingeben = new JMenuItem("Ergebnis Eingabe");

		mntmErgebnisseEingeben.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmErgebnisseEingeben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ErgebnisFrame ergebnisFrame = new ErgebnisFrame(classContext);
				ergebnisFrame.setVisible(true);
				ergebnisFrame.setModal(true);
				Log.info("Menue item 'Ergebnis Eingabe' clicked.");
			}
		});
		mnMen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));

		mnErgebnisse = new JMenu("Ergebnisse");
		mnErgebnisse.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mnErgebnisse);

		menuItem = new JMenuItem("EM 2016");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ErgebnisseEM2016Frame ergebnisseFrame = new ErgebnisseEM2016Frame(classContext);
				ergebnisseFrame.setVisible(true);
				ergebnisseFrame.setModal(true);
				Log.info("Menue item 'Ergebnisse > EM2016' clicked.");
			}
		});
		menuItem.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnErgebnisse.add(menuItem);

		menuItem_1 = new JMenuItem("Bundesliga 16/17");
		menuItem_1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		menuItem_1.setEnabled(false);
		mnErgebnisse.add(menuItem_1);

		mnSpielerranking = new JMenu("Ranking");
		mnSpielerranking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mnSpielerranking);

		mnEm = new JMenu("EM 2016");
		mnEm.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnSpielerranking.add(mnEm);

		mntmSpielerRanking = new JMenuItem("Spieler Ranking");
		mntmSpielerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielerRankingEM2016Frame srkemframe = new SpielerRankingEM2016Frame(classContext);
				srkemframe.setVisible(true);
				srkemframe.setModal(true);
				Log.info("Menue item 'Ranking > EM2016 > Spieler Ranking' clicked.");
			}
		});
		mntmSpielerRanking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnEm.add(mntmSpielerRanking);

		mntmGruppenRanking = new JMenuItem("Gruppen Ranking");
		mntmGruppenRanking.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnEm.add(mntmGruppenRanking);

		mnBundesliga = new JMenu("Bundesliga 16 / 17");
		mnBundesliga.setEnabled(false);
		mnBundesliga.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnSpielerranking.add(mnBundesliga);

		JSeparator separator_1 = new JSeparator();
		mnMen.add(separator_1);
		mnMen.add(mntmErgebnisseEingeben);

		JSeparator separator_2 = new JSeparator();
		mnMen.add(separator_2);

		mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Log.info("'Schließen' clicked.");
				System.exit(0);
			}
		});
		mntmSchlieen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		mnMen.add(mntmSchlieen);

		mnEinstellungen = new JMenu("Einstellungen");
		mnEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnEinstellungen);

		mntmDbEinstellungen = new JMenuItem("DB Einstellungen");
		mntmDbEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mntmDbEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbConfigFrame = new DBConfigFrame(classContext);
				dbConfigFrame.setVisible(true);
				Log.info("Menue item 'DB Einstellungen' clicked.");
			}
		});
		mnEinstellungen.add(mntmDbEinstellungen);

		mntmTestdatenEinpflegen = new JMenuItem("Datenbank Verwaltung");
		mntmTestdatenEinpflegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Config.isDBType()) {
					JOptionPane.showMessageDialog(classContext,
							"Testdaten können nur in der Testdatenbank eingespielt werden.\n Bitte ändere die benutze Datenbank zur test Datenbank.");
					return;
				}
				DBToolDialog dbtd = new DBToolDialog(classContext);
				dbtd.setVisible(true);
				Log.info("Menue item 'Datenbank Verwaltung' clicked.");
			}
		});
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));

		mnDbSwitcher = new JMenu("DB Switcher");
		mnDbSwitcher.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mnDbSwitcher);

		ActionListener actionPrinter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Log.info("Action [" + e.getActionCommand() + "] performed!");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};

		rdbtnmntmLiveDatenbank = new JRadioButtonMenuItem("Live Datenbank");
		rdbtnmntmLiveDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnDbSwitcher.add(rdbtnmntmLiveDatenbank);
		rdbtnmntmLiveDatenbank.addActionListener(actionPrinter);

		rdbtnmntmTestDatenbank = new JRadioButtonMenuItem("Test Datenbank");
		rdbtnmntmTestDatenbank.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnDbSwitcher.add(rdbtnmntmTestDatenbank);
		rdbtnmntmTestDatenbank.addActionListener(actionPrinter);
		if (Config.isDBType()) {
			rdbtnmntmLiveDatenbank.setSelected(true);
		} else {
			rdbtnmntmTestDatenbank.setSelected(true);
		}

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnmntmLiveDatenbank);
		group.add(rdbtnmntmTestDatenbank);

		JSeparator separator = new JSeparator();
		mnEinstellungen.add(separator);
		mntmTestdatenEinpflegen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnEinstellungen.add(mntmTestdatenEinpflegen);

		mnHilfe = new JMenu("Hilfe");
		mnHilfe.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		menuBar.add(mnHilfe);

		mntmEinstellungen = new JMenuItem("\u00DCber Tippspiel Admin - Tool");
		mntmEinstellungen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		mnHilfe.add(mntmEinstellungen);

		content = new JPanel();
		content.setBackground(Config.getGuiColor());
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setLayout(null);
		setContentPane(content);

		lblTop = new JLabel("Top 10 - Tipper");
		lblTop.setBounds(10, 40, 504, 20);
		lblTop.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(lblTop);

		lblDieNchsten = new JLabel("Die n\u00E4chsten 10 Spiele");
		lblDieNchsten.setBounds(509, 40, 504, 20);
		lblDieNchsten.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblDieNchsten.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(lblDieNchsten);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 63, 404, 350);
		content.add(scrollPane);

		table = new JTable();
		table.setShowGrid(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(559, 63, 404, 350);
		content.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setShowGrid(false);
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table_1.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table_1);

		btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reload();
			}
		});
		btnAktualisieren.setBounds(831, 752, 132, 23);
		btnAktualisieren.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		btnAktualisieren.setBackground(Config.getGuiColor());
		content.add(btnAktualisieren);

		lblMysqlKonsoleneingabe = new JLabel("MySQL Konsoleneingabe");
		lblMysqlKonsoleneingabe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMysqlKonsoleneingabe.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblMysqlKonsoleneingabe.setBounds(10, 441, 504, 20);
		content.add(lblMysqlKonsoleneingabe);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseManagement.sendTextToDB(textField.getText());
			}
		});
		textField.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField.setBounds(59, 472, 404, 20);
		content.add(textField);
		textField.setColumns(10);

		btnSenden = new JButton("Senden");
		btnSenden.setBounds(473, 470, 89, 23);
		btnSenden.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		btnSenden.setBackground(Config.getGuiColor());
		btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSqlOutput("mnamamamama");
				DatabaseManagement.sendTextToDB(textField.getText());
			}
		});
		content.add(btnSenden);

		lblMysqlKonsolenausgabe = new JLabel("MySQL Konsolenausgabe");
		lblMysqlKonsolenausgabe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMysqlKonsolenausgabe.setFont(new Font(Config.getFont(), Font.PLAIN, 18));
		lblMysqlKonsolenausgabe.setBounds(10, 503, 504, 20);
		content.add(lblMysqlKonsolenausgabe);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(59, 534, 404, 241);
		content.add(scrollPane_2);

		txtrFasfsafsaf = new JTextArea();
		txtrFasfsafsaf.setEditable(false);
		txtrFasfsafsaf.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		txtrFasfsafsaf.setText("HALLO ICH FUNKTIONIERE NOCH NICHT UND WARTE\nAUF BEARBEITUNG VON MARIO ODER PHILIPP");
		scrollPane_2.setViewportView(txtrFasfsafsaf);

		btnMiroslav = new JButton("MIROSLAV");
		btnMiroslav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Log.info("MIROSLAV CLOSE");
				System.exit(0);
			}
		});
		btnMiroslav.setFont(new Font("Calibri Light", Font.PLAIN, 50));
		btnMiroslav.setBackground(Color.WHITE);
		btnMiroslav.setBounds(559, 573, 404, 102);
		content.add(btnMiroslav);

		if (!showDBSettings) {
			dbConfigFrame = new DBConfigFrame(classContext);
			dbConfigFrame.setVisible(true);
		}
		else {
			reload();
		}
		
		setVisible(true);
	}
	
	public void reload() {
		if (Config.isDBType()) {
			rdbtnmntmLiveDatenbank.setSelected(true);
		} else {
			rdbtnmntmTestDatenbank.setSelected(true);
		}
		table.setModel(DatabaseManagement.implementUserTop10Data());
		table_1.setModel(DatabaseManagement.implementNext10Games());
		
		Log.info("Reload MainFrame data complete.");
	}
}
