package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextPane;


/**
 * 
 * @TODO rewrite the save function and build a ability to create new games
 * @version 1.2
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 * @author Jan-Markus Momper <jan-markus.momper@studmail.w-hs.de>
 */
public class ErgebnisFrame extends JDialog {
	private JFrame parent;
	private ErgebnisFrame classContext;
	
	private JPanel contentPane;
	private JTable table;
	private JTextField textGoalsAfterFirstHalftimeHome;
	private JTextField textGoalsAfterFirstHalftimeGuest;
	private JTextField textGoalsAfterSecondHalftimeHome;
	private JTextField textGoalsAfterSecondHalftimeGuest;
	private JTextField textGoalsOvertimeHome;
	private JTextField textGoalsOvertimeGuest;
	private JTextField textGoalsAfterPentlyHome;
	private JTextField textGoalsAfterPenaltyGuest;
	private JTextField textYellowCardHome;
	private JTextField textYellowCardGuest;
	private JTextField textRedcardHome;
	private JTextField textRedcardGuest;
	private JButton btnSpeichern;
	private JLabel lblToreHalbzeit;
	private JLabel lblEndergebnis;
	private JLabel lblToreNachVerlngerung;
	private JLabel lblToreNachElfmeterschieen;
	private JLabel lblGelbeKarten;
	private JLabel lblRoteKarten;
	private JLabel lblHeim;
	private JLabel lblGast;
	private JLabel lblErgebnisseDrfenNicht;
	private JLabel lblGabEsElfmeterschieen;
	private JCheckBox cbIsPenalty;
	private JCheckBox cbIsOverTime;
	private JLabel lblGabEsEine;
	private JScrollPane scrollPane;

	public ErgebnisFrame(JDialog parent, int spielId) {
		super(parent);
		
		classContext = this;
		
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | Ergebnis - Eingabe");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(screenSize.width/2 - 225, screenSize.height/2 - 235, 450, 470);
		
		InitializeGui();
		InitializeEvents();
		
		reload(spielId);
	}

	public void InitializeGui() {
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 64, 350, 40);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane.setViewportView(table);

		textGoalsAfterFirstHalftimeHome = new JTextField();
		textGoalsAfterFirstHalftimeHome.setText("0");
		textGoalsAfterFirstHalftimeHome.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterFirstHalftimeHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterFirstHalftimeHome.setBounds(50, 140, 86, 20);
		contentPane.add(textGoalsAfterFirstHalftimeHome);
		textGoalsAfterFirstHalftimeHome.setColumns(10);

		textGoalsAfterFirstHalftimeGuest = new JTextField();
		textGoalsAfterFirstHalftimeGuest.setText("0");
		textGoalsAfterFirstHalftimeGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterFirstHalftimeGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterFirstHalftimeGuest.setColumns(10);
		textGoalsAfterFirstHalftimeGuest.setBounds(314, 140, 86, 20);
		contentPane.add(textGoalsAfterFirstHalftimeGuest);

		textGoalsAfterSecondHalftimeHome = new JTextField();
		textGoalsAfterSecondHalftimeHome.setText("0");
		textGoalsAfterSecondHalftimeHome.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterSecondHalftimeHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterSecondHalftimeHome.setColumns(10);
		textGoalsAfterSecondHalftimeHome.setBounds(50, 171, 86, 20);
		contentPane.add(textGoalsAfterSecondHalftimeHome);

		textGoalsAfterSecondHalftimeGuest = new JTextField();
		textGoalsAfterSecondHalftimeGuest.setText("0");
		textGoalsAfterSecondHalftimeGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterSecondHalftimeGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterSecondHalftimeGuest.setColumns(10);
		textGoalsAfterSecondHalftimeGuest.setBounds(314, 171, 86, 20);
		contentPane.add(textGoalsAfterSecondHalftimeGuest);

		lblGabEsEine = new JLabel("Gab es eine Verl\u00E4ngerung?");
		lblGabEsEine.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		lblGabEsEine.setBounds(50, 202, 184, 23);
		contentPane.add(lblGabEsEine);

		cbIsOverTime = new JCheckBox("");
		cbIsOverTime.setEnabled(DatabaseManagement.isGroupPhase());
		cbIsOverTime.setBounds(240, 205, 21, 23);
		cbIsOverTime.setBackground(Config.getGuiColor());
		contentPane.add(cbIsOverTime);

