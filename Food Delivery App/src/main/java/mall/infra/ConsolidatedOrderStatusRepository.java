package mall.infra;

import java.util.List;
import mall.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "consolidatedOrderStatuses",
    path = "consolidatedOrderStatuses"
)
public interface ConsolidatedOrderStatusRepository
    extends PagingAndSortingRepository<ConsolidatedOrderStatus, Long> {}
