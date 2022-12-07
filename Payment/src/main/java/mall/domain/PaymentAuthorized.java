package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class PaymentAuthorized extends AbstractEvent {

    private Long id;
    private Long orderid;
    private Double amount;

    public PaymentAuthorized(PaymentHistory aggregate) {
        super(aggregate);
    }

    public PaymentAuthorized() {
        super();
    }
}
