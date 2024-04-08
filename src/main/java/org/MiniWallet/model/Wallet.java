package org.MiniWallet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wallet {
  String walletId;
  Double balance;
//  List<Transaction> transactionList;
}
