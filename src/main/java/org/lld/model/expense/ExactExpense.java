package org.lld.model.expense;

import org.lld.model.User;
import org.lld.model.split.ExactSplit;
import org.lld.model.split.Split;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy,  List<Split> splits) {
        super( amount, paidBy, splits);
    }

    @Override
    public boolean validate() {

        double totalAmount = 0.0;

        for (Split split: getSplits()){
            if(!(split instanceof ExactSplit exactSplit))
                return false;
            totalAmount += exactSplit.getAmount();
        }
       return totalAmount == getAmount();
    }
}
