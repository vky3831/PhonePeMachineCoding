package org.MiniWallet.exceptions;

public class NoWalletFoundException extends RuntimeException{
  public NoWalletFoundException(String errMsg){
    super(errMsg);
  }
}
