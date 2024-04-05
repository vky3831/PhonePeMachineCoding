package org.MiniWallet.datastore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.MiniWallet.exceptions.NoWalletFoundException;
import org.MiniWallet.model.Wallet;

@Getter
public class WalletData {
  Map<String, Wallet> walletIdToWallet = new HashMap();
  Map<String, String> userIdToWalletId = new HashMap<>();

  public Wallet getWalletByUserId(String userId){
    String walletId = userIdToWalletId.get(userId);
    if(Objects.isNull(walletId)) throw new NoWalletFoundException("No wallet found corresponding to user id: " + userId);
    Wallet reqWallet = walletIdToWallet.get(walletId);
    if(Objects.isNull(reqWallet)) throw new NoWalletFoundException("No wallet found corresponding to user id: " + userId);
    return reqWallet;
  }

}
