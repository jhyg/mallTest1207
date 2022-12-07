package mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentMethodChangedEvent {

    private Long id;
    private Long orderid;
    private Double amount;
}
