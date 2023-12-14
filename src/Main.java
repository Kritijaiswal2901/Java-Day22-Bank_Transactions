import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of accounts: ");
        int numAccounts = scanner.nextInt();

        System.out.print("Enter the initial balance: ");
        int initialBalance = scanner.nextInt();

        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();

        System.out.print("Enter the number of transactions per customer: ");
        int numTransactionsPerCustomer = scanner.nextInt();

        Bank bank = new Bank(numAccounts, initialBalance);

        // Create and start customer threads
        Customer[] customers = new Customer[numCustomers];
        for (int i = 0; i < numCustomers; i++) {
            customers[i] = new Customer(i, bank, numTransactionsPerCustomer);
            customers[i].start();
        }

        // Wait for all customer threads to finish
        for (int i = 0; i < numCustomers; i++) {
            try {
                customers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print final balances
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("Final balance for Account " + i + ": " + bank.getBalance(i));
        }
    }
}


