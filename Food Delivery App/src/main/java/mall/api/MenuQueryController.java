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
public class MenuQueryController {

    private final QueryGateway queryGateway;

    public MenuQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/menus")
    public CompletableFuture findAll(MenuQuery query) {
        return queryGateway
            .query(query, ResponseTypes.multipleInstancesOf(Menu.class))
            .thenApply(resources -> {
                List modelList = new ArrayList<EntityModel<Menu>>();

                resources
                    .stream()
                    .forEach(resource -> {
                        modelList.add(hateoas(resource));
                    });

                CollectionModel<Menu> model = CollectionModel.of(modelList);

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @GetMapping("/menus/{id}")
    public CompletableFuture findById(@PathVariable("id") Long id) {
        MenuSingleQuery query = new MenuSingleQuery();
        query.setId(id);

        return queryGateway
            .query(query, ResponseTypes.optionalInstanceOf(Menu.class))
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

    EntityModel<Menu> hateoas(Menu resource) {
        EntityModel<Menu> model = EntityModel.of(resource);

        model.add(Link.of("/menus/" + resource.getId()).withSelfRel());

        return model;
    }
}
