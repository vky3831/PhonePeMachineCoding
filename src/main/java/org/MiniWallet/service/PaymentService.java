package org.MiniWallet.service;

import org.MiniWallet.enums.PaymentMode;
import org.MiniWallet.model.Transaction;

public interface PaymentService {
  Transaction payment(String payer, String payee, Double amount, PaymentMode paymentMode);

  Transaction topUpWallet(String userId, Double amount, PaymentMode paymentMode);

}
