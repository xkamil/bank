package banking;

/**
 *
 * Private Variables:<br>
 * {@link #accountNumber}: Long<br>
 * {@link #bank}: Bank<br>
 */
public class Transaction implements TransactionInterface{
	private Long accountNumber;
	private Bank bank;
	private Account account;

	/**
	 *
	 * @param bank
	 *            The bank where the account is housed.
	 * @param accountNumber
	 *            The customer's account number.
	 * @param attemptedPin
	 *            The PIN entered by the customer.
	 * @throws Exception
	 *             Account validation failed.
	 */
	public Transaction(Bank bank, Long accountNumber, int attemptedPin) throws Exception {
		this.bank = bank;
		this.account = bank.getAccount(accountNumber);
		this.accountNumber = accountNumber;
		boolean authenticated = bank.authenticateUser(accountNumber, attemptedPin);
		if(!authenticated){
			throw new Exception("Account validation failed.");
		}
	}

	public double getBalance() {
        return account.getBalance();
	}

	public void credit(double amount) {
		account.creditAccount(amount);
	}

	public boolean debit(double amount) {
		return account.debitAccount(amount);
	}
}
