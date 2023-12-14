import java.util.Random;

public class Customer extends Thread {
    private static final Random random = new Random();
    private final int customerId;
    private final Bank bank;
    private final int numTransactions;

    public Customer(int customerId, Bank bank, int numTransactions) {
        this.customerId = customerId;
        this.bank = bank;
        this.numTransactions = numTransactions;
    }

    @Override
    public void run() {
        for (int i = 0; i < numTransactions; i++) {
            int accountNumber = random.nextInt(bank.getNumAccounts());
            int amount = random.nextInt(100) + 1;

            if (random.nextBoolean()) {
                bank.deposit(accountNumber, amount);
            } else {
                bank.withdraw(accountNumber, amount);
            }

            try {
                Thread.sleep(random.nextInt(10)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

