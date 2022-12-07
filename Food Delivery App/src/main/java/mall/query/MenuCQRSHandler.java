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
@ProcessingGroup("menu")
public class MenuCQRSHandler {

    @Autowired
    private MenuRepository menuRepository;

    @QueryHandler
    public List<Menu> handle(MenuQuery query) {
        return menuRepository.findAll();
    }
}
