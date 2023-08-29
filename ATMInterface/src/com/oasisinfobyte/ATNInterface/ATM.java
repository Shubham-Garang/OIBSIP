package com.oasisinfobyte.ATNInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeAccounts(); // Initialize sample accounts

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        Account account = accounts.get(accountNumber);

        if (account != null && account.validatePin(pin)) {
            showMainMenu(account);
        } else {
            System.out.println("Invalid account number or PIN. Exiting...");
        }
    }

    private static void performTransfer(Account sourceAccount) {
        System.out.print("Enter the recipient's account number: ");
        String recipientAccountNumber = scanner.nextLine();

        Account recipientAccount = accounts.get(recipientAccountNumber);

        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter the transfer amount: $");
        double transferAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (sourceAccount.withdraw(transferAmount)) {
            recipientAccount.deposit(transferAmount);
            Transaction transferTransaction = new Transaction(Transaction.TransactionType.TRANSFER, transferAmount);
            sourceAccount.addTransaction(transferTransaction);
            recipientAccount.addTransaction(transferTransaction);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

        
    
    private static void showMainMenu(Account account) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transactions History");
            System.out.println("6. Quit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your balance: $" + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;

                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;

                case 4:
                	performTransfer(account);
                    break;

                case 5:
                    showTransactionHistory(account);
                    break;

                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void showTransactionHistory(Account account) {
        List<Transaction> transactions = account.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(
                    "Type: " + transaction.getType() +
                    ", Amount: $" + transaction.getAmount() +
                    ", Timestamp: " + transaction.getTimestamp()
                );
            }
        }
    }

    private static void initializeAccounts() {
        Account account1 = new Account("123456", "1234", 1000.0);
        Account account2 = new Account("789012", "5678", 500.0);
        accounts.put(account1.getAccountNumber(), account1);
        accounts.put(account2.getAccountNumber(), account2);
    }
}

