package Package;

public class CheckingAccount extends Account {
	private static final long serialVersionUID = 1L;
	private double monthlyFee;

	public CheckingAccount(int number, String dateOpened, String accountOwner,
			double balance, double fee) {
		super(number, dateOpened, accountOwner, balance);
		monthlyFee = fee;
	}

	public void setFee(double fee) {
		monthlyFee = fee;
	}

	public double getFee() {
		return monthlyFee;
	}

	public boolean equals(Object o) {
		return this.getNumber() == ((Account) o).getNumber();
	}

	public String toString() {
		return "c:" + super.getNumber() + ":" + super.getDate() + ":"
				+ super.getOwner() + ":" + super.getBalance() + ":"
				+ monthlyFee;
	}

}
