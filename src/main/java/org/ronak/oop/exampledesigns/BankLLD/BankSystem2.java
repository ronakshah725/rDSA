package org.ronak.oop.exampledesigns.BankLLD;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Enum representing transaction types.
 */
enum TransactionType {
    CREDIT,
    DEBIT,
    TRANSFER,
    ALL
}

/**
 * Main class representing the banking system.
 */
public class BankSystem2 {
    public static void main(String[] args) {
        // Initialize services
        AccountsService accountsService = new AccountsService();
        TransactionService transactionService = new TransactionService();
        NotificationService notificationService = new NotificationService();

        // Register transaction service and notification service
        accountsService.setTransactionService(transactionService);
        accountsService.setNotificationService(notificationService);

        // Create accounts
        String aliceAccountId = accountsService.createAccount("Alice");
        String bobAccountId = accountsService.createAccount("Bob");
        String charlieAccountId = accountsService.createAccount("Charlie");
        String daveAccountId = accountsService.createAccount("Dave");

        // Perform transactions
        accountsService.deposit(aliceAccountId, new BigDecimal("1000.00"));
        accountsService.withdraw(aliceAccountId, new BigDecimal("200.00"));
        accountsService.transfer(aliceAccountId, bobAccountId, new BigDecimal("150.00"));
        accountsService.transfer(aliceAccountId, charlieAccountId, new BigDecimal("100.00"));
        accountsService.transfer(aliceAccountId, daveAccountId, new BigDecimal("50.00"));
        accountsService.deposit(bobAccountId, new BigDecimal("500.00"));
        accountsService.withdraw(bobAccountId, new BigDecimal("100.00"));
        accountsService.transfer(bobAccountId, aliceAccountId, new BigDecimal("50.00"));
        accountsService.transfer(bobAccountId, charlieAccountId, new BigDecimal("50.00"));

        // Get top K transactions for Alice's account by different criteria
        List<Transaction> topRecentTransactions = transactionService.getTopKTransactions(aliceAccountId, 2, TransactionType.ALL);
        System.out.println("Top recent transactions for account " + aliceAccountId + ":");
        for (Transaction txn : topRecentTransactions) {
            System.out.println(txn);
        }

        System.out.println("____");
        System.out.println("____");


        List<Transaction> topTransferTransactions = transactionService.getTopKTransactions(aliceAccountId, 2, TransactionType.TRANSFER);
        System.out.println("Top transfer transactions for account " + aliceAccountId + ":");
        for (Transaction txn : topTransferTransactions) {
            System.out.println(txn);
        }

        System.out.println("____");
        System.out.println("____");


        List<Transaction> topDepositTransactions = transactionService.getTopKTransactions(aliceAccountId, 2, TransactionType.CREDIT);
        System.out.println("Top deposit transactions for account " + aliceAccountId + ":");
        for (Transaction txn : topDepositTransactions) {
            System.out.println(txn);
        }
    }
}

/**
 * Service responsible for managing accounts and performing operations on them.
 */
class AccountsService {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    @Setter
    private TransactionService transactionService;
    @Setter
    private NotificationService notificationService;

    public String createAccount(String ownerName) {
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(accountId, ownerName);
        accounts.put(accountId, account);
        return accountId;
    }

    public void deposit(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        synchronized (account) {
            account.deposit(amount);
            Transaction transaction = new Transaction(TransactionType.CREDIT, amount, accountId, null);
            transactionService.recordTransaction(transaction);
        }
    }

    public void withdraw(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        synchronized (account) {
            if (account.getBalance().compareTo(amount) < 0) {
                notificationService.notifyUser(account.getOwnerName(), "Insufficient funds for account: " + accountId);
                throw new InsufficientFundsException("Insufficient funds for account: " + accountId);
            }
            account.withdraw(amount);
            Transaction transaction = new Transaction(TransactionType.DEBIT, amount, accountId, null);
            transactionService.recordTransaction(transaction);
        }
    }

    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        if (fromAccountId.equals(toAccountId)) {
            throw new InvalidTransferException("Cannot transfer to the same account.");
        }
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    notificationService.notifyUser(fromAccount.getOwnerName(), "Insufficient funds for transfer from account: " + fromAccountId);
                    throw new InsufficientFundsException("Insufficient funds for transfer from account: " + fromAccountId);
                }
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                Transaction transaction = new Transaction(TransactionType.TRANSFER, amount, fromAccountId, toAccountId);
                transactionService.recordTransaction(transaction);
            }
        }
    }

    private Account getAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        return account;
    }
}

