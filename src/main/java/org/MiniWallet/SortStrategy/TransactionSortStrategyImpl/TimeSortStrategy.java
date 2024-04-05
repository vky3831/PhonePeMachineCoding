package org.MiniWallet.SortStrategy.TransactionSortStrategyImpl;

import java.util.List;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;

public class TimeSortStrategy implements TransactionSortStrategy {
    @Override
    public void sort(List<Transaction> transactions, Ordering ordering) {
        if(ordering == Ordering.ASC){
            transactions.sort((t1, t2) -> t1.getTimestamp().compareTo(t2.getTimestamp()));
        } else {
            transactions.sort((t1, t2) -> -1 * t1.getTimestamp().compareTo(t2.getTimestamp()));
        }

    }
}