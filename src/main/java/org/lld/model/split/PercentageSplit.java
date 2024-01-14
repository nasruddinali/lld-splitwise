package org.lld.model.split;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lld.model.User;
import org.lld.model.split.Split;


@Getter
@Setter
public class PercentageSplit extends Split {
    private double percentage;
    public PercentageSplit(User user, double percentage) {
        super(user);
        this.percentage = percentage;
    }
}
