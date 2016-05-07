package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

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
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ErgebnisFrame extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_4;
	private JTextField textField_5;

	public ErgebnisFrame(JFrame parent) {

		super(parent);
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 15, 350, 40);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table.setModel(DatabaseManagement.getGamesWithNoInfoData());
		scrollPane.setViewportView(table);

		button = new JButton("<<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManagement.setOffset(DatabaseManagement.getOffset() - 1);
				ErgebnisFrame.this.dispose();
				if (DatabaseManagement.getOffset() < 0) {
					ErgebnisFrame_2 ef_2 = new ErgebnisFrame_2(parent);
					ef_2.setVisible(true);
				} else {
					ErgebnisFrame ef = new ErgebnisFrame(parent);
					ef.setVisible(true);
				}
				Log.info("Button '<<<' clicked.");
			}
		});
		button.setFont(new Font(Config.getFont(), Font.PLAIN, 11));
		button.setBounds(50, 73, 89, 23);
		button.setBackground(Config.getGuiColor());
		contentPane.add(button);

		button_1 = new JButton(">>>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManagement.setOffset(DatabaseManagement.getOffset() + 1);
				ErgebnisFrame.this.dispose();
				if (DatabaseManagement.getOffset() < 0) {
					ErgebnisFrame_2 ef_2 = new ErgebnisFrame_2(parent);
					ef_2.setVisible(true);
				} else {
					ErgebnisFrame ef = new ErgebnisFrame(parent);
					ef.setVisible(true);
				}
				Log.info("Button '>>>' clicked.");
			}
		});
		button_1.setFont(new Font(Config.getFont(), Font.PLAIN, 11));
		button_1.setBounds(311, 73, 89, 23);
		button_1.setBackground(Config.getGuiColor());
		contentPane.add(button_1);

		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField.setBounds(50, 140, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(314, 140, 86, 20);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(50, 171, 86, 20);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(314, 171, 86, 20);
		contentPane.add(textField_3);

		JLabel lblGabEsEine = new JLabel("Gab es eine Verl\u00E4ngerung?");
		lblGabEsEine.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		lblGabEsEine.setBounds(50, 202, 184, 23);
		contentPane.add(lblGabEsEine);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(DatabaseManagement.isGroupPhase());
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textField_6.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				textField_7.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		checkBox.setBounds(240, 205, 21, 23);
		checkBox.setBackground(Config.getGuiColor());
		contentPane.add(checkBox);

		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(50, 236, 86, 20);
		contentPane.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEnabled(false);
		textField_7.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_7.setColumns(10);
		textField_7.setBounds(314, 236, 86, 20);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setText("0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEnabled(false);
		textField_8.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(50, 301, 86, 20);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setText("0");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEnabled(false);
		textField_9.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(314, 301, 86, 20);
		contentPane.add(textField_9);

		JLabel lblGabEsElfmeterschieen = new JLabel("Gab es Elfmeterschie\u00DFen?");
		lblGabEsElfmeterschieen.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		lblGabEsElfmeterschieen.setBounds(50, 267, 184, 23);
		contentPane.add(lblGabEsElfmeterschieen);

		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(DatabaseManagement.isGroupPhase());
		checkBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textField_8.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
				textField_9.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});
		checkBox_1.setBounds(240, 270, 21, 23);
		checkBox_1.setBackground(Config.getGuiColor());
		contentPane.add(checkBox_1);

		textField_10 = new JTextField();
		textField_10.setText("0");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBounds(50, 345, 86, 20);
		contentPane.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setText("0");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_11.setColumns(10);
		textField_11.setBounds(314, 345, 86, 20);
		contentPane.add(textField_11);

		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(50, 376, 86, 20);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(314, 376, 86, 20);
		contentPane.add(textField_5);

		JLabel lblErgebnisseDrfenNicht = new JLabel("Ergebnisse d\u00FCrfen nicht kleiner werden!");
		lblErgebnisseDrfenNicht.setForeground(Color.RED);
		lblErgebnisseDrfenNicht.setHorizontalAlignment(SwingConstants.CENTER);
		lblErgebnisseDrfenNicht.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		lblErgebnisseDrfenNicht.setBounds(50, 411, 254, 14);
		lblErgebnisseDrfenNicht.setVisible(false);
		contentPane.add(lblErgebnisseDrfenNicht);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String redCardsGuest = textField_5.getText();
				int redCardsGuestInt = Integer.parseInt(redCardsGuest);
				String yellowCardsGuest = textField_11.getText();
				int yellowCardsGuestInt = Integer.parseInt(yellowCardsGuest);
				String redCardsHome = textField_4.getText();
				int redCardsHomeInt = Integer.parseInt(redCardsHome);
				String yellowCardsHome = textField_10.getText();
				int yellowCardsHomeInt = Integer.parseInt(yellowCardsHome);
				String goalsGuestAfterPenalty = textField_9.getText();
				int goalsGuestAfterPenaltyInt = Integer.parseInt(goalsGuestAfterPenalty);
				String goalsHomeAfterPenalty = textField_8.getText();
				int goalsHomeAfterPenaltyInt = Integer.parseInt(goalsHomeAfterPenalty);
				String goalsGuestAfterOvertime = textField_7.getText();
				int goalsGuestAfterOvertimeInt = Integer.parseInt(goalsGuestAfterOvertime);
				String goalsHomeAfterOvertime = textField_6.getText();
				int goalsHomeAfterOvertimeInt = Integer.parseInt(goalsHomeAfterOvertime);
				String goalsGuestAfterSecondHalf = textField_3.getText();
				int goalsGuestAfterSecondHalfInt = Integer.parseInt(goalsGuestAfterSecondHalf);
				String goalsHomeAfterSecondHalf = textField_2.getText();
				int goalsHomeAfterSecondHalfInt = Integer.parseInt(goalsHomeAfterSecondHalf);
				String goalsGuestAfterFirstHalf = textField_1.getText();
				int goalsGuestAfterFirstHalfInt = Integer.parseInt(goalsGuestAfterFirstHalf);
				String goalsHomeAfterFirstHalf = textField.getText();
				int goalsHomeAfterFirstHalfInt = Integer.parseInt(goalsHomeAfterFirstHalf);

				if (goalsHomeAfterFirstHalfInt > goalsHomeAfterSecondHalfInt
						|| goalsGuestAfterFirstHalfInt > goalsGuestAfterSecondHalfInt) {
					lblErgebnisseDrfenNicht.setVisible(true);
				} else {
					lblErgebnisseDrfenNicht.setVisible(false);
					String sqlCommand;
					@SuppressWarnings("unused")
					String sqlInputData;
					if (checkBox.isSelected() && checkBox_1.isSelected()) {
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
					} else if (checkBox.isSelected()) {
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
					ErgebnisFrame ef = new ErgebnisFrame(parent);
					ef.setVisible(true);
				}
			}
		});
		btnSpeichern.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		btnSpeichern.setBounds(314, 407, 86, 23);
		btnSpeichern.setBackground(Config.getGuiColor());
		contentPane.add(btnSpeichern);

		JLabel lblToreHalbzeit = new JLabel("Tore nach erster Halbzeit");
		lblToreHalbzeit.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreHalbzeit.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreHalbzeit.setBounds(146, 143, 158, 14);
		contentPane.add(lblToreHalbzeit);

		JLabel lblEndergebnis = new JLabel("Tore nach zweiter Halbzeit");
		lblEndergebnis.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndergebnis.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblEndergebnis.setBounds(146, 174, 158, 14);
		contentPane.add(lblEndergebnis);

		JLabel lblToreNachVerlngerung = new JLabel("Tore nach Verl\u00E4ngerung");
		lblToreNachVerlngerung.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreNachVerlngerung.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreNachVerlngerung.setBounds(146, 239, 158, 14);
		contentPane.add(lblToreNachVerlngerung);

		JLabel lblToreNachElfmeterschieen = new JLabel("Tore nach Elfmeterschie\u00DFen");
		lblToreNachElfmeterschieen.setHorizontalAlignment(SwingConstants.CENTER);
		lblToreNachElfmeterschieen.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblToreNachElfmeterschieen.setBounds(146, 304, 158, 14);
		contentPane.add(lblToreNachElfmeterschieen);

		JLabel lblGelbeKarten = new JLabel("Gelbe Karten");
		lblGelbeKarten.setHorizontalAlignment(SwingConstants.CENTER);
		lblGelbeKarten.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblGelbeKarten.setBounds(146, 348, 158, 14);
		contentPane.add(lblGelbeKarten);

		JLabel lblRoteKarten = new JLabel("Rote Karten");
		lblRoteKarten.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoteKarten.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblRoteKarten.setBounds(146, 379, 158, 14);
		contentPane.add(lblRoteKarten);

		JLabel lblHeim = new JLabel("Heim");
		lblHeim.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeim.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblHeim.setBounds(50, 115, 89, 14);
		contentPane.add(lblHeim);

		JLabel lblGast = new JLabel("Gast");
		lblGast.setHorizontalAlignment(SwingConstants.CENTER);
		lblGast.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		lblGast.setBounds(311, 115, 89, 14);
		contentPane.add(lblGast);

	}
}
