package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ConsolidatedOrderStatus_table")
@Data
public class ConsolidatedOrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
