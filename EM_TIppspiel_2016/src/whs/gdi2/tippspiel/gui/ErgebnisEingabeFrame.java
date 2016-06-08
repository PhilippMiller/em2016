package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import java.awt.Color;

import javax.swing.event.ChangeListener;

import com.mysql.jdbc.SQLError;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.database.models.Match;
import whs.gdi2.tippspiel.log.Log;

import javax.swing.event.ChangeEvent;

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
	private JRadioButton rdbtnGelaufeneSpieleOhne;
	private JRadioButton rdbtnErfassteSpieleBearbeiten;

	/**
	 * Create the dialog.
	 */
	public ErgebnisEingabeFrame(JFrame parent) {
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | Ergebnis - Ansicht");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SpielplanFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelTop.add(panel_1, BorderLayout.NORTH);

		ButtonGroup rdbtGroup = new ButtonGroup();

		rdbtnGelaufeneSpieleOhne = new JRadioButton("Gelaufene Spiele ohne Ergebnis eintragen");
		rdbtnGelaufeneSpieleOhne.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnGelaufeneSpieleOhne.isSelected())
					selectTheRightList(true);
			}
		});
		rdbtnGelaufeneSpieleOhne.setSelected(true);
		
		panel_1.add(rdbtnGelaufeneSpieleOhne);
		rdbtGroup.add(rdbtnGelaufeneSpieleOhne);

		rdbtnErfassteSpieleBearbeiten = new JRadioButton("Erfasste Spiele bearbeiten");
		rdbtnErfassteSpieleBearbeiten.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnErfassteSpieleBearbeiten.isSelected())
					selectTheRightList(false);
			}
		});
		panel_1.add(rdbtnErfassteSpieleBearbeiten);
		rdbtGroup.add(rdbtnErfassteSpieleBearbeiten);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelTop.add(panel_2, BorderLayout.CENTER);

		JLabel label = new JLabel("Spiele Auswahl");
		panel_2.add(label);

		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);

		JButton button = new JButton("Spiel ausw\u00E4hlen");
		panel_2.add(button);

		JPanel panelContent = new JPanel();
		getContentPane().add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));

		JPanel contentPanelTop = new JPanel();
		panelContent.add(contentPanelTop, BorderLayout.NORTH);

		JLabel lblGametimelabel = new JLabel("gameTimeLabel");
		contentPanelTop.add(lblGametimelabel);

		JPanel contentPanelCenter = new JPanel();
		panelContent.add(contentPanelCenter, BorderLayout.CENTER);
		contentPanelCenter.setLayout(null);

		JLabel label_1 = new JLabel("Heim");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_1.setBounds(37, 11, 89, 14);
		contentPanelCenter.add(label_1);

		JLabel label_2 = new JLabel("Gast");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_2.setBounds(298, 11, 89, 14);
		contentPanelCenter.add(label_2);

		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(37, 36, 86, 20);
		contentPanelCenter.add(textField);

		JLabel label_3 = new JLabel("Tore nach erster Halbzeit");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_3.setBounds(133, 39, 158, 14);
		contentPanelCenter.add(label_3);

		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(301, 36, 86, 20);
		contentPanelCenter.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(37, 67, 86, 20);
		contentPanelCenter.add(textField_2);

		JLabel label_4 = new JLabel("Tore nach zweiter Halbzeit");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_4.setBounds(133, 70, 158, 14);
		contentPanelCenter.add(label_4);

		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(301, 67, 86, 20);
		contentPanelCenter.add(textField_3);

		JLabel label_5 = new JLabel("Gab es eine Verl\u00E4ngerung?");
		label_5.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_5.setBounds(37, 98, 184, 23);
		contentPanelCenter.add(label_5);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(true);
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(227, 101, 21, 23);
		contentPanelCenter.add(checkBox);

		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(37, 132, 86, 20);
		contentPanelCenter.add(textField_4);

		JLabel label_6 = new JLabel("Tore nach Verl\u00E4ngerung");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_6.setBounds(133, 135, 158, 14);
		contentPanelCenter.add(label_6);

		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		textField_5.setBounds(301, 132, 86, 20);
		contentPanelCenter.add(textField_5);

		JLabel label_7 = new JLabel("Gab es Elfmeterschie\u00DFen?");
		label_7.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_7.setBounds(37, 163, 184, 23);
		contentPanelCenter.add(label_7);

		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(37, 197, 86, 20);
		contentPanelCenter.add(textField_6);

		JLabel label_8 = new JLabel("Tore nach Elfmeterschie\u00DFen");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_8.setBounds(133, 200, 158, 14);
		contentPanelCenter.add(label_8);

		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		checkBox_1.setBackground(Color.WHITE);
		checkBox_1.setBounds(227, 166, 21, 23);
		contentPanelCenter.add(checkBox_1);

		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(301, 197, 86, 20);
		contentPanelCenter.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setText("0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(37, 241, 86, 20);
		contentPanelCenter.add(textField_8);

		JLabel label_9 = new JLabel("Gelbe Karten");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_9.setBounds(133, 244, 158, 14);
		contentPanelCenter.add(label_9);

		textField_9 = new JTextField();
		textField_9.setText("0");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(301, 241, 86, 20);
		contentPanelCenter.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setText("0");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBounds(301, 272, 86, 20);
		contentPanelCenter.add(textField_10);

		JLabel label_10 = new JLabel("Rote Karten");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		label_10.setBounds(133, 275, 158, 14);
		contentPanelCenter.add(label_10);

		textField_11 = new JTextField();
		textField_11.setText("0");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textField_11.setColumns(10);
		textField_11.setBounds(37, 272, 86, 20);
		contentPanelCenter.add(textField_11);

		JButton btnSpeichern = new JButton("Speichern");
		getContentPane().add(btnSpeichern, BorderLayout.SOUTH);
		setSize(450, 461);

		Dimension windowSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - windowSize.width / 2, screenSize.height / 2 - windowSize.height / 2);
		
		reload();
	}
	
	private boolean selectTheRightList(Boolean ohneErgebnis) {
		/// the sql strings :D
		String sqlleft = "SELECT * FROM spiele WHERE heimmannschafthz IS NULL AND datumuhrzeit < DATE_SUB(NOW(), INTERVAL 3 HOUR) ORDER BY datumuhrzeit";
		String sqlright ="SELECT * FROM spiele WHERE spielbezeichnung NOT LIKE '%Gruppe%' AND heimmannschafthz IS NOT NULL ORDER BY datumuhrzeit";

		List<Match> matchright = new ArrayList<Match>();
		List<Match> matchleft = new ArrayList<Match>();
		try {
			Statement stmt = Main.mainConnection.getConnection().createStatement();
			ResultSet rs;
			
			if (ohneErgebnis == true) {
				rs = stmt.executeQuery(sqlleft);
			} else {
				rs = stmt.executeQuery(sqlright);
				if (rs == null) {
					rdbtnGelaufeneSpieleOhne.setSelected(true);
					return false;
				}
			}
			
			if (rs != null) {
				while(rs.next()) {
					
				}
			} else {
				JOptionPane.showMessageDialog(null, "Kein Spiel bedarf einer Eingabe.", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void reload() {
		
	}
}