		textGoalsOvertimeHome = new JTextField();
		textGoalsOvertimeHome.setText("0");
		textGoalsOvertimeHome.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsOvertimeHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsOvertimeHome.setEnabled(false);
		textGoalsOvertimeHome.setColumns(10);
		textGoalsOvertimeHome.setBounds(50, 236, 86, 20);
		contentPane.add(textGoalsOvertimeHome);

		textGoalsOvertimeGuest = new JTextField();
		textGoalsOvertimeGuest.setText("0");
		textGoalsOvertimeGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsOvertimeGuest.setEnabled(false);
		textGoalsOvertimeGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsOvertimeGuest.setColumns(10);
		textGoalsOvertimeGuest.setBounds(314, 236, 86, 20);
		contentPane.add(textGoalsOvertimeGuest);

		textGoalsAfterPentlyHome = new JTextField();
		textGoalsAfterPentlyHome.setText("0");
		textGoalsAfterPentlyHome.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterPentlyHome.setEnabled(false);
		textGoalsAfterPentlyHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterPentlyHome.setColumns(10);
		textGoalsAfterPentlyHome.setBounds(50, 301, 86, 20);
		contentPane.add(textGoalsAfterPentlyHome);

		textGoalsAfterPenaltyGuest = new JTextField();
		textGoalsAfterPenaltyGuest.setText("0");
		textGoalsAfterPenaltyGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textGoalsAfterPenaltyGuest.setEnabled(false);
		textGoalsAfterPenaltyGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textGoalsAfterPenaltyGuest.setColumns(10);
		textGoalsAfterPenaltyGuest.setBounds(314, 301, 86, 20);
		contentPane.add(textGoalsAfterPenaltyGuest);

		lblGabEsElfmeterschieen = new JLabel("Gab es Elfmeterschie\u00DFen?");
		lblGabEsElfmeterschieen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		lblGabEsElfmeterschieen.setBounds(50, 267, 184, 23);
		contentPane.add(lblGabEsElfmeterschieen);

		cbIsPenalty = new JCheckBox("");
		cbIsPenalty.setEnabled(DatabaseManagement.isGroupPhase());
		cbIsPenalty.setBounds(240, 270, 21, 23);
		cbIsPenalty.setBackground(Config.getGuiColor());
		contentPane.add(cbIsPenalty);

		textYellowCardHome = new JTextField();
		textYellowCardHome.setText("0");
		textYellowCardHome.setHorizontalAlignment(SwingConstants.CENTER);
		textYellowCardHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textYellowCardHome.setColumns(10);
		textYellowCardHome.setBounds(50, 345, 86, 20);
		contentPane.add(textYellowCardHome);

		textYellowCardGuest = new JTextField();
		textYellowCardGuest.setText("0");
		textYellowCardGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textYellowCardGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textYellowCardGuest.setColumns(10);
		textYellowCardGuest.setBounds(314, 345, 86, 20);
		contentPane.add(textYellowCardGuest);

		textRedcardHome = new JTextField();
		textRedcardHome.setText("0");
		textRedcardHome.setHorizontalAlignment(SwingConstants.CENTER);
		textRedcardHome.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textRedcardHome.setColumns(10);
		textRedcardHome.setBounds(50, 376, 86, 20);
		contentPane.add(textRedcardHome);

		textRedcardGuest = new JTextField();
		textRedcardGuest.setText("0");
		textRedcardGuest.setHorizontalAlignment(SwingConstants.CENTER);
		textRedcardGuest.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textRedcardGuest.setColumns(10);
		textRedcardGuest.setBounds(314, 376, 86, 20);
		contentPane.add(textRedcardGuest);

		lblErgebnisseDrfenNicht = new JLabel("Ergebnisse d\u00FCrfen nicht kleiner werden!");
		lblErgebnisseDrfenNicht.setForeground(Color.RED);
		lblErgebnisseDrfenNicht.setHorizontalAlignment(SwingConstants.CENTER);
		lblErgebnisseDrfenNicht.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblErgebnisseDrfenNicht.setBounds(50, 411, 254, 14);
		lblErgebnisseDrfenNicht.setVisible(false);
		contentPane.add(lblErgebnisseDrfenNicht);

		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		btnSpeichern.setBounds(314, 407, 86, 23);
		btnSpeichern.setBackground(Config.getGuiColor());
		contentPane.add(btnSpeichern);

