package org.MiniWallet;

import org.MiniWallet.FilterStrategy.TransactionFilterStrategyImpl.TransactionTypeFilterStrategy;
import org.MiniWallet.SortStrategy.TransactionSortStrategyImpl.AmountSortStrategy;
import org.MiniWallet.datastore.UserData;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.enums.TransactionType;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.PaymentService;
import org.MiniWallet.service.UserService;
import org.MiniWallet.service.WalletService;
import org.MiniWallet.service.impl.PaymentServiceImpl;
import org.MiniWallet.service.impl.UserServiceImpl;
import org.MiniWallet.service.impl.WalletServiceImpl;

public class Main {

  public static void main(String[] args) {
    UserData userData = new UserData();
    WalletData walletData = new WalletData();

    WalletService walletService = new WalletServiceImpl(walletData);
    UserService userService = new UserServiceImpl(userData, walletService);
    PaymentService paymentService = new PaymentServiceImpl(walletService);

    userService.registerUser("Vikash");
    userService.registerUser("Atul");
    userService.registerUser("Hari");

    walletService.fetchBalance("Vikash");
    paymentService.topUpWallet("Vikash", 100.0, PaymentMode.CREDIT_CARD);

    walletService.getTransaction("Vikash", null, null, null);
    walletService.fetchBalance("Vikash");

    paymentService.payment("Vikash", "Hari", 55.0, PaymentMode.UPI);

    walletService.fetchBalance("Vikash");
    walletService.fetchBalance("Hari");

    walletService.getTransaction("Vikash", null, null, null);
    walletService.getTransaction("Hari", null, null, null);


    walletService.getTransaction("Vikash", new AmountSortStrategy(), Ordering.DESC, new TransactionTypeFilterStrategy(
        TransactionType.DEBIT));
  }
}