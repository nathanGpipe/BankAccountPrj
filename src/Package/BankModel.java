package Package;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BankModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Account> acts = new ArrayList<Account>();
	private String[] columnNames = {"Account Number","Date Created","Owner","Balance"};


	@Override
	public int getRowCount() {
		return acts.size();
	}

	// The table will always only show 4 columns
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:	return acts.get(rowIndex).getNumber();
		case 1:	return acts.get(rowIndex).getDate();
		case 2:	return acts.get(rowIndex).getOwner();
		case 3:	return acts.get(rowIndex).getBalance();
		default: return null;
		}
	}

	
	@Override
	public String getColumnName(int column) {
	    return columnNames[column];
	  }
	
	public int findIndexOf(Object o) {
		return acts.indexOf(o);
	}

	public void add(Account a) {
		if(acts.add(a))
			fireTableDataChanged();
	}

	public void remove(int index) {
		acts.remove(index);
		fireTableRowsDeleted(index, index);
	}

	public void remove(Account a) {
		if(acts.remove(a))
			fireTableDataChanged();
	}

	public void clear() {
		acts.clear();
		fireTableDataChanged();
	}

	public void update(int row, int col) {
		
		fireTableCellUpdated(row, col);
	}


	/*******************************************************************
	 * Accesses a specified file, and reads all serialized(wrote) User 
	 * objects on it. The ArrayList bankData contains all read Users. 
	 * bankData is first copied, then cleared to make sure no important 
	 * data is lost. If an error occurs while reading bankData is 
	 * cleared again, and restored with the copy.
	 * 
	 * @param file
	 * @throws IOException
	 ******************************************************************/
	public void readBinary(String file) throws IOException, ClassNotFoundException {
		ArrayList<Account> tempData = new ArrayList<Account>(acts);
		acts.clear();

		// This try/catch is where the reading actually takes place.
		ObjectInputStream readObj;
		try (FileInputStream fis = new FileInputStream(file);) {

			readObj = new ObjectInputStream(fis);
			while (true) {
				acts.add((Account) readObj.readObject());
			}
		} catch (EOFException expected) {
			// Expected: breaks the loop
		} catch (ClassNotFoundException|IOException e) {
			// Clears anything that may have been written to acts and resets it
			// with tempData.
			// If this exception occurs, it means you're probably reading
			// the wrong file
			acts.clear();
			acts = tempData;
			throw e;
		}
		// Print out what's been read.
		System.out.println("--------------------");
		for (Account a : acts) {
			System.out.println(a.toString());
		}
	}

	/*******************************************************************
	 * Serializes(writes) the information for all User objects in bankData into
	 * a bin file. A students money, classroom, and id is kept inside it's own
	 * Student object. bankData is an ArrayList of User objects to actually
	 * access students bank information.
	 * 
	 * This method will work with any Object type.
	 *
	 * @param file
	 * @param data
	 * @throws IOException
	 ******************************************************************/
	public void writeBinary(String file) throws IOException {

		try (FileOutputStream fos = new FileOutputStream(file)) {
			// Loops through the arraylist of accounts, and writes them
			// to wherever file points
			ObjectOutputStream writeObj = new ObjectOutputStream(fos);
			for (Object o : acts) {
				writeObj.writeObject(o);
			}
			writeObj.close();
		}
	}


	public void readText(String file) throws FileNotFoundException {
		ArrayList<Account> tempData = new ArrayList<Account>(acts);
		acts.clear();
		try {
			// Scan a line in file, then split it into a String[]
			Scanner fileReader = new Scanner(new File(file));
			String[] contents;
			while (fileReader.hasNextLine()) {
				contents = fileReader.nextLine().split(":");
				// checks if this is checking account data or savings data
				if (contents[0].equals("c")) {
					// contents is a checking account
					acts.add(new CheckingAccount(Integer.parseInt(contents[1]),
							contents[2], contents[3], Double
							.parseDouble(contents[4]), Double
							.parseDouble(contents[5])));
				} else if (contents[0].equals("s")) {
					// contents is a savings account
					acts.add(new SavingsAccount(Integer.parseInt(contents[1]),
							contents[2], contents[3], Double
							.parseDouble(contents[4]), Double
							.parseDouble(contents[5]), Double
							.parseDouble(contents[6])));
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			acts = tempData;
			throw e;
		}
	}


	public void writeText(String file) throws IOException {
		// Write the values of all of acts accounts using their toString
		// method
		PrintWriter out = null;
		out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for (Account a : acts)
			out.println(a.toString());
		out.close();
	}
}
