package org.MiniWallet.FilterStrategy.TransactionFilterStrategyImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategy;
import org.MiniWallet.model.Transaction;

public class UserFilterStrategy implements TransactionFilterStrategy {
  String userId;
  public UserFilterStrategy(String userId){
    this.userId = userId;
  }
  @Override
  public List<Transaction> filter(List<Transaction> transactionList) {
    return transactionList.stream().filter(transaction -> userfilterUtil(transaction)).collect(Collectors.toList());
  }

  private boolean userfilterUtil(Transaction transaction) {
    if(userId.equalsIgnoreCase(transaction.getPayee()) || userId.equalsIgnoreCase(transaction.getPayer())){
      return true;
    }
    return false;
  }
}
