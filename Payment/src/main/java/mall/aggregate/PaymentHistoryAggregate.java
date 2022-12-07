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
public class PaymentHistoryAggregate {

    @AggregateIdentifier
    private Long id;

    private Long orderId;
    private Double amount;

    public PaymentHistoryAggregate() {}

    @CommandHandler
    public PaymentHistoryAggregate(PayCommand command) {}

    @EventSourcingHandler
    public void on(PaymentAuthorizedEvent event) {
        BeanUtils.copyProperties(event, this);
    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent event) {
        BeanUtils.copyProperties(event, this);
    }

    @EventSourcingHandler
    public void on(PaymentMethodChangedEvent event) {
        BeanUtils.copyProperties(event, this);
    }
}
