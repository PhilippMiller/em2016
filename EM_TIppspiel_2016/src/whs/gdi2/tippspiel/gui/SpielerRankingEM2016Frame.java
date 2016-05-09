package whs.gdi2.tippspiel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
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

public class SpielerRankingEM2016Frame extends JDialog {

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

	public SpielerRankingEM2016Frame(JFrame parent) {

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
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 15, 350, 381);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		table.setModel(DatabaseManagement.playerRanking());
		scrollPane.setViewportView(table);

		button = new JButton("<<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielerRankingEM2016Frame.this.dispose();
				SpielerRankingEM2016Frame sremframe = new SpielerRankingEM2016Frame(parent);
				sremframe.setVisible(true);
				Log.info("Button '<<<' clicked.");
			}
		});
		button.setFont(new Font(Config.getFont(), Font.PLAIN, 11));
		button.setBounds(45, 407, 89, 23);
		button.setBackground(Config.getGuiColor());
		contentPane.add(button);

		button_1 = new JButton(">>>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpielerRankingEM2016Frame.this.dispose();
				SpielerRankingEM2016Frame sremframe = new SpielerRankingEM2016Frame(parent);
				sremframe.setVisible(true);
				Log.info("Button '>>>' clicked.");
			}
		});
		button_1.setFont(new Font(Config.getFont(), Font.PLAIN, 11));
		button_1.setBounds(306, 407, 89, 23);
		button_1.setBackground(Config.getGuiColor());
		contentPane.add(button_1);

	}
}
