package Package;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OptionsPanel extends JFrame implements ItemListener {

	private JButton cadd, cdelete, cupdate, cclear, sadd, sdelete, supdate,
			sclear;
	private JTextField cnumber, cowner, cdate, cbalance, cfee, srate,
			sminBalance, snumber, sowner, sdate, sbalance;
	private JPanel savings, checkings, buttons, cards, savingsW, savingsC,
			savingsE, checkingsW, checkingsC, checkingsE;
	private JLabel cnumberl, cownerl, cdatel, cbalancel, cfeel, sratel,
			sminBalancel, snumberl, sownerl, sdatel, sbalancel;
	private static JTable table;
	private static JPanel borderLayout;
	private static JMenu file, sort;
	private static JMenuBar menuBar;
	private static JMenuItem loadBinary, saveBinary, loadText, saveText,
			loadXML, saveXML, quit, sortNum, sortOwner, sortDate;

	final static String CHECKINGS = "Checkings Account";
	final static String SAVINGS = "Savings Accounts";

	public void addComponentToPane(Container pane) {
		ButtonListener listener = new ButtonListener();
		// Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(); // use FlowLayout
		String comboBoxItems[] = { SAVINGS, CHECKINGS };
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);

		setLayout(new BorderLayout());

		/***********************************************************************************************/
		// checkings text fields cnumber, cowner, cdate, cbalance, cfee
		cnumber = new JTextField(30);
		cowner = new JTextField(30);
		// cdate = new
		cbalance = new JTextField(30);
		cfee = new JTextField(30);

		/***********************************************************************************************/
		// savings text fields srate, sminBalance, snumber, sowner, sdate,
		// sbalance
		srate = new JTextField(30);
		sminBalance = new JTextField(30);
		snumber = new JTextField(30);
		sowner = new JTextField(30);
		// sdate = new
		sbalance = new JTextField(30);

		// buttons
		cadd = new JButton("Add");
		cdelete = new JButton("Delete");
		cupdate = new JButton("Update");
		cclear = new JButton("Clear");

		sadd = new JButton("Add");
		sdelete = new JButton("Delete");
		supdate = new JButton("Update");
		sclear = new JButton("Clear");

		cadd.addActionListener(listener);
		cdelete.addActionListener(listener);
		cupdate.addActionListener(listener);
		cclear.addActionListener(listener);
		sadd.addActionListener(listener);
		sdelete.addActionListener(listener);
		supdate.addActionListener(listener);
		sclear.addActionListener(listener);

		// //////// Labels
		cnumberl = new JLabel("Account Number:");
		cownerl = new JLabel("Account Owner:");
		cdatel = new JLabel("Date Opened:");
		cbalancel = new JLabel("Account Balance:");
		cfeel = new JLabel("Monthly Fee:");

		snumberl = new JLabel("Account Number:");
		sownerl = new JLabel("Account Owner:");
		sdatel = new JLabel("Date Opened:");
		sbalancel = new JLabel("Account Balance:");
		sratel = new JLabel("Interest Rate:");
		sminBalancel = new JLabel("Minimum Balance:");

		// ///////// table and panel

		savings = new JPanel(new BorderLayout());
		// west savings
		savingsW = new JPanel();
		savingsW.setLayout(new GridLayout(6, 1, 1, 1));
		savingsW.add(snumber);
		savingsW.add(sowner);
		// savingsW.add(sdate);
		savingsW.add(sbalance);
		savingsW.add(srate);
		savingsW.add(sminBalance);

		// center savings
		savingsC = new JPanel();
		savingsC.setLayout(new GridLayout(6, 1, 1, 1));
		savingsC.add(snumberl);
		savingsC.add(sownerl);
		savingsC.add(sdatel);
		savingsC.add(sbalancel);
		savingsC.add(sratel);
		savingsC.add(sminBalancel);

		// east savings
		savingsE = new JPanel();
		savingsE.setLayout(new GridLayout(6, 1, 1, 1));
		savingsE.add(sadd);
		savingsE.add(sdelete);
		savingsE.add(supdate);
		savingsE.add(sclear);

		savings.add(savingsW, BorderLayout.CENTER);
		savings.add(savingsC, BorderLayout.WEST);
		savings.add(savingsE, BorderLayout.EAST);

		checkings = new JPanel(new BorderLayout());

		// west Checkings
		checkingsW = new JPanel();
		checkingsW.setLayout(new GridLayout(6, 1, 1, 1));
		checkingsW.add(cnumber);
		checkingsW.add(cowner);
		// checkingsW.add(cdate);
		checkingsW.add(cbalance);
		checkingsW.add(cfee);

		// center Checkings
		checkingsC = new JPanel();
		checkingsC.setLayout(new GridLayout(6, 1, 1, 1));
		checkingsC.add(cnumberl);
		checkingsC.add(cownerl);
		checkingsC.add(cdatel);
		checkingsC.add(cbalancel);
		checkingsC.add(cfeel);

		// east Checkings
		checkingsE = new JPanel();
		checkingsE.setLayout(new GridLayout(6, 1, 1, 1));
		checkingsE.add(cadd);
		checkingsE.add(cdelete);
		checkingsE.add(cupdate);
		checkingsE.add(cclear);

		checkings.add(checkingsW, BorderLayout.CENTER);
		checkings.add(checkingsC, BorderLayout.WEST);
		checkings.add(checkingsE, BorderLayout.EAST);

		cards = new JPanel(new CardLayout());
		cards.add(savings, SAVINGS);
		cards.add(checkings, CHECKINGS);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

	private static void createAndShowGUI() {
		menuBar = new JMenuBar();
		file = new JMenu("File");
		sort = new JMenu("Sort");

		menuBar.add(file);
		menuBar.add(sort);

		loadBinary = new JMenuItem("Load From Binary...");
		saveBinary = new JMenuItem("Save As Binary...");
		loadText = new JMenuItem("Load From Text...");
		saveText = new JMenuItem("Save As Text...");
		loadXML = new JMenuItem("Load From XML...");
		saveXML = new JMenuItem("Save As XML...");
		quit = new JMenuItem("Quit");

		// loadBinary.addActionListener(listener);
		// saveBinary.addActionListener(listener);
		// loadText.addActionListener(listener);
		// saveText.addActionListener(listener);
		// loadXML.addActionListener(listener);
		// saveXML.addActionListener(listener);
		// quit.addActionListener(listener);

		sortNum = new JMenuItem("By Account Number");
		sortOwner = new JMenuItem("By Account Owner");
		sortDate = new JMenuItem("By Date Opened");

		// sortNum.addActionListener(listener);
		// sortOwner.addActionListener(listener);
		// sortDate.addActionListener(listener);

		file.add(loadBinary);
		file.add(saveBinary);
		file.add(loadText);
		file.add(saveText);
		file.add(loadXML);
		file.add(saveXML);
		file.add(quit);

		sort.add(sortNum);
		sort.add(sortOwner);
		sort.add(sortDate);

		BankModel model = new BankModel();
		JTable table = new JTable(model);
		JPanel panel = new JPanel();
		panel.add(table);

		borderLayout = new JPanel(new BorderLayout());

		borderLayout.add(panel, BorderLayout.NORTH);
		// Create and set up the window.
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menuBar);
		// Create and set up the content pane.
		OptionsPanel demo = new OptionsPanel();
		JPanel whatever = new JPanel();
		demo.addComponentToPane(whatever);
		borderLayout.add(whatever, BorderLayout.SOUTH);
		frame.add(borderLayout);
		
		model.add(new SavingsAccount(123,"","",1.1,1.1,1.1));
		model.add(new SavingsAccount(123,"","1345",1.1,1.1,1.1));
		model.add(new SavingsAccount(123,"","",1.1,1.1,1.1));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

}
