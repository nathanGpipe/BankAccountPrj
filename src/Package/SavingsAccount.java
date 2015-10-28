package Package;

public class SavingsAccount extends Account {

	private static final long serialVersionUID = 1L;
	private double minBalance;
	private double interestRate;

	public SavingsAccount(int number, String dateOpened, String accountOwner,
			double balance, double minBal, double interest) {
		super(number, dateOpened, accountOwner, balance);
		minBalance = minBal;
		interestRate = interest;
	}

	public void setminBalance(double min) {
		minBalance = min;
	}

	public void setinterestRate(double rate) {
		interestRate = rate;
	}

	public double getminBalance() {
		return minBalance;
	}

	public double getinterestRate() {
		return interestRate;
	}

	public String toString() {
		return "s:" + super.getNumber() + ":" + super.getDate() + ":"
				+ super.getOwner() + ":" + super.getBalance() + ":"
				+ minBalance + ":" + interestRate;
	}
}
