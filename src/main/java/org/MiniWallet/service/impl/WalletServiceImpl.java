package org.MiniWallet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategy;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.model.Transaction;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.WalletService;

public class WalletServiceImpl implements WalletService {

  private WalletData walletData;

  public  WalletServiceImpl(WalletData walletData){
    this.walletData = walletData;
  }
  @Override
  public Wallet createWallet(String userId) {
    String newWalletId ="w_"+ userId;
    Wallet newWallet = new Wallet(newWalletId, 0.0, new ArrayList<>());
    walletData.getWalletIdToWallet().put(newWalletId, newWallet);
    walletData.getUserIdToWalletId().put(userId, newWalletId);
    return newWallet;
  }

  @Override
  public Double fetchBalance(String userId) {
    Wallet wallet = walletData.getWalletByUserId(userId);
    return wallet.getBalance();
  }

  @Override
  public List<Transaction> getTransaction(String userId,
      TransactionSortStrategy transactionSortStrategy, Ordering ordering,
      TransactionFilterStrategy transactionFilterStrategy) {

    Wallet wallet = walletData.getWalletByUserId(userId);

    List<Transaction> transactionList = wallet.getTransactionList();

    if(Objects.nonNull(transactionSortStrategy))  transactionSortStrategy.sort(transactionList, ordering);
    if(Objects.nonNull(transactionFilterStrategy))  transactionList = transactionFilterStrategy.filter(transactionList);

    return transactionList;
  }

  public Wallet getWalletByUserId(String userId){
    return walletData.getWalletByUserId(userId);
  }
}
