package mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentAuthorizedEvent {

    private Long id;
    private Long orderid;
    private Double amount;
}
