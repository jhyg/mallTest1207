package mall.api;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import mall.aggregate.*;
import mall.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHistoryController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public PaymentHistoryController(
        CommandGateway commandGateway,
        QueryGateway queryGateway
    ) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @RequestMapping(value = "/paymentHistories", method = RequestMethod.POST)
    public CompletableFuture pay(@RequestBody PayCommand payCommand)
        throws Exception {
        System.out.println("##### /paymentHistory/pay  called #####");

        // send command
        return commandGateway
            .send(payCommand)
            .thenApply(id -> {
                PaymentHistoryAggregate resource = new PaymentHistoryAggregate();
                BeanUtils.copyProperties(payCommand, resource);

                resource.setId((Long) id);

                EntityModel<PaymentHistoryAggregate> model = EntityModel.of(
                    resource
                );
                model.add(
                    Link
                        .of("/paymentHistories/" + resource.getId())
                        .withSelfRel()
                );

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @Autowired
    EventStore eventStore;

    @GetMapping(value = "/paymentHistories/{id}/events")
    public ResponseEntity getEvents(@PathVariable("id") String id) {
        ArrayList resources = new ArrayList<PaymentHistoryAggregate>();
        eventStore.readEvents(id).asStream().forEach(resources::add);

        CollectionModel<PaymentHistoryAggregate> model = CollectionModel.of(
            resources
        );

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
