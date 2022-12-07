package mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCancelledEvent {

    private Long id;
    private Long orderId;
}
