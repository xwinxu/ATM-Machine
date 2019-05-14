package main.utilities;

import main.clients.User;
import main.accounts.BankAccount;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for performing validity checks
 */
public class InputChecker {

    private static Scanner keyboard = new Scanner(System.in);

    public InputChecker() {
    }

    /**
     * Helper function to print prompt message and store inputted value
     *
     * @param message prompt message asking for info input
     * @return a String containing user input
     */
    public String prompt(String message) {
        System.out.print(message + ": ");
        return keyboard.nextLine();
    }

    /**
    Checks if a password string is at least 6 characters, at least 1 Uppercase letter, and 1 number
     */
    public boolean validPassword(String pass) {
        if(pass.length() < 6) {
            return false;
        } else {
            char c;
            int numCount = 0;
            int charCount = 0;
            for(int i = 0; i < pass.length(); i++) {
                c = pass.charAt(i);
                if(!Character.isLetterOrDigit(c)) return false;
                else if(Character.isDigit(c)) {
                    numCount++;
                } else if(Character.isUpperCase(c)) {
                    charCount++;
                }
            }
            return (numCount >= 1 && charCount >=1);
        }
    }

    /**
     * Takes in a float input from the user
     *
     * @return float returns the float the user wishes to enter
     */
    public float inputFloat() {
        String numeric = "^(\\d+\\.)?\\d+$";
        String amount = keyboard.nextLine();
        while (!amount.matches(numeric)) {
            System.out.println("Invalid input. Please enter a numeric value in the format 0.00");
            amount = keyboard.nextLine();
        }
        return Float.valueOf(amount);
    }

    /**
     * Takes in a int input from the user
     *
     * @return int returns the int the user wishes to enter
     */
    public int inputInt() {
        String integerRegex = "^[0-9]+$";
        String input = keyboard.nextLine();
        while (!input.matches(integerRegex)) {
            System.out.println("Invalid input. Please enter an integer value.");
            input = keyboard.nextLine();
        }
        return Integer.valueOf(input);
    }

    /**
     * A checker for if an input is a cashable amount
     *
     * @return a whole number sum
     */
    public int inputCashable() {
        String integerRegex = "^(\\d+\\.)?\\d+$";
        String input = keyboard.nextLine();
        while (!input.matches(integerRegex) || Double.valueOf(input) % 5 != 0) {
            System.out.println("Invalid input. Please enter an integer amount that is cashable by bills.");
            input = keyboard.nextLine();
        }
        float doubleInput = Float.valueOf(input);
        return (Math.round(doubleInput));
    }

    /**
     * Check if user is logging in or person is attempting to create a new user.
     *
     * @param userInput the user's input
     */
    boolean checkNewUser(String userInput) {
        if (userInput.equals("1")) {
            return false;
        } else if (userInput.equals("2")) {
            return true;
        } else {
            System.out.println("input invalid. Please try again.");
            String newUserInput = keyboard.nextLine();
            return checkNewUser(newUserInput);
        }
    }

    /**
     * User account selection.
     *
     * @param user the current user
     * @return the selected bank account
     */
    public BankAccount pickAccount(User user) {
        BankAccount bankAccount;
        ArrayList<BankAccount> accounts = user.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("For " + accounts.get(i) + " enter \"" + (i + 1) + "\"");
        }

        int x = inputInt();
        if (accounts.size() < x) {
            System.out.println("Invalid account option. Please enter a valid input.");
            bankAccount = pickAccount(user);
        } else {
            bankAccount = accounts.get(x - 1);
        }
        return bankAccount;
    }
}
