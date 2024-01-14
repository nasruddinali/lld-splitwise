package org.lld;

import org.lld.model.User;
import org.lld.model.expense.Expense;
import org.lld.model.expense.SplitType;
import org.lld.model.split.Split;
import org.lld.service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager(){
        this.expenses = new ArrayList<>();
        this.userMap = new HashMap<>();
        this.balanceSheet = new HashMap<>();
    }
    public void addUser(User user){
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId() , new HashMap<String,Double>());
    }
    public void addExpense(SplitType splitType, double amount, String paidBy, List<Split> splits) {
        Expense expense = ExpenseService.createExpense(splitType,amount,userMap.get(paidBy), splits);
        expenses.add(expense);

        for( Split split : splits){
            String paidTo = split.getUser().getId();
            Map<String,Double> balances = balanceSheet.get(paidBy);

            if(!balances.containsKey(paidTo)){
                balances.put(paidTo,0.0);
            }
            balances.put(paidTo,balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);

            if(!balances.containsKey(paidBy)){
                balances.put(paidBy,0.0);
            }
            balances.put(paidBy,balances.get(paidBy) - split.getAmount());

        }
    }


    public void showBalance(String userId) {
        boolean isEmpty = true;
        for(Map.Entry<String,Double> userBalance : balanceSheet.get(userId).entrySet()){
            if(userBalance.getValue() != 0){
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }
        if(isEmpty){
            System.out.println("no balance");
        }
    }

    public void showBalance(){
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, Double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

}
