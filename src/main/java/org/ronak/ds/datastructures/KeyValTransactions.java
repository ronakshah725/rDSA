package org.ronak.ds.datastructures;

import java.util.*;

/**
 * Implement a Key-Value store with transaction support.
 * 
 * The store should support the following operations:
 * 
 * - Set: Add or update a key-value pair
 * - Get: Retrieve the value associated with a key
 * - Delete: Remove a key-value pair
 * 
 * Additionally, the store should support transactions, with the following operations:
 * 
 * - Begin: Start a new transaction
 * - Commit: Commit all changes made in the current transaction
 * - Rollback: Discard all changes made in the current transaction
 * 
 * Example: 
 * 
 * 
 * The implementation should be efficient and handle nested transactions.
 */

public class KeyValTransactions {

    class Transaction {
        Map<String, String> txnMap;

        public Transaction() {
            this.txnMap = new HashMap<>();
        }

        public void set(String key, String value){
            txnMap.put(key, value);
        }

        public void markForDeletion(String key){
            txnMap.put(key, null);
        }

        public String get(String key){
            return txnMap.get(key);
        }      
        
        public boolean containsKey(String key){
            return txnMap.containsKey(key);
        }
    }

    class KeyValStore {
        Map<String, String> store;
        Deque<Transaction> txnStack;

        public KeyValStore() {
            this.store = new HashMap<>();
            this.txnStack = new ArrayDeque<>();
        }

        public void set(String key, String value) {
            if (txnStack.isEmpty()) {
                store.put(key, value);
                return;
            }

            txnStack.peek().set(key, value);
        }

        public String get(String key) {
            if (!txnStack.isEmpty()) {
                // find the last transaction which has the key, if not found, get it from store
                for (Transaction txn : txnStack) {
                    if (txn.containsKey(key)) {
                        return txn.get(key);
                    }
                }
            }

            return store.get(key);
        }

        public void delete(String key) {
            if (!txnStack.isEmpty()) {
                txnStack.peek().markForDeletion (key);
            } else {
                store.remove(key);
            }
        }

        public void begin() {
            Transaction txn = new Transaction();
            txnStack.push(txn);
        }

        private void mergeStores(Map<String, String> source, Map<String, String> target) {
            for (var entry : source.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue();

                if (value == null) {
                    target.remove(key);
                    continue;
                }

                target.put(key, value);
            }
        }

        public void commit() {
            if (txnStack.isEmpty()) {
                System.out.println("Cannot commit outside active transaction");
                return;
            }

            Transaction source = txnStack.pop();
            if (txnStack.isEmpty()) {
                mergeStores(source.txnMap, store);
            } else {
                // merge each nested transaction to its parent
                mergeStores(source.txnMap, txnStack.peek().txnMap);
            }
        }

        public void rollback() {
            if (txnStack.isEmpty()) {
                return;
            }
            txnStack.pop();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Running all tests...\n");
            runAllTests();
            System.out.println("\nAll tests completed successfully!");
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
        }
    }

    public static void runAllTests() {
        try {
            testTransactionVisibility();
            System.out.println("Transaction Visibility tests passed.");

            testDeleteOperations();
            System.out.println("Delete Operations tests passed.");

            testStackManagement();
            System.out.println("Stack Management tests passed.");

            testNullValueHandling();
            System.out.println("Null Value Handling tests passed.");

            testTransactionBoundaries();
            System.out.println("Transaction Boundaries tests passed.");

            testMergeOperations();
            System.out.println("Merge Operations tests passed.");

            testStateConsistency();
            System.out.println("State Consistency tests passed.");
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            throw e;  // Re-throw to indicate test failure
        }
    }

