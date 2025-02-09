import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited Successfully!");
        } else {
            System.out.println("Invalid Amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("Withdrawn Successfully!");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add("Transferred: $" + amount + " to " + recipient.userId);
            recipient.transactionHistory.add("Received: $" + amount + " from " + userId);
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No Transactions Yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class Task1 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User currentUser = new User("aniket", "9898");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        if (!currentUser.authenticate(userId, userPin)) {
            System.out.println("Invalid Credentials! Please try again.");
            return;
        }

        while (true) {
            System.out.println("\n1. Transactions History\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Quit");
            System.out.print("Choose an option: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentUser.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input! Please enter a valid amount.");
                        scanner.next();
                        continue;
                    }
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input! Please enter a valid amount.");
                        scanner.next();
                        continue;
                    }
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.nextLine();
                    User recipient = new User(recipientId, "0000"); // Dummy recipient for simplicity
                    System.out.print("Enter amount to transfer: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input! Please enter a valid amount.");
                        scanner.next();
                        continue;
                    }
                    double transferAmount = scanner.nextDouble();
                    currentUser.transfer(recipient, transferAmount);
                    break;
                case 5:
                    System.out.println("Exiting ATM. Thank you!");
                    return;
                default:
                    System.out.println("Invalid Option. Try Again.");
            }
        }
    }
}