		lblToreHalbzeit = new JLabel("Tore nach erster Halbzeit");
		lblToreHalbzeit.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreHalbzeit.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreHalbzeit.setBounds(146, 143, 158, 14);
		contentPane.add(lblToreHalbzeit);

		lblEndergebnis = new JLabel("Tore nach zweiter Halbzeit");
		lblEndergebnis.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndergebnis.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblEndergebnis.setBounds(146, 174, 158, 14);
		contentPane.add(lblEndergebnis);

		lblToreNachVerlngerung = new JLabel("Tore nach Verl\u00E4ngerung");
		lblToreNachVerlngerung.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreNachVerlngerung.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreNachVerlngerung.setBounds(146, 239, 158, 14);
		contentPane.add(lblToreNachVerlngerung);

		lblToreNachElfmeterschieen = new JLabel("Tore nach Elfmeterschie\u00DFen");
		lblToreNachElfmeterschieen.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreNachElfmeterschieen.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreNachElfmeterschieen.setBounds(146, 304, 158, 14);
		contentPane.add(lblToreNachElfmeterschieen);

		lblGelbeKarten = new JLabel("Gelbe Karten");
		lblGelbeKarten.setHorizontalAlignment(SwingConstants.CENTER);
		lblGelbeKarten.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblGelbeKarten.setBounds(146, 348, 158, 14);
		contentPane.add(lblGelbeKarten);

		lblRoteKarten = new JLabel("Rote Karten");
		lblRoteKarten.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoteKarten.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblRoteKarten.setBounds(146, 379, 158, 14);
		contentPane.add(lblRoteKarten);

		lblHeim = new JLabel("Heim");
		lblHeim.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeim.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblHeim.setBounds(50, 115, 89, 14);
		contentPane.add(lblHeim);

		lblGast = new JLabel("Gast");
		lblGast.setHorizontalAlignment(SwingConstants.CENTER);
		lblGast.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblGast.setBounds(311, 115, 89, 14);
		contentPane.add(lblGast);
		
