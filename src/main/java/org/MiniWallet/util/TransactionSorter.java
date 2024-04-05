package org.MiniWallet.util;

import java.util.List;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;

public class TransactionSorter {
  private TransactionSortStrategy strategy;
  private Ordering ordering;

  public TransactionSorter(TransactionSortStrategy strategy, Ordering ordering) {
    this.strategy = strategy;
    this.ordering = ordering;
  }

  public void sort(List<Transaction> transactions) {
    strategy.sort(transactions, ordering);
  }
}
