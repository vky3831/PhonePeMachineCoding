package org.MiniWallet.service.impl;

import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.WalletServiceInternal;

public class WalletServiceInternalImpl implements WalletServiceInternal {

  private WalletData walletData;
  public  WalletServiceInternalImpl(WalletData walletData){
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
  public Wallet getWalletByUserId(String userId) {
    return walletData.getWalletByUserId(userId);
  }
}
