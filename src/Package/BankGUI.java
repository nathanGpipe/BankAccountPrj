package Package;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BankGUI extends JFrame implements ItemListener {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;
	private BankModel model;
	private JButton cadd, cdelete, cupdate, cclear, sadd, sdelete, supdate,
	sclear, date;
    
	private DatePicker blah;
	private JTextField cnumber, cowner, cdate, cbalance, cfee, srate,
	sminBalance, snumber, sowner, sdate, sbalance;
    
	private JPanel savings, checkings, buttons, cards, savingsW, savingsC,
	savingsE, checkingsW, checkingsC, checkingsE;
    
	private JLabel cnumberl, cownerl, cdatel, cbalancel, cfeel, sratel,
	sminBalancel, snumberl, sownerl, sdatel, sbalancel;
    
	private JTable table;
	private JPanel mainPanel;
	private JMenu fileMenu, sortMenu;
	private JMenuBar menuBar;
	private JMenuItem loadBinary, saveBinary, loadText, saveText,
	loadXML, saveXML, quit, sortNum, sortOwner, sortDate;
    
	private final String binaryFile = "./data.bin";
	private final String textFile = "./data.txt";
    
	final static String CHECKINGS = "Checkings Account";
	final static String SAVINGS = "Savings Accounts";

	public BankGUI() {
    	createAndShowGUI();
	}

	private void constructInputFields(Container pane) {
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
    	cdate = new JTextField(30);
    	cbalance = new JTextField(30);
    	cfee = new JTextField(30);

    	/***********************************************************************************************/
    	// savings text fields srate, sminBalance, snumber, sowner, sdate,
    	// sbalance
    	srate = new JTextField(30);
    	sminBalance = new JTextField(30);
    	snumber = new JTextField(30);
    	sowner = new JTextField(30);
     	sdate = new JTextField(30);
    	sbalance = new JTextField(30);

    	// buttons
    	cadd = new JButton("Add");
    	cdelete = new JButton("Delete");
    	cupdate = new JButton("Update");
    	cclear = new JButton("Clear");
    	date = new JButton("Date boys");

    	sadd = new JButton("Add");
    	sdelete = new JButton("Delete");
    	supdate = new JButton("Update");
    	sclear = new JButton("Clear");

    	date.addActionListener(listener);
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
    	savingsW.add(sdate);
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

    	blah = new DatePicker();
    	blah.addPopupListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	cdate.setText(blah.getFormattedDate());
            	blah.popupHide();
        	}
    	});
   	 
   	 
    	// west Checkings
    	checkingsW = new JPanel();
    	checkingsW.setLayout(new GridLayout(6, 1, 1, 1));
    	checkingsW.add(cdate);
    	checkingsW.add(cnumber);
    	checkingsW.add(cowner);
    	//checkingsW.add(cdate);
    	checkingsW.add(cbalance);
    	checkingsW.add(cfee);

    	// center Checkings
    	checkingsC = new JPanel();
    	checkingsC.setLayout(new GridLayout(6, 1, 1, 1));
    	checkingsC.add(cdatel);
    	checkingsC.add(cnumberl);
    	checkingsC.add(cownerl);
    	//checkingsC.add(cdatel);
    	checkingsC.add(cbalancel);
    	checkingsC.add(cfeel);

    	// east Checkings
    	checkingsE = new JPanel();
    	checkingsE.setLayout(new GridLayout(6, 1, 1, 1));
    	checkingsE.add(cadd);
    	checkingsE.add(cdelete);
    	checkingsE.add(cupdate);
    	checkingsE.add(cclear);

    	checkings.add(date, BorderLayout.NORTH);
    	checkings.add(checkingsW, BorderLayout.CENTER);
    	checkings.add(checkingsC, BorderLayout.WEST);
    	checkings.add(checkingsE, BorderLayout.EAST);

    	cards = new JPanel(new CardLayout());
    	cards.add(savings, SAVINGS);
    	cards.add(checkings, CHECKINGS);

    	pane.add(comboBoxPane, BorderLayout.PAGE_START);
    	pane.add(cards, BorderLayout.CENTER);

	}

	@Override
	public void itemStateChanged(ItemEvent evt) {
    	CardLayout cl = (CardLayout) (cards.getLayout());
    	cl.show(cards, (String) evt.getItem());
	}

	private void createAndShowGUI() {
    	ButtonListener listener = new ButtonListener();

    	mainPanel = new JPanel(new BorderLayout());
    	model = new BankModel();
    	table = new JTable(model);
    	JPanel tablePanel = new JPanel();

    	//
    	// Menu bar button setup
    	menuBar = new JMenuBar();
    	fileMenu = new JMenu("File");
    	sortMenu = new JMenu("Sort");

    	menuBar.add(fileMenu);
    	menuBar.add(sortMenu);

    	loadBinary = new JMenuItem("Load From Binary...");
    	saveBinary = new JMenuItem("Save As Binary...");
    	loadText = new JMenuItem("Load From Text...");
    	saveText = new JMenuItem("Save As Text...");
    	loadXML = new JMenuItem("Load From XML...");
    	saveXML = new JMenuItem("Save As XML...");
    	quit = new JMenuItem("Quit");

    	loadBinary.addActionListener(listener);
    	saveBinary.addActionListener(listener);
    	loadText.addActionListener(listener);
    	saveText.addActionListener(listener);
    	loadXML.addActionListener(listener);
    	saveXML.addActionListener(listener);
    	quit.addActionListener(listener);

    	sortNum = new JMenuItem("By Account Number");
    	sortOwner = new JMenuItem("By Account Owner");
    	sortDate = new JMenuItem("By Date Opened");

    	sortNum.addActionListener(listener);
    	sortOwner.addActionListener(listener);
    	sortDate.addActionListener(listener);

    	fileMenu.add(loadBinary);
    	fileMenu.add(saveBinary);
    	fileMenu.add(loadText);
    	fileMenu.add(saveText);
    	fileMenu.add(loadXML);
    	fileMenu.add(saveXML);
    	fileMenu.add(quit);

    	sortMenu.add(sortNum);
    	sortMenu.add(sortOwner);
    	sortMenu.add(sortDate);
   	 
    	setJMenuBar(menuBar);
    	//
    	//


    	tablePanel.add(new JScrollPane(table));
    	mainPanel.add(tablePanel, BorderLayout.NORTH);

    	// Create and set up the content pane.
    	JPanel cardPanel = new JPanel();
    	constructInputFields(cardPanel);
    	mainPanel.add(cardPanel, BorderLayout.SOUTH);
    	add(mainPanel);

    	// Display the window.
    	pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	setVisible(true);
	}

	public static void main(String[] args) {
    	BankGUI frame = new BankGUI();
	}

	private class ButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent event) {
        	if(event.getSource() == loadBinary) {
            	try {
                	model.readBinary(binaryFile);
            	} catch (ClassNotFoundException|IOException e) {
                	JOptionPane.showMessageDialog(null,
                        	"A file read error occured");
                	e.printStackTrace();
            	}
        	}
        	else if(event.getSource() == saveBinary) {
            	try {
                	model.writeBinary(binaryFile);
            	} catch (IOException e) {
                	JOptionPane.showMessageDialog(null,
                        	"A file write error occured");
                	e.printStackTrace();
            	}
        	}
        	else if(event.getSource() == loadText) {
            	try {
                	model.readText(textFile);
            	} catch (IOException e) {
                	JOptionPane.showMessageDialog(null,
                        	"A file read error occured");
                	e.printStackTrace();
            	}
        	}
        	else if(event.getSource() == saveText) {
            	try {
                	model.writeText(textFile);
           	} catch (IOException e) {
                	JOptionPane.showMessageDialog(null,
                        	"A file write error occured");
                	e.printStackTrace();
            	}
        	}
        	else if(event.getSource() == quit) {
            	saveText.doClick();
            	saveBinary.doClick();
            	System.exit(0);
        	}
        	else if(event.getSource() == cadd){
       		 //cnumber, cowner, cdate, cbalance, cfee
       		 int n = Integer.parseInt(cnumber.getText());//cnumber.getText();
       		 String owner = cowner.getText();
       		 String d = cdate.getText();
       		 double b = Double.parseDouble(cbalance.getText());
       		 double f = Double.parseDouble(cfee.getText());
       		 model.add(new CheckingAccount(n, d, owner, b, f));
       		 
        	}
        	else  if(event.getSource() == sadd){
       		 //srate,sminBalance, snumber, sowner, sdate, sbalance;
       		 int n = Integer.parseInt(snumber.getText());//cnumber.getText();
       		 String owner = sowner.getText();
       		 //String d = sdate.getText();
       		 double b = Double.parseDouble(sbalance.getText());
       		 double m = Double.parseDouble(sminBalance.getText());
       		 double i = Double.parseDouble(srate.getText());
       		 
       		 model.add(new SavingsAccount(n, "", owner, b, m, i));
       		 
        	}
        	else if (event.getSource() == date){
       		 blah.setDate(cdate.getText());
            	blah.popupShow(date);
        	}
    	}
	}

}




