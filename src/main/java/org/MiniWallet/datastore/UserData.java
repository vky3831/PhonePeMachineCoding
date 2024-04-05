package org.MiniWallet.datastore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.MiniWallet.exceptions.NoUserFound;
import org.MiniWallet.model.User;

@Getter
public class UserData {
  Map<String, User> userIdToUser = new HashMap<>();

  public User getUserById(String id){
    User user = userIdToUser.get(id);
    if(Objects.isNull(id)) throw new NoUserFound("No user found with Id: " + id);
    return user;
  }
}
