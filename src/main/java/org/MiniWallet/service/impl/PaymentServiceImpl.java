package org.MiniWallet.service.impl;

import static java.time.Instant.now;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.enums.TransactionType;
import org.MiniWallet.model.Transaction;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.PaymentService;
import org.MiniWallet.service.WalletService;

public class PaymentServiceImpl implements PaymentService {

  private WalletService walletService;

  public  PaymentServiceImpl(WalletService walletService){
    this.walletService = walletService;
  }

  @Override
  public Transaction payment(String payer, String payee, Double amount, PaymentMode paymentMode) {
    Wallet payerWallet = walletService.getWalletByUserId(payer);
    Wallet payeeWallet = walletService.getWalletByUserId(payee);

    Double payerUpdatedBalance = payerWallet.getBalance() - amount;
    Double payeeUpdatedBalance = payeeWallet.getBalance() + amount;

    int cashBack = 0;
    if(payerUpdatedBalance == payeeUpdatedBalance){
      cashBack = 5;
    }

    payerWallet.setBalance(payerUpdatedBalance + cashBack);
    payeeWallet.setBalance(payeeUpdatedBalance + cashBack);


    Transaction debit = new Transaction(UUID.randomUUID().toString(), payer, payee, amount, TransactionType.DEBIT,
        new Timestamp(System.currentTimeMillis()), paymentMode);
    payerWallet.getTransactionList().add(debit);

    Transaction credit = new Transaction(UUID.randomUUID().toString(), payer, payee, amount, TransactionType.CREDIT,
        new Timestamp(System.currentTimeMillis()), paymentMode);
    payeeWallet.getTransactionList().add(credit);

    if(cashBack != 0){

      Transaction creditCashbackToPayer = new Transaction(UUID.randomUUID().toString(), "CashBack", payer, amount, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      payeeWallet.getTransactionList().add(creditCashbackToPayer);

      Transaction  creditCashbackToPayee = new Transaction(UUID.randomUUID().toString(), "CashBack", payee, amount, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      payeeWallet.getTransactionList().add(creditCashbackToPayee);
    }

    return debit;
  }

  @Override
  public Transaction topUpWallet(String userId, Double amount, PaymentMode paymentMode) {
    Wallet wallet = walletService.getWalletByUserId(userId);
    int cashBack = 0;
    if(amount >= 100 && wallet.getTransactionList().isEmpty()){
      cashBack = 10;
    }
    wallet.setBalance(wallet.getBalance() + amount +cashBack);

    Transaction transaction = new Transaction(UUID.randomUUID().toString(), userId, wallet.getWalletId(), amount, TransactionType.CREDIT,
        new Timestamp(System.currentTimeMillis()), paymentMode);

    wallet.getTransactionList().add(transaction);
    if(cashBack != 0){
      Transaction cashBackTransaction = new Transaction(UUID.randomUUID().toString(), "CashBack", userId, cashBack, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      wallet.getTransactionList().add(cashBackTransaction);
    }

    return transaction;
  }
}
