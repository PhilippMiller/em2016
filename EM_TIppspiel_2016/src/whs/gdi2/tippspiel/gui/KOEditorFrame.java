package whs.gdi2.tippspiel.gui;

import java.awt.Font;
import java.util.List;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.models.Match;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class KOEditorFrame extends JDialog {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	public KOEditorFrame() {
		setResizable(false);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("KO - Phasen Editor");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SpielplanFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		
		JLabel lblSpieleAuswahl = new JLabel("Spiele Auswahl");
		getContentPane().add(lblSpieleAuswahl, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		panel_1.add(comboBox, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Spiel ausw\u00E4hlen");
		panel_1.add(btnNewButton, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 200, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setBounds(220, 14, 10, 14);
		panel_2.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(230, 11, 200, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSpeichern = new JButton("Speichern");
		panel.add(btnSpeichern, BorderLayout.SOUTH);
		
		
	}

	public List<Match> getKnockOutGames() {
		try {
			List<Match> matches = null;
			String sql = "SELECT * FROM spiele WHERE spielbezeichnung NOT LIKE '%Gruppe%'";

			Statement stmt = Main.mainConnection.getConnection().createStatement();
			ResultSet rs;
			
			rs = stmt.executeQuery(sql);

			if (rs != null) {
				matches = new ArrayList<Match>();
				while (rs.next()) {
					Match match = new Match();
					match.setGameType(rs.getString("spielbezeichnung"));
					match.setGameId(rs.getInt("spieleid"));
					match.setHometeam(rs.getString("heimmannschaft"));
					match.setGuestteam(rs.getString("gastmannschaft"));
					match.setGameTime(rs.getTime("datumuhrzeit"));
					match.setGameDate(rs.getDate("datumuhrzeit"));
					matches.add(match);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Keine passenden KO-Spiele gefunden.\nBitte die Datenbank �berpr�fen!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			return matches;
		} catch (SQLException e) {

		}
		return null;
		
	}

}
