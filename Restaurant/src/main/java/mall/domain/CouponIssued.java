package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class CouponIssued extends AbstractEvent {

    private Long id;
    private String item;
    private String deliveryAddress;

    public CouponIssued(OrderManagement aggregate) {
        super(aggregate);
    }

    public CouponIssued() {
        super();
    }
}
