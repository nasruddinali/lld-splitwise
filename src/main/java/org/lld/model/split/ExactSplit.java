package org.lld.model.split;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lld.model.User;


public class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user, amount);
    }
}
