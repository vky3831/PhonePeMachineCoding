package org.MiniWallet.service;

import java.util.List;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategy;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;
import org.MiniWallet.model.Wallet;

public interface WalletService {
  Wallet createWallet(String userId);
  Double fetchBalance(String userId);
  List<Transaction> getTransaction(String userId, TransactionSortStrategy transactionSortStrategy, Ordering ordering,TransactionFilterStrategy transactionFilterStrategy);

  Wallet getWalletByUserId(String userId);
}

