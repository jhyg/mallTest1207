package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private String item;
    private String deliveryAddress;

    public DeliveryStarted(OrderManagement aggregate) {
        super(aggregate);
    }

    public DeliveryStarted() {
        super();
    }
}
