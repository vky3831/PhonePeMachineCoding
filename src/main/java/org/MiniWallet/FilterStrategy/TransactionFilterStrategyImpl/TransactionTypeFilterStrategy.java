package org.MiniWallet.FilterStrategy.TransactionFilterStrategyImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategy;
import org.MiniWallet.enums.TransactionType;
import org.MiniWallet.model.Transaction;

public class TransactionTypeFilterStrategy implements TransactionFilterStrategy {
  TransactionType transactionType;

  public TransactionTypeFilterStrategy(TransactionType transactionType){
    this.transactionType = transactionType;
  }
  @Override
  public List<Transaction> filter(List<Transaction> transactionList) {
    return transactionList.stream().filter(transaction -> transaction.getTransactionType() == transactionType).collect(
        Collectors.toList());
  }
}
