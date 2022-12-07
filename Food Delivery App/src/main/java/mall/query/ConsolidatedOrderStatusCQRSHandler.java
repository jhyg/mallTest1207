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
@ProcessingGroup("consolidatedOrderStatus")
public class ConsolidatedOrderStatusCQRSHandler {

    @Autowired
    private ConsolidatedOrderStatusRepository consolidatedOrderStatusRepository;

    @QueryHandler
    public List<ConsolidatedOrderStatus> handle(
        ConsolidatedOrderStatusQuery query
    ) {
        return consolidatedOrderStatusRepository.findAll();
    }
}
