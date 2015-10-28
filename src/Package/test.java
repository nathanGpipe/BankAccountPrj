package Package;

import javax.swing.*;

public class test {
	public static void main(String[] args) {
		BankModel model = new BankModel();
		JFrame frame = new JFrame();
		JTable table = new JTable(model);
		JPanel panel = new JPanel();
		panel.add(table);
		frame.add(panel);
		
		frame.setSize(360, 240);
		frame.setVisible(true);
		
		model.add(new SavingsAccount(123,"","",1.1,1.1,1.1));
		model.add(new SavingsAccount(123,"","1345",1.1,1.1,1.1));
		model.add(new SavingsAccount(123,"","",1.1,1.1,1.1));
		
	}

}
