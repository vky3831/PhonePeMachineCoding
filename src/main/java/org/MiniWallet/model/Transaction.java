package org.MiniWallet.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.enums.TransactionType;

@AllArgsConstructor
@Data
public class Transaction {
  String transactionId;
  String payer;
  String payee;
  double amount;
  TransactionType transactionType;
  Timestamp timestamp;
  PaymentMode paymentMode;

}
