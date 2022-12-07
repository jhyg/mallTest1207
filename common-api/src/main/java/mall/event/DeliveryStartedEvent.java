package mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeliveryStartedEvent {

    private Long id;
    private String item;
    private String deliveryAddress;
}
