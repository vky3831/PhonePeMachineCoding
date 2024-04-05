package org.MiniWallet.service.impl;

import static java.time.Instant.now;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.MiniWallet.FilterStrategy.TransactionFilterStrategy;
import org.MiniWallet.SortStrategy.TransactionSortStrategy;
import org.MiniWallet.datastore.PaymentData;
import org.MiniWallet.datastore.WalletData;
import org.MiniWallet.enums.Ordering;
import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.enums.TransactionType;
import org.MiniWallet.model.Transaction;
import org.MiniWallet.model.Wallet;
import org.MiniWallet.service.PaymentService;
import org.MiniWallet.service.WalletService;

public class PaymentServiceImpl implements PaymentService {

  private WalletService walletService;
  private PaymentData paymentData;

  public  PaymentServiceImpl(PaymentData paymentData, WalletService walletService){
    this.paymentData = paymentData;
    this.walletService = walletService;
  }

  @Override
  public Transaction payment(String payer, String payee, Double amount, PaymentMode paymentMode) {
    Wallet payerWallet = walletService.getWalletByUserId(payer);
    Wallet payeeWallet = walletService.getWalletByUserId(payee);

    Double payerUpdatedBalance = payerWallet.getBalance() - amount;
    Double payeeUpdatedBalance = payeeWallet.getBalance() + amount;

    int cashBack = 0;
    if(payerUpdatedBalance.doubleValue() ==  payeeUpdatedBalance.doubleValue()){
      cashBack = 5;
    }

    payerWallet.setBalance(payerUpdatedBalance + cashBack);
    payeeWallet.setBalance(payeeUpdatedBalance + cashBack);


    Transaction debit = new Transaction(UUID.randomUUID().toString(), payer, payee, amount, TransactionType.DEBIT,
        new Timestamp(System.currentTimeMillis()), paymentMode);
    appendTransactionToUser(payer, debit);

    Transaction credit = new Transaction(UUID.randomUUID().toString(), payer, payee, amount, TransactionType.CREDIT,
        new Timestamp(System.currentTimeMillis()), paymentMode);
    appendTransactionToUser(payee, credit);

    if(cashBack != 0){

      Transaction creditCashbackToPayer = new Transaction(UUID.randomUUID().toString(), "CashBack", payer, cashBack, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      appendTransactionToUser(payer, creditCashbackToPayer);

      Transaction  creditCashbackToPayee = new Transaction(UUID.randomUUID().toString(), "CashBack", payee, cashBack, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      appendTransactionToUser(payee, creditCashbackToPayee);
    }

    return debit;
  }

  private void appendTransactionToUser(String payer, Transaction debit) {
    List<Transaction> transactionList = paymentData.getUserIdToTransactionList().getOrDefault(payer, new ArrayList<>());
    transactionList.add(debit);
    paymentData.getUserIdToTransactionList().put(payer, transactionList);
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
    appendTransactionToUser(userId, transaction);

    if(cashBack != 0){
      Transaction cashBackTransaction = new Transaction(UUID.randomUUID().toString(), "CashBack", userId, cashBack, TransactionType.CREDIT,
          new Timestamp(System.currentTimeMillis()), PaymentMode.CASH_BACK);
      appendTransactionToUser(userId, cashBackTransaction);
    }

    return transaction;
  }

  @Override
  public List<Transaction> getTransaction(String userId,
      TransactionSortStrategy transactionSortStrategy, Ordering ordering,
      TransactionFilterStrategy transactionFilterStrategy) {

    List<Transaction> transactionList = paymentData.getUserIdToTransactionList().getOrDefault(userId, new ArrayList<>());

    if(Objects.nonNull(transactionSortStrategy))  transactionSortStrategy.sort(transactionList, ordering);
    if(Objects.nonNull(transactionFilterStrategy))  transactionList = transactionFilterStrategy.filter(transactionList);

    System.out.println("Transaction Records of User " + userId);
    System.out.println(transactionList);

    return transactionList;
  }
}
