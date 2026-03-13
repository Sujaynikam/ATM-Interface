import java.util.*;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account acc1 = new Account("user1", "1111", 5000);
        Account acc2 = new Account("user2", "2222", 7000);
        Account acc3 = new Account("user3", "3333", 10000);

        List<Account> accounts = Arrays.asList(acc1, acc2, acc3);

        System.out.println("===== Welcome to Java ATM =====");
        System.out.print("Enter User ID: ");
        String id = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        Account loggedIn = null;
        for (Account acc : accounts) {
            if (acc.getUserId().equals(id) && acc.authenticate(pin)) {
                loggedIn = acc;
                break;
            }
        }

        if (loggedIn == null) {
            System.out.println("❌ Invalid credentials!");
            return;
        }

        System.out.println("\n✅ Login Successful! Welcome, " + id);

        while (true) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    loggedIn.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    if (loggedIn.withdraw(w))
                        System.out.println("✅ Withdrawal successful.");
                    else
                        System.out.println("❌ Insufficient balance.");
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    loggedIn.deposit(d);
                    System.out.println("✅ Deposit successful.");
                    break;
                case 4:
                    System.out.print("Enter receiver ID: ");
                    String rid = sc.next();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    Account receiver = null;
                    for (Account a : accounts) {
                        if (a.getUserId().equals(rid)) {
                            receiver = a;
                            break;
                        }
                    }
                    if (receiver != null) {
                        loggedIn.transfer(receiver, amt);
                        System.out.println("✅ Transfer successful.");
                    } else {
                        System.out.println("❌ Receiver not found.");
                    }
                    break;
                case 5:
                    System.out.println("👋 Thank you for using Java ATM!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
