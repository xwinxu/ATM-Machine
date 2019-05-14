package main.clients;

import main.accounts.CreditCard;
import main.accounts.LineOfCredit;
import main.accounts.ChequingAccount;
import main.accounts.SavingsAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A Singleton Client Manager for all client types and pending requests affiliated with the ATM machine
 */
public class ClientManager implements Serializable {
    /*
     * The master array with all the bank client lists centralized.
     */
    private static final String MAP_PATH = "mapFile.txt";
    /**
     * Serialize number
     */
    private static final long serialVersionUID = 1L;
    /**
     * A static singleton copy of client manager
     */
    private volatile static ClientManager clientManager;
    /**
     * A logged in BankClient in GUI
     */
    public static BankClient loggedInClient;
    /**
     * A logged in User in GUI
     */
    public static User loggedInUser;
    /**
     * A logged in BankManger in GUI
     */
    public static BankManager loggedInManager;
    /**
     * A logged in BankTeller in GUI
     */
    public static BankTeller loggedInTeller;

    /**
     * A hash map containing all client lists and pending approval lists
     */
    public Map<String, ArrayList<BankClient>> masterList;
    /**
     * An array list containing all pending account requests
     */
    private ArrayList<UserRequest> accountRequestList;

    /**
     * The available currencies
     */
    public HashMap<String, Float> currencies;

    /**
     * A getter for a copy of client manager
     *
     * @return a copy of client manager class
     */
    public static ClientManager getInstance() {
        if (clientManager == null) {
            synchronized (ClientManager.class) {
                if (clientManager == null) {
                    File mapListFile = new File(MAP_PATH);
                    if (!mapListFile.exists()) {
                        clientManager = new ClientManager();
                        clientManager.initClientManager();
                    } else {
                        clientManager = new ClientManager();
                        clientManager.loadClientManager();
                    }
                }
            }
        }
        return clientManager;
    }


    /**
     * Initialize client manager with client and pending lists
     */
    private void initClientManager() {
        clientManager = new ClientManager();
        clientManager.masterList = new HashMap<>();
        clientManager.masterList.put("user", new ArrayList<>());
        clientManager.masterList.put("manager", new ArrayList<>());
        clientManager.masterList.put("teller", new ArrayList<>());
        clientManager.masterList.put("pendUser", new ArrayList<>());
        clientManager.masterList.put("pendTeller", new ArrayList<>());
        clientManager.accountRequestList = new ArrayList<>();



        // HARDCODED USERS FOR TESTING PURPOSES
        User user = new User("chris", "Chris123");
        BankManager manager = new BankManager("sally", "Meow123");
        BankTeller teller = new BankTeller("winnie", "Boba123");
        BankTeller teller2 = new BankTeller("sam", "Sam123");

        ChequingAccount chequingAccount = new ChequingAccount(user);
        CreditCard creditCard = new CreditCard(user);
        LineOfCredit line = new LineOfCredit(user);
        SavingsAccount savingsAccount = new SavingsAccount(user);

        clientManager.addToMasterList("user", user);
        clientManager.addToMasterList("manager", manager);
        clientManager.addToMasterList("teller", teller);
        clientManager.addToMasterList("teller", teller2);
    }

    /**
     * Load the client manager class and map containing client and pending lists
     */
    public void loadClientManager() {
        try {
            FileInputStream fi = new FileInputStream(MAP_PATH);
            ObjectInputStream oi = new ObjectInputStream(fi);
            clientManager = (ClientManager) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream for load!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the file path of the hash map
     *
     * @return the location of the map
     */
    public String getPath() {
        return MAP_PATH;
    }

    /**
     * Gets the list of requesting accounts
     *
     * @return the list of requests
     */
    public ArrayList<UserRequest> getAccountRequestList() {
        return accountRequestList;
    }

    /**
     * Add a request to the request queue
     *
     * @param request the user request
     */
    public void addToAccountRequestList(UserRequest request) {
        accountRequestList.add(request);
    }

    /**
     * Clear the pending teller list
     */
    public void clearPendTellerList() {
        masterList.get("pendTeller").clear();
    }

    /**
     * clear th pending user list
     */
    public void clearPendUserList() {
        masterList.get("pendUser").clear();
    }

    /**
     * Clear account request list
     */
    public void clearAccountRequestList() {
        accountRequestList.clear();
    }

    /**
     * Remove the list pointed to by listtype
     *
     * @param listtype the list to remove
     */
    public void clearList(String listtype) {
        masterList.replace(listtype, new ArrayList<>());
    }

    /*
     * To add to the master list according to client/list type
     */
    public void addToMasterList(String clientType, BankClient client) {
        ArrayList<BankClient> temp = masterList.get(clientType);
        temp.add(client);
    }

    /**
     * To remove an object from a client list
     *
     * @param clientType the client's list from which to remove
     * @param client     the specific client to remove from the list
     */
    public void removeFromMasterList(String clientType, BankClient client) {
        ArrayList<BankClient> cand = masterList.get(clientType);
        int idx = -1;
        for (BankClient e : cand) {
            idx++;
            if (e.equals(client)) {
                break;
            }
        }
        cand.remove(idx);
        masterList.replace(clientType, cand);
    }

    /**
     * Check by client array list to see if username is containing within
     *
     * @param clients  an array list of bank clients
     * @param userName the target username
     * @return
     */
    private boolean checkClientName(ArrayList<BankClient> clients, String userName) {
        for (BankClient client : clients) {
            if (client.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given client already is registered in the system
     *
     * @return a flag for if the client exists
     */
    public boolean isExistingClient(String client) {
        boolean exists = false;
        for (ArrayList<BankClient> clientList : masterList.values()) {
            if (checkClientName(clientList, client)) {
                exists = true;
            }
        }
        return exists;
    }

    /*
     * Gives a string representation of the client manager master list
     */
    @Override
    public String toString() {
        String ret = "";
        Iterator it = masterList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ret = ret + pair.getKey() + ": " + pair.getValue() + "\n";
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return ret;
    }

    /**
     * Getters for the specific client type's list
     *
     * @param clientType the client's list we want
     * @return clientType's list
     */
    public ArrayList<BankClient> getClientList(String clientType) {
        return masterList.get(clientType);
    }

    public ArrayList<BankClient> getUserList() {
        return masterList.get("user");
    }

    public ArrayList<BankClient> getManagerList() {
        return masterList.get("manager");
    }

    public ArrayList<BankClient> getTellerList() {
        return masterList.get("teller");
    }

    public ArrayList<BankClient> getPendUserList() {
        return masterList.get("pendUser");
    }

    public ArrayList<BankClient> getPendTellerList() {
        return masterList.get("pendTeller");
    }


    /**
     * takes the list in the parameter and write as object in binary type file
     *
     * @param filePath path of file to be saved
     */
    public void saveMap(String filePath) {
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(this);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream for save");
        }
    }
}
