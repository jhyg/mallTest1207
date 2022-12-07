package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.RestaurantApplication;
import mall.domain.CouponIssued;
import mall.domain.DeliveryStarted;

@Entity
@Table(name = "OrderManagement_table")
@Data
public class OrderManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long menuId;

    private Integer orderStatus;

    @Embedded
    private Address deliveryAddress;

    private Long riderId;

    private Integer deliveryStatus;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

        CouponIssued couponIssued = new CouponIssued(this);
        couponIssued.publishAfterCommit();
    }

    public static OrderManagementRepository repository() {
        OrderManagementRepository orderManagementRepository = RestaurantApplication.applicationContext.getBean(
            OrderManagementRepository.class
        );
        return orderManagementRepository;
    }

    public static void orderReceived(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        OrderManagement orderManagement = new OrderManagement();
        repository().save(orderManagement);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(orderManagement->{
            
            orderManagement // do something
            repository().save(orderManagement);


         });
        */

    }

    public static void orderReceived(PaymentAuthorized paymentAuthorized) {
        /** Example 1:  new item 
        OrderManagement orderManagement = new OrderManagement();
        repository().save(orderManagement);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentAuthorized.get???()).ifPresent(orderManagement->{
            
            orderManagement // do something
            repository().save(orderManagement);


         });
        */

    }

    public static void orderCancelProcessing(
        PaymentCancelled paymentCancelled
    ) {
        /** Example 1:  new item 
        OrderManagement orderManagement = new OrderManagement();
        repository().save(orderManagement);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCancelled.get???()).ifPresent(orderManagement->{
            
            orderManagement // do something
            repository().save(orderManagement);


         });
        */

    }
}
