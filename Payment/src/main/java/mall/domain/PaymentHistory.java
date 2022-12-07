package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.PaymentApplication;
import mall.domain.PaymentAuthorized;
import mall.domain.PaymentCancelled;
import mall.domain.PaymentMethodChanged;

@Entity
@Table(name = "PaymentHistory_table")
@Data
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Double amount;

    @PostPersist
    public void onPostPersist() {
        PaymentAuthorized paymentAuthorized = new PaymentAuthorized(this);
        paymentAuthorized.publishAfterCommit();

        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        mall.external.PaymentHistory paymentHistory = new mall.external.PaymentHistory();
        // mappings goes here
        PaymentApplication.applicationContext
            .getBean(mall.external.PaymentHistoryService.class)
            .pay(paymentHistory);

        PaymentMethodChanged paymentMethodChanged = new PaymentMethodChanged(
            this
        );
        paymentMethodChanged.publishAfterCommit();
    }

    public static PaymentHistoryRepository repository() {
        PaymentHistoryRepository paymentHistoryRepository = PaymentApplication.applicationContext.getBean(
            PaymentHistoryRepository.class
        );
        return paymentHistoryRepository;
    }

    public static void orderCancelled(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        PaymentHistory paymentHistory = new PaymentHistory();
        repository().save(paymentHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(paymentHistory->{
            
            paymentHistory // do something
            repository().save(paymentHistory);


         });
        */

    }
}
