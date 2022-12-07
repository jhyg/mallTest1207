package mall.query;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

@Entity
@Table(name = "ConsolidatedOrderStatus_table")
@Data
@Relation(collectionRelation = "consolidatedOrderStatuses")
public class ConsolidatedOrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
