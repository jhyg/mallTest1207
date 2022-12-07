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
public class OrderDetailsViewQueryController {

    private final QueryGateway queryGateway;

    public OrderDetailsViewQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/orderDetailsViews")
    public CompletableFuture findAll(OrderDetailsViewQuery query) {
        return queryGateway
            .query(
                query,
                ResponseTypes.multipleInstancesOf(OrderDetailsView.class)
            )
            .thenApply(resources -> {
                List modelList = new ArrayList<EntityModel<OrderDetailsView>>();

                resources
                    .stream()
                    .forEach(resource -> {
                        modelList.add(hateoas(resource));
                    });

                CollectionModel<OrderDetailsView> model = CollectionModel.of(
                    modelList
                );

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @GetMapping("/orderDetailsViews/{id}")
    public CompletableFuture findById(@PathVariable("id") Long id) {
        OrderDetailsViewSingleQuery query = new OrderDetailsViewSingleQuery();
        query.setId(id);

        return queryGateway
            .query(
                query,
                ResponseTypes.optionalInstanceOf(OrderDetailsView.class)
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

    EntityModel<OrderDetailsView> hateoas(OrderDetailsView resource) {
        EntityModel<OrderDetailsView> model = EntityModel.of(resource);

        model.add(
            Link.of("/orderDetailsViews/" + resource.getId()).withSelfRel()
        );

        return model;
    }
}
