package main.UIOptions.user;

import main.UIOptions.UIOption;
import main.clients.User;

/**
 * Class to manage user actions on the ATM machine.
 */
public abstract class UserUIOption extends UIOption<UserUI, User> {

    public UserUIOption(String name, UserUI ui, User client) {
        super(name, ui, client);
    }

    public abstract void select();

    public String getOptionName() {
        return name;
    }

}
