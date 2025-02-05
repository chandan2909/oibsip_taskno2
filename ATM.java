import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private String timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    @Override
    public String toString() {
        return String.format("%s: Rs.%.2f - %s", type, amount, timestamp);
    }
}

class Account {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }

    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transaction history available.");
            return;
        }
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        balance -= amount;
        addTransaction("Withdrawal", amount);
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
    }

    public boolean transfer(Account recipient, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        balance -= amount;
        recipient.deposit(amount);
        addTransaction("Transfer to " + recipient.getUserId(), amount);
        return true;
    }
}

public class ATM {
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Account currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize some sample accounts
        initializeAccounts();

        while (true) {
            if (currentAccount == null) {
                if (!login()) {
                    continue;
                }
            }

            displayMenu();
            int choice = scanner.nextInt();
            performOperation(choice);

            if (choice == 5) {
                break;
            }
        }
        scanner.close();
    }

    private static void initializeAccounts() {
        // Add some sample accounts (userId, pin, initial balance)
        accounts.put("user1", new Account("user1", "1234", 1000.0));
        accounts.put("user2", new Account("user2", "5678", 2000.0));
    }

    private static boolean login() {
        System.out.print("\nEnter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        Account account = accounts.get(userId);
        if (account != null && account.getPin().equals(pin)) {
            currentAccount = account;
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid User ID or PIN!");
            return false;
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void performOperation(int choice) {
        switch (choice) {
            case 1:
                currentAccount.showTransactionHistory();
                break;

            case 2:
                System.out.print("Enter amount to withdraw: Rs.");
                double withdrawAmount = scanner.nextDouble();
                if (currentAccount.withdraw(withdrawAmount)) {
                    System.out.printf("Successfully withdrew Rs.%.2f\n", withdrawAmount);
                    System.out.printf("Current balance: Rs.%.2f\n", currentAccount.getBalance());
                }
                break;

            case 3:
                System.out.print("Enter amount to deposit: Rs.");
                double depositAmount = scanner.nextDouble();
                currentAccount.deposit(depositAmount);
                System.out.printf("Successfully deposited Rs.%.2f\n", depositAmount);
                System.out.printf("Current balance: Rs.%.2f\n", currentAccount.getBalance());
                break;

            case 4:
                System.out.print("Enter recipient's User ID: ");
                String recipientId = scanner.next();
                Account recipient = accounts.get(recipientId);
                if (recipient == null) {
                    System.out.println("Recipient not found!");
                    break;
                }
                System.out.print("Enter amount to transfer: Rs.");
                double transferAmount = scanner.nextDouble();
                if (currentAccount.transfer(recipient, transferAmount)) {
                    System.out.printf("Successfully transferred Rs.%.2f to %s\n", 
                        transferAmount, recipientId);
                    System.out.printf("Current balance: Rs.%.2f\n", currentAccount.getBalance());
                }
                break;

            case 5:
                System.out.println("Thank you for using our ATM. Goodbye!");
                currentAccount = null;
                break;

            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }
}
