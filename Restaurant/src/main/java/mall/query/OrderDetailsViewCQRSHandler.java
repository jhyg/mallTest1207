package mall.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mall.event.*;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("orderDetailsView")
public class OrderDetailsViewCQRSHandler {

    @Autowired
    private OrderDetailsViewRepository orderDetailsViewRepository;

    @QueryHandler
    public List<OrderDetailsView> handle(OrderDetailsViewQuery query) {
        return orderDetailsViewRepository.findAll();
    }
}
