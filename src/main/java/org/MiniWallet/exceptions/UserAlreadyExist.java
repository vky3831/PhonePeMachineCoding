package org.MiniWallet.exceptions;

public class UserAlreadyExist extends RuntimeException{
  public UserAlreadyExist(String errMsg){
    super(errMsg);
  }

}
