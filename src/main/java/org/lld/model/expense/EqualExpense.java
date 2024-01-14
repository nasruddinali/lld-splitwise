package org.lld.model.expense;

import org.lld.model.User;
import org.lld.model.split.EqualSplit;
import org.lld.model.split.Split;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(  double amount,User paidBy ,  List<Split> splits) {
        super( amount,paidBy ,  splits);
    }

    @Override
    public boolean validate() {

        for(Split split : getSplits()){
            if(!(split instanceof EqualSplit))
                return false;
        }
        return true;
    }
}
