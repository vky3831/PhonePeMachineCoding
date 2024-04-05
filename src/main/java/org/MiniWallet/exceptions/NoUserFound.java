package org.MiniWallet.exceptions;

public class NoUserFound extends RuntimeException{
  public NoUserFound(String errMsg){
    super(errMsg);
  }
}
