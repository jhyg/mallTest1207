package mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderPlacedEvent {

    private Long id;
    private Long orderId;
    private Long menuId;
    private Integer quantity;
    private Long storeId;
}
