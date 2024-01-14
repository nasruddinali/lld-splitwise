package org.lld.model.expense;

import org.lld.model.User;
import org.lld.model.split.PercentageSplit;
import org.lld.model.split.Split;

import java.util.List;

public class PercentageExpense extends Expense {
    public PercentageExpense( double amount,User paidBy, List<Split> splits) {
        super(  amount,paidBy, splits);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof PercentageSplit)) return false;
        }


        double totalPercentage = 0.0;

        for (Split split : getSplits()) {
            PercentageSplit percentageSplit = (PercentageSplit) split;
            totalPercentage += percentageSplit.getPercentage();
        }
        return totalPercentage == 100.0;
    }
}
