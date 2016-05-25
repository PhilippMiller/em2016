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
	private JTable table_A;
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
	private JTable table_B;
	private JTable table_C;
	private JTable table_D;
	private JTable table_E;
	private JTable table_F;

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

		table_A = new JTable();
		table_A.setShowVerticalLines(false);
		table_A.setRowSelectionAllowed(false);
		table_A.setShowGrid(false);
		table_A.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		table_A.setModel(DatabaseManagement.groupRanking("A"));
		scrollPane.setViewportView(table_A);
		
		JLabel lblGruppeA = new JLabel("Gruppe A");
		lblGruppeA.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		lblGruppeA.setBounds(10, 11, 91, 14);
		contentPane.add(lblGruppeA);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 152, 711, 85);
		contentPane.add(scrollPane_1);
		
		table_B = new JTable();
		table_B.setShowVerticalLines(false);
		table_B.setShowGrid(false);
		table_B.setRowSelectionAllowed(false);
		table_B.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table_B.setModel(DatabaseManagement.groupRanking("B"));
		scrollPane_1.setViewportView(table_B);
		
		lblGruppeB = new JLabel("Gruppe B");
		lblGruppeB.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeB.setBounds(10, 132, 91, 14);
		contentPane.add(lblGruppeB);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(10, 273, 711, 85);
		contentPane.add(scrollPane_2);
		
		table_C = new JTable();
		table_C.setShowVerticalLines(false);
		table_C.setShowGrid(false);
		table_C.setRowSelectionAllowed(false);
		table_C.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table_C.setModel(DatabaseManagement.groupRanking("C"));
		scrollPane_2.setViewportView(table_C);
		
		lblGruppeC = new JLabel("Gruppe C");
		lblGruppeC.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeC.setBounds(10, 253, 91, 14);
		contentPane.add(lblGruppeC);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 394, 711, 85);
		contentPane.add(scrollPane_3);
		
		table_D = new JTable();
		table_D.setShowVerticalLines(false);
		table_D.setShowGrid(false);
		table_D.setRowSelectionAllowed(false);
		table_D.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table_D.setModel(DatabaseManagement.groupRanking("D"));
		scrollPane_3.setViewportView(table_D);
		
		lblGruppeD = new JLabel("Gruppe D");
		lblGruppeD.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeD.setBounds(10, 374, 91, 14);
		contentPane.add(lblGruppeD);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_4.setBounds(10, 515, 711, 85);
		contentPane.add(scrollPane_4);
		
		table_E = new JTable();
		table_E.setShowVerticalLines(false);
		table_E.setShowGrid(false);
		table_E.setRowSelectionAllowed(false);
		table_E.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table_E.setModel(DatabaseManagement.groupRanking("E"));
		scrollPane_4.setViewportView(table_E);
		
		lblGruppeE = new JLabel("Gruppe E");
		lblGruppeE.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeE.setBounds(10, 495, 91, 14);
		contentPane.add(lblGruppeE);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_5.setBounds(10, 636, 711, 85);
		contentPane.add(scrollPane_5);
		
		table_F = new JTable();
		table_F.setShowVerticalLines(false);
		table_F.setShowGrid(false);
		table_F.setRowSelectionAllowed(false);
		table_F.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table_F.setModel(DatabaseManagement.groupRanking("F"));
		scrollPane_5.setViewportView(table_F);
		
		lblGruppeF = new JLabel("Gruppe F");
		lblGruppeF.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeF.setBounds(10, 616, 91, 14);
		contentPane.add(lblGruppeF);

	}
}
