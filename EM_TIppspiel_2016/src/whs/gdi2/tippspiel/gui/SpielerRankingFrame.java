package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.Statement;

public class SpielerRankingFrame extends JDialog {

	private JPanel contentPane;
	private JTable table;
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
	private JPanel panel;
	private JLabel lblTipperRanglisteStand;
	private JLabel lblDatum;

	public SpielerRankingFrame(JFrame parent) {

		super(parent);
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | EM2016 Spieler - Ranking");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SpielplanFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblTipperRanglisteStand = new JLabel("Tipper Rangliste. Stand:");
		panel.add(lblTipperRanglisteStand);
		
		lblDatum = new JLabel("datum");
		panel.add(lblDatum);
		
		reload();
	}
	
	public void reload() {
		table.setModel(DatabaseManagement.playerRanking());
		try {
			Statement statement1 = Main.mainConnection.getConnection().createStatement();
			
			ResultSet daters = statement1.executeQuery("SELECT datum FROM ranking WHERE datum < now() ORDER BY datum DESC LIMIT 1");
			if(daters.next()) {
				lblDatum.setText(daters.getString("datum"));
			}
		}
		catch(Exception e) {
			Log.error("Cannot refresh frame");
		}
	}
}
