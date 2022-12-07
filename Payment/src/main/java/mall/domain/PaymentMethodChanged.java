package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class PaymentMethodChanged extends AbstractEvent {

    private Long id;
    private Long orderid;
    private Double amount;

    public PaymentMethodChanged(PaymentHistory aggregate) {
        super(aggregate);
    }

    public PaymentMethodChanged() {
        super();
    }
}
