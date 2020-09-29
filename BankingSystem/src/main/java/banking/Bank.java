package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }

    public Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        long accountNumber = System.currentTimeMillis();
        Account acc = new CommercialAccount(company, accountNumber, pin, startingDeposit);
        return acc.getAccountNumber();
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        ConsumerAccount ca = new ConsumerAccount(person, System.currentTimeMillis(), pin, startingDeposit);
        accounts.put(ca.getAccountNumber(), ca);
        return ca.getAccountNumber();
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.validatePin(pin);
        } else {
            return false;
        }
    }

    public double getBalance(Long accountNumber) {
		Account account = accounts.get(accountNumber);
		if (account != null) {
			return account.getBalance();
		} else {
			return -1;
		}
    }

    public void credit(Long accountNumber, double amount) {
		Account account = accounts.get(accountNumber);
		if (account != null) {
			account.creditAccount(amount);
		}
    }

    public boolean debit(Long accountNumber, double amount) {
		Account account = accounts.get(accountNumber);
		return account.debitAccount(amount);
	}
}
