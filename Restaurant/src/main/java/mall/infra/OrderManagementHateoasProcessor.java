package mall.infra;

import mall.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderManagementHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<OrderManagement>> {

    @Override
    public EntityModel<OrderManagement> process(
        EntityModel<OrderManagement> model
    ) {
        return model;
    }
}
