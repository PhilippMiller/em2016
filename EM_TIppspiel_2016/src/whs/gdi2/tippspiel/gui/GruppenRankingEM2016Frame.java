package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class GruppenRankingEM2016Frame extends JDialog {

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
	private JScrollPane scrollPane_1;
	private JLabel lblGruppeB;
	private JScrollPane scrollPane_2;
	private JLabel lblGruppeC;
	private JScrollPane scrollPane_3;
	private JLabel lblGruppeD;
	private JScrollPane scrollPane_4;
	private JLabel lblGruppeE;
	private JScrollPane scrollPane_5;
	private JLabel lblGruppeF;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;

	public GruppenRankingEM2016Frame(JFrame parent) {

		super(parent);
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | EM2016 Spieler - Ranking");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 737, 765);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 31, 711, 85);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		table.setModel(DatabaseManagement.groupRanking());
		scrollPane.setViewportView(table);
		
		JLabel lblGruppeA = new JLabel("Gruppe A");
		lblGruppeA.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		lblGruppeA.setBounds(10, 11, 91, 14);
		contentPane.add(lblGruppeA);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 152, 711, 85);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setShowVerticalLines(false);
		table_1.setShowGrid(false);
		table_1.setRowSelectionAllowed(false);
		table_1.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane_1.setViewportView(table_1);
		
		lblGruppeB = new JLabel("Gruppe B");
		lblGruppeB.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeB.setBounds(10, 132, 91, 14);
		contentPane.add(lblGruppeB);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(10, 273, 711, 85);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setShowVerticalLines(false);
		table_2.setShowGrid(false);
		table_2.setRowSelectionAllowed(false);
		table_2.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane_2.setViewportView(table_2);
		
		lblGruppeC = new JLabel("Gruppe C");
		lblGruppeC.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeC.setBounds(10, 253, 91, 14);
		contentPane.add(lblGruppeC);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 394, 711, 85);
		contentPane.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setShowVerticalLines(false);
		table_3.setShowGrid(false);
		table_3.setRowSelectionAllowed(false);
		table_3.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane_3.setViewportView(table_3);
		
		lblGruppeD = new JLabel("Gruppe D");
		lblGruppeD.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeD.setBounds(10, 374, 91, 14);
		contentPane.add(lblGruppeD);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_4.setBounds(10, 515, 711, 85);
		contentPane.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setShowVerticalLines(false);
		table_4.setShowGrid(false);
		table_4.setRowSelectionAllowed(false);
		table_4.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane_4.setViewportView(table_4);
		
		lblGruppeE = new JLabel("Gruppe E");
		lblGruppeE.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeE.setBounds(10, 495, 91, 14);
		contentPane.add(lblGruppeE);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_5.setBounds(10, 636, 711, 85);
		contentPane.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setShowVerticalLines(false);
		table_5.setShowGrid(false);
		table_5.setRowSelectionAllowed(false);
		table_5.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		scrollPane_5.setViewportView(table_5);
		
		lblGruppeF = new JLabel("Gruppe F");
		lblGruppeF.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeF.setBounds(10, 616, 91, 14);
		contentPane.add(lblGruppeF);

	}
}
