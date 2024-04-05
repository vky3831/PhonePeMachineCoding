package org.MiniWallet.SortStrategy.TransactionSortStrategyImpl;

import java.util.List;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;

public class AmountSortStrategy implements TransactionSortStrategy {
    @Override
    public void sort(List<Transaction> transactions, Ordering ordering) {
        if(ordering == Ordering.ASC){
            transactions.sort((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()));
        } else {
            transactions.sort((t1, t2) -> -1 * Double.compare(t1.getAmount(), t2.getAmount()));
        }
    }
}