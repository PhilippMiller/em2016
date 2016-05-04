package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class SpielplanEM2016Frame extends JDialog {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpielplanEM2016Frame frame = new SpielplanEM2016Frame(new MainFrame(false));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpielplanEM2016Frame(JFrame parent) {
		super(parent);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 860);
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1006, 813);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		table.setModel(DatabaseManagement.implementData());	
		scrollPane.setViewportView(table);
	}

}
