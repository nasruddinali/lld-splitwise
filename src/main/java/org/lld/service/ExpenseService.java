package org.lld.service;

import org.lld.Main;
import org.lld.model.User;
import org.lld.model.expense.*;
import org.lld.model.split.PercentageSplit;
import org.lld.model.split.Split;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(SplitType splitType, double amount, User paidBy, List<Split> splits){
        switch (splitType){
            case EQUAL -> {
                int totalSplits = splits.size();
                double splitAmount =((double)  Math.round(amount*100/totalSplits))/100.0;

                for (Split split: splits){
                    split.setAmount(splitAmount);
                }

                splits.get(0).setAmount(splitAmount + (amount -totalSplits * splitAmount) );

                return new EqualExpense(amount,paidBy,splits);
            }
            case EXACT -> {
                return new ExactExpense(amount,paidBy, splits);
            }

            case PERCENT -> {
                for (Split split : splits) {
                    PercentageSplit percentageSplit = (PercentageSplit) split;
                    double percent = percentageSplit.getPercentage();
                    double splitAmount = Math.round(amount * 100*percent /100  );
                    split.setAmount(splitAmount);
                }

                return new PercentageExpense(amount,paidBy,splits);
            }
        }
        return null;
    }
}
