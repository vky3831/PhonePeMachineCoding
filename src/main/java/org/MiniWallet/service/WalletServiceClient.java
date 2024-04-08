package org.MiniWallet.service;

import org.MiniWallet.model.Wallet;

public interface WalletServiceClient {
  Wallet createWallet(String userId);
  Double fetchBalance(String userId);
//  List<Transaction> getTransaction(String userId, TransactionSortStrategy transactionSortStrategy, Ordering ordering,TransactionFilterStrategy transactionFilterStrategy);

  Wallet getWalletByUserId(String userId);
}

