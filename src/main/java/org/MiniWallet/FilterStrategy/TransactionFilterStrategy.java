package org.MiniWallet.FilterStrategy;

import java.util.List;
import org.MiniWallet.model.Transaction;

public interface TransactionFilterStrategy {
    List<Transaction> filter(List<Transaction> transactionList);
}
