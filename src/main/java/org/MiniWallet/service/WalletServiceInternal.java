package org.MiniWallet.service;

import org.MiniWallet.model.Wallet;

// Interface Segregation for Wallet Service
public interface WalletServiceInternal {
  Wallet createWallet(String userId);
  Wallet getWalletByUserId(String userId);
}
