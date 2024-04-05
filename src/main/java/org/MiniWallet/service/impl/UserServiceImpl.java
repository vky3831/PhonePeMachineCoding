package org.MiniWallet.service.impl;

import java.util.Objects;
import org.MiniWallet.datastore.UserData;
import org.MiniWallet.exceptions.UserAlreadyExist;
import org.MiniWallet.model.User;
import org.MiniWallet.service.UserService;
import org.MiniWallet.service.WalletService;

public class UserServiceImpl implements UserService {

  private UserData userData;

  private WalletService walletService;

  public UserServiceImpl(UserData userData, WalletService walletService){
    this.userData = userData;
    this.walletService = walletService;
  }

  @Override
  public User registerUser(String username) {
    User newUser = new User(username, username);
    User user = userData.getUserIdToUser().get(username);
    if(Objects.nonNull(user)){
      throw new UserAlreadyExist("User already exist");
    }
    userData.getUserIdToUser().put(username, newUser);
    walletService.createWallet(username);
    return newUser;
  }
}