    public static void testTransactionVisibility() {
        System.out.println("\nRunning Transaction Visibility tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC1.1: Basic nested transaction visibility
        store.begin();  // T1
        store.set("k1", "v1-parent");
        store.begin();  // T2
        store.set("k1", "v1-child");
        assert "v1-child".equals(store.get("k1")) : "TC1.1: Child value not visible";
        store.rollback();  // Rollback T2
        assert "v1-parent".equals(store.get("k1")) : "TC1.1: Parent value not restored after rollback";
        
        // TC1.2: Multiple level transaction visibility
        store.begin();  // T1
        store.set("k2", "v2-t1");
        store.begin();  // T2
        store.set("k2", "v2-t2");
        store.begin();  // T3
        store.set("k2", "v2-t3");
        store.rollback(); // Rollback T3
        assert "v2-t2".equals(store.get("k2")) : "TC1.2: T2 value not visible after T3 rollback";
        store.commit();  // Commit T2 into T1
        assert "v2-t2".equals(store.get("k2")) : "TC1.2: T2 value not maintained after commit";
    }
    
    public static void testDeleteOperations() {
        System.out.println("\nRunning Delete Operations tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC2.1: Delete and recreate in same transaction
        store.begin();
        store.set("k1", "v1");
        store.delete("k1");
        store.set("k1", "v1-new");
        assert "v1-new".equals(store.get("k1")) : "TC2.1: Value not recreated after delete";
        
        // TC2.2: Delete in child, access in parent
        store.begin();  // Parent
        store.set("k2", "v2");
        store.begin();  // Child
        store.delete("k2");
        assert null == store.get("k2") : "TC2.2: Key not deleted in child transaction";
        store.rollback(); // Rollback child
        assert "v2".equals(store.get("k2")) : "TC2.2: Value not preserved in parent after child rollback";
        
        // TC2.3: Delete propagation
        store.begin();
        store.set("k3", "v3");
        store.begin();
        store.delete("k3");
        store.commit(); // Commit child
        assert null == store.get("k3") : "TC2.3: Delete not propagated to parent transaction";
        store.commit(); // Commit parent
        assert null == store.get("k3") : "TC2.3: Delete not propagated to main store";
    }

    // Converting instance methods to static
    public static void testStackManagement() {
        System.out.println("\nRunning Stack Management tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC3.1: Operations without transaction
        store.commit();  // Should handle gracefully
        store.rollback(); // Should handle gracefully
        
        // TC3.2: Nested stack operations
        store.begin();
        store.begin();
        store.commit();
        store.commit(); // Should work fine
        store.rollback(); // Should fail gracefully - no transaction
    }

    public static void testNullValueHandling() {
        System.out.println("\nRunning Null Value Handling tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC4.1: Explicit null value
        store.begin();
        store.set("k1", null);  // Explicitly set null
        assert store.get("k1") == null : "TC4.1: Null value not handled correctly";
        
        // TC4.2: Deleted key
        store.set("k2", "v2");
        store.delete("k2");  // Delete the key
        assert store.get("k2") == null : "TC4.2: Deleted key should return null";
    }

    public static void testTransactionBoundaries() {
        System.out.println("\nRunning Transaction Boundaries tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC5.1: Commit without begin
        store.commit();  // Should fail gracefully
        
        // TC5.2: Nested transaction boundaries
        store.begin();
        store.set("k1", "v1-outer");
        store.begin();
        store.set("k1", "v1-inner");
        store.commit(); // Commits inner
        assert "v1-inner".equals(store.get("k1")) : "TC5.2: Inner transaction value not visible";
        store.commit(); // Commits outer
    }

    public static void testMergeOperations() {
        System.out.println("\nRunning Merge Operations tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC6.1: Merge with deletions
        store.begin();  // T1
        store.set("k1", "v1");
        store.begin();  // T2
        store.delete("k1");
        store.commit(); // Commit T2
        assert null == store.get("k1") : "TC6.1: Deletion not preserved after inner commit";
        store.commit(); // Commit T1
        assert null == store.get("k1") : "TC6.1: Deletion not preserved after outer commit";
    }

    public static void testStateConsistency() {
        System.out.println("\nRunning State Consistency tests...");
        KeyValTransactions kvt = new KeyValTransactions();
        KeyValStore store = kvt.new KeyValStore();
        
        // TC7.1: Multiple operations consistency
        store.begin();
        store.set("k1", "v1");
        store.set("k2", "v2");
        store.delete("k1");
        store.set("k2", "v2-updated");
        store.rollback();
        assert null == store.get("k1") : "TC7.1: k1 should not exist after rollback";
        assert null == store.get("k2") : "TC7.1: k2 should not exist after rollback";

        // TC7.2: Nested transaction consistency
        store.begin();  // T1
        store.set("k3", "v3-t1");
        store.begin();  // T2
        store.set("k3", "v3-t2");
        store.begin();  // T3
        store.delete("k3");
        store.rollback(); // Rollback T3
        assert "v3-t2".equals(store.get("k3")) : "TC7.2: T2 state not maintained after T3 rollback";
    }
}
