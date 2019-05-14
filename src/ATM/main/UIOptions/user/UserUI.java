package main.UIOptions.user;

import main.UIOptions.UI;
import main.clients.User;

import java.util.Map;
import java.util.TreeMap;

/*
The main user UI class handling all options available on their menu.
 */
public class UserUI extends UI {

    public UserUI(User user) {
        super(user);
        options.put("1", new CheckBalanceOption(this, user));
        options.put("2", new DepositOption(this, user));
        options.put("3", new WithdrawOption(this, user));
        options.put("4", new TransferOption(this, user));
        options.put("5", new UserInfoOption(this, user));
        options.put("6", new ChangePassOption(this, user));
        options.put("7", new NewAccountOption(this, user));
        options.put("8", new UserLogoutOption(this, user));
    }
}
