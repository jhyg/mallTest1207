package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class PaymentCancelled extends AbstractEvent {

    private Long id;

    public PaymentCancelled(PaymentHistory aggregate) {
        super(aggregate);
    }

    public PaymentCancelled() {
        super();
    }
}
