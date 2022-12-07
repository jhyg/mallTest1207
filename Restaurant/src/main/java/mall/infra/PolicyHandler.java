package mall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import mall.config.kafka.KafkaProcessor;
import mall.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderManagementRepository orderManagementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_OrderReceived(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener OrderReceived : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        OrderManagement.orderReceived(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentAuthorized'"
    )
    public void wheneverPaymentAuthorized_OrderReceived(
        @Payload PaymentAuthorized paymentAuthorized
    ) {
        PaymentAuthorized event = paymentAuthorized;
        System.out.println(
            "\n\n##### listener OrderReceived : " + paymentAuthorized + "\n\n"
        );

        // Sample Logic //
        OrderManagement.orderReceived(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCancelled'"
    )
    public void wheneverPaymentCancelled_OrderCancelProcessing(
        @Payload PaymentCancelled paymentCancelled
    ) {
        PaymentCancelled event = paymentCancelled;
        System.out.println(
            "\n\n##### listener OrderCancelProcessing : " +
            paymentCancelled +
            "\n\n"
        );

        // Sample Logic //
        OrderManagement.orderCancelProcessing(event);
    }
}
