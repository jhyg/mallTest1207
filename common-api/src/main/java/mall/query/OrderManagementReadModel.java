package mall.query;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

@Entity
@Table(name = "OrderManagement_table")
@Data
@Relation(collectionRelation = "orderManagements")
public class OrderManagementReadModel {

    @Id
    private Long id;

    private Long orderId;

    private Long menuId;

    private Integer orderStatus;

    @Embedded
    private Address deliveryAddress;

    private Long riderId;

    private Integer deliveryStatus;
}
