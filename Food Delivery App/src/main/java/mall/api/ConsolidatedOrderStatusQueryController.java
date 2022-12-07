package mall.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import mall.query.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsolidatedOrderStatusQueryController {

    private final QueryGateway queryGateway;

    public ConsolidatedOrderStatusQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/consolidatedOrderStatuses")
    public CompletableFuture findAll(ConsolidatedOrderStatusQuery query) {
        return queryGateway
            .query(
                query,
                ResponseTypes.multipleInstancesOf(ConsolidatedOrderStatus.class)
            )
            .thenApply(resources -> {
                List modelList = new ArrayList<EntityModel<ConsolidatedOrderStatus>>();

                resources
                    .stream()
                    .forEach(resource -> {
                        modelList.add(hateoas(resource));
                    });

                CollectionModel<ConsolidatedOrderStatus> model = CollectionModel.of(
                    modelList
                );

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @GetMapping("/consolidatedOrderStatuses/{id}")
    public CompletableFuture findById(@PathVariable("id") Long id) {
        ConsolidatedOrderStatusSingleQuery query = new ConsolidatedOrderStatusSingleQuery();
        query.setId(id);

        return queryGateway
            .query(
                query,
                ResponseTypes.optionalInstanceOf(ConsolidatedOrderStatus.class)
            )
            .thenApply(resource -> {
                if (!resource.isPresent()) {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(
                    hateoas(resource.get()),
                    HttpStatus.OK
                );
            })
            .exceptionally(ex -> {
                throw new RuntimeException(ex);
            });
    }

    EntityModel<ConsolidatedOrderStatus> hateoas(
        ConsolidatedOrderStatus resource
    ) {
        EntityModel<ConsolidatedOrderStatus> model = EntityModel.of(resource);

        model.add(
            Link
                .of("/consolidatedOrderStatuses/" + resource.getId())
                .withSelfRel()
        );

        return model;
    }
}
