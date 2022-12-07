package mall.external;

import java.util.Date;
import lombok.Data;

@Data
public class PaymentHistory {

    private Long id;
    private Long orderId;
    private Double amount;
}
