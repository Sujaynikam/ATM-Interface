import java.util.*;

public class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean authenticate(String pin) {
        return this.userPin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: ₹" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            return true;
        } else {
            transactionHistory.add("Failed Withdrawal: ₹" + amount + " (Insufficient balance)");
            return false;
        }
    }

    public void transfer(Account receiver, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add("Transferred ₹" + amount + " to " + receiver.getUserId());
        } else {
            transactionHistory.add("Failed Transfer ₹" + amount + " to " + receiver.getUserId());
        }
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + userId + ":");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(" - " + t);
            }
        }
    }
}
