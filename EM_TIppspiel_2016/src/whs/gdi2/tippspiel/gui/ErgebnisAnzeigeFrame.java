package whs.gdi2.tippspiel.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ErgebnisAnzeigeFrame extends JDialog {

	private JPanel contentPane;
	private JTable table;

	public ErgebnisAnzeigeFrame(JFrame parent) {
		super(parent);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | Ergebnisse EM2016");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErgebnisAnzeigeFrame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table.setModel(DatabaseManagement.implementMatchScheduleWithScores());	
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
	}

}
