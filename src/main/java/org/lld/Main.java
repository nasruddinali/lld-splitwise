package org.lld;

import org.lld.model.User;
import org.lld.model.expense.SplitType;
import org.lld.model.split.EqualSplit;
import org.lld.model.split.ExactSplit;
import org.lld.model.split.PercentageSplit;
import org.lld.model.split.Split;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addUser(User.builder().id("u1").email("12132@").mobileNumber("2324242").name("user1").build());
        expenseManager.addUser(User.builder().id("u2").email("12132@").mobileNumber("2324242").name("user2").build());
        expenseManager.addUser(User.builder().id("u3").email("12132@").mobileNumber("2324242").name("user3").build());
        expenseManager.addUser(User.builder().id("u4").email("12132@").mobileNumber("2324242").name("user4").build());

//        Scanner scanner =  new Scanner(System.in);
//        String command = scanner.nextLine();
//        String[] commands = command.split(" ");
//        System.out.println(commands.toString());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0].toLowerCase();

            commandType = commandType.toLowerCase();
            switch (commandType) {

                case "show" -> {
                    if (commands.length == 1) {
                        expenseManager.showBalance();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                }
                case "expense" -> {
                    String paidBy = commands[1];
                    double amount = Double.parseDouble(commands[2]);
                    int numberOfUsers = Integer.parseInt(commands[3]);
                    String splitType = commands[4 + numberOfUsers].toUpperCase();
                    System.out.println("paid by : " + paidBy);
                    System.out.println("amount: " + amount);
                    System.out.println("number of users: " + numberOfUsers);


                    List<Split> splits = new ArrayList<>();

                    switch (splitType) {

                        case "EQUAL" -> {
                            System.out.println("splitType : " + splitType);

                            for (int i = 0; i < numberOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
                            }
                            expenseManager.addExpense(SplitType.EQUAL, amount, paidBy, splits);
                            break;
                        }
                        case "EXACT" -> {
                            for (int i = 0; i < numberOfUsers; i++) {
                                splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + numberOfUsers + i])));
                            }
                            expenseManager.addExpense(SplitType.EXACT, amount, paidBy, splits);
                        }
                        case "PERCENTAGE" -> {
                            for (int i = 0; i < numberOfUsers; i++) {
                                splits.add(new PercentageSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + numberOfUsers + i])));
                            }
                            expenseManager.addExpense(SplitType.PERCENT, amount, paidBy, splits);
                        }
                    }
                }
            }
        }


    }
}