/**
 * Service responsible for recording transactions and managing top K transactions.
 */
class TransactionService {
    private static final int K = 50;
    private final Map<String, List<Transaction>> transactions = new ConcurrentHashMap<>();
    private final Map<TransactionType, PriorityQueue<Transaction>> filterToTopK = new HashMap<>();

    public TransactionService() {
        for (TransactionType type : TransactionType.values()) {
            filterToTopK.put(type, new PriorityQueue<>(Comparator.comparing(Transaction::getAmount)));
        }
    }

    public void recordTransaction(Transaction transaction) {
        transactions.computeIfAbsent(transaction.getFromAccountId(), k -> Collections.synchronizedList(new ArrayList<>())).add(transaction);
        if (transaction.getToAccountId() != null) {
            transactions.computeIfAbsent(transaction.getToAccountId(), k -> Collections.synchronizedList(new ArrayList<>())).add(transaction);
        }
        manageTopK(transaction);
    }

    private void manageTopK(Transaction transaction) {
        PriorityQueue<Transaction> topKHeap = filterToTopK.get(transaction.getType());
        synchronized (topKHeap) {
            topKHeap.offer(transaction);
            if (topKHeap.size() > K) {
                topKHeap.poll(); // Keep only the top K
            }
        }
    }

    public List<Transaction> getTopKTransactions(String accountId, int k, TransactionType type) {
        if (type == TransactionType.ALL) {
            List<Transaction> transactionsForAccount = transactions.get(accountId);
            if (transactionsForAccount == null) {
                return Collections.emptyList();
            }
            transactionsForAccount.sort(Comparator.comparing(Transaction::getTimestamp).reversed());
            return transactionsForAccount.subList(0, Math.min(k, transactionsForAccount.size()));
        } else {
            PriorityQueue<Transaction> topKHeap = filterToTopK.get(type);
            synchronized (topKHeap) {
                List<Transaction> topK = new ArrayList<>(topKHeap);
                topK.sort(Comparator.comparing(Transaction::getAmount).reversed());
                return topK.subList(0, Math.min(k, topK.size()));
            }
        }
    }
}

/**
 * Service responsible for notifying users of important events.
 */
class NotificationService {
    public void notifyUser(String userName, String message) {
        // In a real system, this could send an email or SMS.
        System.out.println("Notification to " + userName + ": " + message);
    }
}

/**
 * Class representing a bank account.
 */
@Data
@RequiredArgsConstructor
class Account {
    private final String accountId;
    private final String ownerName;
    @Setter
    private BigDecimal balance = BigDecimal.ZERO;

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionAmountException("Deposit amount must be positive.");
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionAmountException("Withdrawal amount must be positive.");
        }
        balance = balance.subtract(amount);
    }
}

/**
 * Class representing a transaction.
 */
@Data
@RequiredArgsConstructor
@ToString
class Transaction {
    private final TransactionType type;
    private final BigDecimal amount;
    private final String fromAccountId;
    private final String toAccountId;
    private final LocalDateTime timestamp = LocalDateTime.now();
}

/**
 * Custom exception classes for handling specific error scenarios.
 */
@Getter
@RequiredArgsConstructor
class AccountNotFoundException extends RuntimeException {
    private final String message;
}

@Getter
@RequiredArgsConstructor
class InsufficientFundsException extends RuntimeException {
    private final String message;
}

@Getter
@RequiredArgsConstructor
class InvalidTransactionAmountException extends RuntimeException {
    private final String message;
}

@Getter
@RequiredArgsConstructor
class InvalidTransferException extends RuntimeException {
    private final String message;
}
