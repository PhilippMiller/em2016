package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import java.awt.Color;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.models.Match;
import whs.gdi2.tippspiel.log.Log;

@SuppressWarnings("serial")
public class ErgebnisEingabeFrame extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	protected JRadioButton rdbtnGelaufeneSpieleOhne;
	protected JRadioButton rdbtnErfassteSpieleBearbeiten;
	private JComboBox<Match> comboBox;
	private JButton button;
	protected JCheckBox checkBox_1;
	protected JCheckBox checkBox;
	private JLabel lblGametimelabel;
	protected Match currentMatch;
	/**
	 * Create the dialog.
	 */
	public ErgebnisEingabeFrame(JFrame parent) {
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | Ergebnis - Ansicht");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpielplanFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelTop.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(Config.getGuiColor());
		
		ButtonGroup rdbtGroup = new ButtonGroup();

		rdbtnGelaufeneSpieleOhne = new JRadioButton("Gelaufene Spiele ohne Ergebnis eintragen");
		JRadioButton rdbtnGelaufeneSpieleOhne = new JRadioButton("Ergebnisse eintragen");
		rdbtnGelaufeneSpieleOhne.setBackground(Config.getGuiColor());
		
		rdbtnGelaufeneSpieleOhne = new JRadioButton("Ergebnisse eintragen");
		rdbtnGelaufeneSpieleOhne.setBackground(Config.getGuiColor());
		rdbtnGelaufeneSpieleOhne.setSelected(true);
		
		panel_1.add(rdbtnGelaufeneSpieleOhne);
		rdbtGroup.add(rdbtnGelaufeneSpieleOhne);

		rdbtnErfassteSpieleBearbeiten = new JRadioButton("Erfasste Spiele bearbeiten");		
		rdbtnErfassteSpieleBearbeiten.setBackground(Config.getGuiColor());
		
		panel_1.add(rdbtnErfassteSpieleBearbeiten);
		rdbtGroup.add(rdbtnErfassteSpieleBearbeiten);

		JPanel panel_2 = new JPanel();
		panelTop.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Config.getGuiColor());
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel, BorderLayout.NORTH);
		panel.setBackground(Config.getGuiColor());
		
		JLabel lblSpieleAuswahl = new JLabel("Spiele Auswahl:");
		panel.add(lblSpieleAuswahl);
		lblSpieleAuswahl.setBackground(Config.getGuiColor());;
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		panel_3.setBackground(Config.getGuiColor());
		
		comboBox = new JComboBox<Match>();
		comboBox.setBackground(Config.getGuiColor());
		panel_3.add(comboBox, BorderLayout.CENTER);
		
		button = new JButton("Spiel ausw\u00E4hlen");
		button.setBackground(Config.getGuiColor());
		panel_3.add(button, BorderLayout.EAST);

		JPanel panelContent = new JPanel();
		getContentPane().add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanelTop = new JPanel();
		panelContent.add(contentPanelTop, BorderLayout.NORTH);
		contentPanelTop.setBackground(Config.getGuiColor());
		
		lblGametimelabel = new JLabel("gameTimeLabel");
		contentPanelTop.add(lblGametimelabel);

		JPanel contentPanelCenter = new JPanel();
		panelContent.add(contentPanelCenter, BorderLayout.CENTER);
		contentPanelCenter.setLayout(null);
		contentPanelCenter.setBackground(Config.getGuiColor());
		
		JLabel label_1 = new JLabel("Heim");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_1.setBounds(50, 10, 89, 14);
		contentPanelCenter.add(label_1);

		JLabel label_2 = new JLabel("Gast");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_2.setBounds(311, 10, 89, 14);
		contentPanelCenter.add(label_2);

		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(50, 35, 86, 20);
		contentPanelCenter.add(textField);

		JLabel label_3 = new JLabel("Tore nach erster Halbzeit");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_3.setBounds(146, 38, 158, 14);
		contentPanelCenter.add(label_3);

		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(314, 35, 86, 20);
		contentPanelCenter.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(50, 66, 86, 20);
		contentPanelCenter.add(textField_2);

		JLabel label_4 = new JLabel("Tore nach zweiter Halbzeit");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_4.setBounds(146, 69, 158, 14);
		contentPanelCenter.add(label_4);

		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(314, 66, 86, 20);
		contentPanelCenter.add(textField_3);

		JLabel label_5 = new JLabel("Gab es eine Verl\u00E4ngerung?");
		label_5.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_5.setBounds(50, 97, 184, 23);
		contentPanelCenter.add(label_5);

		checkBox = new JCheckBox("");
		checkBox.setEnabled(true);
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(240, 100, 21, 23);
		contentPanelCenter.add(checkBox);

		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(50, 131, 86, 20);
		contentPanelCenter.add(textField_4);

		JLabel label_6 = new JLabel("Tore nach Verl\u00E4ngerung");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_6.setBounds(146, 134, 158, 14);
		contentPanelCenter.add(label_6);

		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		textField_5.setBounds(314, 131, 86, 20);
		contentPanelCenter.add(textField_5);

		JLabel label_7 = new JLabel("Gab es Elfmeterschie\u00DFen?");
		label_7.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_7.setBounds(50, 162, 184, 23);
		contentPanelCenter.add(label_7);

		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(50, 196, 86, 20);
		contentPanelCenter.add(textField_6);

		JLabel label_8 = new JLabel("Tore nach Elfmeterschie\u00DFen");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_8.setBounds(146, 199, 158, 14);
		contentPanelCenter.add(label_8);

		checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		checkBox_1.setBackground(Color.WHITE);
		checkBox_1.setBounds(240, 165, 21, 23);
		contentPanelCenter.add(checkBox_1);

		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(314, 196, 86, 20);
		contentPanelCenter.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setText("0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(50, 240, 86, 20);
		contentPanelCenter.add(textField_8);

		JLabel label_9 = new JLabel("Gelbe Karten");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_9.setBounds(146, 243, 158, 14);
		contentPanelCenter.add(label_9);

		textField_9 = new JTextField();
		textField_9.setText("0");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(314, 240, 86, 20);
		contentPanelCenter.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setText("0");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBounds(314, 271, 86, 20);
		contentPanelCenter.add(textField_10);

		JLabel label_10 = new JLabel("Rote Karten");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_10.setBounds(146, 274, 158, 14);
		contentPanelCenter.add(label_10);

		textField_11 = new JTextField();
		textField_11.setText("0");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_11.setColumns(10);
		textField_11.setBounds(50, 271, 86, 20);
		contentPanelCenter.add(textField_11);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBackground(Config.getGuiColor());
		
		getContentPane().add(btnSpeichern, BorderLayout.SOUTH);
		setSize(450, 480);

		Dimension windowSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - windowSize.width / 2, screenSize.height / 2 - windowSize.height / 2);
		
		/*
		 * LISTENER
		 */
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentMatch != null) {
					try {
						
						Integer.parseInt(textField.getText());
						Integer.parseInt(textField_1.getText());
						Integer.parseInt(textField_2.getText());
						Integer.parseInt(textField_3.getText());
						
						if (checkBox.isSelected()) Integer.parseInt(textField_4.getText());
						if (checkBox.isSelected()) Integer.parseInt(textField_5.getText());
						if (checkBox_1.isSelected()) Integer.parseInt(textField_6.getText());
						if (checkBox_1.isSelected()) Integer.parseInt(textField_7.getText());
						
						Integer.parseInt(textField_8.getText());
						Integer.parseInt(textField_9.getText());
						Integer.parseInt(textField_10.getText());
						Integer.parseInt(textField_11.getText());
						
						Statement stmt = Main.mainConnection.getConnection().createStatement();
						
						String sql = "UPDATE spiele SET heimmannschafthz="
									+ textField.getText()
									+ ", gastmannschafthz="
									+ textField_1.getText()
									+ ", heimmannschaftende="
									+ textField_2.getText()
									+ ", gastmannschaftende="
									+ textField_3.getText();
						if (checkBox.isSelected()) {
							sql += ", verlaengerung=1"
									+ ", heimmannschaftverl="
									+ textField_4.getText()
									+ ", gastmannschaftverl="
									+ textField_5.getText();
							if (checkBox_1.isSelected()) {
								sql += ", elfmeter=1"
										+ ", heimmannschaftelf="
										+ textField_6.getText()
										+ ", gastmannschaftelf="
										+ textField_7.getText();
							}
						}
									
						sql += ", gelbekartenheim="
								+ textField_8.getText()
								+ ", gelbekartengast="
								+ textField_9.getText()
								+ ", rotekartenheim="
								+ textField_10.getText()
								+ ", rotekartengast="
								+ textField_11.getText()
								+ " WHERE  spieleid=" + currentMatch.getGameId();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Ergebnis erfolgreich gespeichert.", "Information", JOptionPane.INFORMATION_MESSAGE);
						reload();
					} catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Bitte nur Zahlen eingeben!", "Error", JOptionPane.ERROR_MESSAGE);
						Log.error("Insert wrong data value! [@ErgebnisEingabe_save] Error: " + ex.getMessage());
					} catch(SQLException e) {
						JOptionPane.showMessageDialog(null, "Beim Eintragen ist ein Fehler aufgetreten.\nBitte versuchen sie es später erneut.", "Error", JOptionPane.ERROR_MESSAGE);
						Log.mysqlError("An error occured. [@ErgebnisEingabe_save] Error: " + e.getMessage());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Du bearbeitest kein Spiel. Bitte wähle zuerst ein Spiel aus.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				currentMatch = (Match) comboBox.getSelectedItem();
				try {
					setFields(currentMatch);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Log.debug("Selected game ID: " + currentMatch.getGameId());
			}
		});
		
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected()) {
					textField_4.setEnabled(true);
					textField_5.setEnabled(true);
					checkBox_1.setEnabled(true);
				} else {
					textField_4.setEnabled(false);
					textField_5.setEnabled(false);
					textField_6.setEnabled(false);
					textField_7.setEnabled(false);
					checkBox_1.setSelected(false);
					checkBox_1.setEnabled(false);
				}
			}
		});
		
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBox_1.isSelected()) {
					checkBox.setSelected(true);
					textField_6.setEnabled(true);
					textField_7.setEnabled(true);
				} else {
					textField_6.setEnabled(false);
					textField_7.setEnabled(false);
				}
			}
		});
		
		rdbtnGelaufeneSpieleOhne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErgebnisEingabeFrame.this.reload();
			}
		});
		rdbtnErfassteSpieleBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErgebnisEingabeFrame.this.reload();
			}
		});
		
		reload();
	}
	
	private List<Match> selectTheRightList(Boolean ohneErgebnis) {
		String sqlleft = "SELECT * FROM spiele WHERE heimmannschafthz IS NULL AND datumuhrzeit < DATE_SUB(NOW(), INTERVAL 3 HOUR) ORDER BY datumuhrzeit";
		String sqlright ="SELECT * FROM spiele WHERE spielbezeichnung NOT LIKE '%Gruppe%' AND heimmannschafthz IS NOT NULL ORDER BY datumuhrzeit";

		List<Match> matches = null;
		try {
			Statement stmt = Main.mainConnection.getConnection().createStatement();
			ResultSet rs;
			
			if (ohneErgebnis == true) {
				rs = stmt.executeQuery(sqlleft);
			} else {
				rs = stmt.executeQuery(sqlright);
				if (rs == null) {
					rdbtnGelaufeneSpieleOhne.setSelected(true);
					return null;
				}
			}
			
			if (rs != null) {
				matches = new ArrayList<Match>();
				while(rs.next()) {
					Match match = new Match();
					match.setGameType(rs.getString("spielbezeichnung"));
					match.setGameId(rs.getInt("spieleid"));
					match.setHometeam(rs.getString("heimmannschaft"));
					match.setGuestteam(rs.getString("gastmannschaft"));
					match.setGameTime(rs.getTime("datumuhrzeit"));
					match.setGameDate(rs.getDate("datumuhrzeit"));
					if (!ohneErgebnis){
						match.setHomeTeamHt(rs.getInt("heimmannschafthz"));
						match.setGuestTeamHt(rs.getInt("gastmannschafthz"));
						match.setHomeTeamEnd(rs.getInt("heimmannschaftende"));
						match.setGuestTeamEnd(rs.getInt("gastmannschaftende"));
						match.setYellowCardsHome(rs.getInt("gelbekartenheim"));
						match.setYellowCardsGuest(rs.getInt("gelbekartengast"));
						match.setRedCardsHome(rs.getInt("rotekartenheim"));
						match.setRedCardsGuest(rs.getInt("rotekartenheim"));
						if (!rs.getString("spielbezeichnung").contains("Gruppe")) {
							match.setExtension(rs.getBoolean("verlaengerung"));
							if (match.isExtension()) {
								match.setHomeExtendEnd(rs.getInt("heimmannschaftverl"));
								match.setGuestExtendEnd(rs.getInt("gastmannschaftverl"));
							}
							match.setPenalty(rs.getBoolean("elfmeter"));
							if (match.isPenalty()){
								match.setHomePenaltyEnd(rs.getInt("heimmannschaftelf"));
								match.setGuestPenaltyEnd(rs.getInt("gastmannschaftelf"));
							}
						}
					}
					matches.add(match);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Kein Spiel bedarf einer Eingabe.", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
			return matches;
		} catch(Exception e) {
			return null;
		}
	}
	
	public void reload() {
		boolean ohneErgebnis = true;
		if (rdbtnErfassteSpieleBearbeiten.isSelected()) {
			ohneErgebnis = false;
		} else if (rdbtnGelaufeneSpieleOhne.isSelected()){
			ohneErgebnis = true;
		}
		List<Match> matches = selectTheRightList(ohneErgebnis);
		if (matches != null) {
			if (comboBox.getItemCount() != 0) {
				comboBox.removeAllItems();
			}
			clear();
			for (Match item : matches) {
				comboBox.addItem(item);
			}
		}
	}
	
	public void setFields(Match selectedMatch) throws Exception {
		if(selectedMatch == null) {
			throw new NullPointerException("Cannot edit a null-match");
		}
		
		SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.YYYY");
		SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
		lblGametimelabel.setText(sdf_date.format(selectedMatch.getGameDate()) + " " + sdf_time.format(selectedMatch.getGameTime()));

		textField.setText(selectedMatch.getHomeTeamHt() + "");
		textField_1.setText(selectedMatch.getGuestTeamHt() + "");
		textField_2.setText(selectedMatch.getHomeTeamEnd() + "");
		textField_3.setText(selectedMatch.getGuestTeamEnd() + "");
		
		if (selectedMatch.isExtension()) {
			textField_4.setText(selectedMatch.getHomeExtendEnd() + "");
			textField_5.setText(selectedMatch.getGuestExtendEnd() + "");
			textField_4.setEnabled(true);
			textField_5.setEnabled(true);
		}
		else {
			textField_4.setEnabled(false);
			textField_5.setEnabled(false);
		}
		if (selectedMatch.isPenalty()) {
			textField_6.setText(selectedMatch.getHomePenaltyEnd() + "");
			textField_7.setText(selectedMatch.getGuestPenaltyEnd() + "");
			textField_6.setEnabled(true);
			textField_7.setEnabled(true);
		}
		else {
			textField_6.setEnabled(false);
			textField_7.setEnabled(false);
		}

		checkBox_1.setSelected(selectedMatch.isPenalty());
		checkBox.setSelected(selectedMatch.isExtension());

		textField_8.setText(selectedMatch.getYellowCardsHome() + "");
		textField_9.setText(selectedMatch.getYellowCardsGuest() + "");
		
		textField_10.setText(selectedMatch.getRedCardsHome() + "");
		textField_11.setText(selectedMatch.getRedCardsGuest() + "");
		
		textField.setEnabled(true);
		textField_1.setEnabled(true);
		textField_2.setEnabled(true);
		textField_3.setEnabled(true);
		textField_8.setEnabled(true);
		textField_9.setEnabled(true);
		textField_10.setEnabled(true);
		textField_11.setEnabled(true);
		
		if (!selectedMatch.getGameType().contains("Gruppe")) {
			checkBox.setEnabled(true);
		}
	}
	
	public void clear() {
		currentMatch = null;
		
		lblGametimelabel.setText(" ");
		textField.setText("0");
		textField_1.setText("0");
		textField_2.setText("0");
		textField_3.setText("0");
		textField_4.setText("0");
		textField_5.setText("0");
		textField_6.setText("0");
		textField_7.setText("0");
		textField_8.setText("0");
		textField_9.setText("0");
		textField_10.setText("0");
		textField_11.setText("0");
		
		textField.setEnabled(false);
		textField_1.setEnabled(false);
		textField_2.setEnabled(false);
		textField_3.setEnabled(false);
		textField_4.setEnabled(false);
		textField_5.setEnabled(false);
		textField_6.setEnabled(false);
		textField_7.setEnabled(false);
		textField_8.setEnabled(false);
		textField_9.setEnabled(false);
		textField_10.setEnabled(false);
		textField_11.setEnabled(false);
		checkBox.setSelected(false);
		checkBox.setEnabled(false);
		checkBox_1.setSelected(false);
		checkBox_1.setEnabled(false);
	}
}
