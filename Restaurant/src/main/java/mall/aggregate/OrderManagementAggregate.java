package mall.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import mall.command.*;
import mall.event.*;
import mall.query.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
@ToString
public class OrderManagementAggregate {

    @AggregateIdentifier
    private Long id;

    private Long orderId;
    private Long menuId;
    private Integer orderStatus;
    private Address deliveryAddress;
    private Long riderId;
    private Integer deliveryStatus;

    public OrderManagementAggregate() {}

    @CommandHandler
    public OrderManagementAggregate(OrderAcceptedCommand command) {}

    @EventSourcingHandler
    public void on(DeliveryStartedEvent event) {
        BeanUtils.copyProperties(event, this);
    }

    @EventSourcingHandler
    public void on(CouponIssuedEvent event) {
        BeanUtils.copyProperties(event, this);
    }
}
