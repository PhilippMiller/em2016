package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.database.DatabaseManagement;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OffeneErgebnisseFrame extends JDialog {

	private JPanel contentPane;
	protected OffeneErgebnisseFrame classContext;
	private JTable tableOpenGames;
	private JPanel panel;
	private JButton btnOpenGame;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JButton btnSchlieen;

	/**
	 * Create the dialog.
	 */
	public OffeneErgebnisseFrame(JFrame parent) {
		
		super(parent);
		
		classContext = this;
		
		setResizable(false);
		setModal(true);
		setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		setType(Type.NORMAL);
		setTitle("Tippspiel Admin - Tool | Offene Ergebnis");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SpielplanEM2016Frame.class.getResource("/whs/gdi2/tippspiel/data/em_Logo.png")));
		setBackground(Config.getGuiColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(screenSize.width/2 - 273, screenSize.height/2 - 235, 474, 470);
		
		InitializeGui();
		InitializeEvents();
		reload();
	}
	
	public void InitializeGui() {
		contentPane = new JPanel();
		contentPane.setBackground(Config.getGuiColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(Config.getGuiColor());
		
		lblNewLabel = new JLabel("Liste aller noch unvollst\u00E4ndigen Spielergebnissen:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font(Config.getFont(), Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tableOpenGames = new JTable();
		tableOpenGames.setShowGrid(false);
		tableOpenGames.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		tableOpenGames.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableOpenGames);
		
		panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(Config.getGuiColor());
		
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		btnOpenGame = new JButton("Ausgew\u00E4hltes Spiel \u00F6ffnen");
		btnOpenGame.setBackground(Config.getGuiColor());
		panel.add(btnOpenGame);
		
		btnSchlieen = new JButton("Schlie\u00DFen");
		btnSchlieen.setBackground(Config.getGuiColor());
		panel.add(btnSchlieen);
	}
	
	public void InitializeEvents() {
		btnOpenGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(classContext.tableOpenGames.getSelectedRow() != -1) {
					int selectedIndex = classContext.tableOpenGames.convertRowIndexToModel(classContext.tableOpenGames.getSelectedRow());
					TableModel model = classContext.tableOpenGames.getModel();
					int spielid = Integer.parseInt((String) model.getValueAt(selectedIndex, 0));
					
					ErgebnisFrame ergebnis = new ErgebnisFrame(classContext, spielid);
					ergebnis.setVisible(true);
					ergebnis.setModal(true);
					
				}
				else {
					JOptionPane.showMessageDialog(classContext, "Bitte wähle ein Spiel aus.");
				}
			}
		});
	}
	
	public void reload() {
		tableOpenGames.setModel(DatabaseManagement.getGameIncompleteGamesModel());
	}
}
