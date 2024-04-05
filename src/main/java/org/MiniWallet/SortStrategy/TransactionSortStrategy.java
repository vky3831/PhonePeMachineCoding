package org.MiniWallet.SortStrategy;

import java.util.List;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;

public interface TransactionSortStrategy {
  void sort(List<Transaction> transactions, Ordering ordering);
}
