package mall.query;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

@Entity
@Table(name = "OrderDetailsView_table")
@Data
@Relation(collectionRelation = "orderDetailsViews")
public class OrderDetailsView {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
