package org.lld.model.expense;

import lombok.Getter;
import lombok.Setter;
import org.lld.model.User;
import org.lld.model.split.Split;

import java.util.List;


@Setter
@Getter
public abstract class Expense {
    private User paidBy;
    private double amount;
    List<Split> splits;

    public Expense( double amount, User paidBy, List<Split> splits) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }
    public abstract boolean validate();
}
