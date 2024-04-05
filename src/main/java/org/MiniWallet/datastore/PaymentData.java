package org.MiniWallet.datastore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.MiniWallet.model.Transaction;

@Getter
public class PaymentData {
  Map<String, List<Transaction>> userIdToTransactionList = new HashMap<>();
}
