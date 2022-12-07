package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.FoodDeliveryAppApplication;
import mall.domain.OrderCancelled;
import mall.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long menuId;

    private Integer quantity;

    private Long storeId;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        mall.external.PaymentHistory paymentHistory = new mall.external.PaymentHistory();
        // mappings goes here
        FoodDeliveryAppApplication.applicationContext
            .getBean(mall.external.PaymentHistoryService.class)
            .pay(paymentHistory);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FoodDeliveryAppApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void orderStatusChanged(DeliveryStarted deliveryStarted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryStarted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
