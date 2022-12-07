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
public class OrderManagementController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderManagementController(
        CommandGateway commandGateway,
        QueryGateway queryGateway
    ) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @RequestMapping(value = "/orderManagements", method = RequestMethod.POST)
    public CompletableFuture orderAccepted(
        @RequestBody OrderAcceptedCommand orderAcceptedCommand
    ) throws Exception {
        System.out.println(
            "##### /orderManagement/orderAccepted  called #####"
        );

        // send command
        return commandGateway
            .send(orderAcceptedCommand)
            .thenApply(id -> {
                OrderManagementAggregate resource = new OrderManagementAggregate();
                BeanUtils.copyProperties(orderAcceptedCommand, resource);

                resource.setId((Long) id);

                EntityModel<OrderManagementAggregate> model = EntityModel.of(
                    resource
                );
                model.add(
                    Link
                        .of("/orderManagements/" + resource.getId())
                        .withSelfRel()
                );

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @Autowired
    EventStore eventStore;

    @GetMapping(value = "/orderManagements/{id}/events")
    public ResponseEntity getEvents(@PathVariable("id") String id) {
        ArrayList resources = new ArrayList<OrderManagementAggregate>();
        eventStore.readEvents(id).asStream().forEach(resources::add);

        CollectionModel<OrderManagementAggregate> model = CollectionModel.of(
            resources
        );

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
