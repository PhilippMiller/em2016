package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.database.models.KnockOutStageCalculator;
import whs.gdi2.tippspiel.database.models.TableRankingObject;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class GruppenRankingEM2016Frame extends JDialog {

	private JPanel contentPane;
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
	
	private JScrollPane scrollPane_grpA;
	private JLabel lblGruppeA;
	private JTable table_A;
	private JScrollPane scrollPane_grpB;
	private JLabel lblGruppeB;
	private JTable table_B;
	private JScrollPane scrollPane_grpC;
	private JLabel lblGruppeC;
	private JTable table_C;
	private JScrollPane scrollPane_grpD;
	private JLabel lblGruppeD;
	private JTable table_D;
	private JScrollPane scrollPane_grpE;
	private JLabel lblGruppeE;
	private JTable table_E;
	private JScrollPane scrollPane_grpF;
	private JLabel lblGruppeF;
	private JTable table_F;

	public GruppenRankingEM2016Frame(JFrame parent) {

		super(parent);
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | EM2016 Spieler - Ranking");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 737, 765);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane_grpA = new JScrollPane();
		scrollPane_grpA.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpA.setBounds(10, 31, 711, 85);
		contentPane.add(scrollPane_grpA);

		table_A = new JTable();
		table_A.setShowVerticalLines(false);
		table_A.setRowSelectionAllowed(false);
		table_A.setShowGrid(false);
		table_A.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		TableRankingObject tro_grpA = DatabaseManagement.groupRanking("A");
		table_A.setModel(tro_grpA.getDtm());
		scrollPane_grpA.setViewportView(table_A);
		
		lblGruppeA = new JLabel("Gruppe A");
		lblGruppeA.setFont(new Font(Config.getFont(), Font.PLAIN, 14));
		lblGruppeA.setBounds(10, 11, 91, 14);
		contentPane.add(lblGruppeA);
		
		scrollPane_grpB = new JScrollPane();
		scrollPane_grpB.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpB.setBounds(10, 152, 711, 85);
		contentPane.add(scrollPane_grpB);
		
		table_B = new JTable();
		table_B.setShowVerticalLines(false);
		table_B.setShowGrid(false);
		table_B.setRowSelectionAllowed(false);
		table_B.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		TableRankingObject tro_grpB = DatabaseManagement.groupRanking("B");
		table_B.setModel(tro_grpB.getDtm());
		scrollPane_grpB.setViewportView(table_B);
		
		lblGruppeB = new JLabel("Gruppe B");
		lblGruppeB.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeB.setBounds(10, 132, 91, 14);
		contentPane.add(lblGruppeB);
		
		scrollPane_grpC = new JScrollPane();
		scrollPane_grpC.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpC.setBounds(10, 273, 711, 85);
		contentPane.add(scrollPane_grpC);
		
		table_C = new JTable();
		table_C.setShowVerticalLines(false);
		table_C.setShowGrid(false);
		table_C.setRowSelectionAllowed(false);
		table_C.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		TableRankingObject tro_grpC = DatabaseManagement.groupRanking("C");
		table_C.setModel(tro_grpC.getDtm());
		scrollPane_grpC.setViewportView(table_C);
		
		lblGruppeC = new JLabel("Gruppe C");
		lblGruppeC.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeC.setBounds(10, 253, 91, 14);
		contentPane.add(lblGruppeC);
		
		scrollPane_grpD = new JScrollPane();
		scrollPane_grpD.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpD.setBounds(10, 394, 711, 85);
		contentPane.add(scrollPane_grpD);
		
		table_D = new JTable();
		table_D.setShowVerticalLines(false);
		table_D.setShowGrid(false);
		table_D.setRowSelectionAllowed(false);
		table_D.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		TableRankingObject tro_grpD = DatabaseManagement.groupRanking("D");
		table_D.setModel(tro_grpD.getDtm());
		scrollPane_grpD.setViewportView(table_D);
		
		lblGruppeD = new JLabel("Gruppe D");
		lblGruppeD.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeD.setBounds(10, 374, 91, 14);
		contentPane.add(lblGruppeD);
		
		scrollPane_grpE = new JScrollPane();
		scrollPane_grpE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpE.setBounds(10, 515, 711, 85);
		contentPane.add(scrollPane_grpE);
		
		table_E = new JTable();
		table_E.setShowVerticalLines(false);
		table_E.setShowGrid(false);
		table_E.setRowSelectionAllowed(false);
		table_E.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		TableRankingObject tro_grpE = DatabaseManagement.groupRanking("E");
		table_E.setModel(tro_grpE.getDtm());
		scrollPane_grpE.setViewportView(table_E);
		
		lblGruppeE = new JLabel("Gruppe E");
		lblGruppeE.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeE.setBounds(10, 495, 91, 14);
		contentPane.add(lblGruppeE);
		
		scrollPane_grpF = new JScrollPane();
		scrollPane_grpF.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_grpF.setBounds(10, 636, 711, 85);
		contentPane.add(scrollPane_grpF);
		
		table_F = new JTable();
		table_F.setShowVerticalLines(false);
		table_F.setShowGrid(false);
		table_F.setRowSelectionAllowed(false);
		table_F.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		TableRankingObject tro_grpF = DatabaseManagement.groupRanking("F");
		table_F.setModel(tro_grpF.getDtm());
		scrollPane_grpF.setViewportView(table_F);
		
		lblGruppeF = new JLabel("Gruppe F");
		lblGruppeF.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblGruppeF.setBounds(10, 616, 91, 14);
		contentPane.add(lblGruppeF);
		
		ArrayList<TableRankingObject> allTros = new ArrayList<TableRankingObject>();
		allTros.add(tro_grpA);
		allTros.add(tro_grpB);
		allTros.add(tro_grpC);
		allTros.add(tro_grpD);
		allTros.add(tro_grpE);
		allTros.add(tro_grpF);
		KnockOutStageCalculator.RoundOfSixteen(allTros);
		DatabaseManagement.knockOutStages();
		

	}
}
