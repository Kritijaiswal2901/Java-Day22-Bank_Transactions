import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final int numAccounts;
    private final int[] balances;
    private final Lock[] locks;

    public Bank(int numAccounts, int initialBalance) {
        this.numAccounts = numAccounts;
        this.balances = new int[numAccounts];
        this.locks = new Lock[numAccounts];

        for (int i = 0; i < numAccounts; i++) {
            balances[i] = initialBalance;
            locks[i] = new ReentrantLock();
        }
    }

    public void deposit(int accountNumber, int amount) {
        locks[accountNumber].lock();
        try {
            balances[accountNumber] += amount;
            System.out.println("Deposited " + amount + " into Account " + accountNumber +
                    ". New balance: " + balances[accountNumber]);
        } finally {
            locks[accountNumber].unlock();
        }
    }

    public void withdraw(int accountNumber, int amount) {
        locks[accountNumber].lock();
        try {
            if (balances[accountNumber] >= amount) {
                balances[accountNumber] -= amount;
                System.out.println("Withdrawn " + amount + " from Account " + accountNumber +
                        ". New balance: " + balances[accountNumber]);
            } else {
                System.out.println("Insufficient funds for withdrawal from Account " + accountNumber);
            }
        } finally {
            locks[accountNumber].unlock();
        }
    }

    public int getBalance(int accountNumber) {
        return balances[accountNumber];
    }

    public int getNumAccounts() {
        return numAccounts;
    }

}

