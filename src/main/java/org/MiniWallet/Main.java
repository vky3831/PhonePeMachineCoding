package org.MiniWallet;

import java.util.Arrays;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategyImpl.TransactionTypeFilterStrategy;
import org.MiniWallet.SortStrategy.TransactionSortStrategyImpl.AmountSortStrategy;
import org.MiniWallet.datastore.PaymentData;
import org.MiniWallet.datastore.UserData;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.enums.TransactionType;
import org.MiniWallet.service.PaymentService;
import org.MiniWallet.service.UserService;
import org.MiniWallet.service.WalletServiceClient;
import org.MiniWallet.service.WalletServiceInternal;
import org.MiniWallet.service.impl.PaymentServiceImpl;
import org.MiniWallet.service.impl.UserServiceImpl;
import org.MiniWallet.service.impl.WalletServiceClientImpl;
import org.MiniWallet.service.impl.WalletServiceInternalImpl;

public class Main {

  public static void main(String[] args) {
    UserData userData = new UserData();
    WalletData walletData = new WalletData();
    PaymentData paymentData = new PaymentData();

    WalletServiceClient walletService = new WalletServiceClientImpl(walletData);
    WalletServiceInternal walletServiceInternal = new WalletServiceInternalImpl(walletData);
    UserService userService = new UserServiceImpl(userData, walletServiceInternal);
    PaymentService paymentService = new PaymentServiceImpl(paymentData, walletServiceInternal);

    userService.registerUser("Vikash");
    userService.registerUser("Atul");
    userService.registerUser("Hari");

    walletService.fetchBalance("Vikash");
    paymentService.topUpWallet("Vikash", 100.0, PaymentMode.CREDIT_CARD);

    paymentService.getTransaction("Vikash", null, null, null);
    walletService.fetchBalance("Vikash");

    paymentService.payment("Vikash", "Hari", 55.0, PaymentMode.UPI);

    walletService.fetchBalance("Vikash");
    walletService.fetchBalance("Hari");

    paymentService.getTransaction("Vikash", null, null, null);
    paymentService.getTransaction("Hari", null, null, null);


    paymentService.getTransaction("Vikash", new AmountSortStrategy(), Ordering.ASC, Arrays.asList(new TransactionTypeFilterStrategy(
        TransactionType.TOP_UP)));
  }
}