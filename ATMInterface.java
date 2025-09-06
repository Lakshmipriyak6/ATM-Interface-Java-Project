package JavaProject;

import java.util.Scanner;

// Class 1: User
class User {
    private String userId;
    private String userPin;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return this.userPin.equals(pin);
    }
}

// Class 2: BankAccount
class BankAccount {
    private float balance = 10000f;
    private String transactionHistory = "";
    private int transactions = 0;

    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            transactions++;
            transactionHistory += amount + " Rs deposited\n";
            System.out.println("\nDeposit Successful!");
        } else {
            System.out.println("\nInvalid Deposit Amount!");
        }
    }

    public void withdraw(float amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions++;
            transactionHistory += amount + " Rs withdrawn\n";
            System.out.println("\nWithdrawal Successful!");
        } else {
            System.out.println("\nInsufficient Balance or Invalid Amount!");
        }
    }

    public void transfer(String recipient, float amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions++;
            transactionHistory += amount + " Rs transferred to " + recipient + "\n";
            System.out.println("\nSuccessfully Transferred to " + recipient);
        } else {
            System.out.println("\nInsufficient Balance or Invalid Amount!");
        }
    }

    public void showBalance() {
        System.out.println("\nAvailable Balance: " + balance + " Rs");
    }

    public void showTransactionHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions yet.");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }
}

// Class 3: ATM Menu
class ATMMenu {
    private BankAccount account;
    private Scanner sc = new Scanner(System.in);

    public ATMMenu(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n******************** WELCOME TO LAKSHMIPRIYA ATM INTERFACE ********************");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    account.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("\nEnter amount to withdraw: ");
                    float wAmt = sc.nextFloat();
                    account.withdraw(wAmt);
                    break;
                case 3:
                    System.out.print("\nEnter amount to deposit: ");
                    float dAmt = sc.nextFloat();
                    account.deposit(dAmt);
                    break;
                case 4:
                    sc.nextLine(); // consume leftover newline
                    System.out.print("\nEnter recipient's name: ");
                    String recipient = sc.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    float tAmt = sc.nextFloat();
                    account.transfer(recipient, tAmt);
                    break;
                case 5:
                    System.out.println("\nThank you for using Lakshmipriya ATM. Goodbye!");
                    return;
                default:
                    System.out.println("\nInvalid Choice! Try again.");
            }
        }
    }
}

// Class 4: Authentication
class Authentication {
    private User user;
    private Scanner sc = new Scanner(System.in);

    public Authentication(User user) {
        this.user = user;
    }

    public boolean login() {
        System.out.print("\nEnter User ID: ");
        String enteredId = sc.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = sc.nextLine();

        if (enteredId.equals(user.getUserId()) && user.validatePin(enteredPin)) {
            System.out.println("\nLogin Successful! Welcome " + enteredId);
            return true;
        } else {
            System.out.println("\nInvalid User ID or PIN.");
            return false;
        }
    }
}

// Class 5: Main ATMInterface
public class ATMInterface {
    public static void main(String[] args) {
        User user = new User("Lakshmipriya", "1234");
        BankAccount account = new BankAccount();
        Authentication auth = new Authentication(user);

        System.out.println("\n******************** WELCOME TO LAKSHMIPRIYA ATM ********************");

        // login loop
        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            isAuthenticated = auth.login();
        }

        // Show menu after login
        ATMMenu menu = new ATMMenu(account);
        menu.showMenu();
    }
}
