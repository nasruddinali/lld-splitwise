package org.lld.model.split;

import lombok.*;
import org.lld.model.User;

@Getter
@Setter
public abstract class Split {
    private User user;
    private double amount;
    public Split(User user){
        this.user = user;
    }

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}
