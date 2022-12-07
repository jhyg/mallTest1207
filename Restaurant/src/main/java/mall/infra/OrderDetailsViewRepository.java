package mall.infra;

import java.util.List;
import mall.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "orderDetailsViews",
    path = "orderDetailsViews"
)
public interface OrderDetailsViewRepository
    extends PagingAndSortingRepository<OrderDetailsView, Long> {}
