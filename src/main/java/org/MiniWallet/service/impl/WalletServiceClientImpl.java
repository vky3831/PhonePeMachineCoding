package org.MiniWallet.service.impl;

import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.WalletServiceClient;

public class WalletServiceClientImpl implements WalletServiceClient {

  private WalletData walletData;

  public WalletServiceClientImpl(WalletData walletData){
    this.walletData = walletData;
  }
  @Override
  public Wallet createWallet(String userId) {
    String newWalletId ="w_"+ userId;
    Wallet newWallet = new Wallet(newWalletId, 0.0);
    walletData.getWalletIdToWallet().put(newWalletId, newWallet);
    walletData.getUserIdToWalletId().put(userId, newWalletId);
    return newWallet;
  }

  @Override
  public Double fetchBalance(String userId) {
    Wallet wallet = walletData.getWalletByUserId(userId);
    System.out.println("Current Balance of " + userId + " is " + wallet.getBalance());
    return wallet.getBalance();
  }

  public Wallet getWalletByUserId(String userId){
    return walletData.getWalletByUserId(userId);
  }
}