		JTextPane txtpnButte = new JTextPane();
		txtpnButte.setText("Hier k\u00F6nnen Sie das Ergebnis gespielter Spiele, die mindestens 3 Stunden zur\u00FCck liegen, vervollst\u00E4ndigen.\r\nRot hinterlegte Felder m\u00FCssen ausgef\u00FCllt werden.\r\n");
		txtpnButte.setBounds(50, 11, 350, 52);
		contentPane.add(txtpnButte);
	}
	
	public void InitializeEvents() {
		cbIsPenalty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textGoalsAfterPentlyHome.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				textGoalsAfterPenaltyGuest.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		
		cbIsOverTime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textGoalsOvertimeHome.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				textGoalsOvertimeGuest.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String redCardsGuest = textRedcardGuest.getText();
				int redCardsGuestInt = Integer.parseInt(redCardsGuest);
				String yellowCardsGuest = textYellowCardGuest.getText();
				int yellowCardsGuestInt = Integer.parseInt(yellowCardsGuest);
				String redCardsHome = textRedcardHome.getText();
				int redCardsHomeInt = Integer.parseInt(redCardsHome);
				String yellowCardsHome = textYellowCardHome.getText();
				int yellowCardsHomeInt = Integer.parseInt(yellowCardsHome);
				String goalsGuestAfterPenalty = textGoalsAfterPenaltyGuest.getText();
				int goalsGuestAfterPenaltyInt = Integer.parseInt(goalsGuestAfterPenalty);
				String goalsHomeAfterPenalty = textGoalsAfterPentlyHome.getText();
				int goalsHomeAfterPenaltyInt = Integer.parseInt(goalsHomeAfterPenalty);
// ACHTUNG NUMBER FORMAT EXCEPTION!!!!
				String goalsGuestAfterOvertime = textGoalsOvertimeGuest.getText();
				int goalsGuestAfterOvertimeInt = Integer.parseInt(goalsGuestAfterOvertime);
				String goalsHomeAfterOvertime = textGoalsOvertimeHome.getText();
				int goalsHomeAfterOvertimeInt = Integer.parseInt(goalsHomeAfterOvertime);
				String goalsGuestAfterSecondHalf = textGoalsAfterSecondHalftimeGuest.getText();
				int goalsGuestAfterSecondHalfInt = Integer.parseInt(goalsGuestAfterSecondHalf);
				String goalsHomeAfterSecondHalf = textGoalsAfterSecondHalftimeHome.getText();
				int goalsHomeAfterSecondHalfInt = Integer.parseInt(goalsHomeAfterSecondHalf);
				String goalsGuestAfterFirstHalf = textGoalsAfterFirstHalftimeGuest.getText();
				int goalsGuestAfterFirstHalfInt = Integer.parseInt(goalsGuestAfterFirstHalf);
				String goalsHomeAfterFirstHalf = textGoalsAfterFirstHalftimeHome.getText();
				int goalsHomeAfterFirstHalfInt = Integer.parseInt(goalsHomeAfterFirstHalf);

				if (goalsHomeAfterFirstHalfInt > goalsHomeAfterSecondHalfInt
						|| goalsGuestAfterFirstHalfInt > goalsGuestAfterSecondHalfInt) {
					lblErgebnisseDrfenNicht.setVisible(true);
				} else {
					lblErgebnisseDrfenNicht.setVisible(false);
					String sqlCommand;
					String sqlInputData;
					if (cbIsOverTime.isSelected() && cbIsPenalty.isSelected()) {
						sqlCommand = "UPDATE " + Config.getTableGames() + " SET heimmannschafthz = "
								+ goalsHomeAfterFirstHalfInt + ", gastmannschafthz = " + goalsGuestAfterFirstHalfInt
								+ "," + " heimmannschaftende = " + goalsHomeAfterSecondHalfInt
								+ ", gastmannschaftende = " + goalsGuestAfterSecondHalfInt
								+ ", verlaengerung=TRUE, heimmannschaftverl=" + goalsHomeAfterOvertimeInt
								+ ", gastmannschaftverl=" + goalsGuestAfterOvertimeInt
								+ ", elfmeter=TRUE, heimmannschaftelf=" + goalsHomeAfterPenaltyInt
								+ ", gastmannschaftelf=" + goalsGuestAfterPenaltyInt + ", gelbekartenheim="
								+ yellowCardsHomeInt + ", gelbekartengast=" + yellowCardsGuestInt + ", rotekartenheim="
								+ redCardsHomeInt + ", rotekartengast=" + redCardsGuestInt + " ";
					} else if (cbIsOverTime.isSelected()) {
						sqlCommand = "UPDATE " + Config.getTableGames() + " SET heimmannschafthz = "
								+ goalsHomeAfterFirstHalfInt + ", gastmannschafthz = " + goalsGuestAfterFirstHalfInt
								+ "," + " heimmannschaftende = " + goalsHomeAfterSecondHalfInt
								+ ", gastmannschaftende = " + goalsGuestAfterSecondHalfInt
								+ ", verlaengerung=TRUE, heimmannschaftverl=" + goalsHomeAfterOvertimeInt
								+ ", gastmannschaftverl=" + goalsGuestAfterOvertimeInt
								+ ", elfmeter=FALSE, gelbekartenheim=" + yellowCardsHomeInt + ", gelbekartengast="
								+ yellowCardsGuestInt + ", rotekartenheim=" + redCardsHomeInt + ", rotekartengast="
								+ redCardsGuestInt + " ";
					} else if (DatabaseManagement.isGroupPhase()) {
						sqlCommand = "UPDATE " + Config.getTableGames() + " SET heimmannschafthz = "
								+ goalsHomeAfterFirstHalfInt + ", gastmannschafthz = " + goalsGuestAfterFirstHalfInt
								+ "," + " heimmannschaftende = " + goalsHomeAfterSecondHalfInt
								+ ", gastmannschaftende = " + goalsGuestAfterSecondHalfInt + ", gelbekartenheim="
								+ yellowCardsHomeInt + ", gelbekartengast=" + yellowCardsGuestInt + ", rotekartenheim="
								+ redCardsHomeInt + ", rotekartengast=" + redCardsGuestInt + " ";
					} else {
						sqlCommand = "UPDATE " + Config.getTableGames() + " SET heimmannschafthz = "
								+ goalsHomeAfterFirstHalfInt + ", gastmannschafthz = " + goalsGuestAfterFirstHalfInt
								+ "," + " heimmannschaftende = " + goalsHomeAfterSecondHalfInt
								+ ", gastmannschaftende = " + goalsGuestAfterSecondHalfInt
								+ ", verlaengerung=FALSE, elfmeter=FALSE, gelbekartenheim=" + yellowCardsHomeInt
								+ ", gelbekartengast=" + yellowCardsGuestInt + ", rotekartenheim=" + redCardsHomeInt
								+ ", rotekartengast=" + redCardsGuestInt + " ";
					}
					DatabaseManagement.addGameData(sqlCommand);
					ErgebnisFrame.this.dispose();
					//ErgebnisFrame ef = new ErgebnisFrame(parent);
					//ef.setVisible(true);
				}
			}
		});
	}
		
	public void reload(int spielId) {
		
		try {
			Statement stmt = Main.mainConnection.getConnection().createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM spiele WHERE spieleid = "+ spielId);
			
			if(rs.next()) {
				textRedcardGuest.setText(rs.getString("rotekartengast"));
				if(rs.wasNull()) {
					textRedcardGuest.setBorder(BorderFactory.createLineBorder(Color.red));
					textRedcardGuest.setText(null);
				}

				textYellowCardGuest.setText(rs.getString("gelbekartengast"));
				if(rs.wasNull()) {
					textYellowCardGuest.setBorder(BorderFactory.createLineBorder(Color.red));
					textYellowCardGuest.setText(null);
				}

				textYellowCardHome.setText(rs.getString("gelbekartenheim"));
				if(rs.wasNull()) {
					textYellowCardHome.setBorder(BorderFactory.createLineBorder(Color.red));
					textYellowCardHome.setText(null);
				}
				textRedcardHome.setText(rs.getString("rotekartenheim"));
				if(rs.wasNull()) {
					textRedcardHome.setBorder(BorderFactory.createLineBorder(Color.red));
					textRedcardHome.setText(null);
				}

				textGoalsAfterPenaltyGuest.setText(rs.getString("gastmannschaftelf"));
				if(rs.wasNull()) {
					textGoalsAfterPenaltyGuest.setText(null);
				}


				textGoalsAfterPentlyHome.setText(rs.getString("heimmannschaftelf"));
				if(rs.wasNull()) {
					textGoalsAfterPentlyHome.setText(null);
				}

				textGoalsAfterFirstHalftimeHome.setText(rs.getString("heimmannschafthz"));
				if(rs.wasNull()) {
					textGoalsAfterFirstHalftimeHome.setBorder(BorderFactory.createLineBorder(Color.red));
					textGoalsAfterFirstHalftimeHome.setText(null);
				}

				textGoalsAfterFirstHalftimeGuest.setText(rs.getString("gastmannschafthz"));
				if(rs.wasNull()) {
					textGoalsAfterFirstHalftimeGuest.setBorder(BorderFactory.createLineBorder(Color.red));
					textGoalsAfterFirstHalftimeGuest.setText(null);
				}

				textGoalsAfterSecondHalftimeGuest.setText(rs.getString("gastmannschaftende"));
				if(rs.wasNull()) {
					textGoalsAfterSecondHalftimeGuest.setBorder(BorderFactory.createLineBorder(Color.red));
					textGoalsAfterSecondHalftimeGuest.setText(null);
				}

				textGoalsAfterSecondHalftimeHome.setText(rs.getString("heimmannschaftende"));
				if(rs.wasNull()) {
					textGoalsAfterSecondHalftimeHome.setBorder(BorderFactory.createLineBorder(Color.red));
					textGoalsAfterSecondHalftimeHome.setText(null);
				}

				textGoalsOvertimeHome.setText(rs.getString("heimmannschaftverl"));
				if(rs.wasNull()) {
					textGoalsOvertimeHome.setText(null);
				}

				textGoalsOvertimeHome.setText(rs.getString("heimmannschaftverl"));
				if(rs.wasNull()) {
					textGoalsOvertimeHome.setText(null);
				}
				textGoalsOvertimeGuest.setText(rs.getString("gastmannschaftverl"));
				if(rs.wasNull()) {
					textGoalsOvertimeGuest.setText(null);
				}
				
				cbIsOverTime.setSelected(rs.getBoolean("verlaengerung"));
			}
			else {
				JOptionPane.showMessageDialog(classContext, "Das ausgewählte Spiel konnte nicht geladen werden.", "Ergebnis eingabe", JOptionPane.WARNING_MESSAGE);
				classContext.dispose();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.setModel(DatabaseManagement.getGameTableModel(spielId));
	}
}
